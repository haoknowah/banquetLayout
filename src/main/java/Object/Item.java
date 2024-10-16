package Object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Item{

	/**
	 * 
	 */
	private BufferedImage img;
	private double height;
	private double width;
	private double radius;
	public final double SCALE = 1/35;
	private int type;
	public static final int SQUARE = 1;
	public static final int CIRCLE = 2;
	private String name = "a";
	private final Point location = new Point();
	public Item()
	{
		this.width = 0;
	}
	public Item(double heightft, double heightin, double widthft, double widthin)
	{
		this.height = heightft * 12 + heightin;
		this.width = widthft * 12 + widthin;
		this.type = SQUARE;
		setImg();
	}
	public Item(double radiusft, double radiusin)
	{
		this.radius = radiusft * 12 + radiusin;
		this.type = CIRCLE;
		setImg();
	}
	
	public void setLength(double length)
	{
		this.height = length;
	}
	public String getName()
	{
		return this.name;
	}
	public void setWidth(double width)
	{
		this.width = width;
	}
	
	public void setRadius(double radius)
	{
		this.radius = radius;
	}
	
	public double getItemHeight()
	{
		return this.height;
	}
	
	public double getItemWidth()
	{
		return this.width;
	}
	
	public double getRadius()
	{
		return this.radius;
	}
	public Point getLocation()
	{
		return this.location;
	}
	public void moveToPoint(Point location)
	{
		this.location.setLocation(location);
	}
	public BufferedImage getImg()
	{
		return img;
	}
	
	public void setImg()
	{
		BufferedImage icon;
		if(type == 1)
		{
			icon = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_RGB);
			Graphics g = icon.createGraphics();
			g.drawRect(0, 0, (int) this.width, (int) this.height);
			g.dispose();
		}
		else if(type == 2)
		{
			icon = new BufferedImage((int) radius, (int) radius, BufferedImage.TYPE_INT_RGB);
			Graphics g = icon.createGraphics();
			g.drawOval(0, 0, (int) this.radius/2, (int) this.radius/2);
			g.dispose();
		}
		else
		{
			icon = null;
		}
		this.img = icon;
	}
}
