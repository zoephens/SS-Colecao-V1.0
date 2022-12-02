import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.File;
import java.awt.Color; 

public class AdministerCoupons extends JPanel{
	//Attributes/Istance Variables
	private JTextField amt;
	private JRadioButton showpword;
	private JButton confirm;
    private JButton back;

	private JLabel error;
	private Scanner scan;
	private boolean found;
	private ManageOrders moscreen;

	private JPanel couponPanel;
	private JPanel logoPanel;
	private JFrame frame;
	private JLabel logo;

	private JLabel background;

	//Constructor
	public AdministerCoupons(JFrame frame){
		//Font
		Font f = new Font("Montserrat", Font.BOLD, 30);
		this.frame = frame;

		//logo Panel
		logoPanel = new JPanel();
		logoPanel.setSize(400, 350);
		logoPanel.setOpaque(false);
		logoPanel.setBounds(400, 40, 300, 200);
		logoPanel.setLayout(null);

		//Logo
		ImageIcon logo_img = new ImageIcon("Ologo.png");
        Image image = logo_img.getImage();
		Image temp = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logo_img = new ImageIcon(temp);
        logo = new JLabel(logo_img);
        logo.setBounds(0, 0, 100, 100);
        logoPanel.add(logo);

		//Login Panel
		couponPanel = new JPanel();
		couponPanel.setSize(400, 300);
		couponPanel.setOpaque(false);
		couponPanel.setBounds(40, 100, 800, 410);
		couponPanel.setLayout(null);

		//Login lable
		JLabel loginLabel = new JLabel("Please enter the amount you want to deduce price by:");
		loginLabel.setForeground(Color.WHITE);
		loginLabel.setFont(f);
		loginLabel.setBounds(30, 60, 1200, 100);
		couponPanel.add(loginLabel);

		//Password
		JLabel pwordLabel= new JLabel("Amount");
		pwordLabel.setBounds(250, 190, 70, 20);
		pwordLabel.setForeground(Color.WHITE);
		couponPanel.add(pwordLabel);
		
		amt = new JTextField("");
		amt.setText("");
		amt.setBackground(new Color(255, 243, 194));
		amt.setForeground(Color.BLACK);
		amt.setBounds(250, 220, 300, 40);
		couponPanel.add(amt);

		//Buttons

		//Confirm Button
		confirm = new JButton("Confirm");
		confirm.setBounds(220, 300, 150, 50);
		confirm.setForeground(Color.BLACK);
		confirm.setBackground(new Color(251, 192, 68));
		confirm.addActionListener(new ButtonListener());
		couponPanel.add(confirm);

        //Back Button
		back = new JButton("Nevermind");
		back.setBounds(420, 300, 150, 50);
		back.setForeground(Color.BLACK);
		back.setBackground(new Color(251, 192, 68));
		back.addActionListener(new ButtonListener());
		couponPanel.add(back);

		//Default Frame Settings
		setPreferredSize(new Dimension(910,600));
		setLayout(null);

		//Set Panel Background
		ImageIcon background_img = new ImageIcon("bg.png");
		Image img = background_img.getImage();
		Image temp_img = img.getScaledInstance(910, 600, Image.SCALE_SMOOTH);
		background_img = new ImageIcon(temp_img);
		background = new JLabel("", background_img, JLabel.CENTER);
		background.setBounds(0, 0, 910, 600);

		//background.add(header);
		background.add(logoPanel);
		background.add(couponPanel);
		frame.add(background);
	}
	
	//method to add function to login button
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			//back
            if(eve.getSource()==back){
                background.setVisible(false);

                ManageOrders mscreen = new ManageOrders(frame);
                frame.add(mscreen);
                mscreen.setVisible(true);   
            }

		}
	}
}
