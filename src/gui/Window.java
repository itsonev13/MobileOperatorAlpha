package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Window 
{
	JFrame frame;
	JPanel panel;
	JLabel username;
	JLabel password;
	JTextField usertext;
	JPasswordField passtext;
	JButton loginButton;
	JButton registerButton;
	JLabel success;
	JLabel logo;
void CreateWindow(int sizeX,int sizeY)
{
	if(frame!=null) 
	{
		//Clear screen
		//JPannel parameters
			this.frame.getContentPane().removeAll();
			this.frame.repaint();
			this.panel=new JPanel();
			this.panel.setBackground(Color.WHITE);
			this.frame.add(this.panel);
			this.panel.setLayout(null);
	}
	
//JFrame parameters
	if(frame==null)
	{
	this.frame=new JFrame();
	this.frame.setSize(sizeX,sizeY);
	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
// JPannel parameters
	this.panel=new JPanel();
	panel.setBackground(Color.WHITE);
	this.frame.add(panel);
	panel.setLayout(null);
//User text parameters
	this.username=new JLabel("Email or username:");
	this.username.setBounds(10,20,165,25);
	panel.add(this.username);
//User box parameters
	this.usertext=new JTextField();
	this.usertext.setBounds(10,40,165,25);
	panel.add(this.usertext);
//Password text parameters
	this.password=new JLabel("Password:");
	this.password.setBounds(200,20,165,25);
	panel.add(this.password);
//Password box parameters
	this.passtext=new JPasswordField();
	this.passtext.setBounds(200,40,165,25);
	panel.add(this.passtext);
//Login button parameters;
	this.loginButton=new JButton("Log in");
	this.loginButton.setBounds(370,40,100,25);
	this.loginButton.addActionListener(new LogInActionListener(this));
	panel.add(this.loginButton);
//Text for successful or invalid user or password
	this.success=new JLabel("");
	this.success.setBounds(130,80,300,25);
	panel.add(this.success);
// LOGO
	logo=new JLabel();
	this.logo.setBounds(0,0,this.frame.getWidth(),this.frame.getHeight());
	BufferedImage img = null;
	try {
	    img = ImageIO.read(new File("D:\\Programing test\\java\\MobileOperatorAlpha\\logo3.png"));
	    
	} catch (IOException e) 
	{
	    e.printStackTrace();
	}
	Image dimg = img.getScaledInstance(logo.getWidth(), logo.getHeight(),Image.SCALE_SMOOTH);
	ImageIcon imageIcon = new ImageIcon(dimg);
	logo.setIcon(imageIcon);
	panel.add(logo);
//////////////////////////////////////////////////
	this.frame.setVisible(true);
	this.frame.setResizable(false);
}

}

