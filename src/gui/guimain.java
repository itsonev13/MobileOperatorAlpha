package gui;

import Calling.Phone;

public class guimain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Phone.Call(2,1,10);
		Phone.Call(64, 3, 10);
		// Phone.Texting(62,2,1);
		// Phone.Surfing(62,2,50);
		Window win = new Window();
		win.CreateWindow(1500, 1000);

	}

}
