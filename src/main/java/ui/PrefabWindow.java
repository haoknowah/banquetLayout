package ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Object.Item;
import Object.Prefab;
import io.Save;

public class PrefabWindow extends JFrame implements ActionListener, ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrefabScreen screen;
	private JFrame yub;
	private JPanel span;
	private JPanel cpan;
	public PrefabWindow()
	{
		JMenuBar menuBar = new JMenuBar();
		popups();
		JMenuItem menuItem = new JMenuItem("Add");
		menuItem.addActionListener(this);
		menuBar.add(menuItem);
		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menuBar.add(menuItem);
		setJMenuBar(menuBar);
		this.screen = new PrefabScreen();
		add(this.screen);
		pack();
	}
	public PrefabWindow(Prefab prefab)
	{
		JMenuBar menuBar = new JMenuBar();
		popups();
		JMenuItem menuItem = new JMenuItem("Add");
		menuItem.addActionListener(this);
		menuBar.add(menuItem);
		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menuBar.add(menuItem);
		setJMenuBar(menuBar);
		this.screen = new PrefabScreen(prefab);
		add(this.screen);
		pack();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		Item item;
		switch(s)
		{
		case "Add":
			createItem();
			break;
		case "Save":
			Save.saveFile(screen);
			break;
		case "Create Item":
			item = constructItem();
			screen.addObject(item);
			screen.revalidate();
			screen.repaint();
			break;
		}
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
	@Override
	public void itemStateChanged(ItemEvent e) {
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
			item = new Item(Double.parseDouble(((JTextField) com[0]).getText()), Double.parseDouble(((JTextField) com[3]).getText()));
		}
		else
		{
			item = null;
		}
		return item;
	}
}
