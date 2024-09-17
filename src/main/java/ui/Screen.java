package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

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
	private MouseHandler m;
	public Screen()
	{
		objects = new ArrayList<Item>();
		setPreferredSize(new Dimension(1800, 500));
		m = new MouseHandler();
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
		JLabel lab = new JLabel(item.getImg());
		lab.addMouseListener(m);
		lab.addMouseMotionListener(m);
		add(lab);
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
