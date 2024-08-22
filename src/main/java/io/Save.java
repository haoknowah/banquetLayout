package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.svg.SVGDocument;

public class Save {
	public static void saveFile()
	{
		JFileChooser find = new JFileChooser();
		find.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = find.showSaveDialog(find);
		if(find.getSelectedFile().exists() == false)
		{
			find.approveSelection();
			try {
				Writer write = new FileWriter(find.getSelectedFile().getPath() + ".svg");
				write.flush();
				write.close();
				find.setSelectedFile(new File(find.getSelectedFile().getPath() + ".svg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(JFileChooser.APPROVE_OPTION == result)
		{
			File file = find.getSelectedFile();
			//implement save funtionality
		}
	}
	public static File loadFile()
	{
		JFileChooser find = new JFileChooser();
		find.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = find.showOpenDialog(find);
		if(find.getSelectedFile().exists() == false)
		{
			find.approveSelection();
			try {
				Writer write = new FileWriter(find.getSelectedFile().getPath() + ".svg");
				write.flush();
				write.close();
				find.setSelectedFile(new File(find.getSelectedFile().getPath() + ".svg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(JFileChooser.APPROVE_OPTION == result)
		{
			File file = find.getSelectedFile();
			return file;
		}
		else
		{
			JFrame f = new JFrame("A");
			JLabel war = new JLabel("File was not selected or could not be loaded.");
			f.add(war);
			f.pack();
			f.setLocationRelativeTo(null);
			f.setVisible(true);
			return null;
		}
	}
}
