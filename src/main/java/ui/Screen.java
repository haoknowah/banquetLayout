package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Object.Item;
import io.MouseHandler;

public class Screen extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Item> objects;
	private Image img;
	public Screen() throws IOException
	{
		objects = new ArrayList<Item>();
		setPreferredSize(new Dimension(1800, 500));
		BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + "/batman beyond.jpg"));
		Item item = new Item();
		item.setImg(img);
		JLabel lab = new JLabel(item.getImg());
		lab.setSize(500, 300);
		lab.setPreferredSize(new Dimension(500, 300));
		MouseHandler m = new MouseHandler();
		item.addMouseListener(m);
		item.addMouseMotionListener(m);
		lab.addMouseListener(m);
		lab.addMouseMotionListener(m);
		add(lab);
		add(item);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}
	public void addObject(Item item)
	{
		objects.add(item);
	}
	public List<Item> getObjects()
	{
		return objects;
	}
	public void setBackground(BufferedImage img)
	{
		
	}
	public void save()
	{
		
	}
}
