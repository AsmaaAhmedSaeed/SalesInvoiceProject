 
package com.sales.model;

public class invoiceLine {
    
    //private int invoice_num;
    private String productName;
    private double price;
    private int quantity;
    private invoiceHeader invoice;

    public invoiceLine() {
    }

    public invoiceLine( String product, double price, int quantity ) {
         
        this.productName = product;
        this.price = price;
        this.quantity = quantity;
         
    }

    public invoiceLine(String product, int quantity,double price, invoiceHeader invoice) {
        
        this.productName = product;
        this.quantity = quantity;
        this.price = price;
        this.invoice = invoice;
    }

    public double getLineTotal(){
    return price * quantity ;
    }


    public String getProduct() {
        return productName;
    }

    public void setProduct(String product) {
        this.productName = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "invoiceLine{" + "invoice_num=" + invoice.getinvoice_Num() + ", productName=" + productName + ", price=" + price + ", quantity=" + quantity + '}';
    }

    public String getProductName() {
        return productName;
    }

    public invoiceHeader getInvoice() {
        return invoice;
    }
    
     public String getAsSeparatedValue(){
     return invoice.getinvoice_Num() +"," + productName +"," +price + "," + quantity;
    }
    
}
