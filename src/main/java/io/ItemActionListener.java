package io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import Object.Item;

public class ItemActionListener implements ActionListener{

	private Set<Item> square;
	private Set<Item> circle;
	public ItemActionListener()
	{
		square = Save.getSquare();
		circle = Save.getCircle();
	}
	public Set<Item> getSquare() {
		return square;
	}
	public void setSquare(Set<Item> square) {
		this.square = square;
	}
	public Set<Item> getCircle() {
		return circle;
	}
	public void setCircle(Set<Item> circle) {
		this.circle = circle;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
