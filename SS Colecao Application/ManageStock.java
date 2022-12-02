import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.event.*;

import java.awt.*;
import java.util.List;
import java.awt.Color; 

import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.*;

import java.util.*;
import java.io.File;
import java.io.IOException;

public class ManageStock extends JPanel implements OrderQueue{
    //Instance Variables/Attributes
    private JTextField searchbar;
    private JLabel search_label;
    private DefaultTableModel model;
    private JTable otable;
    private TableRowSorter sort;
    private JScrollPane pane;

    private JFrame frame;
    private JPanel background;
    private JPanel table;
    private JPanel addOrder;

    private JLabel cust_fname;
    private JLabel cust_lnamelbl;
    private JLabel contact;
    private JLabel address;
    private JLabel email;
    private JLabel courierlbl; //Drop Down
    private JLabel item_countlbl;
    private JLabel item_countlbl2;

    private JButton cancel_btn;
    private JButton confirm_btn;

    private JLabel item_typelbl;
    private JLabel item_colorlbl;
    private JLabel item_size;

    private JComboBox courier_menu;
    private JComboBox item_type_menu;
    private JComboBox item_color_menu;
    private JComboBox item_size_menu;

    private JTextField c_fname;
    private JTextField c_lname;
    private JTextField cont;
    private JTextField add;
    private JTextField e_add;
    private JTextField counter;
    private JTextField counter2;

    private JButton submit_btn;
    private JButton reset_btn;

    //bottom Pane
    private JButton back;
    private JButton details;
    private JLabel logo;

    private Order order;
    private ManageStock stock_manager;
    private ArrayList<Order> order_queue = new ArrayList<Order>();
    
    private int ticket_number;

    public ManageStock(JFrame frame){
        //Font and Defaults
        this.ticket_number = make_ticket();
        this.frame = frame;
        Font f = new Font("Montserrat", Font.BOLD, 20);
        frame.setTitle("SS Colecao - Manage Stock");

        //Search Bar
        searchbar = new JTextField(15);
        search_label = new JLabel("Search");

        //Table Setup
        order_queue = load_orders("database/stock.txt");
        String[] columnNames = {"Ticket #", "Customer ID", "Full Name", "Item Amount", "Status"};

        model = new DefaultTableModel(columnNames, 0);
        otable = new JTable(model);
        sort = new TableRowSorter<>(model);
        showTable(order_queue);
        otable.setRowSorter(sort);
        otable.setBounds(20, 30, 450, 450);
        pane = new JScrollPane(otable);
        pane.setViewportView(otable);

        //Set Clickable Rows
        //otable.addMouseListener();

        //Content
        //Order Table Display
        table = new JPanel();
        table.setBackground(Color.GRAY);
        table.add(search_label);
        table.add(searchbar);
        table.add(pane);

        back = new JButton("Back");
        back.addActionListener(new ButtonListener());
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

        //Add Order Pane
        JPanel infoPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JPanel cinfoPanel = new JPanel(new GridLayout(7, 4, 30, 10));
        addOrder = new JPanel(new BorderLayout());

        //Logo 
        ImageIcon logo_img = new ImageIcon("Ologo.png");
        Image img = logo_img.getImage();
		Image temp_img = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logo_img = new ImageIcon(temp_img);
        logo = new JLabel(logo_img);
        logo.setBounds(0, 0, 100, 100);
        addOrder.add(logo);

        //Add Order 
        JLabel addl = new JLabel("NEW STOCK ENTRY                    ");
        addl.setFont(f);
        addOrder.setBorder( new TitledBorder("Add Stockr"));
        addl.setForeground(Color.BLACK);
        addl.setBounds(100, 0, 1000, 100);
        addOrder.add(addl);

        //Customer Information
        item_countlbl2 = new JLabel("Item Count: ");

        //Labels
        item_typelbl = new JLabel("Item Type: ");
        item_colorlbl = new JLabel("Item Color: ");
        item_size = new JLabel("Item Size: ");

        //Stock Type
        String[] stockTypes = {"Bikini_Bottoms", "Bikini_Tops"};
        String[] stockColors = {"Black", "White", "Pattern"};
        String[] stockSizes = {"S", "M", "L"};

        //Combo Boxes
        item_type_menu = new JComboBox<>(stockTypes);
        item_color_menu = new JComboBox<>(stockColors);
        item_size_menu = new JComboBox<>(stockSizes);

        //TextFields
        c_fname = new JTextField(4);
		c_lname = new JTextField(4);
		cont = new JTextField(4);
		add = new JTextField(4);
		e_add = new JTextField(4);
        counter = new JTextField(4);
        counter2 = new JTextField(4);

        //Add to Cinfo Panel
        cinfoPanel.setBorder( new TitledBorder("Customer Details"));
        cinfoPanel.setBounds(20, 80, 400, 250);

        cinfoPanel.add(cust_fname);
        cinfoPanel.add(c_fname);

        cinfoPanel.add(cust_lnamelbl);
        cinfoPanel.add(c_lname);

        cinfoPanel.add(contact);
        cinfoPanel.add(cont);

        cinfoPanel.add(address);
        cinfoPanel.add(add);

        cinfoPanel.add(email);
        cinfoPanel.add(e_add);

        cinfoPanel.add(item_countlbl);
        cinfoPanel.add(counter);

        cinfoPanel.add(courierlbl);
        cinfoPanel.add(courier_menu);

        //Add to Info Panel
        infoPanel.setBorder( new TitledBorder("Item Details"));
        infoPanel.setBounds(20, 350, 400, 200);
        infoPanel.add(item_typelbl);
        infoPanel.add(item_type_menu);

        infoPanel.add(item_colorlbl);
        infoPanel.add(item_color_menu);

        infoPanel.add(item_size);
        infoPanel.add(item_size_menu);

        infoPanel.add(item_countlbl2);
        infoPanel.add(counter2);

        //Button For Order Confirmation and Cancelling
        submit_btn = new JButton("Submit");
        submit_btn.addActionListener(new ButtonListener());

        reset_btn = new JButton("Reset");
        reset_btn.addActionListener(new ButtonListener());

        cancel_btn = new JButton("Cancel");
        cancel_btn.addActionListener(new ButtonListener());

        confirm_btn = new JButton("Confirm Order");
        confirm_btn.addActionListener(new ButtonListener());

        infoPanel.add(submit_btn);
        infoPanel.add(reset_btn);
        infoPanel.add(cancel_btn);
        infoPanel.add(confirm_btn);

        //Add to addOrder Panel
        addOrder.add(addl);
        addOrder.add(cinfoPanel);
        addOrder.add(infoPanel);
        addOrder.add(new JLabel(" "));

        //Loop for as many times as there are items accept input from JCombo fields

        //Control Panel
		JPanel mainPanel = new JPanel(new GridLayout(1, 2, 1, 0));
		mainPanel.setBackground(new Color(22, 22, 21));
        table.add(back);
        mainPanel.add(table);
        mainPanel.add(addOrder);


        //Set Panel Background
		background = new JPanel(new GridLayout(1, 1, 0, 10));
		background.setSize(910, 600);
        background.setBackground(new Color(22, 22, 21));
        
        //Add Panels to display
        background.add(mainPanel);
		frame.add(background);
    }

