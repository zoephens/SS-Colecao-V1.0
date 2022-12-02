import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Color; 
import java.awt.event.*;

public class MainMenu extends JPanel{
    //Instance Variables/Attributes
    private JPanel navbar;
    private JLabel background;
    private JFrame frame;
    private JPanel header;
    private JButton notif, stock, order, deliv, back, cust;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel midPanel;
    
    public MainMenu(JFrame frame){
        frame.setTitle("SS Colecao Main Menu");
        Font f = new Font("Montserrat", Font.BOLD, 45);
		this.frame = frame;

        //Header
		header = new JPanel();
		header.setOpaque(false);
		header.setBounds(0, 60, 910, 100);
		JLabel name = new JLabel("Main Menu");
		name.setForeground(new Color(251, 192, 68));
		name.setFont(f);
		header.add(name);

        //Left Panel
		leftPanel = new JPanel(new BorderLayout());
		leftPanel.setOpaque(false);
		leftPanel.setBounds(160, 150, 250, 370);


        //Center Panel
        midPanel = new JPanel();
        midPanel.setBackground(new Color(251, 192, 68));
		midPanel.setBounds(442, 150, 6, 365);
		midPanel.setLayout(null);

        //Right Panel
        rightPanel = new JPanel(new GridLayout(4, 1, 0, 10));
		rightPanel.setOpaque(false);
		rightPanel.setBounds(478, 150, 250, 370);
		rightPanel.setLayout(null);

        //Navigation Bar
		navbar = new JPanel(new BorderLayout());
        navbar.setBorder(new LineBorder(Color.BLACK, 3));
		navbar.setBounds(0, 0, 200, 600);
        navbar.setBackground(new Color(255, 255, 255, 100));

        //Buttons
        //--------------------------------------------------------
        //Manage Stock
        stock = new JButton("Manage Stocks");
        stock.setBounds(25, 12, 200, 60);
		stock.setForeground(Color.WHITE);
		stock.setBackground(new Color(55, 55, 54));
		stock.addActionListener(new ButtonListener());

        //Manage Orders
        order = new JButton("Manage Orders");
        order.setBounds(25, 100, 200, 60);
		order.setForeground(Color.WHITE);
		order.setBackground(new Color(55, 55, 54));
		order.addActionListener(new ButtonListener());

        //Manage Deliveries
        deliv = new JButton("Manage Deliveries");
        deliv.setBounds(25, 188, 200, 60);
		deliv.setForeground(Color.WHITE);
		deliv.setBackground(new Color(55, 55, 54));
		deliv.addActionListener(new ButtonListener());

        //Manage Customers
        cust = new JButton("Manage Customers");
        cust.setBounds(25, 276, 200, 60);
		cust.setForeground(Color.WHITE);
		cust.setBackground(new Color(55, 55, 54));
		cust.addActionListener(new ButtonListener());

        //--------------------------------------------------------

        //Manage Notifications
        notif = new JButton("Notifications");
        notif.setBounds(25, 12, 200, 60);
		notif.setForeground(Color.WHITE);
		notif.setBackground(new Color(55, 55, 54));
		notif.addActionListener(new ButtonListener());

        //Back Button
        back = new JButton("Logout");
        back.setBounds(25, 100, 200, 60);
		back.setForeground(Color.BLACK);
		back.setBackground(Color.WHITE);
		back.addActionListener(new ButtonListener());
		back.addActionListener(new LogoutListener());


        //.add(notif);
        leftPanel.add(stock);
        leftPanel.add(order);
        leftPanel.add(deliv);
        leftPanel.add(cust);

        rightPanel.add(notif);
        rightPanel.add(back);

        leftPanel.add(new JLabel(""));
        rightPanel.add(new JLabel(""));

        //Set Panel Background
		ImageIcon background_img = new ImageIcon("Background.jpg");
		Image img = background_img.getImage();
		Image temp_img = img.getScaledInstance(910, 600, Image.SCALE_SMOOTH);
		background_img = new ImageIcon(temp_img);
		background = new JLabel("", background_img, JLabel.CENTER);
		background.setBounds(0, 0, 910, 600);

        background.add(header);
        background.add(midPanel);
        background.add(leftPanel);
        background.add(rightPanel);
		frame.add(background);
    }

    //method to add function to the menu buttons
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			//Notifications
            //if(eve.getSource()==notif){}

            //Stock Management
            if(eve.getSource()==stock){
                navbar.setVisible(false);
                background.setVisible(false);

                ManageStock sscreen = new ManageStock(frame);
                add(sscreen);
                sscreen.setVisible(true);   
            }

            //Order Management
            if(eve.getSource()==order){
                navbar.setVisible(false);
                background.setVisible(false);

                ManageOrders oscreen = new ManageOrders(frame);
                add(oscreen);
                oscreen.setVisible(true);   
            }

            //Customer Management
            if(eve.getSource()==cust){
                navbar.setVisible(false);
                background.setVisible(false);

                Verification vscreen = new Verification(frame);
                add(vscreen);
                vscreen.setVisible(true);   
            }
        }
	}

    //method to logout of admin screen
    private class LogoutListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae){
            Login l = new Login();
            setVisible(false);
            frame.dispose();   
        }
    }
}