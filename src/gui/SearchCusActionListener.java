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
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchCusActionListener implements ActionListener {
Window window;
 JButton scus;
 JButton allcustomerswiththisservice;
private JButton exit;
private JLabel logo;

	public SearchCusActionListener(Window window) {
		// TODO Auto-generated constructor stub
	this.window=window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Clear screen
				//JPannel parameters
				window.frame.getContentPane().removeAll();
				window.frame.repaint();
				window.panel=new JPanel();
				window.panel.setBackground(Color.WHITE);
				window.frame.add(window.panel);
				window.panel.setLayout(null);
		//Search customer's sevices
		this.scus=new JButton("Search all customers with a service");
		this.scus.setBounds(10,10,250,25);
		window.panel.add(this.scus);
		this.scus.addActionListener(new SearchServofCus(window));
		//Search all customers with a service
		this.allcustomerswiththisservice=new JButton("Search customer's sevices");
		this.allcustomerswiththisservice.setBounds(260,10,250,25);
		window.panel.add(this.allcustomerswiththisservice);
		this.allcustomerswiththisservice.addActionListener(new SearchCusWithSer(window));
		//Exit button parameters;
				this.exit=new JButton("Go back");	
				this.exit.setBounds(120,100,200,25);
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
				this.exit.addActionListener(new ExittoAdimnActionListener(window));
				
				
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
				window.frame.revalidate();
				window.frame.repaint();
				window.frame.setVisible(true);

	}

}
