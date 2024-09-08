package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;


import Object.Item;
import io.PrefabActionListener;

public class Menu extends JPanel implements ActionListener, ItemListener, PropertyChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	private Screen screen;
	private JFrame yub;
	private PrefabActionListener preActList = new PrefabActionListener();
	private List<Item> square = new ArrayList<Item>();
	private List<Item> circle = new ArrayList<Item>();
	public Menu() throws IOException
	{
		screen = new Screen();
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
		submenu = new JMenu("Round");
		
		//3rd menu
		menu = new JMenu("Prefab");
		menuBar.add(menu);
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(preActList);
		menu.add(menuItem);
		menuItem = new JMenuItem("Load");
		menuItem.addActionListener(preActList);
		menu.add(menuItem);
		menuItem = new JMenuItem("Edit Exisisting");
		menuItem.addActionListener(preActList);
		menu.add(menuItem);
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		switch(s)
		{
			case "Chair":
				createItem();
				break;
			case "8 foot long":
				Item item = new Item();
				screen.addObject(item);
				break;
			case "6 foot round":
				break;
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	public void createItem()
	{
		yub = new JFrame("Item");
		GridBagConstraints con = new GridBagConstraints();
		yub.setLayout(new GridBagLayout());
		con.fill = GridBagConstraints.HORIZONTAL;
		JComboBox<String> options = new JComboBox<>(new String[] {"Square", "Circle"});
		options.addItemListener(this);
		yub.add(options, con);
		if(options.getSelectedItem().equals("Square"))
		{
			JLabel lenLabel = new JLabel("Length: ");
			JLabel widLabel = new JLabel("Width: ");
			JLabel ft = new JLabel("FT");
			JLabel in = new JLabel("IN");
			JTextField lenft = new JTextField(5);
			JTextField lenin = new JTextField(5);
			JTextField widft = new JTextField(5);
			JTextField widin = new JTextField(5);
			con.gridy = 1;
			yub.add(lenLabel, con);
			con.gridx = 1;
			yub.add(lenft, con);
			con.gridx = 2;
			yub.add(new JLabel("FT"), con);
			con.gridx = 3;
			yub.add(lenin, con);
			con.gridx = 4;
			yub.add(new JLabel("IN"), con);
			con.gridx = 0;
			con.gridy = 2;
			yub.add(widLabel, con);
			con.gridx = 1;
			yub.add(widft, con);
			con.gridx = 2;
			yub.add(ft, con);
			con.gridx = 3;
			yub.add(widin, con);
			con.gridx = 4;
			yub.add(in, con);
		}
		else if(options.getSelectedItem().equals("Circle"))
		{
			JTextField ft = new JTextField(5);
			JTextField in = new JTextField(5);
			con.gridy = 1;
			yub.add(ft, con);
			con.gridx = 1;
			yub.add(new JLabel("Radius: "), con);
			con.gridx = 2;
			yub.add(new JLabel("FT"), con);
			con.gridx = 3;
			yub.add(in, con);
			con.gridx = 4;
			yub.add(new JLabel("IN"), con);
		}
		yub.pack();
		yub.setVisible(true);
		yub.setLocationRelativeTo(null);
	}
	
	public void setScreen(Screen screen)
	{
		this.screen = screen;
	}
	
	public Screen getScreen()
	{
		return this.screen;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	public JMenuBar getMenuBar()
	{
		return menuBar;
	}
	
}
