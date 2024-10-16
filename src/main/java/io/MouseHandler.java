package io;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;

import Object.Item;
import ui.Screen;

public class MouseHandler extends MouseAdapter{

	private Point offset;
	private Screen screen;

	public MouseHandler()
	{
		
	}
	public MouseHandler(Screen screen)
	{
		this.screen = screen;
	}
    @Override
    public void mousePressed(MouseEvent e) {
        offset = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getPoint().x - offset.x;
        int y = e.getPoint().y - offset.y;
        JLabel component = (JLabel) e.getComponent();
        Point location = component.getLocation();
        location.x += x;
        location.y += y;
        component.setLocation(location);
    }
    @Override
    public void mouseReleased(MouseEvent e)
    {
    	JLabel component = (JLabel) e.getComponent();
    }
    
    public Screen getScreen()
    {
    	return this.screen;
    }
    public void setScreen(Screen screen)
    {
    	this.screen = screen;
    }
}
