package ui;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JPanel;

import Object.Item;
import Object.Prefab;

public class PrefabScreen extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -266989429048868947L;
	private Item selectedItem;
	private Point relativeLocation;
	private List<Item> objects;

	public PrefabScreen()
	{
		enableEvents(
	            AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
		objects = new ArrayList<Item>();
		setPreferredSize(new Dimension(500, 500));
	}
	public PrefabScreen(Prefab prefab)
	{
		enableEvents(
	            AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
		objects = prefab.getObjects();
		setPreferredSize(new Dimension(500, 500));
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(Item i : objects)
		{
			BufferedImage img = i.getImg();
			g.drawImage(img, i.getLocation().x, i.getLocation().y, this);
		}
	}
	public void addObject(Item item)
	{
		objects.add(item);
	}
	public Optional<Item> itemAtPoint(Point point)
	{
		for(Item i : objects)
		{
			Point location = i.getLocation();
			BufferedImage icon = i.getImg();
            int width = icon.getWidth();
            int height = icon.getHeight();
            if (point.x >= location.x && point.x < location.x + width &&
                point.y >= location.y && point.y < location.y + height) {

                return Optional.of(i);
            }
		}
		return Optional.empty();
	}
	@Override
	protected void processMouseEvent(MouseEvent event)
	{
		if(event.getButton() == MouseEvent.BUTTON1)
		{
			int id = event.getID();
			switch(id)
			{
				case MouseEvent.MOUSE_PRESSED:
					Point location = event.getPoint();
					Optional<Item> clicked = itemAtPoint(location);
					if(clicked.isPresent())
					{
						selectedItem = clicked.get();
						Point loc = selectedItem.getLocation();
						relativeLocation = new Point(location.x - loc.x, location.y - loc.y);
					}
					event.consume();
					break;
				case MouseEvent.MOUSE_RELEASED:
					selectedItem = null;
					relativeLocation = null;
					break;
				default:
					break;
			}
		}
		super.processMouseEvent(event);
	}
	@Override
	protected void processMouseMotionEvent(MouseEvent event)
	{
		@SuppressWarnings("deprecation")
		int mods = event.getModifiers();
		if(event.getID() == MouseEvent.MOUSE_DRAGGED &&
	            (mods | MouseEvent.BUTTON1_DOWN_MASK) != 0 &&
	            selectedItem != null)
		{
			Point newLocation = event.getPoint();
			newLocation.x -= relativeLocation.x;
			newLocation.y -= relativeLocation.y;
			selectedItem.moveToPoint(newLocation);
			repaint();
			event.consume();
		}
		super.processMouseMotionEvent(event);
	}
	public Item getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Item selectedItem) {
		this.selectedItem = selectedItem;
	}

	public Point getRelativeLocation() {
		return relativeLocation;
	}

	public void setRelativeLocation(Point relativeLocation) {
		this.relativeLocation = relativeLocation;
	}

	public List<Item> getObjects() {
		return objects;
	}

	public void setObjects(List<Item> objects) {
		this.objects = objects;
	}
}
