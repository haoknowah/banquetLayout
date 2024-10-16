package banquetLayout;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Tester {
	public Tester()
	{
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Window win = new Window();
				win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
	public class Window extends JFrame
	{
		private Menu menu;
		public Window()
		{
			setDefaultLookAndFeelDecorated(true);
			menu = new Menu(this);
			JMenuBar menuBar = menu.getMenuBar();
			setJMenuBar(menuBar);
			add(menu.screen);
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		}
	}
	public class MouseHandler extends MouseAdapter{

		private Point offset;
		public MouseHandler()
		{
			
		}
		
	    @Override
	    public void mousePressed(MouseEvent e) {
	        offset = e.getPoint();
	    }

	    @Override
	    public void mouseDragged(MouseEvent e) {
	        int x = e.getPoint().x - offset.x;
	        int y = e.getPoint().y - offset.y;
	        JLabel component = (JLabel) e.getComponent();
	        Point location = component.getLocation();
	        location.x += x;
	        location.y += y;
	        component.setLocation(location);
	    }
	    
	   
	}
	public class Screen extends JPanel
	{
		private MouseHandler m;
		public Screen()
		{
			setPreferredSize(new Dimension(700, 500));
			m = new MouseHandler();
		}
		public void addObject()
		{
			BufferedImage move = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
			Graphics2D x = move.createGraphics();
			x.setStroke(new BasicStroke(1));
			x.drawRect(0, 0, 20, 20);
			JFrame f = new JFrame();
			f.pack();
			f.paint(x);
			JLabel lab = new JLabel(new ImageIcon(move));
			lab.addMouseListener(m);
			lab.addMouseMotionListener(m);
			add(lab);
		}
	}
	public class Menu extends JPanel implements ActionListener
	{
		private Window window;
		private JMenuBar menuBar;
		private JMenu menu;
		private JMenuItem menuItem;
		public Screen screen = new Screen();
		public Menu(Window window)
		{
			this.window = window;
			menuBar = new JMenuBar();
			menu = new JMenu("1");
			menuItem = new JMenuItem("2"); 
			menuBar.add(menu);
			menu.add(menuItem);
			menuItem.addActionListener(this);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			screen.addObject();
			screen.revalidate();
			screen.repaint();
		}
		public JMenuBar getMenuBar()
		{
			return this.menuBar;
		}
		
	}
	public class Item
	{
		private final Point loc = new Point();
		public Item()
		{
			
		}
		public Point getLocation()
		{
			return this.loc;
		}
		public void moveToPoint(Point loc)
		{
			this.loc.setLocation(loc);
		}
	}
}
