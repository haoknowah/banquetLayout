package ui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import Object.Item;

public class PopupMenu extends JPopupMenu implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3795596055301573671L;
	private JMenuItem item;
	private Point location;
	private Screen screen;
	public PopupMenu(Point p, Screen screen)
	{
		item = new JMenuItem("Delete");
		item.addActionListener(this);
		location = p;
		this.screen = screen;
		add(item);
		item = new JMenuItem("Copy");
		item.addActionListener(this);
		add(item);
		item = new JMenuItem("Paste");
		item.addActionListener(this);
		add(item);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		Optional<Item> clicked = screen.itemAtPoint(location);
		switch(s)
		{
			case "Delete":
				if(clicked.isPresent())
				{
					Item selectedItem = clicked.get();
					screen.removeObject(selectedItem);
				}
				break;
			case "Copy":
				if(clicked.isPresent())
				{
					Item selectedItem = clicked.get();
					screen.setCopy(selectedItem);
				}
				break;
			case "Paste":
				Item item = null;
				if(screen.getCopy().getType() == Item.SQUARE)
				{
					item = new Item(screen.getCopy().getItemWidth(), screen.getCopy().getItemHeight(), Item.SQUARE, location);
				}
				else if(screen.getCopy().getType() == Item.CIRCLE)
				{
					item = new Item(screen.getCopy().getDiameter(), 0, Item.CIRCLE, location);
				}
				screen.addObject(item);
				break;
			default:
				break;
		}
	}
}
