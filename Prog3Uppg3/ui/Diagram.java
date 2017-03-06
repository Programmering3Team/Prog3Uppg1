package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Diagram extends JPanel {
	private LinkedList<Line> lines = new LinkedList<Line>();
	private Color color;
	
	public Diagram() {
		lines = new LinkedList<Line>();
		color = Color.RED;
	}
	
	public void drawDiagram(ArrayList<Double> stockValues, ArrayList<String> stockDates) {
		int minValue = (int) (Collections.min(stockValues) * 1);
		int maxValue = (int) (Collections.max(stockValues) * 1);
		float heightMultiplier = this.getHeight() / (maxValue - minValue);
		float withMultiplier = this.getWidth() / stockValues.size();
		
		int x1 = 0;
		int y1 = (int) (stockValues.get(0) * heightMultiplier);
		for (int i = 1; i <= stockValues.size(); i++) {
			int x2 = (int) (i * withMultiplier);
			int y2 = (int) (stockValues.get(i) * heightMultiplier);
			
			addLine(x1, y1, x2, y2, color);
			x1 = x2;
			y1 = y2;
		}
	}

	private void addLine(int x1, int y1, int x2, int y2, Color color) {
	    lines.add(new Line(x1,y1,x2,y2, color));        
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


