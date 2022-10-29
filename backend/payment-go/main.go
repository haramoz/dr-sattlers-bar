package main

import (
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
)

type bill struct {
	TableID string  `json:"tableid"`
	Amount  float64 `json:"amount"`
}

// payment represents data about a payment method.
type payment struct {
	PaymentMethod string  `json:"method"`
	TableID       string  `json:"tableid"`
	Amount        float64 `json:"amount"`
}

// bills slice to seed record bill data.
var bills = []bill{
	//{TableID: "3", Amount: 39.99},
	//{TableID: "2", Amount: 79.99},
}

// payments slice to seed record payment data.
var paymentsRecieved = []payment{
	//{TableID: "3", Amount: 39.99, PaymentMethod: "Cash"},
}

func main() {
	router := gin.Default()
	router.GET("/bill/:tableid", getBill)
	router.GET("/payments", getPayments)
	router.POST("/payment", postProcessPayment)
	router.POST("/newbill", postPrepareBill)

	router.Run("localhost:8080")
}

// getPayments responds with the list of all payments Recieved as JSON.
func getPayments(c *gin.Context) {
	c.IndentedJSON(http.StatusOK, paymentsRecieved)
}

// postProcessPayment adds an payment from JSON received in the request body.
func postProcessPayment(c *gin.Context) {
	var newPayment payment

	// Call BindJSON to bind the received JSON to
	// newPayment.
	if err := c.BindJSON(&newPayment); err != nil {
		return
	}
	// Delete entry from bills slice
	var billAmount = updateBill(newPayment.TableID, c)
	if billAmount == 0 {
		c.IndentedJSON(http.StatusNotFound, gin.H{"message": "POST failed!"})
		return
	}

	newPayment.Amount = billAmount
	// Add the new payment to the slice.
	paymentsRecieved = append(paymentsRecieved, newPayment)
	c.IndentedJSON(http.StatusCreated, newPayment)
}

// Deletes the bill amount from the bills slice and returns the amount, 0 otherwise
func updateBill(tableId string, c *gin.Context) float64 {
	if len(bills) == 0 {
		return 0
	}

	for i, s := range bills {
		fmt.Println(i, s)
		if s.TableID == tableId {
			bills = RemoveIndex(bills, i)
			return s.Amount
		}
	}

	return 0
}

// Removes the desired index from the Bills slice
func RemoveIndex(s []bill, index int) []bill {
	return append(s[:index], s[index+1:]...)
}

// postPrepareBill adds an payment from JSON received in the request body.
func postPrepareBill(c *gin.Context) {
	var newBill bill

	// Call BindJSON to bind the received JSON to
	// newBill.
	if err := c.BindJSON(&newBill); err != nil {
		return
	}

	// Add the new payment to the slice.
	bills = append(bills, newBill)
	// TODO delete entry from bills struct
	c.IndentedJSON(http.StatusCreated, newBill)
}

// getBill locates the bill amount whose ID value matches the id
// parameter sent by the client, then returns that bill as a response.
func getBill(c *gin.Context) {
	id := c.Param("tableid")

	// Loop through the list of albums, looking for
	// an album whose ID value matches the parameter.
	for _, a := range bills {
		if a.TableID == id {
			c.IndentedJSON(http.StatusOK, a)
			return
		}
	}
	c.IndentedJSON(http.StatusNotFound, gin.H{"message": "bill not found!"})
}
