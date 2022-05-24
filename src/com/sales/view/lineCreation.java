 
package com.sales.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class lineCreation extends JDialog {
    private JTextField itemNameField;
    private JTextField itemQuantityField;
    private JTextField itemPriceField;
    private JLabel itemNameLabel;
    private JLabel itemQuantityLabel;
    private JLabel itemPriceLabel;
    private JButton acceptButton2;
    private JButton cancelButton2;
    
public lineCreation(SalesInvoiceFrame frame)   {
   itemNameLabel =new JLabel("Item name ");
    itemNameField =new JTextField(15);
    
    itemQuantityLabel =new JLabel("Item Quantity ");
    itemQuantityField =new JTextField(15);
    
    itemPriceLabel =new JLabel("Item price ");
    itemPriceField =new JTextField(15);
    
    acceptButton2 =new JButton("Accept");
    cancelButton2 =new JButton("Cancel");    
    
    acceptButton2.setActionCommand("CreateLineAccept");
    cancelButton2.setActionCommand("CreateLineCancel");
    acceptButton2.addActionListener(frame.getInterface());
    cancelButton2.addActionListener(frame.getInterface());
    setLayout(new GridLayout(4,2));
    
    add(itemNameLabel);
    add(itemNameField);
    add(itemQuantityLabel);
    add(itemQuantityField);
    add(itemPriceLabel);
    add(itemPriceField);
    add(acceptButton2);
    add(cancelButton2);
    
    pack();
    
   
} 

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemQuantityField() {
        return itemQuantityField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }

    
}
