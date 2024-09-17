package Object;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import io.MouseHandler;

public class Item extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon img;
	MouseHandler mh = new MouseHandler();
	private double height;
	private double width;
	private double radius;
	public final double SCALE = 1/35;
	private int type;
	public static final int SQUARE = 1;
	public static final int CIRCLE = 2;
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
	public ImageIcon getImg()
	{
		return img;
	}
	
	public void setImg()
	{
		BufferedImage icon;
		if(type == 1)
		{
			icon = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_RGB);
		}
		else if(type == 2)
		{
			icon = new BufferedImage((int) radius, (int) radius, BufferedImage.TYPE_INT_RGB);
		}
		else
		{
			icon = null;
		}
		Graphics2D x = icon.createGraphics();
		x.setStroke(new BasicStroke(1));
		if(type == 1)
		{
			x.drawRect(0, 0, (int) width, (int) height);
		}
		else if(type == 2)
		{
			x.drawOval(0, 0, (int) radius/2, (int) radius/2);
		}
		JFrame f = new JFrame();
		f.pack();
		f.paint(x);
		this.img = new ImageIcon(icon);
	}
}
