/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author ADMIN
 */
public class orders {
    String orderID;
    String customerID;
    String productID;
    String orderQuantity;
    String orderDate;
    boolean Status;

    public orders(String orderID, String customerID, String productID, String orderQuantity, String orderDate,
            boolean Status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.Status = Status;
    }

    public String getCustomerName(CustomerList cList) {
        int pos = cList.findID(this.getCustomerID());
        return cList.get(pos).getCustomerName();
    }

    public orders() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return this.getOrderID() + ", "
                + this.getCustomerID() + ", "
                + this.getProductID() + ", "
                + this.getOrderQuantity() + ", "
                + this.getOrderDate() + ", "
                + this.isStatus();
    }
}