 
package com.sales.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

 
public class invoicesTableData extends AbstractTableModel{
    private ArrayList<invoiceHeader> Invoices;
    private String [] columns = {"No.", "Date","Customer" , "Total"};

    public invoicesTableData(ArrayList<invoiceHeader> Invoices) {
        this.Invoices = Invoices;
    }
   
    @Override
    public int getRowCount() {
        return Invoices.size();  
    }

    @Override
    public int getColumnCount() {
       return columns.length;
    }
    @Override
    public String getColumnName(int column) {
       return columns[column];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        invoiceHeader invoiceHeader = Invoices.get(rowIndex);
        
        switch(columnIndex){
            case 0: return invoiceHeader.getinvoice_Num() ;
            case 1: return invoiceHeader.getDate();
            case 2: return invoiceHeader.getCustomer_name();
            case 3: return invoiceHeader.getInvoiceTotal();
            default : return "";
        }
    }
    
}
