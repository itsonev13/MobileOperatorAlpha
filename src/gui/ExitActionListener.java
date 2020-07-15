package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitActionListener implements ActionListener{
	
	Window window;
	ExitActionListener(Window window)
	{
		this.window=window;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		window.CreateWindow(1500,1000);
		
	}

}
