package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import DataBaseConn.Users;
import DataBaseConn.conn;

public class LogInActionListener implements ActionListener {
 Window window;

	LogInActionListener(Window window)
	{
		this.window=window;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String usercheck=window.usertext.getText();
		String passcheck=window.passtext.getText();
		boolean userfound=false;
		List<Users> userlist=conn.GetAllUsers();
		for(Users u:userlist) 
		{
		
		if(u.getName().equals(usercheck)&&u.getPass().equals(passcheck)&&u.isAdmin())
		{
			userfound=true;
			AdminWindow admin = new AdminWindow(this.window);
			admin.createAdminWindow();
			
		}
		if(u.getEmail().equals(usercheck)&&u.getPass().equals(passcheck)&&u.isAdmin())
		{
			userfound=true;
			
			AdminWindow admin = new AdminWindow(this.window);
			admin.createAdminWindow();
			
		}
		
		else if(u.getName().equals(usercheck)&&u.getPass().equals(passcheck)&&!(u.isAdmin()))
		{
			userfound=true;
			
			CustomerWindow customer = new CustomerWindow(this.window);
			customer.createCustomerWindow();
			
		}
		else if(u.getEmail().equals(usercheck)&&u.getPass().equals(passcheck)&&!(u.isAdmin()))
		{
			userfound=true;

			CustomerWindow customer = new CustomerWindow(this.window);
			customer.createCustomerWindow();
			
		}
	
		}
		if(!userfound)
		{
			window.success.setText("Invalid username or password");
			window.success.setForeground(Color.red);
		}
		
		
	}

}
