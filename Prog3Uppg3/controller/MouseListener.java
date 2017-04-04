package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

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
			
			System.out.println(graph.getStock1().getSingleData(x));
			
		}
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
