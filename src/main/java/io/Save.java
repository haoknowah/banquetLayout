package io;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Object.Item;
import Object.Prefab;
import ui.PrefabScreen;
import ui.Screen;

public class Save{
	public static Set<Item> getSquare()
	{
		try
		{
			File file = new File(System.getProperty("user.dir") + "/square.txt");
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream is = new ObjectInputStream(fi);
			int items = is.readInt();
			Set<Item> square = new HashSet<Item>();
			for(int i = 0; i < items; i++)
			{
				Item object = (Item) is.readObject();
				object.setImg();
				square.add(object);
			}
			is.close();
			fi.close();
			return square;
		}
		catch(OptionalDataException e)
		{
			System.out.println(e.eof);
			e.printStackTrace();
			return null;
		}
		catch(FileNotFoundException e)
		{
			try {
				Writer writer = new FileWriter("square.txt");
				writer.flush();
				writer.close();
				return new HashSet<Item>();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return new HashSet<Item>();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try {
				Writer writer = new FileWriter("square.txt");
				writer.flush();
				writer.close();
				return new HashSet<Item>();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return new HashSet<Item>();
			}
		}
	}
	public static Set<Item> getCircle()
	{
		try
		{
			File file = new File(System.getProperty("user.dir") + "/circle.txt");
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream is = new ObjectInputStream(fi);
			int items = is.readInt();
			Set<Item> circle = new HashSet<Item>();
			for(int i = 0; i < items; i++)
			{
				Item object = (Item) is.readObject();
				object.setImg();
				circle.add(object);
			}
			is.close();
			fi.close();
			return circle;
		}
		catch(OptionalDataException e)
		{
			System.out.println(e.eof);
			e.printStackTrace();
			return null;
		}
		catch(FileNotFoundException e)
		{
			try {
				Writer writer = new FileWriter("circle.txt");
				writer.flush();
				writer.close();
				return new HashSet<Item>();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return new HashSet<Item>();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try {
				Writer writer = new FileWriter("circle.txt");
				writer.flush();
				writer.close();
				return new HashSet<Item>();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return new HashSet<Item>();
			}
		}
	}
	public static void updateSquare(Set<Item> square)
	{
		try
		{
			FileOutputStream fo = new FileOutputStream(System.getProperty("user.dir") + "/square.txt");
			ObjectOutputStream os = new ObjectOutputStream(fo);
			os.writeInt(square.size());
			for(Item i : square)
			{
				i.removeImage();
				os.writeObject(i);
			}
			os.close();
			fo.close();
			for(Item i : square)
			{
				i.setImg();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void updateCircle(Set<Item> circle)
	{
		try
		{
			FileOutputStream fo = new FileOutputStream(System.getProperty("user.dir") + "/circle.txt");
			ObjectOutputStream os = new ObjectOutputStream(fo);
			os.writeInt(circle.size());
			for(Item i : circle)
			{
				i.removeImage();
				os.writeObject(i);
			}
			os.close();
			fo.close();
			for(Item i : circle)
			{
				i.setImg();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void saveFile(Screen screen)
	{
		JFileChooser find = new JFileChooser();
		find.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = find.showSaveDialog(find);
		if(find.getSelectedFile().exists() == false)
		{
			find.approveSelection();
			try {
				Writer write = new FileWriter(find.getSelectedFile().getPath() + ".txt");
				write.flush();
				write.close();
				find.setSelectedFile(new File(find.getSelectedFile().getPath() + ".txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(JFileChooser.APPROVE_OPTION == result)
		{
			File file = find.getSelectedFile();
			try
			{
				//add code for importing background image
				FileOutputStream fo = new FileOutputStream(file);
				ObjectOutputStream os = new ObjectOutputStream(fo);
				os.writeDouble(screen.getScale());
				ByteArrayOutputStream bo = new ByteArrayOutputStream();
				ImageIO.write(screen.getBackgroundImage(), "png", bo);
				byte[] backgroundData = bo.toByteArray();
				os.writeObject(backgroundData);
				os.writeInt(screen.getObjects().size());
				for(Item i : screen.getObjects())
				{
					i.removeImage();
					os.writeObject(i);
				}
				os.close();
				bo.close();
				fo.close();
				for(Item i : screen.getObjects())
				{
					i.setImg();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void saveFile(PrefabScreen screen)
	{
		JFileChooser find = new JFileChooser();
		find.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = find.showSaveDialog(find);
		if(find.getSelectedFile().exists() == false)
		{
			find.approveSelection();
			try {
				Writer write = new FileWriter(find.getSelectedFile().getPath() + ".txt");
				write.flush();
				write.close();
				find.setSelectedFile(new File(find.getSelectedFile().getPath() + ".txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File file = find.getSelectedFile();
		if(JFileChooser.APPROVE_OPTION == result && file.getPath().substring(file.getPath().length()-3).equals("txt"))
		{
			try
			{
				FileOutputStream fo = new FileOutputStream(file);
				ObjectOutputStream os = new ObjectOutputStream(fo);
				os.writeInt(screen.getObjects().size());
				for(Item i : screen.getObjects())
				{
					i.removeImage();
					os.writeObject(i);
				}
				os.close();
				fo.close();
				for(Item i : screen.getObjects())
				{
					i.setImg();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			JFrame f = new JFrame();
			f.add(new JLabel("Invalid selection."));
			f.setVisible(true);
			f.setLocationRelativeTo(null);
		}
	}
	public static Screen loadFile()
	{
		JFileChooser find = new JFileChooser();
		find.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = find.showOpenDialog(find);
		if(find.getSelectedFile().exists() == false)
		{
			find.approveSelection();
			try {
				Writer write = new FileWriter(find.getSelectedFile().getPath() + ".txt");
				write.flush();
				write.close();
				find.setSelectedFile(new File(find.getSelectedFile().getPath() + ".txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(JFileChooser.APPROVE_OPTION == result)
		{
			File file = find.getSelectedFile();
			try
			{
				FileInputStream fi = new FileInputStream(file);
				ObjectInputStream is = new ObjectInputStream(fi);
				double scale = is.readDouble();
				byte[] backgroundData = (byte[]) is.readObject();
				ByteArrayInputStream bi = new ByteArrayInputStream(backgroundData);
				Screen screen = new Screen(scale, bi);
				int items = is.readInt();
				for(int i = 0; i < items; i++)
				{
					Item object = (Item) is.readObject();
					object.setImg();
					screen.addObject(object);
				}
				is.close();
				bi.close();
				fi.close();
				return screen;
			}
			catch(OptionalDataException e)
			{
				System.out.println(e.eof);
				e.printStackTrace();
				return null;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
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
	public static Prefab loadPrefab()
	{
		JFileChooser find = new JFileChooser();
		find.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = find.showOpenDialog(find);
		if(find.getSelectedFile().exists() == false)
		{
			find.approveSelection();
			try {
				Writer write = new FileWriter(find.getSelectedFile().getPath() + ".txt");
				write.flush();
				write.close();
				find.setSelectedFile(new File(find.getSelectedFile().getPath() + ".txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(JFileChooser.APPROVE_OPTION == result)
		{
			File file = find.getSelectedFile();
			try
			{
				FileInputStream fi = new FileInputStream(file);
				ObjectInputStream is = new ObjectInputStream(fi);
				List<Item> objects = new ArrayList<Item>();
				int items = is.readInt();
				for(int i = 0; i < items; i++)
				{
					Item object = (Item) is.readObject();
					object.setImg();
					objects.add(object);
				}
				Prefab prefab = new Prefab(objects);
				is.close();
				fi.close();
				return prefab;
			}
			catch(OptionalDataException e)
			{
				System.out.println(e.eof);
				e.printStackTrace();
				return null;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
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
	public static void publish(Screen screen)
	{
		JFileChooser find = new JFileChooser();
		find.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = find.showSaveDialog(find);
		if(find.getSelectedFile().exists() == false)
		{
			find.approveSelection();
			try {
				Writer write = new FileWriter(find.getSelectedFile().getPath() + ".png");
				write.flush();
				write.close();
				find.setSelectedFile(new File(find.getSelectedFile().getPath() + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(JFileChooser.APPROVE_OPTION == result)
		{
			File file = find.getSelectedFile();
			try
			{
				BufferedImage image = new BufferedImage(screen.getWidth(), screen.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics g = image.createGraphics();
				screen.paint(g);
				ImageIO.write(image, "png", file);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void newRoom(double scale)
	{
		try
		{
			JFileChooser find = new JFileChooser();
			find.setCurrentDirectory(new File(System.getProperty("user.dir")));
			find.setApproveButtonText("Select image of room");
			int result = find.showOpenDialog(find);
			File file = null;
			File room = null;
			if(JFileChooser.APPROVE_OPTION == result)
			{
				file = find.getSelectedFile();
				JFileChooser save = new JFileChooser();
				save.setCurrentDirectory(new File(System.getProperty("user.dir")));
				save.showSaveDialog(save);
				room = save.getSelectedFile();
				room = new File(room.getParent(), room.getName() + ".room");
			}
			FileOutputStream fo = new FileOutputStream(room);
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeDouble(scale);
			ImageIO.write(ImageIO.read(file), "png", bo);
			byte[] data = bo.toByteArray();
			oo.writeObject(data);
			oo.flush();
			oo.close();
			bo.flush();
			bo.close();
			fo.flush();
			fo.close();
		}
		catch(NullPointerException e)
		{
			JFrame f = new JFrame();
			f.setLayout(new GridBagLayout());
			GridBagConstraints con = new GridBagConstraints();
			JLabel label = new JLabel("Invalid selection.");
			JButton button = new JButton("Close");
			button.addActionListener(c -> {f.dispose();});
			f.add(label, con);
			con.gridy = 1;
			f.add(button, con);
			f.pack();
			f.setVisible(true);
			f.setLocationRelativeTo(null);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
