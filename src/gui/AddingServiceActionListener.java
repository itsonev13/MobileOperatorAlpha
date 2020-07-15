package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import DataBaseConn.Users;
import DataBaseConn.conn;

public class AddingServiceActionListener implements ActionListener {
	Window window;
	private boolean isValidPlan;
	private AddOptionsActionListener ad;
	private boolean userfound=false;
	public AddingServiceActionListener(Window window,AddOptionsActionListener ad) {
		// TODO Auto-generated constructor stub
		this.window=window;
		this.ad=ad;
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		List<Users> allusers=conn.GetAllUsers();
		window.success.setBounds(120,120,150,25);
		//plan check
		if(ad.choosenplan.getText().equalsIgnoreCase("smart")) 
		{
			isValidPlan=true;
		}
		else if(ad.choosenplan.getText().equalsIgnoreCase("icon")) 
		{
			isValidPlan=true;
		}
		else if(ad.choosenplan.getText().equalsIgnoreCase("infinity")) 
		{
			isValidPlan=true;
		}
		else 
		{
			window.success.setText("Invalid plan choise");
			window.success.setForeground(Color.RED);
		}
		for(Users u:allusers) 
		{
			 if(!userfound)
				{
					window.success.setBounds(120,120,150,25);
					window.success.setText("Invalid username or email");
					window.success.setForeground(Color.red);
				}
			if(window.usertext.getText().equals(u.getEmail())||window.usertext.getText().equals(u.getName()))
			{	
				if(isValidPlan)
				{
				
				try {
					window.success.setBounds(120,120,150,25);
					window.success.setText("Services was added");
					window.success.setForeground(Color.green);
					conn.InserIntousers_plan(window.usertext.getText(),this.ad.choosenplan.getText());
					break;
				} catch (SQLException e1) {
					window.success.setBounds(120,120,250,25);
					window.success.setText("This customer already has this plan");
					window.success.setForeground(Color.red);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				userfound=true;
				if(!isValidPlan) window.success.setText("Invalid plan choise");
			}
			
		}
		

	}

}
