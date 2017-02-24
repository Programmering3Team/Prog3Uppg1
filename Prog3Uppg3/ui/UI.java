package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UI extends JFrame {
	
	private final String TITLE = "Programmering 3 uppgift 1";
	private final int WINDOW_WIDTH = 500;
	private final int WINDOW_HEIGHT = 500;
	
	private JButton button;
	private JTextField fieldTicker1, fieldTicker2;
	private JTextField fieldStartDatum, fieldStopDatum;
	
	
	//Initializes the UI
	public UI() {
        setTitle(TITLE); 
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 
        setLocationRelativeTo(null);         
        
        //layout
//        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
//        setLayout(new GridLayout(5,5)); // tre rader, en kolumn
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        
        //Labels
        add(new JLabel("test"));
        add(new JLabel("test"));
        add(new JLabel("test"));
        
        initTextFields();
        initButtons();
        
        setVisible(true); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * Initializes the buttons and adds them to the frame
	 */
	private void initButtons() {
		button = new JButton("Detta är en knapp");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Add event here
			}
		});
		add(button);
	}
	
	/*
	 * Initializes the text fields and adds them to the frame
	 */
	private void initTextFields() {
		fieldTicker1 = new JTextField();
		fieldTicker2 = new JTextField();
		fieldStartDatum = new JTextField();
		fieldStopDatum = new JTextField();
		
		add(fieldTicker1);
		add(fieldTicker2);
		add(fieldStartDatum);
		add(fieldStopDatum);
	}

    
}
