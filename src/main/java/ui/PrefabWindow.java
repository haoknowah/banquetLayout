package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PrefabWindow extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Screen screen;
	public PrefabWindow()
	{
		JMenuBar menuBar = new JMenuBar();
		JMenuItem menuItem = new JMenuItem("Save");
		menuBar.add(menuItem);
		setJMenuBar(menuBar);
		this.screen = new Screen();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
