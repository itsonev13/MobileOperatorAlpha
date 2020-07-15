package gui;

import Calling.Phone;

public class guimain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Phone.Call(1,1,10);
		Phone.Texting(1,1,1);
		Phone.Surfing(1,1,50);
		Window win=new Window();
		win.CreateWindow(1500,1000);
		
	}

}
