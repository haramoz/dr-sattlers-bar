const express = require('express')
const kafka = require('kafka-node')

const app = express()

// Set up a Kafka consumer
const client = new kafka.KafkaClient({ kafkaHost: 'localhost:9092' })
const consumer = new kafka.Consumer(
  client,
  [{ topic: 'my-topic' }],
  { autoCommit: true }
)

// Expose an API endpoint that returns Kafka messages
app.get('/messages', (req, res) => {
  const messages = []

  consumer.on('message', message => {
    messages.push(JSON.parse(message.value))
  })

  consumer.on('error', error => {
    console.error(error)
    res.status(500).send('Error fetching messages from Kafka')
  })

  consumer.on('end', () => {
    res.send(messages)
  })
})

app.listen(3000, () => {
  console.log('Server listening on port 3000')
})
