FROM golang:1.19

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
