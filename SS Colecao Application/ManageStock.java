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

public class ManageStock extends JPanel{
    //Instance Variables/Attributes
    private JTextField searchbar;
    private JLabel search_label;
    private DefaultTableModel model;
    private JTable stable;
    private TableRowSorter sort;
    private JScrollPane pane;

    private JFrame frame;
    private JPanel background;
    private JPanel table;
    private JPanel addStock;

    private JLabel item_countlbl;
    private JLabel item_countlbl2;

    private JButton cancel_btn;
    private JButton confirm_btn;

    private JLabel item_typelbl;
    private JLabel item_colorlbl;
    private JLabel item_size;

    private JComboBox item_type_menu;
    private JComboBox item_color_menu;
    private JComboBox item_size_menu;

    private JTextField counter;
    private JTextField counter2;

    private JButton submit_btn;
    private JButton reset_btn;

    //bottom Pane
    private JButton back;
    private JLabel logo;

    private ArrayList<Stock> stocks = new ArrayList<Stock>();

    public ManageStock(JFrame frame){
        //Font and Defaults
        this.frame = frame;
        Font f = new Font("Montserrat", Font.BOLD, 20);
        frame.setTitle("SS Colecao - Manage Stock");

        //Search Bar
        searchbar = new JTextField(15);
        search_label = new JLabel("Search");

        //Table Setup
        stocks = load_stock("database/stock.txt");
        String[] columnNames = {"Stock Type", "Stock Color", "Stock Size", "Quantity", "Price"};

        model = new DefaultTableModel(columnNames, 0);
        stable = new JTable(model);
        sort = new TableRowSorter<>(model);
        showTable(stocks);
        stable.setRowSorter(sort);
        stable.setBounds(20, 30, 450, 450);
        pane = new JScrollPane(stable);
        pane.setViewportView(stable);

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
        addStock = new JPanel(new BorderLayout());

        //Logo 
        ImageIcon logo_img = new ImageIcon("Ologo.png");
        Image img = logo_img.getImage();
		Image temp_img = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logo_img = new ImageIcon(temp_img);
        logo = new JLabel(logo_img);
        logo.setBounds(0, 0, 100, 100);
        addStock.add(logo);

        //Add Order 
        JLabel addl = new JLabel("NEW STOCK ENTRY                    ");
        addl.setFont(f);
        addStock.setBorder( new TitledBorder("Add Stockr"));
        addl.setForeground(Color.BLACK);
        addl.setBounds(100, 0, 1000, 100);
        addStock.add(addl);

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
        counter = new JTextField(4);
        counter2 = new JTextField(4);

        //Add to Cinfo Panel
        cinfoPanel.setBorder( new TitledBorder("Amount of a Type of Stock"));
        cinfoPanel.setBounds(20, 80, 400, 250);

        cinfoPanel.add(item_countlbl2);
        cinfoPanel.add(counter);

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

        confirm_btn = new JButton("Confirm Stock");
        confirm_btn.addActionListener(new ButtonListener());

        infoPanel.add(submit_btn);
        infoPanel.add(reset_btn);
        infoPanel.add(cancel_btn);
        infoPanel.add(confirm_btn);

        //Add to addOrder Panel
        addStock.add(addl);
        addStock.add(cinfoPanel);
        addStock.add(infoPanel);
        addStock.add(new JLabel(" "));

        //Loop for as many times as there are items accept input from JCombo fields

        //Control Panel
		JPanel mainPanel = new JPanel(new GridLayout(1, 2, 1, 0));
		mainPanel.setBackground(new Color(22, 22, 21));
        table.add(back);
        mainPanel.add(table);
        mainPanel.add(addStock);

        //Set Panel Background
		background = new JPanel(new GridLayout(1, 1, 0, 10));
		background.setSize(910, 600);
        background.setBackground(new Color(22, 22, 21));
        
        //Add Panels to display
        background.add(mainPanel);
		frame.add(background);
    }

    //Method to show orders in a list in GUI table display.
    private void showTable(ArrayList<Stock> slst){
        if (stocks.size()>0)
        for(int j=0; stocks.size()>j; j++)
            addToTable(stocks.get(j));
    }

    //Function to add a row to the table
    private void addToTable(Stock st){
        int quantity = st.getAmount();
        String atype = st.getStockType().name();
        String col = st.getItemColor().name();
        String siz = st.getItemSize().name();
        int price = st.getPrice();
        
        //{"Ticket #", "Customer ID", "Item Amount", "Status"};
        String[] item= {""+quantity, atype, col, siz, ""+price};
        model.addRow(item);   
    }
    
    //Function to create an array list of orders from a file
    private ArrayList<Stock> load_stock(String sfile){

        Scanner sscan = null;
        ArrayList<Stock> StockList = new ArrayList<Stock>();

        try{
            sscan  = new Scanner(new File(sfile));
            while(sscan.hasNext())
            {
                String data = sscan.nextLine(); 
                String[] nextLine = data.split(", ");
                //Output:6, bikini_top, Pattern, L, 300

                int amt = Integer.parseInt(nextLine[0]);
                StockType atype = StockType.valueOf(nextLine[1]);
                ItemColor col = ItemColor.valueOf(nextLine[2]);
                Size siz = Size.valueOf(nextLine[3]);
                int price = Integer.parseInt(nextLine[4]);

                Stock nStock = new Stock(amt, atype, col, siz, price);
                StockList.add(nStock);
            }
            sscan.close();
        }
        catch(IOException e)
        {}
        return StockList; 
    }

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

