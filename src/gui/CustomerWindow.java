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

public class CustomerWindow {
	 Window window;
	 JLabel logo;
	 JButton Services;
	 JButton payDate;
	 JButton exit;
	 CustomerWindow(Window window)
		{
			this.window=window;
		}

	 void createCustomerWindow()
	 {
		
		//Clear screen
		//JPannel parameters
				window.frame.getContentPane().removeAll();
				window.frame.repaint();
				window.panel=new JPanel();
				window.panel.setBackground(Color.WHITE);
				window.frame.add(window.panel);
				window.panel.setLayout(null);
		//Check services button parameters;
				this.Services=new JButton("Check service");	
				this.Services.setBounds(10,20,300,25);
				this.Services.addActionListener(new ServicesActionListener(this.window));
				window.panel.add(this.Services);
		//Check when is paydate button parameters;
				this.payDate=new JButton("Check when is paydate");	
				this.payDate.setBounds(310,20,300,25);
				this.payDate.addActionListener(new PaydayActionListener(this.window));
				window.panel.add(this.payDate);
		//Exit button parameters;
				this.exit=new JButton("Sign Out");	
				this.exit.setBounds(700,20,100,25);
				//Set color red when hoverd over button
				this.exit.addMouseListener(new java.awt.event.MouseAdapter() 
				{
				    public void mouseEntered(java.awt.event.MouseEvent evt) 
				    {
				       exit.setBackground(Color.RED);
				    }
				    public void mouseExited(java.awt.event.MouseEvent evt) 
				    {
				        exit.setBackground(Services.getBackground());
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
