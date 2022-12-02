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

	private JPanel header;
	private JPanel loginPanel;
	private JFrame frame;

	private JLabel background;

	//Constructor
	public Login(){
		//Font
		Font f = new Font("Montserrat", Font.BOLD, 30);
		this.frame = this;

		//Header
		setTitle("SS Colecao - Login");
		header = new JPanel();
		header.setOpaque(false);
		header.setBounds(0, 60, 910, 100);
		JLabel name = new JLabel("SS Colecao Stock Management System");
		name.setForeground(Color.WHITE);
		name.setFont(f);
		header.add(name);

		//Login Panel
		loginPanel = new JPanel();
		loginPanel.setSize(400, 350);
		loginPanel.setBackground(new Color(255, 255, 255, 100));
		loginPanel.setBounds(250, 130, 400, 350);
		loginPanel.setLayout(null);

		//Username
		JLabel unameLabel= new JLabel("Username");
		unameLabel.setForeground(Color.BLACK);
		unameLabel.setBounds(50, 30, 70, 20);
		loginPanel.add(unameLabel);

		uname=new JTextField("");
		uname.setText("Admin");
		uname.setBounds(50, 50, 300, 50);
		uname.setForeground(Color.BLACK);
		uname.setBackground(new Color(210, 180, 140));
		loginPanel.add(uname);

		//Password
		JLabel pwordLabel= new JLabel("Password");
		pwordLabel.setForeground(Color.BLACK);
		pwordLabel.setBounds(50, 130, 70, 20);
		loginPanel.add(pwordLabel);
		
		pword = new JPasswordField("");
		pword.setText("password123");
		pword.setBackground(new Color(210, 180, 140));
		pword.setForeground(Color.BLACK);
		pword.setEchoChar('*');
		pword.setBounds(50, 150, 300, 50);
		loginPanel.add(pword);

		//Buttons
		//Show Password Button
		showpword = new JRadioButton("Show Password");
		showpword.setBounds(145, 210, 150, 20);
		showpword.addItemListener(new RadioListener());
		showpword.setForeground(Color.BLACK);
		showpword.setBackground(new Color(255, 255, 255, 100));
		showpword.setOpaque(false);
		loginPanel.add(showpword);

		//Login Button
		login = new JButton("Login");
		login.setBounds(125, 250, 150, 50);
		login.setForeground(Color.BLACK);
		login.setBackground(new Color(160, 182, 45));
		login.addActionListener(new ButtonListener());
		loginPanel.add(login);

		//Default Frame Settings
		setPreferredSize(new Dimension(910,600));
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set Panel Background
		ImageIcon background_img = new ImageIcon("matte.jpg");
		Image img = background_img.getImage();
		Image temp_img = img.getScaledInstance(910, 600, Image.SCALE_SMOOTH);
		background_img = new ImageIcon(temp_img);
		background = new JLabel("", background_img, JLabel.CENTER);
		background.setBounds(0, 0, 910, 600);

		background.add(header);
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
			try
			{
				found=false;
				String username=uname.getText();
				String password=new String(pword.getPassword());
				scan = new Scanner(new File("database/credentials.txt"));
				
				while(scan.hasNext()){
					String [] nextLine = scan.nextLine().split(" ");
					String unamef = nextLine[0];
					String pwordf = nextLine[1];

					if(username.equals(unamef) && password.equals(pwordf)){
						found=true;

						loginPanel.setVisible(false);
						header.setVisible(false);
						background.setVisible(false);

						mmscreen = new MainMenu(frame);
						add(mmscreen);
						mmscreen.setVisible(true);              
			
						//setVisible(false);
						//dispose();
						break;
					}
					
					if(found==false)
					{
						error.setText("Incorrect Username or Password");
						uname.setText("");
						pword.setText("");
					}
					scan.close();	
				}
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
