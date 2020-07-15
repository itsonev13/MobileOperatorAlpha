package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataBaseConn.Plan;
import DataBaseConn.conn;

public class PaydayActionListener implements ActionListener {

	Window win;
	private JButton exit;
	private JLabel logo;
	public PaydayActionListener(Window window) {
		// TODO Auto-generated constructor stub
	this.win=window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<Integer> plans_id =new ArrayList<>();
		List<Plan> plans=conn.GetAllPlans();
		JLabel paydatetext=null;
		JLabel paydatetext2=null;
		JLabel paydatetext3=null;
		// TODO Auto-generated method stub
		int[] months_for_plans=conn.GetLastMonthOfpayday(conn.GetUserid(win.usertext.getText()),plans_id);
		
				
					String[] months= {"January","February","March","April","May","June","July","August","September","October","November","December"};
					win.frame.getContentPane().removeAll();
					win.frame.repaint();
					win.panel=new JPanel();
					win.panel.setBackground(Color.WHITE);
					win.frame.add(win.panel);
					win.panel.setLayout(null);
					if(months_for_plans[0]!=-1) 
					{ 
					paydatetext=new JLabel("Plan "+plans.get(0).getName()+ " expires in "+months[months_for_plans[0]-1]);
					paydatetext.setBounds(10,10,300,25);
					win.panel.add(paydatetext);
					}
					if(months_for_plans[1]!=-1) 
					{ 
					paydatetext2=new JLabel("Plan "+plans.get(1).getName()+ " expires in "+months[months_for_plans[1]-1]);
					paydatetext2.setBounds(10,30,300,25);
					win.panel.add(paydatetext2);
					}
					if(months_for_plans[2]!=-1) 
					{ 
					paydatetext3=new JLabel("Plan "+plans.get(2).getName()+ " expires in "+months[months_for_plans[2]-1]);
					paydatetext3.setBounds(10,60,300,25);
					win.panel.add(paydatetext3);
					}
					if((months_for_plans[0]==-1)&&(months_for_plans[1]==-1)&&(months_for_plans[2]==-1))
					{
						paydatetext=new JLabel("You have no plans");
						paydatetext.setBounds(10,10,300,25);
						win.panel.add(paydatetext);
					}
					//Exit button parameters;
					this.exit=new JButton("Go back");	
					this.exit.setBounds(10,90,100,25);
					//Set color red when hoverd over button
					this.exit.addMouseListener(new java.awt.event.MouseAdapter() 
					{
					    public void mouseEntered(java.awt.event.MouseEvent evt) 
					    {
					       exit.setBackground(Color.RED);
					    }
					    public void mouseExited(java.awt.event.MouseEvent evt) 
					    {
					        exit.setBackground(win.loginButton.getBackground());
					    }
					});
					win.panel.add(this.exit);
					this.exit.addActionListener(new ExittoCustomerActionListener(this.win));
					// LOGO
					logo=new JLabel();
					this.logo.setBounds(0,0,win.frame.getWidth(),win.frame.getHeight());
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
					win.panel.add(logo);
				//////////////////////////////////////////////////
					win.frame.revalidate();
					win.frame.repaint();
					win.frame.setVisible(true);
				
			
	
	}

}
