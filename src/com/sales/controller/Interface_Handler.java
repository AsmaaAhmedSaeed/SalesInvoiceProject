 
package com.sales.controller;

//import java.awt.List;
import com.sales.model.invoiceHeader;
import com.sales.model.invoiceLine;
import com.sales.model.invoicesTableData;
import com.sales.model.linesTableData;
import com.sales.view.SalesInvoiceFrame;
import com.sales.view.invoiceCreation;
import com.sales.view.lineCreation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
 


public class Interface_Handler implements ActionListener ,ListSelectionListener {
    private SalesInvoiceFrame frame;
    private invoiceCreation InvoiceCreation;
    private lineCreation LineCreation;
    public Interface_Handler(SalesInvoiceFrame aThis) {
     this.frame = aThis ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
       System.out.println("Action : " + actionCommand);
       switch(actionCommand){
           case"Load file":
            loadFile(); 
            break;
                
                   case"Save file":
                       saveFile();
                   break;
                   case"Create New Invoice":
                       createNewInvoice();
                   break;
                   case"Delete Invoice":
                       deleteInvoice();
                   break;
                   case"Create New Item":
                       createNewItem();
                   break;
                   case"Delete Item":
                       deleteItem();
                   break;
                   case"CreateInvoiceAccept":
                       CreateInvoiceAccept();
                    break;
                   case"CreateInvoiceCancel" :
                        CreateInvoiceCancel();
                    break;  
                    case"CreateLineAccept":
                       CreateLineAccept();
                    break;
                   case"CreateLineCancel" :
                        CreateLineCancel();
                    break;   
                   
                   
       }
    }

     @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = frame.getInvoiceTable().getSelectedRow();
        
