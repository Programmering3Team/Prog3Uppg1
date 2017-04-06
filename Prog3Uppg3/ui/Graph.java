package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.Stock;
import controller.MouseListener;

@SuppressWarnings("serial")
public class Graph extends JPanel {
	private ArrayList<Line> lines1;
	private ArrayList<Line> lines2;
	private Line selectedLine1, selectedLine2;
	private Color color1, color2, selectedColor1, selectedColor2;
	private MouseListener mouseListener;
	private Stock stock1, stock2;
	private JLabel label1, label2;
	
	public Graph() {
		lines1 = new ArrayList<Line>();
		lines2 = new ArrayList<Line>();
		color1 = Color.RED.darker();
		color2 = Color.GREEN.darker();
		selectedColor1 = Color.ORANGE;
		selectedColor2 = Color.WHITE;
		initFonts();
		//Mouse listener
		mouseListener = new MouseListener(this);
		addMouseMotionListener(mouseListener);
	}
	
	private void initFonts() {
	    label1 = new JLabel("");
	    label2 = new JLabel("");
	    label1.setFont(new Font("Arial",1,10));
	    label2.setFont(new Font("Arial", 1, 10));
	    label1.setForeground(color1);
	    label2.setForeground(color2);
	    add(label1);
	    add(label2);
	}

	public void drawDiagram(ArrayList<Double> stockValues, ArrayList<String> stockDates, Color color, int diagramNumber) {
		double minValue = Collections.min(stockValues);
		double maxValue =  Collections.max(stockValues); 
		double valueRange = maxValue - minValue;
		double scaleRange = this.getHeight();
		
		double widthMultiplier = ((this.getWidth()*1.06f) / stockValues.size());
	
		int x1 = 0;
//		int y1 = this.getHeight() - (getPosition(stockValues.get(0), scaleRange, valueRange, maxValue));
		int y1 = getPosition(stockValues.get(0), scaleRange, valueRange, maxValue);
		
		for (int i = 1; i < stockValues.size(); i++) {
			int x2 = (int) (i * widthMultiplier);
//			int y2 = this.getHeight() - (getPosition(stockValues.get(i), scaleRange, valueRange, maxValue));
			int y2 = getPosition(stockValues.get(i), scaleRange, valueRange, maxValue);
			
			if (diagramNumber == 1) {
				addLine1(x1, y1, x2, y2, color);
			} else {
				addLine2(x1, y1, x2, y2, color);
			}
			x1 = x2;
			y1 = y2;
		}
	}

	private int getPosition(double value, double scaleRange, double valueRange, double maxValue) {
		double temp = (maxValue - value) / valueRange;
		int y = (int) (temp * scaleRange);
		return y;
	}

	private void addLine1(int x1, int y1, int x2, int y2, Color color) {
	    lines1.add(new Line(x1,y1,x2,y2, color));        
	    repaint();
	}
	private void addLine2(int x1, int y1, int x2, int y2, Color color) {
	    lines2.add(new Line(x1,y1,x2,y2, color));        
	    repaint();
	}
	
	public void setSelectedLine1(int lineIndex) {
		selectedLine1 = lines1.get(lineIndex);
		repaint();
	}
	
	public void setSelectedLine2(int lineIndex) {
		selectedLine2 = lines2.get(lineIndex);
		repaint();
	}

	public void clear() {
	    lines1.clear();
	    lines2.clear();
	    repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    for (Line line : lines1) {
	    	if (line == selectedLine1) {
		        g.setColor(selectedColor1);
	    	} else {
		        g.setColor(color1);
	    	}
	        g.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
	    }
	    for (Line line : lines2) {
	    	if (line == selectedLine2) {
		        g.setColor(selectedColor2);
	    	} else {
		        g.setColor(color2);
	    	}
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
	
	public JLabel getLabel1() {
		return label1;
	}
	
	public JLabel getLabel2() {
		return label2;
	}
}


