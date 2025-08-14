FROM golang:1.19

# Set environment variable
ENV REACT_APP_ORIGIN_URL=http://localhost:3001
ENV MY_APP_PORT=8085

WORKDIR /app

# Copy the main.go file to the working directory
COPY . /app

# Download dependencies
COPY go.mod /app
COPY go.sum /app
RUN go mod download

# Build the binary
RUN go build -o main .

EXPOSE 8085

# Start the application
CMD ["/app/main"]