    //Method to show orders in a list in GUI table display.
    private void showTable(ArrayList<Order> olst){
        //checks if plist is empty
        if (order_queue.size()>0)
        for(int j=0; order_queue.size()>j; j++)
            addToTable(order_queue.get(j));
    }

    //Function to add a row to the table
    private void addToTable(Order o){
        int ticket_num = o.getTicketNum();
        int iD = o.getCustomerID();
        String full = o.getCustomerName();
        int amt = o.getItemCount();
        String stat = o.getStatus();
        
        //{"Ticket #", "Customer ID", "Item Amount", "Status"};
        String[] item= {""+ticket_num, ""+iD, full, ""+amt, stat};
        model.addRow(item);   
    }
    
    //Function to create an array list of orders from a file
    private ArrayList<Order> load_orders(String ofile){

        Scanner sscan = null;
        ArrayList<Order> OrderList = new ArrayList<Order>();
        List<List<String>> compressed_details = new ArrayList<List<String>>();;
        List<String> order_details = new ArrayList<String>();

        try{
            sscan  = new Scanner(new File(ofile));
            while(sscan.hasNext())
            {
                String data = sscan.nextLine(); 
                String[] nextLine = data.split(", ");
                //Output: 1, 620148438, Jane Doe, 2, [['bikini_bottom', 'red', 'M']], Home_Delivery, Pending

                int leng = nextLine.length;

                int ticket = Integer.parseInt(nextLine[0]);
                String custID = nextLine[1];
                String cust_fName = nextLine[2];
                String cust_lName = nextLine[3];
                OrderStatus status = OrderStatus.valueOf(nextLine[leng-1]);
                CourierMode mode = CourierMode.valueOf(nextLine[leng-2]);

                int item_count = Integer.parseInt(nextLine[4]);
                int i = 5;

                while(i<(item_count*3)+4){ //16 -> 3(5,6,7)+3(8,9,10)+3(11,12,13)+3(14,15,16)
                    String stype = nextLine[i].replaceAll("\\[", "").replaceAll("\\]","");
                    String scolor = nextLine[i+1];
                    String ssize = nextLine[i+2].replaceAll("\\[", "").replaceAll("\\]","");
                    
                    System.out.println(stype);
                    System.out.println(scolor);
                    System.out.println(ssize);

                    order_details.add(stype);
                    order_details.add(scolor);
                    order_details.add(ssize);

                    compressed_details.add(order_details);
                    i=i+3;
                }
                //Search for customer by Id: if found return the customer else create a customer
                //Order(Customer customer, int item_count, CourierMode delMode, int ticket_num, OrderStatus status, String[] details)

                Order o = new Order(null, item_count, mode, ticket, status, compressed_details);
                OrderList.add(o);
            }
            //order_queue = OrderList;
            sscan.close();
        }
        catch(IOException e)
        {}
        return OrderList; 
    }

