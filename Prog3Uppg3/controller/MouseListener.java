package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import general.Constants;
import ui.Graph;

public class MouseListener implements MouseMotionListener{
	private int x, y;
	private Graph graph;
	
	public MouseListener(Graph graph) {
		this.graph = graph;
		this.x = 0;
		this.y = 0;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
//		System.out.println("musen på " + (e.getX() + ", " + e.getY()));
		if (graph.getStock1() != null) {
			
			double tempIndex = (double) x / (double) Constants.WINDOW_WIDTH * (double) graph.getStock1().getValues().size();
			int index1 = (int) tempIndex;
			graph.setSelectedLine1(index1);
			if (index1 == 0) index1 = 1;
			graph.getLabel1().setText(graph.getStock1().getSingleData(index1));
			
			double tempIndex2 = (double) x / (double) Constants.WINDOW_WIDTH * (double) graph.getStock2().getValues().size();
			int index2 = (int) tempIndex2;
			graph.setSelectedLine2(index2);
			if (index2 == 0) index2 = 1;
			graph.getLabel2().setText(graph.getStock2().getSingleData(index2));
		}
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
