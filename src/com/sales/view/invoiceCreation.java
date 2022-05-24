 
package com.sales.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class invoiceCreation extends JDialog {
    private JTextField customerNameField;
    private JTextField invoiceDateField;
    private JLabel customerNameLabel;
    private JLabel invoiceDateLabel;
    private JButton acceptButton;
    private JButton cancelButton;
    
public invoiceCreation(SalesInvoiceFrame frame)   {
    customerNameLabel =new JLabel("Customer name : ");
    customerNameField =new JTextField(15);
    
    invoiceDateLabel =new JLabel("Invoice Date : ");
    invoiceDateField =new JTextField(15);
    
    acceptButton =new JButton("Accept");
    cancelButton =new JButton("Cancel");    
    
    acceptButton.setActionCommand("CreateInvoiceAccept");
    cancelButton.setActionCommand("CreateInvoiceCancel");
    acceptButton.addActionListener(frame.getInterface());
    cancelButton.addActionListener(frame.getInterface());
    setLayout(new GridLayout(3,2));
    
    add(customerNameLabel);
    add(customerNameField);
    add(invoiceDateLabel);
    add(invoiceDateField);
    add(acceptButton);
    add(cancelButton);
    
    pack();
    
   
} 

    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getInvoiceDateField() {
        return invoiceDateField;
    }

}
