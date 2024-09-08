package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Window() throws IOException
	{
		setDefaultLookAndFeelDecorated(true);
		Menu menu = new Menu();
		Screen screen = menu.getScreen();
		setJMenuBar(menu.getMenuBar());
		add(screen);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
