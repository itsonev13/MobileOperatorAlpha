package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterActionListener implements ActionListener {
	 Window window;
	 JLabel reppasslabel;
	 JPasswordField reppass;
	 JLabel username2;
	 JTextField usernametext;
	 JLabel phonenumber;
	 JTextField phonetext;
	 JLabel logo;
	 JLabel registrationForm;
	 JButton exit;
	 JLabel plan1;
	 JLabel plan2;
	 JLabel plan3;
	 JLabel choose;
	 JTextField choosenplan;
	public RegisterActionListener(Window window)
	{
				this.window=window;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
//Clear screen
//JPannel parameters
		window.frame.getContentPane().removeAll();
		window.frame.repaint();
		window.panel=new JPanel();
		window.panel.setBackground(Color.WHITE);
		window.frame.add(window.panel);
		window.panel.setLayout(null);
//Registration form text parameters
		registrationForm=new JLabel("Registration Form");
		registrationForm.setBounds(160,10,165,25);
		window.panel.add(registrationForm);
//Username text parameters
		username2=new JLabel("Username:");
		username2.setBounds(56,40,165,25);
		window.panel.add(username2);
//Username box parameters
		usernametext=new JTextField();
		usernametext.setBounds(120,40,165,25);
		window.panel.add(usernametext);
//Email text parameters
			window.username=new JLabel("Email:");
			window.username.setBounds(85,70,165,25);
			window.panel.add(window.username);
//Email box parameters
			window.usertext=new JTextField();
			window.usertext.setBounds(120,70,165,25);
			window.panel.add(window.usertext);
//Password text parameters
			window.password=new JLabel("Password:");
			window.password.setBounds(58,100,165,25);
			window.panel.add(window.password);
//Password box parameters
			window.passtext=new JPasswordField();
			window.passtext.setBounds(120,100,165,25);
			window.panel.add(window.passtext);
//REPEATEPassword text parameters
			reppasslabel=new JLabel("Repeat Password:");
			reppasslabel.setBounds(15,130,165,25);
			window.panel.add(reppasslabel);
//REPEATEPassword box parameters
			reppass=new JPasswordField();
			reppass.setBounds(120,130,165,25);
			window.panel.add(reppass);
//Phone text parameters
			phonenumber=new JLabel("Phone:");
			phonenumber.setBounds(80,170,165,25);
			window.panel.add(phonenumber);
//Phone box parameters
			phonetext=new JTextField();
			phonetext.setBounds(120,170,165,25);
			window.panel.add(phonetext);
//Register button parameters;
			window.registerButton=new JButton("Register!");
			window.registerButton.setBounds(120,200,200,25);
			window.panel.add(window.registerButton);
			window.registerButton.addActionListener(new RegistratedActionListener(window,this));
//Exit button parameters;
			this.exit=new JButton("Go back");	
			this.exit.setBounds(350,200,200,25);
//Set color red when hoverd over button
			this.exit.addMouseListener(new java.awt.event.MouseAdapter() 
			{
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			 {
			 exit.setBackground(Color.RED);
			 }
			 public void mouseExited(java.awt.event.MouseEvent evt) 
			 {
				exit.setBackground(window.loginButton.getBackground());
			 }
			});
			window.panel.add(this.exit);
			this.exit.addActionListener(new ExittoAdimnActionListener(this.window));
//Text for successful or invalid user or password
			window.success=new JLabel("");
			window.success.setBounds(120,260,300,25);
			window.panel.add(window.success);
			window.frame.revalidate();
			window.frame.repaint();
			window.frame.setVisible(true);
//Choose plan text parameters
			choose=new JLabel("Choose a plan:");
			choose.setBounds(940,10,165,25);
			window.panel.add(choose);
//plan1 text parameters			
			plan1=new JLabel("<html>Plan Smart:<br>SMS:100<br>Minutes:100<br>Mobile Data:1024 MB</html>");
			plan1.setBounds(800,40,120,80);
			window.panel.add(plan1);
//plan 2 text parameters
			plan2=new JLabel("<html>Plan Icon:<br>SMS:0<br>Minutes:500<br>Mobile Data:2048 MB</html>");
			plan2.setBounds(940,40,120,80);
			window.panel.add(plan2);
//plan 3 text parameters
			plan3=new JLabel("<html>Plan Infinity:<br>SMS:1000<br>Minutes:1000<br>Mobile Data:8000 MB</html>");
			plan3.setBounds(1080,40,120,80);
			window.panel.add(plan3);
//plans box parameers
			choosenplan=new JTextField();
			choosenplan.setBounds(940,120,165,25);
			window.panel.add(choosenplan);
			
//LOGO/////////////////////////////////////////////////////
			logo=new JLabel();
			this.logo.setBounds(0,0,window.frame.getWidth(),window.frame.getHeight());
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("D:\\Programing test\\java\\MobileOperatorAlpha\\logo3.png"));
			    
			} catch (IOException e2) 
			{
			    e2.printStackTrace();
			}
			Image dimg = img.getScaledInstance(logo.getWidth(), logo.getHeight(),Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			logo.setIcon(imageIcon);
			window.panel.add(logo);
//////////////////////////////////////////////////

		
	}
}
