package Object;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Prefab extends Item{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4573429647747280116L;
	private List<Item> objects;
	private BufferedImage img;
	private int width = 10;
	private int height = 10;
	private Point origin = new Point();
	public Prefab()
	{
		objects = new ArrayList<Item>();
		this.setType(PREFAB);
	}
	public Prefab(List<Item> objects)
	{
		this.objects = objects;
		setSize(objects);
		setImg();
		this.setType(PREFAB);
	}
	public void addObject(Item object)
	{
		objects.add(object);
		setSize(this.objects);
		setImg();
	}
	public List<Item> getObjects()
	{
		return this.objects;
	}
	@Override
	public void setImg()
	{
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.createGraphics();
		for(Item i : objects)
		{
			i.setImg();
			BufferedImage icon = i.getImg();
			g.drawImage(icon, i.getLocation().x - origin.x, i.getLocation().y - origin.y, null);
		}
		g.dispose();
		this.img = img;
	}
	public void setImg(double scale)
	{
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.createGraphics();
		for(Item i : objects)
		{
			i.setWidth(scale * i.getItemWidth());
			i.setHeight(scale * i.getItemHeight());
			i.setImg();
			BufferedImage icon = i.getImg();
			g.drawImage(icon, (int) ((i.getLocation().x - origin.x) * scale), (int) ((i.getLocation().y - origin.y) * scale), null);
		}
		g.dispose();
		this.img = img;
	}
	@Override
	public BufferedImage getImg()
	{
		return this.img;
	}
	public void setSize(List<Item> objects)
	{
		int lx = objects.get(0).getLocation().x;
		int uy = objects.get(0).getLocation().y;
		int rx = 0;
		int dy = 0;
		for(Item i : objects)
		{
			BufferedImage icon = i.getImg();
			if(lx > i.getLocation().x)
			{
				lx = i.getLocation().x;
			}
			if(uy > i.getLocation().y)
			{
				uy = i.getLocation().y;
			}
			if(rx < i.getLocation().x + icon.getWidth())
			{
				rx = i.getLocation().x + icon.getWidth();
			}
			if(dy < i.getLocation().y + icon.getHeight())
			{
				dy = i.getLocation().y + icon.getHeight();
			}
		}
		this.width = rx - lx;
		this.height = dy - uy;
		this.origin.x = lx;
		this.origin.y = uy;
	}
	public Dimension getSize()
	{
		return new Dimension(this.width, this.height);
	}
}
