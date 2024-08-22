package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener, ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	public Menu()
	{
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menuBar.add(menu);
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Load");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		//2nd menu
		menu = new JMenu("Add");
		menuBar.add(menu);
		menuItem = new JMenuItem("Chair");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		submenu = new JMenu("Table");
		menuItem = new JMenuItem("8 foot long");
		menuItem.addActionListener(this);
		submenu.add(menuItem);
		menuItem = new JMenuItem("6 foot round");
		menuItem.addActionListener(this);
		submenu.add(menuItem);
		menu.add(submenu);
		
		//3rd menu
		menu = new JMenu("Prefab");
		menuBar.add(menu);
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Load");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Edit Exisisting");
		menuItem.addActionListener(this);
		menu.add(menuItem);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	public JMenuBar getMenuBar()
	{
		return menuBar;
	}

}
