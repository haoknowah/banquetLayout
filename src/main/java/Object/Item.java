package Object;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import io.MouseHandler;

public class Item extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon img;
	MouseHandler mh = new MouseHandler();
	private double length;
	private double width;
	private double radius;
	public final double scale = 1/35;
	public Item()
	{
		this.width = 0;
	}
	public Item(double lengthft, double lengthin, double widthft, double widthin)
	{
		this.length = lengthft + (lengthin / 12);
		this.width = widthft + (widthin / 12);
	}
	public Item(double radiusft, double radiusin)
	{
		this.radius = radiusft + (radiusin / 12);
	}
	
	public void setLength(double length)
	{
		this.length = length;
	}
	
	public void setWidth(double width)
	{
		this.width = width;
	}
	
	public void setRadius(double radius)
	{
		this.radius = radius;
	}
	
	public double getLength()
	{
		return this.length;
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
	
	public void setImg(BufferedImage img)
	{
		this.img = new ImageIcon(img);
	}
}
