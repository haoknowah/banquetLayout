package io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Object.Item;
import ui.Menu;
import ui.Screen;

public class ItemActionListener implements ActionListener, MenuListener{

	private List<Item> square;
	private List<Item> circle;
	private boolean isSquare = false;
	private boolean isRound = false;
	private Screen screen;
	public ItemActionListener(Menu menu)
	{
		square = Save.getSquare();
		square.sort((x, y) -> x.getName().compareTo(y.getName()));
		circle = Save.getCircle();
		circle.sort((x, y) -> x.getName().compareTo(y.getName()));
	}
	public void setScreen(Screen screen)
	{
		this.screen = screen;
	}
	public List<Item> getSquare() {
		return square;
	}
	public void setSquare(List<Item> square) {
		this.square = square;
	}
	public List<Item> getCircle() {
		return circle;
	}
	public void setCircle(List<Item> circle) {
		this.circle = circle;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Item item = null;
		if(isSquare)
		{
			item = (Item) square.stream().filter(x -> x.getName().equalsIgnoreCase(e.getActionCommand())).toArray()[0];
			item = new Item(item.getItemHeight(), item.getItemWidth(), true);
		}
		else if(isRound)
		{
			item = (Item) circle.stream().filter(x -> x.getName().equalsIgnoreCase(e.getActionCommand())).toArray()[0];
			item = new Item(item.getDiameter(), 0 , false);
		}
		screen.addObject(item);
		screen.revalidate();
		screen.repaint();
	}
	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		if(((JMenu) e.getSource()).getName().equalsIgnoreCase("square"))
		{
			isSquare = true;
			isRound = false;
		}
		else if(((JMenu) e.getSource()).getName().equalsIgnoreCase("round"))
		{
			isRound = true;
			isSquare = false;
		}
	}
	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

}
