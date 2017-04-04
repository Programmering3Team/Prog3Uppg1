package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JPanel;

import backend.Stock;
import controller.MouseListener;

@SuppressWarnings("serial")
public class Graph extends JPanel {
	private LinkedList<Line> lines = new LinkedList<Line>();
	private Color color;
	private MouseListener mouseListener;
	private Stock stock1, stock2;
	
	public Graph() {
		lines = new LinkedList<Line>();
		color = Color.RED;
		
		//Mouse listener
		mouseListener = new MouseListener(this);
		addMouseMotionListener(mouseListener);
	}
	
	public void drawDiagram(ArrayList<Double> stockValues, ArrayList<String> stockDates, Color color) {
		double minValue = Collections.min(stockValues);
		double maxValue =  Collections.max(stockValues); 
		double valueRange = maxValue - minValue;
		double scaleRange = this.getHeight();
		
		double widthMultiplier = ((this.getWidth()*1.06f) / stockValues.size());
	
		int x1 = 0;
		int y1 = this.getHeight() - (getPosition(stockValues.get(0), scaleRange, valueRange, maxValue));
		
		for (int i = 1; i < stockValues.size(); i++) {
			int x2 = (int) (i * widthMultiplier);
			int y2 = this.getHeight() - (getPosition(stockValues.get(i), scaleRange, valueRange, maxValue));

			addLine(x1, y1, x2, y2, color);
			x1 = x2;
			y1 = y2;
		}
	}

	private int getPosition(double value, double scaleRange, double valueRange, double maxValue) {
		double temp = (maxValue - value) / valueRange;
		int y = (int) (temp * scaleRange);
		return y;
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
	
	public void setStock1(Stock stock) {
		this.stock1 = stock;
	}
	
	public void setStock2(Stock stock) {
		this.stock2 = stock;
	}
	
	public Stock getStock1() {
		return stock1;
	}
	
	public Stock getStock2() {
		return stock2;
	}
}


