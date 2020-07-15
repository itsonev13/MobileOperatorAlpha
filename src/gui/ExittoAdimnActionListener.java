package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExittoAdimnActionListener implements ActionListener {

	private Window window;

	public ExittoAdimnActionListener(Window window) {
		// TODO Auto-generated constructor stub
		this.window=window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AdminWindow adminwindow=new AdminWindow(window);
		adminwindow.createAdminWindow();;
	}

}
