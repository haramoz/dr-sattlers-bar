package main

import (
	"fmt"
	"net/http"
	"os"

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

// @title           Dr sattler Bar Payment API
// @version         1.0
// @description     This is a billing/payment API.
// @termsOfService  http://swagger.io/terms/

// @contact.name   Arka Mallick
// @contact.email  ar.mallick@reply.de

// @license.name  Apache 2.0
// @license.url   http://www.apache.org/licenses/LICENSE-2.0.html

// @host      localhost:8085
func main() {
	router := gin.Default()
	router.GET("/bill/:tableid", getBill)
	router.GET("/payments", getPayments)
	router.POST("/newpayment", postProcessPayment)
	router.POST("/newbill", postPrepareBill)

	port := os.Getenv("MY_APP_PORT")

	if port == "" {
		port = "8085"
	}
	router.Run(":" + port)
}

// getPayments godoc
// @Description getPayments responds with the list of all payments Recieved as JSON.
// @Accept json
// @Produce json
// @Success 200 {array} payment
// @Router /payments [get]
func getPayments(c *gin.Context) {
	c.IndentedJSON(http.StatusOK, paymentsRecieved)
}

// postProcessPayment godoc
// @Description postProcessPayment adds an payment from JSON received in the request body.
// @Accept json
// @Produce json
// @Success 201 object payment
// @Failure 404 string error
// @Router /newpayment [post]
// @Param payment body payment true "payment details"
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

// RemoveIndex godoc
// @Description Removes the desired index from the Bills slice
func RemoveIndex(s []bill, index int) []bill {
	return append(s[:index], s[index+1:]...)
}

// postPrepareBill godoc
// @Description postPrepareBill adds an bill from JSON received in the request body.
// @Accept json
// @Produce json
// @Success 201 string bill
// @Failure 404 string error
// @Param bill body bill true "New bill details"
// @Router /newbill [post]
func postPrepareBill(c *gin.Context) {
	var newBill bill

	// Call BindJSON to bind the received JSON to
	// newBill.
	if err := c.BindJSON(&newBill); err != nil {
		return
	}

	// Add the new bill to the slice.
	bills = append(bills, newBill)
	// TODO delete entry from bills struct
	c.IndentedJSON(http.StatusCreated, newBill)
}

// getBill godoc
// @Description getBill locates the bill amount whose ID value matches the id
// parameter sent by the client, then returns that bill as a response.
// @Accept json
// @Produce json
// @Param tableid query string true "table id"
// @Success 200 {array} bill
// @Router /bill [get]
func getBill(c *gin.Context) {
	id := c.Param("tableid")

	for b, a := range bills {
		fmt.Println(a.TableID)
		fmt.Println(b)

		if a.TableID == id {
			fmt.Println("match")
			c.IndentedJSON(http.StatusOK, a)
			return
		}
	}
	c.IndentedJSON(http.StatusNotFound, gin.H{"message": "bill not found!"})
}
