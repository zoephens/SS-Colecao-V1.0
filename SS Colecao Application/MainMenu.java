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
    private JButton notif, stock, order, back;
    
    public MainMenu(JFrame frame){
        frame.setTitle("SS Colecao Main Menu");
        this.frame = frame;

        //Navigation Bar
		navbar = new JPanel(new BorderLayout());
        navbar.setBorder(new LineBorder(Color.BLACK, 3));

		navbar.setBounds(0, 0, 200, 600);
        navbar.setBackground(new Color(255, 255, 255, 100));

        //Buttons
        JPanel buttoPanel = new JPanel(new GridLayout(0,1,0,4));

        //Notification
        notif = new JButton("Notifications");
        notif.setMaximumSize(new Dimension(300, 300));
		notif.setForeground(Color.BLACK);
		notif.setBackground(new Color(160, 182, 45));
		//notif.addActionListener(new ButtonListener());

        //Manage Stock
        stock = new JButton("Manage Stocks");
        stock.setMaximumSize(new Dimension(300, 300));
		stock.setForeground(Color.BLACK);
		stock.setBackground(new Color(160, 182, 45));
		stock.addActionListener(new ButtonListener());

        //Manage Orders
        order = new JButton("Manage Orders");
        order.setMaximumSize(new Dimension(300, 300));
		order.setForeground(Color.BLACK);
		order.setBackground(new Color(160, 182, 45));
		order.addActionListener(new ButtonListener());

        //deliv = new JButton("Manage Deliveries");

        //Back Button
        back = new JButton("Logout");
        back.addActionListener(new ButtonListener());
        back.setMaximumSize(new Dimension(300, 300));
		back.setForeground(Color.BLACK);
		back.setBackground(new Color(160, 182, 45));
		back.addActionListener(new LogoutListener());

        buttoPanel.add(notif);
        buttoPanel.add(stock);
        buttoPanel.add(order);
        buttoPanel.add(back);
        buttoPanel.setBackground(new Color(255, 255, 255, 100));
        navbar.add(buttoPanel, BorderLayout.PAGE_START);

        //Set Panel Background
		ImageIcon background_img = new ImageIcon("Background.jpg");
		Image img = background_img.getImage();
		Image temp_img = img.getScaledInstance(910, 600, Image.SCALE_SMOOTH);
		background_img = new ImageIcon(temp_img);
		background = new JLabel("", background_img, JLabel.CENTER);
		background.setBounds(0, 0, 910, 600);

        background.add(navbar);
		background.add(navbar);
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