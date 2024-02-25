package engine;

import gui.GUI;

public class Sat extends Thread
{
	private GUI gui;
	
	public Sat(GUI gui)
	{
		this.gui = gui;
	}
	
	@Override
	public void run() 
	{
		super.run();
		while(true)
		{
			gui.Clock();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
