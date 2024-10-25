package io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.PrefabWindow;

public class PrefabActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		switch(s)
		{
			case "New":
				PrefabWindow window = new PrefabWindow();
				window.setLocationRelativeTo(null);
				window.setVisible(true);
				break;
			case "Load":
				break;
			case "Edit Existing":
				break;
		}
	}

}
