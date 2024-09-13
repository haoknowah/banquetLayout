package banquetLayout;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ui.Window;

public class Meow {
	public static void main(String[] args) throws IOException
	{
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Window win;
				try {
					win = new Window();
					win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
}
