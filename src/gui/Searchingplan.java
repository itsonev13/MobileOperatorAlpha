package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import DataBaseConn.Users;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataBaseConn.conn;

public class Searchingplan implements ActionListener {
	Window window;
	SearchServofCus sc;
	private JButton exit;
	private JLabel logo;
	public Searchingplan(Window window, SearchServofCus searchServofCus) {
		// TODO Auto-generated constructor stub
		this.window=window;
		this.sc=sc;
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
		//////////codee//////////////////////////
		String choosenPlan=window.usertext.getText();
		int choosenPlanid=conn.getPlanId(choosenPlan);
		if(choosenPlanid==-1)
		{
			window.success=new JLabel("Invalid plan");
			window.success.setBounds(120,130,300,25);
			window.success.setForeground(Color.RED);
			window.panel.add(window.success);
		}
		else
		{
			List <Users>users =conn.GetAllUserswithPlan(choosenPlanid);
			JLabel userinfo=new JLabel("");
			StringBuilder allusersinfo= new StringBuilder("");;
			userinfo.setBounds(0,0,500,300);
			allusersinfo.append("<html>");
			String balnk="     ";
			for(Users u:users)
			{
				allusersinfo.append("<pre>"+u.getName()+balnk+u.getPhone()+balnk+u.getEmail()+"</pre>");
			}
			allusersinfo.append("</html>");
			userinfo.setText(allusersinfo.toString());
			this.window.panel.add(userinfo);
			
		}
		
		
		
		
		
//Exit button parameters;
		this.exit=new JButton("Go back");	
		this.exit.setBounds(400,100,200,25);
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
		this.exit.addActionListener(new SearchServofCus(window));
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
