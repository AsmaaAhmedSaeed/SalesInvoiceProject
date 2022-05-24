 
package com.sales.model;

import java.util.ArrayList;

public class invoiceHeader {
    private int invoice_Num;
    private String invoice_Date;
    private String Customer_name;
    private ArrayList<invoiceLine> Data_lines;

    public invoiceHeader() {
    }

    public invoiceHeader(int invoice_Num, String Date, String Customer_name) {
        this.invoice_Num = invoice_Num;
        this.invoice_Date = Date;
        this.Customer_name = Customer_name;
    }
     
    public double getInvoiceTotal(){
        double total =0.0;
        for(invoiceLine line :getData_lines()){
        total += line.getLineTotal();
        }
    return total;
    }
    
    public ArrayList<invoiceLine> getData_lines() {
        if(Data_lines == null){
        Data_lines = new ArrayList<>();
        }
        return Data_lines;
    }
    

    public String getCustomer_name() {
        return Customer_name;
    }

    public void setCustomer_name(String Customer_name) {
        this.Customer_name = Customer_name;
    }

    public int getinvoice_Num() {
        return invoice_Num;
    }

    public void setinvoice_Num(int invoice_Num) {
        this.invoice_Num = invoice_Num;
    }

    public String getDate() {
        return invoice_Date;
    }

    public void setDate(String Date) {
        this.invoice_Date = Date;
    }

    @Override
    public String toString() {
        return "Invoice_class{" + "invoice_Num=" + invoice_Num + ", Date=" + invoice_Date + ", Customer_name=" + Customer_name + ", Data_lines=" + Data_lines + '}';
    }
    
    public String getAsSeparatedValue(){
     return invoice_Num +"," + invoice_Date +"," +Customer_name ;
    }
    
    
}