    //Method to create a ticket with #
    private int make_ticket(){
        /*try
		{
			//File file=new File("database/orders.txt");
			//Scanner sscan=new Scanner(file);
			//while(sscan.hasNext()){
				//String [] nextLine = sscan.nextLine().split(" ");
                //get last ticket in queue and increment the ticket num
				//if(Integer.parseInt(nextLine[0])==last().getTicketNum())
				//{
				ticket_number++;
				//}
			//}
            //sscan.close();
		}
		catch(Exception e)
		{ }*/ 
		return ticket_number++;
    }
    
    //Order Queue
    // Adds one element to the rear of the queue
    //Function to add one order to the Order queue
    @Override
    public void enqueue(Order o){
        
    }

    //Function to Create an Order
    //1, 620148438, Jane Doe, 2, [['bikini_bottom', 'red', 'M']], pickup, Pending


    //Function to delete an Order - pop the queue and rewrite the file
    // Removes and returns the element at the front of the queue
    //removed element will be added to a new list IF status == completed
    @Override
    public Order dequeue( ){
        return order;
    }

    //Returns without removing the last element of the queue
    @Override
    public Order last( ){
        Order last_order = order_queue.get(-1);
        return last_order;
    }

    // Returns without removing the element at the front of the queue
    @Override
    public Order first( ){
        Order first_order = order_queue.get(0);
        return first_order;
    }

    // Returns true if the queue contains no elements
    @Override
    public boolean isEmpty( ){
        return false;
    }

    // Returns the number of elements in the queue
    @Override
    public  int queue_size( ){
        return 0;
    }

    // Returns a string representation of the queue
    @Override
    public String toString( ){
        return "";
    }


    /*How many items do you want to add? 2
     * for(int i; i<2; i++){
     *   String type = dropdown1.text()
     *   String color = dropdown2.text()
     *   String size = dropdown3.text()
     *  
     *   if java event button'confirm' clicked:
     *      orderdetails += [type, size, color]
     *      dropdown1 = ""
     *      dropdown2 = ""
     *      dropdown3 = ""
     *      continue....
     *   else if java even button'reset' clicked:
     *      dropdown1 = ""
     *      dropdown2 = ""
     *      dropdown3 = ""
     * }
     *  
     * then create the order instance and send orderdetails in as a parameter for it to be saved to the
     * database.
     */

    //Function to search for an order ticket

     //Function to edit an Order - to change details

     //Function to Generate Order Number

     //Function to calculate the price of an order

     //Function to store orders in a queue and save the queue to a file
     // - can utilize the delete as a 'pop' from the queue

     //Function to view an order ticket that is highlighted
     //that is when clicked - not hovered
     //Will allow vaiables to be edited i.e status

    //Mouse Listener
    //private class MouseAdapter implements MouseListener{
       // public void mouseClicked(MouseEvent evt) {
        //    otableMouseClicked(evt);
            
        //}
        
    //}

    /* 
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
        
        // get the model from the jtable
       DefaultTableModel model = (DefaultTableModel)jTable1.getModel();

        // get the selected row index
       int selectedRowIndex = jTable1.getSelectedRow();
       
        // set the selected row data into jtextfields
       c_fname.setText(model.getValueAt(selectedRowIndex, 0).toString());
       c_lname.setText(model.getValueAt(selectedRowIndex, 1).toString());
       jTextFieldLN.setText(model.getValueAt(selectedRowIndex, 2).toString());
       jTextFieldAGE.setText(model.getValueAt(selectedRowIndex, 3).toString());
        
    }     */                             

    //Button Listeners for: Back
    //method to add function to the back button
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent eve)
		{
            if(eve.getSource()==back){
                //navbar.setVisible(false);
                background.setVisible(false);

                MainMenu mscreen = new MainMenu(frame);
                frame.add(mscreen);
                mscreen.setVisible(true);   
            }
        }
	}

     //JDropDownSelection Menu with sorts of: Ticket #, Customer ID,
     //Successful Orders and Unsuccessful Orders - more of filter
}

