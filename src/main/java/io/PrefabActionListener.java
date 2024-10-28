package io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Object.Prefab;
import ui.Menu;
import ui.PrefabWindow;
import ui.Screen;

public class PrefabActionListener implements ActionListener {

	private Menu menu;
	public PrefabActionListener(Menu menu)
	{
		this.menu = menu;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		Screen screen = menu.getScreen();
		PrefabWindow window;
		switch(s)
		{
			case "New":
				window = new PrefabWindow();
				window.setLocationRelativeTo(null);
				window.setVisible(true);
				break;
			case "Load":
				Prefab prefab = Save.loadPrefab();
				screen.addObject(prefab);
				screen.revalidate();
				screen.repaint();
				break;
			case "Edit Existing":
				window = new PrefabWindow(Save.loadPrefab());
				window.setLocationRelativeTo(null);
				window.setVisible(true);
				break;
		}
	}

}
