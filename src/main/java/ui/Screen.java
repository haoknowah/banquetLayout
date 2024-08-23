package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
	public Screen() throws IOException
	{
		setPreferredSize(new Dimension(1800, 500));
		BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + "/batman beyond.jpg"));
		Item item = new Item();
		item.setImg(img);
		JLabel lab = new JLabel(item.getImg());
		lab.setSize(500, 300);
		lab.setPreferredSize(new Dimension(500, 300));
		MouseHandler m = new MouseHandler();
		lab.addMouseListener(m);
		lab.addMouseMotionListener(m);
		add(lab);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
