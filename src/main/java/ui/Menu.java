package ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;


import Object.Item;
import io.ItemActionListener;
import io.PrefabActionListener;

public class Menu extends JPanel implements ActionListener, ItemListener{

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
	private ItemActionListener itmActList = new ItemActionListener();
	private Set<Item> square = itmActList.getSquare();
	private Set<Item> circle = itmActList.getCircle();
	private JPanel span;
	private JPanel cpan;
	private JTextField name;
	public Menu() throws IOException
	{
		screen = new Screen();
		popups();
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
		menuItem = new JMenuItem("New Item");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Remove Item From List");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		submenu = new JMenu("Square");
		for(Item i : square)
		{
			menuItem = new JMenuItem(i.getName());
			menuItem.addActionListener(itmActList);
			submenu.add(menuItem);
		}
		submenu = new JMenu("Round");
		for(Item i : circle)
		{
			menuItem = new JMenuItem(i.getName());
			menuItem.addActionListener(itmActList);
			submenu.add(menuItem);
		}
		
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
		GridBagConstraints con = new GridBagConstraints();
		Item item;
		switch(s)
		{
			case "New Item":
				createItem();
				break;
			case "New":
				break;
			case "Save":
				break;
			case "Load":
				break;
			case "Create Item":
				item = constructItem();
				break;
			case "Save Item":
				saveNameMenu();
				break;
			case "Remove Item From List":
				removeItem();
				break;
			case "Remove":
				break;
			case "Enter":
				String name = this.name.getText();
				findDup(name);
				break;
			default:
				break;
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		GridBagConstraints con = new GridBagConstraints();
		con.gridy = 1;
		if(e.getStateChange() == ItemEvent.SELECTED)
		{
			if(e.getItem().equals("Square"))
			{
				yub.add(span, con);
				yub.remove(cpan);
				cpan(con);
			}
			else if(e.getItem().equals("Circle"))
			{
				yub.add(cpan, con);
				yub.remove(span);
				span(con);
			}
			else if(e.getItem().equals("Select Item"))
			{
				yub.remove(span);
				yub.remove(cpan);
				span(con);
				cpan(con);
			}
		}
		yub.pack();
	}
	
	public void loadItem(Item item)
	{
		
	}
	public void createItem()
	{
		yub = new JFrame("Item");
		GridBagConstraints con = new GridBagConstraints();
		yub.setLayout(new GridBagLayout());
		con.fill = GridBagConstraints.HORIZONTAL;
		JComboBox<String> options = new JComboBox<>(new String[] {"Select Item", "Square", "Circle"});
		options.addItemListener(this);
		yub.add(options, con);
		yub.pack();
		yub.setVisible(true);
		yub.setLocationRelativeTo(null);
	}
	public void saveNameMenu()
	{
		JFrame nam = new JFrame("Name");
		JLabel label = new JLabel("Name: ");
		name = new JTextField(20);
		JButton button = new JButton("Enter");
		button.addActionListener(this);
		button.addActionListener(c -> {yub.dispose(); nam.dispose();});
		nam.add(label);
		nam.add(name);
		nam.add(button);
		nam.pack();
		nam.setLocationRelativeTo(null);
		nam.setVisible(true);
	}
 	public void popups()
	{
		GridBagConstraints con = new GridBagConstraints();
		span(con);
		cpan(con);
	}
	public void span(GridBagConstraints con)
	{
		span = new JPanel();
		span.setLayout(new GridBagLayout());
		JLabel lenLabel = new JLabel("Length: ");
		JLabel widLabel = new JLabel("Width: ");
		JLabel ft = new JLabel("FT");
		JLabel in = new JLabel("IN");
		JTextField lenft = new JTextField(5);
		JTextField lenin = new JTextField(5);
		JTextField widft = new JTextField(5);
		JTextField widin = new JTextField(5);
		con.gridy = 1;
		span.add(lenLabel, con);
		con.gridx = 1;
		span.add(lenft, con);
		con.gridx = 2;
		span.add(new JLabel("FT"), con);
		con.gridx = 3;
		span.add(lenin, con);
		con.gridx = 4;
		span.add(new JLabel("IN"), con);
		con.gridx = 0;
		con.gridy = 2;
		span.add(widLabel, con);
		con.gridx = 1;
		span.add(widft, con);
		con.gridx = 2;
		span.add(ft, con);
		con.gridx = 3;
		span.add(widin, con);
		con.gridx = 4;
		span.add(in, con);
		con.gridx = 2;
		con.gridy = 3;
		JButton create = new JButton("Create Item");
		create.addActionListener(this);
		create.addActionListener(c -> {yub.dispose();});
		span.add(create, con);
		con.gridx = 3;
		JButton save = new JButton("Save Item");
		save.addActionListener(this);
		span.add(save, con);
	}
	public void cpan(GridBagConstraints con)
	{
		cpan = new JPanel();
		cpan.setLayout(new GridBagLayout());
		JTextField cft = new JTextField(5);
		JTextField cin = new JTextField(5);
		con.gridx = 1;
		con.gridy = 1;
		cpan.add(cft, con);
		con.gridx = 0;
		cpan.add(new JLabel("Radius: "), con);
		con.gridx = 2;
		cpan.add(new JLabel("FT"), con);
		con.gridx = 3;
		cpan.add(cin, con);
		con.gridx = 4;
		cpan.add(new JLabel("IN"), con);
		con.gridx = 2;
		con.gridy = 2;
		JButton create = new JButton("Create Item");
		create.addActionListener(this);
		create.addActionListener(c -> {yub.dispose();});
		JButton save = new JButton("Save Item");
		save.addActionListener(this);
		cpan.add(create, con);
		con.gridx = 3;
		cpan.add(save, con);
	}
	public Item constructItem()
	{
		Item item;
		if(yub.getContentPane().getComponent(1) == span)
		{
			Component[] com = span.getComponents();
			item = new Item(Double.parseDouble(((JTextField) com[1]).getText()), Double.parseDouble(((JTextField) com[3]).getText()),
					Double.parseDouble(((JTextField) com[6]).getText()), Double.parseDouble(((JTextField) com[8]).getText()));
		}
		else if(yub.getContentPane().getComponent(1) == cpan)
		{
			Component[] com = cpan.getComponents();
			item = new Item(Double.parseDouble(((JTextField) com[1]).getText()), Double.parseDouble(((JTextField) com[3]).getText()));
		}
		else
		{
			item = null;
		}
		return item;
	}
	//initialize lists of saved items
	public void updateLists()
	{
		
	}
	public void findDup(String name)
	{
		boolean x = false;
		if(yub.getContentPane().getComponent(1) == span)
		{
			for(Item i : square)
			{
				if(i.getName().equals(name))
				{
					x = true;
				}
			}
			if(x)
			{
				JFrame f = new JFrame();
				JLabel label = new JLabel("Name is already in use.");
				JButton button = new JButton("Close");
				button.addActionListener(c -> {f.dispose();});
				f.add(label);
				f.add(button);
				f.pack();
				f.setVisible(true);
				f.setLocationRelativeTo(null);
				saveNameMenu();
			}
			else
			{
				Component[] com = span.getComponents();
				span.add(new Item(Double.parseDouble(((JTextField) com[1]).getText()), Double.parseDouble(((JTextField) com[3]).getText()),
						Double.parseDouble(((JTextField) com[6]).getText()), Double.parseDouble(((JTextField) com[8]).getText())));
			}
		}
		else if(yub.getContentPane().getComponent(1) == cpan)
		{
			for(Item i : circle)
			{
				if(i.getName().equals(name))
				{
					x = true;
				}
			}
			if(x)
			{
				JFrame f = new JFrame();
				JLabel label = new JLabel("Name is already in use.");
				JButton button = new JButton("Close");
				button.addActionListener(c -> {f.dispose();});
				f.add(label);
				f.add(button);
				f.pack();
				f.setVisible(true);
				f.setLocationRelativeTo(null);
				saveNameMenu();
			}
			else
			{
				Component[] com = cpan.getComponents();
				span.add(new Item(Double.parseDouble(((JTextField) com[1]).getText()), Double.parseDouble(((JTextField) com[3]).getText()),
						Double.parseDouble(((JTextField) com[6]).getText()), Double.parseDouble(((JTextField) com[8]).getText())));
			}
		}
	}
	public void removeItem()
	{
		JFrame f = new JFrame("Remove Item From List");
		JComboBox<String> options = new JComboBox<>(new String[] {"Select Item", "Square", "Circle"});
		options.addItemListener(this);
		//add code to listener to change class variable for list here
		JButton remove = new JButton("Remove");
		remove.addActionListener(this);
		remove.addActionListener(c -> {f.dispose();});
	}
	public void setScreen(Screen screen)
	{
		this.screen = screen;
	}	
	public Screen getScreen()
	{
		return this.screen;
	}
	public JMenuBar getMenuBar()
	{
		return menuBar;
	}
}
