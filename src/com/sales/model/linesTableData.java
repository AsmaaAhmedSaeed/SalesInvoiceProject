 
package com.sales.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

 
public class linesTableData extends AbstractTableModel {
    private ArrayList<invoiceLine> Lines;
    private String [] columns = {"No." ,"Product","Price" , "Quantity" , "Product Total"};

    public linesTableData(ArrayList<invoiceLine> Lines) {
        this.Lines = Lines;
    }

    public ArrayList<invoiceLine> getLines() {
        return Lines;
    }

    
    @Override
    public int getRowCount() {
    return Lines.size();
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
    invoiceLine invoiceLine = Lines.get(rowIndex);
        
        switch(columnIndex){
            case 0: return invoiceLine.getInvoice().getinvoice_Num() ;
            case 1: return invoiceLine.getProduct();
            case 2: return invoiceLine.getPrice();
            case 3: return invoiceLine.getQuantity();
            case 4: return invoiceLine.getLineTotal();
            default : return "";
        }
    
    }
    
}
