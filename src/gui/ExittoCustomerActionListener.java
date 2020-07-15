package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExittoCustomerActionListener implements ActionListener {

	Window window;
	ExittoCustomerActionListener(Window window)
	{
		this.window=window;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		CustomerWindow customerwindow=new CustomerWindow(window);
		customerwindow.createCustomerWindow();
		
	}

}
