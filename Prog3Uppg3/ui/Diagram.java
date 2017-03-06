package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Diagram extends JPanel {
	private LinkedList<Line> lines = new LinkedList<Line>();
	private Color color;
	
	public Diagram() {
		lines = new LinkedList<Line>();
	}
	
	public void drawDiagram(ArrayList<Double> stockData) {
		for (int i = 0; i < stockData.size(); i++) {
//			x1. y1. x2. y2
		}
	}

	private void addLine(int x1, int x2, int x3, int x4, Color color) {
	    lines.add(new Line(x1,x2,x3,x4, color));        
	    repaint();
	}

	public void clear() {
	    lines.clear();
	    repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    for (Line line : lines) {
	        g.setColor(line.getColor());
	        g.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
	    }
	}	
}


