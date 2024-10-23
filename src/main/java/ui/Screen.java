package ui;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Object.Item;

public class Screen extends JPanel implements ActionListener, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1232776591596204518L;
	/**
	 * 
	 */
	public File backFile = new File("commodore.png");
	private List<Item> objects;
	private BufferedImage background;
	private Item selectedItem;
	private Point relativeLocation;
	public Screen()
	{
		enableEvents(
	            AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
		objects = new ArrayList<Item>();
		try
		{
			this.background = ImageIO.read(new File("commodore.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));
	}
	public Screen(File backFile)
	{
		enableEvents(
	            AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
		objects = new ArrayList<Item>();
		try {
			this.background = ImageIO.read(backFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.backFile = backFile;
		try
		{
			this.background = ImageIO.read(new File("commodore.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
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
	public List<Item> getObjects()
	{
		return objects;
	}
	public void setBackground(BufferedImage img)
	{
		this.paintComponent(img.createGraphics());
	}
	public BufferedImage getBackgroundImage()
	{
		return this.background;
	}
	public void save()
	{
		
	}
}
