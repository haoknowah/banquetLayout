package Object;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7992484780027516800L;
	/**
	 * 
	 */
	private BufferedImage img;
	private double height;
	private double width;
	private double diameter;
	public final double SCALE = 1/35;
	private int type;
	public static final int SQUARE = 1;
	public static final int CIRCLE = 2;
	public static final int PREFAB = 3;
	private String name = "";
	private final Point location = new Point();
	private double degrees = 0;
	public Item()
	{
		this.width = 0;
		this.height = 0;
		this.type = SQUARE;
	}
	public Item(double dheight, double width, int type)
	{
		if(SQUARE == type)
		{
			this.height = dheight;
			this.width = width;
			this.type = SQUARE;
			setImg();
		}
		else if(type == CIRCLE)
		{
			this.diameter = dheight;
			this.type = CIRCLE;
			setImg();
		}
	}
	public Item(double dheight, double width, int type, Point point)
	{
		if(type == SQUARE)
		{
			this.height = dheight;
			this.width = width;
			this.type = SQUARE;
			setImg();
		}
		else if(type == CIRCLE)
		{
			this.diameter = dheight;
			this.type = CIRCLE;
			setImg();
		}
		this.moveToPoint(point);
	}
	public Item(double heightft, double heightin, double widthft, double widthin)
	{
		this.height = heightft * 12 + heightin;
		this.width = widthft * 12 + widthin;
		this.type = SQUARE;
		setImg();
	}
	public Item(double heightft, double heightin, double widthft, double widthin, Point point)
	{
		this.height = heightft * 12 + heightin;
		this.width = widthft * 12 + widthin;
		this.type = SQUARE;
		this.moveToPoint(point);
		setImg();
	}
	public Item(double radiusft, double radiusin)
	{
		this.diameter = radiusft * 12 + radiusin;
		this.type = CIRCLE;
		setImg();
	}
	public Item(double radiusft, double radiusin, Point point)
	{
		this.diameter = radiusft * 12 + radiusin;
		this.type = CIRCLE;
		this.moveToPoint(point);
		setImg();
	}
	public void setHeight(double height)
	{
		this.height = height;
	}
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setWidth(double width)
	{
		this.width = width;
	}
	
	public void setDiameter(double radius)
	{
		this.diameter = radius;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}
	public double getItemHeight()
	{
		return this.height;
	}
	
	public double getItemWidth()
	{
		return this.width;
	}
	
	public double getDiameter()
	{
		return this.diameter;
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
			if(degrees == 0)
			{
				icon = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = icon.createGraphics();
				//System.out.println(this.width + " " + this.height);
				g.setColor(Color.yellow);
				g.fillRect(0, 0, (int) this.width-1, (int) this.height-1);
				g.setColor(Color.BLACK);
				g.drawRect(0, 0, (int) this.width, (int) this.height);
				g.dispose();
			}
			else
			{
	            double sin = Math.abs(Math.sin(Math.toRadians(degrees))), cos = Math.abs(Math.cos(Math.toRadians(degrees)));
	            double newWidth = Math.floor(width * cos + height * sin);
	            double newHeight = Math.floor(height * cos + width * sin);
	            //System.out.println(newWidth + " " + newHeight);
	            icon = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_ARGB);
	            Graphics2D g = icon.createGraphics();
	            AffineTransform afflac = new AffineTransform();
	            afflac.translate((newWidth - width)/2, (newHeight - height)/2);
	            afflac.rotate(Math.toRadians(this.degrees), width/2, height/2);
	            g.setTransform(afflac);
	            g.setColor(Color.yellow);
	            g.fillRect(0, 0, (int) this.width - 1, (int) this.height - 1);
	            g.setColor(Color.black);
	            g.drawRect(0, 0, (int) this.width, (int) this.height);
	            g.dispose();
			}
		}
		else if(type == 2)
		{
			icon = new BufferedImage((int) diameter+5, (int) diameter+5, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = icon.createGraphics();
			g.setComposite(AlphaComposite.Src);
			g.setColor(Color.yellow);
			g.fillOval(0, 0, (int) this.diameter, (int) this.diameter);
			g.setColor(Color.black);
			g.drawOval(0, 0, (int) this.diameter, (int) this.diameter);
			g.dispose();
		}
		else
		{
			icon = null;
		}
		this.img = icon;
	}
	public void removeImage()
	{
		this.img = null;
	}
	public void addDegrees(double degrees)
	{
		this.degrees += degrees;
		if(this.degrees < 0)
		{
			this.degrees = 360 + this.degrees;
		}
		if(this.degrees >= 360)
		{
			this.degrees = this.degrees%360;
		}
		//System.out.println(this.degrees);
	}
	public void setdegrees(double degrees)
	{
		this.degrees = degrees;
	}
	public double getDegrees()
	{
		return this.degrees;
	}
	public void rotateImage()
	{
		double sin = Math.abs(Math.sin(Math.toRadians(degrees))), cos = Math.abs(Math.cos(Math.toRadians(degrees)));
        double newWidth = Math.floor(width * cos + height * sin);
        double newHeight = Math.floor(height * cos + width * sin);
        //System.out.println(newWidth + " " + newHeight);
        BufferedImage icon = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = icon.createGraphics();
        AffineTransform afflac = new AffineTransform();
        afflac.translate((newWidth - width)/2, (newHeight - height)/2);
        afflac.rotate(Math.toRadians(this.degrees), width/2, height/2);
        g.setTransform(afflac);
        g.drawImage(icon, 0, 0, null);
        g.dispose();
        this.img = icon;
	}
}
