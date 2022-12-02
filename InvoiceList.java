import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
public class InvoiceList extends JFrame {


    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    private JButton     salesStats;

    private JPanel      background;
    private JPanel      invoicePanel;
    private JPanel      mainPanel2;
    private JPanel      tablePanel;

    private ArrayList<Invoice> ilist;

    public String selectedInvoiceString;

    private JTextField searchbar;
    private JLabel search_label;
    private TableRowSorter sort;

    private Invoice selectedInvoiceObject;


    public InvoiceList()
    {
        this.selectedInvoiceString=selectedInvoiceString;
        ilist=loadInvoices();
        setTitle("Invoice List");
        

        //Search Bar
        searchbar = new JTextField(15);
        search_label = new JLabel("Search");

        String[] columnNames= {"Order Number","Date","Customer ID","Total Cost"};
        model=new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        sort= new TableRowSorter<>(model);
        showTable(ilist);
        table.setRowSorter(sort);
        table.setBounds(20,20,450,450);
        scrollPane=new JScrollPane();

        tablePanel= new JPanel();
        tablePanel.setBackground(Color.GRAY);
        tablePanel.add(search_label);
        tablePanel.add(searchbar);
        tablePanel.add(scrollPane);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            private String selectedInvoiceString;

            @Override
            public void valueChanged(ListSelectionEvent e) {
                // TODO Auto-generated method stub
                this.selectedInvoiceString=selectedRowIntToString(table.getSelectedRow());
                //Verification verify=new Verification;
            }
            
        });
         
        

        //Logic for the search bar
        searchbar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchbar.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchbar.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchbar.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    sort.setRowFilter(null);
                } 
                else{
                    sort.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });

        

        salesStats= new JButton("Overall Sales");
        salesStats.addActionListener(new SalesStatsButtonListener());
        salesStats.setBackground(Color.yellow);
        salesStats.setForeground(Color.black);

        //Add Invoice Pane
        invoicePanel = new JPanel();
        invoicePanel.setBackground(Color.WHITE);

        //Invoice

        //array with string of row selected
        String[] selInvoice=selectedInvoiceString.split("\t");
        JLabel addl = new JLabel("Invoice Details                                                                     ");
        invoicePanel.setBorder( new TitledBorder("Invoice"));
        addl.setForeground(Color.BLACK);
        //Invoice(int orderNum,String date,int customerIdNum,String fName, String lName,String address,int quantity, String item,int unitPrice, int cost,String courier)
        JLabel customerIdNumL= new JLabel("Customer ID#: "+selInvoice[2]);
        JLabel customerNameL= new JLabel("Customer Name: "+selInvoice[3]+" "+selInvoice[4]);
        JLabel addressL= new JLabel("Customer Address: "+selInvoice[5]);
        JLabel courierL= new JLabel("Courier: "+selInvoice[10]);
        JLabel orderNumL= new JLabel("Order Number: "+selInvoice[0]);
        JLabel dateL= new JLabel("Date: "+selInvoice[1]);
        JLabel quantityL= new JLabel("Quantity: "+ selInvoice[6]);
        JLabel itemL= new JLabel("Item: "+selInvoice[7]);
        JLabel unitpriceL= new JLabel("Unit Price: $"+selInvoice[8]);
        JLabel costpriceL= new JLabel("Cost: $"+selInvoice[9]);
        
        
        //add invoice table to show quantity, item,

        invoicePanel.add(addl);
        invoicePanel.add(customerIdNumL);
        invoicePanel.add(customerNameL);
        invoicePanel.add(addressL);
        invoicePanel.add(courierL);
        invoicePanel.add(orderNumL);
        invoicePanel.add(dateL);
        invoicePanel.add(quantityL);
        invoicePanel.add(itemL);
        invoicePanel.add(unitpriceL);
        invoicePanel.add(costpriceL);


        

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 1, 0));
	    mainPanel.setBackground(new Color(22, 22, 21));
        mainPanel.add(table);
        mainPanel.add(invoicePanel);

        //Control Panel2
	    mainPanel2 = new JPanel();
	    mainPanel2.setBackground(Color.DARK_GRAY);
        mainPanel2.add(salesStats);

        //Set Panel Background
	    background = new JPanel(new GridLayout(2, 1, 0, 10));
    	background.setSize(900, 600);
        background.setBackground(new Color(22, 22, 21));
        
        //Add Panels to display
        background.add(mainPanel);
        background.add(mainPanel2);

        add(background);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
    }

    private String selectedRowIntToString(int selectedInt){
        int counter=-1;
        String selectedRowString="";
        if (ilist.size()>0)
        {
            for(Invoice i: ilist)
            {
                counter++;
                if (counter==selectedInt){
                    selectedRowString=i.toString();
                    break;
                }
            
            }
            return selectedRowString;

        }
        else{
            System.out.println("List is Empty");
        }

        return selectedRowString;
    } 

    private void showTable(ArrayList<Invoice> clist)
    {
        if (ilist.size()>0)
        {
            for(Invoice i: ilist)
            {
                addToTable(i);
            }
        }
    }

    private void addToTable(Invoice i)
    {

        String[] item={""+i.getorderNum(),""+i.getcustomerIdNum(),i.getdate(),""+i.getcost()};
        model.addRow(item);
    }

    public void addPerson(Invoice c)
    {
        //not sure where this is used
        ilist.add(c);
        addToTable(c);

    }

    private ArrayList<Invoice> loadInvoices(){
        
        Scanner iscan= null;
        
        ArrayList<Invoice> invoicelist = new ArrayList<Invoice>();
        
        try{
            
            iscan  = new Scanner(new File("/Users/Akele Benjamin/Desktop/SE project/SE project/src/invoiceList.txt"));
            
            while(iscan.hasNext())
            {
                
                String [] nextLine = iscan.nextLine().split("%");
                
                int orderNum = Integer.parseInt(nextLine[0]);
                System.out.println(String.valueOf(orderNum));
                String date= nextLine[1];
                System.out.println(date);
                int customerIdNum = Integer.parseInt(nextLine[2]);
                System.out.println(String.valueOf(customerIdNum));
                String fname= nextLine[3];
                System.out.println(fname);
                String lname = nextLine[4];
                System.out.println(lname);
                String address= nextLine[5];
                System.out.println(address);
                int quantity = Integer.parseInt(nextLine[6]);
                System.out.println(String.valueOf(quantity));
                
                String item= nextLine[7];
                System.out.println(item);

                int unitPrice = Integer.parseInt(nextLine[8]);
                System.out.println(String.valueOf(unitPrice));
                int cost= Integer.parseInt(nextLine[9]);
                System.out.println(String.valueOf(cost));

                String courier= nextLine[10];
                System.out.println(courier);
                

                Invoice customerInvoice= new Invoice(orderNum,date,customerIdNum,fname,lname,address,quantity,item,unitPrice,cost,courier);
                invoicelist.add(customerInvoice);
                
            }
            iscan.close();
        }
        catch(IOException e){}
        return invoicelist;
         

    }

    

    private class SalesStatsButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {


            
            }        
         }

}

