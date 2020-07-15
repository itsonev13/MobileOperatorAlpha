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

public class AddOptionsActionListener implements ActionListener {
 Window window;
 JLabel Search;
 JLabel uorelabel;
 JLabel logo;
 JLabel choose;
 JLabel plan1;
 JLabel plan2;
 JLabel plan3;
 JTextField choosenplan;
 JButton exit;
 JButton exit2;
	public AddOptionsActionListener(Window window) {
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
				Search=new JLabel("Enter customer's username or email ");
				Search.setBounds(140,10,250,25);
				window.panel.add(Search);
		//Username text parameters
				uorelabel=new JLabel("Username or email:");
				uorelabel.setBounds(56,40,165,25);
				window.panel.add(uorelabel);
		//Username box parameters
				window.usertext=new JTextField();
				window.usertext.setBounds(170,40,165,25);
				window.panel.add(window.usertext);
		//Text for successful or invalid user or password
				window.success=new JLabel("");
				window.success.setBounds(120,90,300,25);
				window.panel.add(window.success);
				window.frame.revalidate();
				window.frame.repaint();
				window.frame.setVisible(true);
		//Search button parameters;
				this.exit2=new JButton("Search");
				this.exit2.setBounds(120,70,200,25);
				window.panel.add(this.exit2);
				this.exit2.addActionListener(new AddingServiceActionListener(window,this));
		// 	window.registerButton.addActionListener(new RegistratedActionListener(window,this));
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

						
	}

}
