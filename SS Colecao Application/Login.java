import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.File;
import java.awt.Color; 

public class Login extends JFrame{
	//Attributes/Istance Variables
	private JTextField uname;
	private JPasswordField pword;
	private JRadioButton showpword;
	private JButton login;

	private JLabel error;
	private Scanner scan;
	private boolean found;
	private MainMenu mmscreen;

	private JPanel loginPanel;
	private JPanel logoPanel;
	private JFrame frame;
	private JLabel logo;

	private JLabel background;

	//Constructor
	public Login(){
		//Font
		Font f = new Font("Montserrat", Font.BOLD, 30);
		this.frame = this;

		//logo Panel
		logoPanel = new JPanel();
		logoPanel.setSize(400, 350);
		logoPanel.setOpaque(false);
		logoPanel.setBounds(30, 70, 410, 410);
		logoPanel.setLayout(null);

		//Logo
		ImageIcon logo_img = new ImageIcon("Ologo.png");
        Image image = logo_img.getImage();
		Image temp = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        logo_img = new ImageIcon(temp);
        logo = new JLabel(logo_img);
        logo.setBounds(0, 0, 420, 420);
        logoPanel.add(logo);

		//Login Panel
		loginPanel = new JPanel();
		loginPanel.setSize(400, 350);
		loginPanel.setOpaque(false);
		loginPanel.setBounds(450, 70, 400, 410);
		loginPanel.setLayout(null);

		//Login lable
		JLabel loginLabel = new JLabel("Login");
		loginLabel.setForeground(Color.WHITE);
		loginLabel.setFont(f);
		loginLabel.setBounds(150, 0, 150, 100);
		loginPanel.add(loginLabel);

		//Username
		JLabel unameLabel= new JLabel("Username");
		unameLabel.setForeground(Color.WHITE);
		unameLabel.setBounds(50, 100, 70, 20);
		loginPanel.add(unameLabel);

		uname=new JTextField("");
		//uname.setText("Admin");
		uname.setBounds(50, 130, 300, 40);
		uname.setForeground(Color.BLACK);
		uname.setBackground(new Color(255, 243, 194));
		loginPanel.add(uname);

		//Password
		JLabel pwordLabel= new JLabel("Password");
		pwordLabel.setBounds(50, 190, 70, 20);
		pwordLabel.setForeground(Color.WHITE);
		loginPanel.add(pwordLabel);
		
		pword = new JPasswordField("");
		//pword.setText("password123");
		pword.setBackground(new Color(255, 243, 194));
		pword.setForeground(Color.BLACK);
		pword.setEchoChar('*');
		pword.setBounds(50, 220, 300, 40);
		loginPanel.add(pword);

		//Buttons
		//Show Password Button
		showpword = new JRadioButton("Show Password");
		showpword.setBounds(140, 270, 150, 20);
		showpword.addItemListener(new RadioListener());
		showpword.setForeground(Color.WHITE);
		showpword.setBackground(new Color(255, 255, 255, 100));
		showpword.setOpaque(false);
		loginPanel.add(showpword);

		//Login Button
		login = new JButton("Login");
		login.setBounds(125, 300, 150, 50);
		login.setForeground(Color.BLACK);
		login.setBackground(new Color(251, 192, 68));
		login.addActionListener(new ButtonListener());
		loginPanel.add(login);

		//Default Frame Settings
		setPreferredSize(new Dimension(910,600));
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set Panel Background
		ImageIcon background_img = new ImageIcon("bg.png");
		Image img = background_img.getImage();
		Image temp_img = img.getScaledInstance(910, 600, Image.SCALE_SMOOTH);
		background_img = new ImageIcon(temp_img);
		background = new JLabel("", background_img, JLabel.CENTER);
		background.setBounds(0, 0, 910, 600);

		//background.add(header);
		background.add(logoPanel);
		background.add(loginPanel);
		add(background);

		//Error Message Display
		//error= new JLabel("");
		//loginPanel.add(error);

		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//method to add function to login button
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			//login
			try{
				found=false;
				String username=uname.getText();
				String password=new String(pword.getPassword());
				scan = new Scanner(new File("database/credentials.txt"));
				
				while(scan.hasNext()){
					String [] nextLine = scan.nextLine().split(" ");
					String unamef = nextLine[0];
					String pwordf = nextLine[1];
					System.out.println(unamef);
					System.out.println(pwordf);

					if(username.equals(unamef) && password.equals(pwordf)){
						found=true;

						loginPanel.setVisible(false);
						background.setVisible(false);

						mmscreen = new MainMenu(frame);
						add(mmscreen);
						mmscreen.setVisible(true);              
						break;
					}
					else{
						error.setText("Incorrect Username or Password");
						uname.setText("");
						pword.setText("");
					}
				}
				scan.close();	
			}
			catch(Exception e)
			{}
		}
	}
	
	
	
	//method to record the state of radio buttons on screen
	private class RadioListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent event)
		{
			if(event.getSource()==showpword)
			{
				if(showpword.isSelected())
				{
					pword.setEchoChar((char)0);
				}
				else
				{
					pword.setEchoChar('*');
				}
			}
		}
	}
}
