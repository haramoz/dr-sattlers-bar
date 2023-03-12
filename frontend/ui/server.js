const path = require('path')
const express = require('express')
const resolve = file => path.resolve(__dirname, file)

const isProd = process.env.NODE_ENV === 'production'

const app = express()

const serve = (path, cache) => express.static(resolve(path), {
  maxAge: cache && isProd ? 1000 * 60 * 60 * 24 * 30 : 0
})

app.use('/', serve('./dist', true))

const port = process.env.PORT || 3000
app.listen(port, () => {
  console.log(`server started at localhost:${port}`)
})