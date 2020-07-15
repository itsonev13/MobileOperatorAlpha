package gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DataBaseConn.Users;
import DataBaseConn.conn;

public class RegistratedActionListener  implements ActionListener 
{
	Window window;
	RegisterActionListener registerwin;
	RegistratedActionListener(Window window, RegisterActionListener registerwin)
	{
		this.window=window;
		this.registerwin=registerwin;
	}

		
		@Override
	public void actionPerformed(ActionEvent e) 
	{
			boolean isValidpass=false,isValidMail=false,isValidPhone=false,isValidUsernae=false, isValidPlan=false;
			final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
			final Pattern VALID_PHONE_REGEX=Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
			List<Users> userlist=conn.GetAllUsers();
			
		//plan check
			if(registerwin.choosenplan.getText().equalsIgnoreCase("smart")) 
			{
				isValidPlan=true;
			}
			else if(registerwin.choosenplan.getText().equalsIgnoreCase("icon")) 
			{
				isValidPlan=true;
			}
			else if(registerwin.choosenplan.getText().equalsIgnoreCase("infinity")) 
			{
				isValidPlan=true;
			}
			else 
			{
				window.success.setText("Invalid plan choise");
				window.success.setForeground(Color.RED);
			}
		//Username check
		if(registerwin.usernametext.getText().length()<5)
		{
			window.success.setText("Username is less than 5 characters");
			window.success.setForeground(Color.RED);
		}
		else isValidUsernae=true;
		//Password check
		if(window.passtext.getPassword().length<5)
		{
			window.success.setText("Password is less than 5 characters");
			window.success.setForeground(Color.RED);
		}
		//Password matching
		if(!(window.passtext.getText().equals(registerwin.reppass.getText())))
		{
			window.success.setText("Passwords don't match");
			window.success.setForeground(Color.RED);
		}
		else isValidpass=true;
		//email check
		Matcher emailmatcher = VALID_EMAIL_ADDRESS_REGEX.matcher(window.usertext.getText());
		if(!(emailmatcher.find()))
		{
			window.success.setText("Invalid Email");
			window.success.setForeground(Color.RED);
			
		}
		else isValidMail=true;
		Matcher phonematcher = VALID_PHONE_REGEX.matcher(registerwin.phonetext.getText());
		if(!(phonematcher.find()))
		{
			window.success.setText("Invalid phone number");
			window.success.setForeground(Color.RED);
		}
		else isValidPhone=true;
		//Valid
			if((isValidpass)&&(isValidMail)&&(isValidPhone)&&(isValidUsernae)&&(isValidPlan))
			{ 
				for(Users u:userlist) 
				{
					if(!(u.getName().equals(registerwin.usernametext.getText())))
					{
						
						try {
							window.success.setText("You have successfully created your account");
							window.success.setForeground(Color.green);
							conn.InserIntoUsers(registerwin.usernametext.getText(),window.usertext.getText(), registerwin.phonetext.getText(),window.passtext.getText(),false);
							conn.InserIntousers_plan(registerwin.usernametext.getText(),registerwin.choosenplan.getText());
							break;
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
						
							e1.printStackTrace();
						}
					}
					else
					{
						window.success.setText("Username already exists");
						window.success.setForeground(Color.red);
						break;
					}
					
				}
				
			}
			
			
	}

	

}
