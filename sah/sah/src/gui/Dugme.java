package gui;

import java.awt.Dimension;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Dugme extends JButton
{
	int i,j;
	public Dugme(int i, int j)
	{
		this.i = i;
		this.j = j;
		setPreferredSize(new Dimension(90, 90));
	}
}
