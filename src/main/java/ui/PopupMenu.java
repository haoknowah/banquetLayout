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
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		switch(s)
		{
			case "Delete":
				Optional<Item> clicked = screen.itemAtPoint(location);
				if(clicked.isPresent())
				{
					Item selectedItem = clicked.get();
					screen.removeObject(selectedItem);
				}
				break;
			default:
				break;
		}
	}
}