        if(selectedIndex > -1){
    System.out.println("You selected row : " + selectedIndex );
    invoiceHeader currentInvoice = frame.getInvoices().get(selectedIndex);
    
    frame.getInvoiceNO_label().setText("" + currentInvoice.getinvoice_Num());
    frame.getInvoiceDate_label().setText(currentInvoice.getDate());
    frame.getInvoiceCustomer_label().setText(currentInvoice.getCustomer_name());
    frame.getInvoiceTotal_label().setText("" + currentInvoice.getInvoiceTotal());
    
    linesTableData LinesTableData = new linesTableData(currentInvoice.getData_lines());
    frame.getDataTable().setModel(LinesTableData);
    LinesTableData.fireTableDataChanged();
        }
        
    }
    
    private void loadFile() {
   JFileChooser openFile = new JFileChooser();
   try{
     int optionResult = openFile.showOpenDialog(frame);
       if(optionResult == JFileChooser.APPROVE_OPTION)
       { File headerFile = openFile.getSelectedFile();
          Path headerPath = Paths.get(headerFile.getAbsolutePath());
          List<String> headerLines = Files.readAllLines(headerPath);
           System.out.println("Invoices have been read");
          // 1 ,22-11-2020,Ali
          //2 , 13-10-2021,Saleh
          ArrayList<invoiceHeader> invoicesParameters = new ArrayList<>();
          for(String headerLine : headerLines ){
            String[] headerData = headerLine.split(",");
            
            int invoice_Num = Integer.parseInt(headerData[0]); 
            String Date = headerData[1];
            String Customer_name = headerData[2];
            
          invoiceHeader invoiceHeader = new invoiceHeader(invoice_Num,Date ,Customer_name);
          invoicesParameters.add(invoiceHeader);
           }
          System.out.println("check1");
          
          optionResult = openFile.showOpenDialog(frame);
            if(optionResult == JFileChooser.APPROVE_OPTION)
           { File lineFile = openFile.getSelectedFile();
              Path linePath = Paths.get(lineFile.getAbsolutePath());
              List<String> lineLines = Files.readAllLines(linePath);
               System.out.println("lines have been read");
               
               for(String lineLine : lineLines ){
                 String[] lineData = lineLine.split(",");
            
                    int invoiceNumOfLine = Integer.parseInt(lineData[0]); 
                     String itemName = lineData[1];
                     double price = Double.parseDouble(lineData[2]);
                     int quantity = Integer.parseInt(lineData[3]); 
                     invoiceHeader invo = null;
                      for(invoiceHeader invoiceHeader : invoicesParameters){
                            if (invoiceHeader.getinvoice_Num()== invoiceNumOfLine ){
                                invo = invoiceHeader;
                                  break;
                            }
                        }
                      invoiceLine invoiceLine = new invoiceLine(itemName,quantity ,price , invo);
                       invo.getData_lines().add(invoiceLine);
                }
               System.out.println("check  2");
           } 
            frame.setInvoices(invoicesParameters);
            invoicesTableData InvoicesTableData = new invoicesTableData(invoicesParameters);
           frame.setInvoicesTableData(InvoicesTableData);
           frame.getInvoiceTable().setModel(InvoicesTableData);
           frame.getInvoicesTableData().fireTableDataChanged();
               
       }
   }
    catch(IOException ex)
       {
       ex.printStackTrace();
       }
    }

    private void saveFile() {
        ArrayList<invoiceHeader> Invoices = frame.getInvoices();
        String headersCV = "";
        String linesCV = "";
        for (invoiceHeader invoice : Invoices){
        String invoiceCV = invoice.getAsSeparatedValue();
        headersCV +=invoiceCV ;
        headersCV += "\n" ;
        
           for (invoiceLine InvoiceLine : invoice.getData_lines()){
           String lineCV = InvoiceLine.getAsSeparatedValue();
           linesCV += lineCV ;
           linesCV += "\n" ;
           }
        }
       System.out.println("check3");
       try{
       JFileChooser saveFile = new JFileChooser();
        int optionResult =  saveFile.showSaveDialog(frame);
            if(optionResult == JFileChooser.APPROVE_OPTION)
               { File headerFile = saveFile.getSelectedFile();
                 FileWriter headerWriter = new FileWriter(headerFile); 
                 headerWriter.write(headersCV);
                 headerWriter.flush();
                 headerWriter.close();
                 
               optionResult =  saveFile.showSaveDialog(frame);
               if(optionResult == JFileChooser.APPROVE_OPTION)
                  { File lineFile = saveFile.getSelectedFile();
                    FileWriter lineWriter = new FileWriter(lineFile); 
                 headerWriter.write(linesCV);
                 headerWriter.flush();
                 headerWriter.close();
               }
            }
       } catch(Exception ex){
           }       
    }

    private void createNewInvoice() {
        InvoiceCreation =new invoiceCreation(frame);
        InvoiceCreation.setVisible(true);
        
        }

    private void deleteInvoice() {
        int selectedRow = frame.getInvoiceTable().getSelectedRow();
        if(selectedRow > -1){
           frame.getInvoices().remove(selectedRow);
           frame.getInvoicesTableData().fireTableDataChanged();
          
        }
    }

    private void createNewItem() {
        
        LineCreation =new lineCreation(frame);
        LineCreation.setVisible(true);
     }

    private void deleteItem() {
        int selectedInvoice = frame.getInvoiceTable().getSelectedRow();
         int selectedRow = frame.getDataTable().getSelectedRow();
         
        if(selectedInvoice >-1 && selectedRow > -1){
            
           invoiceHeader InvoiceHeader = frame.getInvoices().get(selectedInvoice);
           InvoiceHeader.getData_lines().remove(selectedRow);
           linesTableData LinesTableData = new linesTableData(InvoiceHeader.getData_lines());
           frame.getDataTable().setModel(LinesTableData);
           LinesTableData.fireTableDataChanged();
           frame.getInvoicesTableData().fireTableDataChanged();
          
        }
    }

    private void CreateInvoiceAccept() {
        String date = InvoiceCreation.getInvoiceDateField().getText();
        String customer = InvoiceCreation.getCustomerNameField().getText();
        int Num = frame.getNextInvoiceNum();
        
        invoiceHeader invoice = new invoiceHeader(Num ,date, customer);
        frame.getInvoices().add(invoice);
        frame.getInvoicesTableData().fireTableDataChanged();
        
        InvoiceCreation.setVisible(false);
        InvoiceCreation.dispose();
        InvoiceCreation= null;
    }

    private void CreateInvoiceCancel() {
        InvoiceCreation.setVisible(false);
        InvoiceCreation.dispose();
        InvoiceCreation= null;
    }

    private void CreateLineAccept() {
        String product = LineCreation.getItemNameField().getText();
        String quantityStr = LineCreation.getItemQuantityField().getText();
        String priceStr = LineCreation.getItemPriceField().getText();
        int quantity = Integer.parseInt(quantityStr);
        double price = Double.parseDouble(priceStr);
        
        int selectedInvoice = frame.getInvoiceTable().getSelectedRow();
        if(selectedInvoice > -1){
           invoiceHeader invoice = frame.getInvoices().get(selectedInvoice);
           invoiceLine line = new invoiceLine(product ,quantity ,price ,invoice);
           invoice.getData_lines().add(line);
           linesTableData LinesTableData = (linesTableData) frame.getDataTable().getModel();
           //LinesTableData.getLines().add(line);
           LinesTableData.fireTableDataChanged();
           frame.getInvoicesTableData().fireTableDataChanged();
           
        }
        LineCreation.setVisible(false);
        LineCreation.dispose();
        LineCreation= null;
    }

    private void CreateLineCancel() {
        LineCreation.setVisible(false);
        LineCreation.dispose();
        LineCreation= null;
    }

   
    
}
