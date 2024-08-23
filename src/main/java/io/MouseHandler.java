package io;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class MouseHandler extends MouseAdapter{

	private Point offset;

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel label = (JLabel) e.getComponent();
        offset = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getPoint().x - offset.x;
        int y = e.getPoint().y - offset.y;
        Component component = e.getComponent();
        Point location = component.getLocation();
        location.x += x;
        location.y += y;
        component.setLocation(location);
    }
}
