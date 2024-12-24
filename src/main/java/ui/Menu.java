package ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;

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
import io.Save;

public class Menu extends JPanel implements ActionListener, ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuBar menuBar;
	JMenu menu, submenu, submenu2;
	JMenuItem menuItem;
	private Window window;
	private Screen screen;
	private JFrame yub;
	private PrefabActionListener preActList;
	private ItemActionListener itmActList;
	private List<Item> square;
	private List<Item> circle;
	private JPanel span;
	private JPanel cpan;
	private JTextField name;
	private JComboBox<String> options;
	private boolean isSquare = false;
	private JTextField scale;
	public Menu(Window window) throws IOException
	{
		this.window = window;
		preActList = new PrefabActionListener(this);
		itmActList = new ItemActionListener(this);
		this.square = itmActList.getSquare();
		this.circle = itmActList.getCircle();
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
		menuItem = new JMenuItem("Publish");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("New Room");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Instructions");
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
		submenu.setName("Square");
		for(Item i : square)
		{
			menuItem = new JMenuItem(i.getName());
			menuItem.addActionListener(itmActList);
			submenu.add(menuItem);
		}
		submenu.addMenuListener(itmActList);
		menu.add(submenu);
		submenu2 = new JMenu("Round");
		submenu2.setName("round");
		for(Item i : circle)
		{
			menuItem = new JMenuItem(i.getName());
			menuItem.addActionListener(itmActList);
			submenu2.add(menuItem);
		}
		submenu2.addMenuListener(itmActList);
		menu.add(submenu2);
		
		//3rd menu
		menu = new JMenu("Prefab");
		menuBar.add(menu);
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(preActList);
		menu.add(menuItem);
		menuItem = new JMenuItem("Load");
		menuItem.addActionListener(preActList);
		menu.add(menuItem);
		menuItem = new JMenuItem("Edit Existing");
		menuItem.addActionListener(preActList);
		menu.add(menuItem);
		
	}
	@Override
	public void paintComponent(Graphics g)
	{
		this.screen = this.window.screen;
		this.screen.revalidate();
		this.screen.repaint();
		super.paintComponent(g);
	}
	@Override
	public void paintComponents(Graphics g)
	{
		super.paintComponents(g);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		Item item;
		switch(s)
		{
			case "New Item":
				createItem();
				break;
			case "New":
				if(this.screen != null)
				{
					this.window.removeScreen();
				}
				this.screen = new Screen();
				this.window.addScreen(this.screen);
				this.itmActList.setScreen(this.screen);
				this.window.pack();
				this.window.revalidate();
				this.window.repaint();
				break;
			case "Save":
				Save.saveFile(screen);
				break;
			case "New Room":
				newRoom();
				break;
			case "Create Room":
				Save.newRoom((double) (1 / ((double) Integer.parseInt(scale.getText()) * 12)));
				break;
			case "Instructions":
				Save.getInstructions();
				break;
			case "Load":
				if(this.screen != null)
				{
					this.window.removeScreen();
				}
				this.screen = Save.loadFile();
				this.itmActList.setScreen(this.screen);
				this.window.addScreen(screen);
				this.window.pack();
				this.window.revalidate();
				this.window.repaint();
				break;
			case "Publish":
				Save.publish(screen);
				break;
			case "Create Item":
				item = constructItem();
				screen.addObject(item);
				screen.revalidate();
				screen.repaint();
				break;
			case "Save Item":
				saveNameMenu();
				break;
			case "Remove Item From List":
				removeItem();
				break;
			case "Remove":
				String nam = (String) options.getSelectedItem();
				if(isSquare)
				{
					Item i = (Item) square.stream().filter(x -> x.getName().equals(nam)).toArray()[0];
					square.remove(i);
					Save.updateSquare(square);
					submenu.removeAll();
					for(Item it : square)
					{
						menuItem = new JMenuItem(it.getName());
						menuItem.addActionListener(this);
						submenu.add(menuItem);
					}
				}
				else
				{
					Item i = (Item) circle.stream().filter(x -> x.getName().equals(nam)).toArray()[0];
					circle.remove(i);
					Save.updateCircle(circle);
					submenu2.removeAll();
					for(Item it : circle)
					{
						menuItem = new JMenuItem(it.getName());
						menuItem.addActionListener(this);
						submenu2.add(menuItem);
					}
				}
				break;
			case "Enter":
				String name = this.name.getText();
				boolean newItem = findDup(name);
				if(newItem)
				{
					item = constructItem();
					item.setName(name);
					if(item.getType() == Item.SQUARE)
					{
						this.square.add(item);
						this.square.sort((x, y) -> x.getName().compareTo(y.getName()));
						Save.updateSquare(square);
						menuItem = new JMenuItem(item.getName());
						menuItem.addActionListener(itmActList);
						submenu.add(menuItem);
						itmActList.setSquare(this.square);
					}
					if(item.getType() == Item.CIRCLE)
					{
						this.circle.add(item);
						this.circle.sort((x, y) -> x.getName().compareTo(y.getName()));
						Save.updateCircle(circle);
						menuItem = new JMenuItem(item.getName());
						menuItem.addActionListener(itmActList);
						submenu2.add(menuItem);
						itmActList.setCircle(this.circle);
					}
				}
				break;
			default:
				break;
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		GridBagConstraints con = new GridBagConstraints();
		yub.setLayout(new GridBagLayout());
		con.gridy = 1;
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		options = new JComboBox<String>();
		p.add(options, con);
		JButton remove = new JButton("Remove");
		remove.addActionListener(this);
		remove.addActionListener(c -> {yub.dispose();});
		con.gridy = 2;
		p.add(remove, con);
		yub.pack();
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
			else if(e.getItem().equals("SQUARE"))
			{
				isSquare = true;
				if(yub.getContentPane().getComponentCount() >= 2)
				{
					yub.getContentPane().remove(yub.getContentPane().getComponentCount()-1);
				}
				yub.add(p, con);
				options.removeAllItems();
				for(Item i : square)
				{
					options.addItem(i.getName());
				}
			}
			else if(e.getItem().equals("CIRCLE"))
			{
				isSquare = false;
				if(yub.getContentPane().getComponentCount() >= 2)
				{
					yub.getContentPane().remove(yub.getContentPane().getComponentCount()-1);
				}
				yub.add(p, con);
				options.removeAllItems();
				for(Item i : circle)
				{
					options.addItem(i.getName());
				}
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
	
	public String getSelection()
	{
		return (String) options.getSelectedItem();
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
		nam.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		JLabel label = new JLabel("Name: ");
		name = new JTextField(30);
		JButton button = new JButton("Enter");
		button.addActionListener(this);
		button.addActionListener(c -> {yub.dispose(); nam.dispose();});
		nam.add(label, con);
		con.gridx = 1;
		nam.add(name, con);
		con.gridy = 1;
		con.gridx = 0;
		con.gridwidth = 2;
		nam.add(button, con);
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
		JLabel heiLabel = new JLabel("Height: ");
		JLabel widLabel = new JLabel("Width: ");
		JLabel ft = new JLabel("FT");
		JLabel in = new JLabel("IN");
		JTextField heift = new JTextField(5);
		JTextField heiin = new JTextField(5);
		JTextField widft = new JTextField(5);
		JTextField widin = new JTextField(5);
		con.gridy = 1;
		span.add(heiLabel, con);
		con.gridx = 1;
		span.add(heift, con);
		con.gridx = 2;
		span.add(new JLabel("FT"), con);
		con.gridx = 3;
		span.add(heiin, con);
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
		JTextField circum = new JTextField(5);
		con.gridx = 1;
		con.gridy = 1;
		cpan.add(cft, con);
		con.gridx = 0;
		cpan.add(new JLabel("Diameter: "), con);
		con.gridx = 2;
		cpan.add(new JLabel("FT"), con);
		con.gridx = 3;
		cpan.add(cin, con);
		con.gridx = 4;
		cpan.add(new JLabel("IN"), con);
		con.gridx = 2;
		con.gridy = 2;
		cpan.add(new JLabel("or"));
		con.gridx = 1;
		con.gridy = 3;
		cpan.add(new JLabel("Circumference: "));
		con.gridx = 2;
		cpan.add(circum, con);
		con.gridx = 3;
		cpan.add(new JLabel("FT"));
		con.gridx = 2;
		con.gridy = 4;
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
			if(((JTextField) com[1]).getText().equals(""))
			{
				((JTextField) com[1]).setText("0");
			}
			if(((JTextField) com[3]).getText().equals(""))
			{
				((JTextField) com[3]).setText("0");
			}
			if(((JTextField) com[6]).getText().equals(""))
			{
				((JTextField) com[6]).setText("0");
			}
			if(((JTextField) com[8]).getText().equals(""))
			{
				((JTextField) com[8]).setText("0");
			}
			item = new Item(Double.parseDouble(((JTextField) com[1]).getText()), Double.parseDouble(((JTextField) com[3]).getText()),
					Double.parseDouble(((JTextField) com[6]).getText()), Double.parseDouble(((JTextField) com[8]).getText()));
		}
		else if(yub.getContentPane().getComponent(1) == cpan)
		{
			Component[] com = cpan.getComponents();
			if(((JTextField) com[0]).getText().equals(""))
			{
				((JTextField) com[0]).setText("0");
			}
			if(((JTextField) com[3]).getText().equals(""))
			{
				((JTextField) com[3]).setText("0");
			}
			if(((JTextField) com[7]).getText().equals(""))
			{
				((JTextField) com[7]).setText("0");
			}
			if(!((JTextField) com[7]).getText().equals("0"))
			{
				((JTextField) com[0]).setText(((JTextField) com[5]).getText());
			}
			item = new Item(Double.parseDouble(((JTextField) com[0]).getText()), Double.parseDouble(((JTextField) com[3]).getText()));
		}
		else
		{
			item = null;
		}
		return item;
	}

	public void removeItem()
	{
		yub = new JFrame("Remove Item From List");
		JComboBox<String> options = new JComboBox<>(new String[] {"Select Item", "SQUARE", "CIRCLE"});
		options.addItemListener(this);
		yub.add(options);
		yub.pack();
		yub.setVisible(true);
		yub.setLocationRelativeTo(null);
	}
	
	public boolean findDup(String name)
	{
		boolean x = true;
		if(yub.getContentPane().getComponent(1) == span)
		{
			for(Item i : square)
			{
				if(i.getName().equals(name))
				{
					x = false;
				}
			}
			if(x == false)
			{
				JFrame f = new JFrame();
				f.setLayout(new GridBagLayout());
				GridBagConstraints con = new GridBagConstraints();
				JLabel label = new JLabel("Name is already in use.");
				JButton button = new JButton("Close");
				button.addActionListener(c -> {f.dispose(); saveNameMenu();});
				f.add(label, con);
				con.gridy = 1;
				f.add(button, con);
				f.pack();
				f.setVisible(true);
				f.setLocationRelativeTo(null);
			}
			else
			{
				constructItem();
			}
		}
		else if(yub.getContentPane().getComponent(1) == cpan)
		{
			for(Item i : circle)
			{
				if(i.getName().equals(name))
				{
					x = false;
				}
			}
			if(x == false)
			{
				JFrame f = new JFrame();
				JLabel label = new JLabel("Name is already in use.");
				JButton button = new JButton("Close");
				button.addActionListener(c -> {f.dispose(); saveNameMenu();});
				f.add(label);
				f.add(button);
				f.pack();
				f.setVisible(true);
				f.setLocationRelativeTo(null);
			}
			else
			{
				constructItem();
			}
		}
		return x;
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
	public void newRoom()
	{
		try
		{
			JFrame f = new JFrame();
			f.setLayout(new GridBagLayout());
			GridBagConstraints con = new GridBagConstraints();
			JLabel lab = new JLabel("What is the length of the room's longest side? ");
			scale = new JTextField(5);
			JLabel ft = new JLabel("FT");
			JButton create = new JButton("Create Room");
			f.add(lab, con);
			con.gridx = 1;
			f.add(scale, con);
			con.gridx = 2;
			f.add(ft, con);
			con.gridx = 0;
			con.gridy = 1;
			con.gridwidth = 2;
			create.addActionListener(this);
			create.addActionListener(c -> {f.dispose();});
			f.add(create, con);
			f.pack();
			f.setVisible(true);
			f.setLocationRelativeTo(null);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}