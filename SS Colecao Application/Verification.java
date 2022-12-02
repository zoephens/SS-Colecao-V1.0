import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.File;
import java.awt.Color; 

public class Verification extends JPanel{
	//Attributes/Istance Variables
	private JTextField uname;
	private JPasswordField pword;
	private JRadioButton showpword;
	private JButton login;
    private JButton back;

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
	public Verification(JFrame frame){
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
		loginPanel = new JPanel();
		loginPanel.setSize(400, 300);
		loginPanel.setOpaque(false);
		loginPanel.setBounds(40, 100, 800, 410);
		loginPanel.setLayout(null);

		//Login lable
		JLabel loginLabel = new JLabel("Please re-enter your password in order ro proceed:");
		loginLabel.setForeground(Color.WHITE);
		loginLabel.setFont(f);
		loginLabel.setBounds(50, 60, 1200, 100);
		loginPanel.add(loginLabel);

		//Password
		JLabel pwordLabel= new JLabel("Password");
		pwordLabel.setBounds(250, 190, 70, 20);
		pwordLabel.setForeground(Color.WHITE);
		loginPanel.add(pwordLabel);
		
		pword = new JPasswordField("");
		pword.setText("password123");
		pword.setBackground(new Color(255, 243, 194));
		pword.setForeground(Color.BLACK);
		pword.setEchoChar('*');
		pword.setBounds(250, 220, 300, 40);
		loginPanel.add(pword);

		//Buttons
		//Show Password Button
		showpword = new JRadioButton("Show Password");
		showpword.setBounds(350, 270, 150, 20);
		showpword.addItemListener(new RadioListener());
		showpword.setForeground(Color.WHITE);
		showpword.setBackground(new Color(255, 255, 255, 100));
		showpword.setOpaque(false);
		loginPanel.add(showpword);

		//Login Button
		login = new JButton("Proceed");
		login.setBounds(220, 300, 150, 50);
		login.setForeground(Color.BLACK);
		login.setBackground(new Color(251, 192, 68));
		login.addActionListener(new ButtonListener());
		loginPanel.add(login);

        //Back Button
		back = new JButton("Back");
		back.setBounds(420, 300, 150, 50);
		back.setForeground(Color.BLACK);
		back.setBackground(new Color(251, 192, 68));
		back.addActionListener(new ButtonListener());
		loginPanel.add(back);

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
		background.add(loginPanel);
		frame.add(background);
	}
	
	//method to add function to login button
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			//Re-verify
            if(eve.getSource()==login){
                try{
                    found=false;
                    String password=new String(pword.getPassword());
                    scan = new Scanner(new File("database/credentials.txt"));
                    
                    while(scan.hasNext()){
                        String [] nextLine = scan.nextLine().split(" ");
                        String pwordf = nextLine[1];
                        System.out.println(pwordf);

                        if(password.equals(pwordf)){
                            found=true;

                            loginPanel.setVisible(false);
                            //header.setVisible(false);

                            background.setVisible(false);

                            //mmscreen = new MainMenu(frame);
                            //add(mmscreen);
                            //mmscreen.setVisible(true);              
                            break;
                        }
                        else{
                            error.setText("Incorrect Password");
                            pword.setText("");
                        }
                    }
                    scan.close();	
                }
                catch(Exception e)
                {}
            }

            //Back
            if(eve.getSource()==back){
                loginPanel.setVisible(false);
                background.setVisible(false);

                mmscreen = new MainMenu(frame);
                add(mmscreen);
                mmscreen.setVisible(true);              
            }
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
