package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class Window extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Menu menu;
	public Screen screen;
	public Window() throws IOException
	{
		this.setBackground(Color.white);
		setDefaultLookAndFeelDecorated(true);
		menu = new Menu(this);
		JMenuBar menuBar = menu.getMenuBar();
		setJMenuBar(menuBar);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void addScreen(Screen screen)
	{
		this.screen = screen;
		add(screen);
		pack();
	}
	public void removeScreen()
	{
		remove(screen);
		pack();
	}
	@Override
	public void paintComponents(Graphics g)
	{
		this.screen = this.menu.getScreen();
		super.paintComponents(g);
	}
}
