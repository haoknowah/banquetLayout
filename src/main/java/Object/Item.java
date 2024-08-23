package Object;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Item extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon img;
	public Item()
	{
		
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
