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
import javax.swing.JTextField;

public class SearchServofCus implements ActionListener {

	Window window;
	private JButton exit;
	private JLabel logo;
	 JLabel Search;
	 JLabel planlbl;
	 JButton seachbutton;
	
	public SearchServofCus(Window window) {
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
//Enter customer's username or email
					Search=new JLabel("Search Customer with plan");
					Search.setBounds(140,10,250,25);
					window.panel.add(Search);
//Username text parameters
					planlbl=new JLabel("Plan:");
					planlbl.setBounds(130,40,165,25);
					window.panel.add(planlbl);
//Username box parameters
					window.usertext=new JTextField();
					window.usertext.setBounds(160,40,165,25);
					window.panel.add(window.usertext);
//Search button parameters;
					this.seachbutton=new JButton("Search");
					this.seachbutton.setBounds(120,70,200,25);
					window.panel.add(this.seachbutton);
					this.seachbutton.addActionListener(new Searchingplan(window,this));
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
					this.exit.addActionListener(new SearchCusActionListener(window));
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
