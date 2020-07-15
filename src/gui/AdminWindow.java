package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminWindow {
	 Window window;
	 JLabel logo;
	 JButton addOptions;
	 JButton addCustomer;
	 JButton searchCustomerOrService;
	 JButton notPaid;
	 JButton exit;
	 AdminWindow(Window window)
		{
			this.window=window;
		}

	 void createAdminWindow()
	 {
		//Clear screen
		//JPannel parameters
				window.frame.getContentPane().removeAll();
				window.frame.repaint();
				window.panel=new JPanel();
				window.panel.setBackground(Color.WHITE);
				window.frame.add(window.panel);
				window.panel.setLayout(null);
		//Customer services button parameters;
				this.addOptions=new JButton("Add customer services");	
				this.addOptions.setBounds(10,20,300,25);
				this.addOptions.addActionListener(new AddOptionsActionListener(this.window));
				window.panel.add(this.addOptions);
				
		//Add Customer button parameters;
				this.addCustomer=new JButton("Add new customer");	
				this.addCustomer.setBounds(310,20,300,25);
				this.addCustomer.addActionListener(new RegisterActionListener(this.window));
				window.panel.add(this.addCustomer);
				
		//Search Customer or Service button parameters;
				this.searchCustomerOrService=new JButton("Search Customer Or Service");	
				this.searchCustomerOrService.setBounds(610,20,300,25);
				this.searchCustomerOrService.addActionListener(new SearchCusActionListener(this.window));
				window.panel.add(this.searchCustomerOrService);
				
		//Customers that haven't paid yet button parameters;
				this.notPaid=new JButton("Customers that haven't paid yet for next month");	
				this.notPaid.setBounds(910,20,300,25);
				this.notPaid.addActionListener(new notPaidActionListener(this.window));
				window.panel.add(this.notPaid);
		//Exit button parameters;
				this.exit=new JButton("Sign Out");	
				this.exit.setBounds(560,200,100,25);
				//Set color red when hoverd over button
				this.exit.addMouseListener(new java.awt.event.MouseAdapter() 
				{
				    public void mouseEntered(java.awt.event.MouseEvent evt) 
				    {
				       exit.setBackground(Color.RED);
				    }
				    public void mouseExited(java.awt.event.MouseEvent evt) 
				    {
				        exit.setBackground(searchCustomerOrService.getBackground());
				    }
				});
				window.panel.add(this.exit);
				this.exit.addActionListener(new ExitActionListener(this.window));
		//Text for successful or invalid user or password
					window.success=new JLabel("");
					window.success.setBounds(120,200,300,25);
					window.panel.add(window.success);
					window.frame.revalidate();
					window.frame.repaint();
					window.frame.setVisible(true);
					// LOGO
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
