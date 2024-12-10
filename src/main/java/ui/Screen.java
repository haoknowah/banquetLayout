package ui;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Object.Item;
import Object.Prefab;

public class Screen extends JPanel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1232776591596204518L;
	/**
	 * 
	 */
	public File backFile;
	private List<Item> objects;
	private BufferedImage background;
	private Item selectedItem;
	private Point relativeLocation;
	private double scale;
	private PopupMenu popup;
	public Screen()
	{
		enableEvents(
	            AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);
		objects = new ArrayList<Item>();
		setFocusable(true);
		requestFocusInWindow();
		try
		{
			JFileChooser find = new JFileChooser();
			find.setCurrentDirectory(new File(System.getProperty("user.dir")));
			find.showOpenDialog(find);
			backFile = find.getSelectedFile();
			FileInputStream fi = new FileInputStream(backFile);
			ObjectInputStream oi = new ObjectInputStream(fi);
			this.scale = oi.readDouble();
			String backPath = (String) oi.readObject();
			this.background = ImageIO.read(new File(backPath));
			oi.close();
			fi.close();
			this.scale = ((double) Math.max(this.background.getHeight(), this.background.getWidth())) * this.scale;
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
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
	            AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);
		objects = new ArrayList<Item>();
		setFocusable(true);
		requestFocusInWindow();
		try 
		{
			FileInputStream fi = new FileInputStream(backFile);
			ObjectInputStream oi = new ObjectInputStream(fi);
			this.scale = oi.readDouble();
			String backPath = (String) oi.readObject();
			this.background = ImageIO.read(new File(backPath));
			oi.close();
			fi.close();
			this.scale = ((double) Math.max(this.background.getHeight(), this.background.getWidth())) * this.scale;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.backFile = backFile;
		setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));
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
		if(item.getType() == Item.SQUARE)
		{
			item.setWidth(scale * item.getItemWidth());
			item.setHeight(scale * item.getItemHeight());
			item.setImg();
		}
		if(item.getType() == Item.CIRCLE)
		{
			item.setDiameter(scale * item.getDiameter());
			item.setImg();
		}
		if(item.getType() == Item.PREFAB)
		{
			Dimension dim = ((Prefab) item).getSize();
			item.setWidth(scale * dim.width);
			item.setHeight(scale * dim.height);
			((Prefab) item).setImg(scale);
		}
		objects.add(item);
	}

	public void removeObject(Item item)
	{
		objects.remove(item);
		this.revalidate();
		this.repaint();
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
		Point location = event.getPoint();
		if(event.getButton() == MouseEvent.BUTTON1)
		{
			int id = event.getID();
			switch(id)
			{
				case MouseEvent.MOUSE_PRESSED:
					Optional<Item> clicked = itemAtPoint(location);
					if(clicked.isPresent())
					{
						selectedItem = clicked.get();
						System.out.println(selectedItem.getItemWidth());
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
		else if(SwingUtilities.isRightMouseButton(event) && MouseEvent.MOUSE_PRESSED == event.getID())
		{
			popup = new PopupMenu(location, this);
			popup.show(event.getComponent(), event.getX(), event.getY());
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
	public double getScale()
	{
		return this.scale;
	}
	public void setScale(double scale)
	{
		this.scale = scale;
	}
	public void setScale(int scale)
	{
		this.scale = (double) (1/scale);
	}
}
