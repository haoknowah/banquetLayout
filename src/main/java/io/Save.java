package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Object.Item;

public class Save {
	private static final Type FILE_TYPE = new TypeToken<Set<Item>>() {}.getType();
	public static Set<Item> getSquare()
	{
		File file = new File(System.getProperty("user.dir") + "/square.json");
		Gson gson = new Gson();
		try
		{
			Reader reader = new FileReader(file);
			Set<Item> square = gson.fromJson(reader, FILE_TYPE);
			if(square == null)
			{
				square = new HashSet<Item>();
			}
			return square;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public static Set<Item> getCircle()
	{
		File file = new File(System.getProperty("user.dir") + "/circle.json");
		Gson gson = new Gson();
		try
		{
			Reader reader = new FileReader(file);
			Set<Item> circle = gson.fromJson(reader, FILE_TYPE);
			if(circle == null)
			{
				circle = new HashSet<Item>();
			}
			return circle;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
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
