package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import backend.Stock;
import backend.Stock.InvalidDateExeption;
import general.Constants;

public class UI extends JFrame {
	private final String TITLE = "Aktieanalys";
	private final int WINDOW_WIDTH = 500;
	private final int WINDOW_HEIGHT = 700;
	
	private String selectedCurrency;
	
	private JPanel fieldPanel;
	private JTextField[] textFields;
	private JRadioButton[] radioButtons;
	private JButton button;
	private JTextArea textArea;
	
	private Graph graph;
	
	//Initializes the UI
	public UI() {
        setTitle(TITLE); 
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 
        setLocationRelativeTo(null);
        
        //layout
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); //Layout for the frame
        
        fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        initTextFields();
        initRadioButtons();
        initButtons();
        add(fieldPanel);
        
        initTextArea();
        initGraphPanel();
        
        setVisible(true); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * Initializes the buttons and adds them to the frame
	 */
	private void initButtons() {
		button = new JButton("------- Do Query -------");
		button.setAlignmentX(this.CENTER_ALIGNMENT);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonClicked();
			}
		});
		fieldPanel.add(button);
	}
	
	//Action when "Do Querry" button is clicked
	private void buttonClicked() {
		//If text aren't filled, break
		if (textFieldsAreFilled() == false) {
			textArea.setText("One or more text fields are empty.\nPlease enter the information and try again.");
			return;
		}
		String ticker1 = textFields[0].getText();
		String ticker2 = textFields[1].getText();
		String startDate = textFields[2].getText();
		String endDate = textFields[3].getText();
		updateSelectedCurrency();
		try {
			Stock stock1 = new Stock(ticker1, startDate, endDate, selectedCurrency);
			Stock stock2 = new Stock(ticker2, startDate, endDate, selectedCurrency);
			textArea.setText(stock1.getData(stock2));
			
			graph.clear();
			graph.drawDiagram(stock1.withCurr(), stock1.getOnlyValues(Constants.DATE), Color.RED);
			graph.drawDiagram(stock2.withCurr(), stock2.getOnlyValues(Constants.DATE), Color.GREEN);
		} catch (InvalidDateExeption e) {
			textArea.setText("Invalid date\nEnter the date in the specified format.");
		} catch (IOException e2) {
			textArea.setText("Could not find data.\nTicker entered is invalid or there is no internet connection");
		} catch (IndexOutOfBoundsException e) {
			textArea.setText("Invalid date\nEnter the date in the specified format.");
		} catch (Exception e) {
			textArea.setText(e.getMessage());
		}
	}
	
	private boolean textFieldsAreFilled() {
		for (int i = 0; i < textFields.length; i++) {
			if (textFields[i].getText().equals("")) return false;
		}
		return true;
	}

	/*
	 * Creates a pane, creates and adds radiobuttons to it and creates a button group.
	 * Adds everything to the frame.
	 */

	private void initRadioButtons() {
		JPanel panel = new JPanel();
		ButtonGroup group = new ButtonGroup();
		panel.setLayout(new FlowLayout());
		radioButtons = new JRadioButton[3];
		radioButtons[0] = new JRadioButton("EUR");
		radioButtons[1] = new JRadioButton("USD");
		radioButtons[2] = new JRadioButton("SEK");
		
		for (int i = 0; i < radioButtons.length; i++) {
			panel.add(radioButtons[i]);
			group.add(radioButtons[i]);
		}
		radioButtons[0].setSelected(true);
		selectedCurrency = Constants.EUR;
		fieldPanel.add(panel);
	}
	
	//Updates the selected currency 
	private void updateSelectedCurrency() {
		if (radioButtons[0].isSelected()) selectedCurrency = Constants.EUR;
		if (radioButtons[1].isSelected()) selectedCurrency = Constants.USD;
		if (radioButtons[2].isSelected()) selectedCurrency = Constants.SEK;
	}
	
	/*
	 * Initializes the array of text fields (and labels to the fields) and adds them to the frame
	 */
	private void initTextFields() {
		JLabel[] labels = new JLabel[4]; //Labels for the text fields
		labels[0] = new JLabel("Ticker 1");
		labels[1] = new JLabel("Ticker 2");
		labels[2] = new JLabel("Startdatum dd.mm.yyyy");
		labels[3] = new JLabel("Stopdaturm dd.mm.yyyy");
		
		textFields = new JTextField[4];
		for (int i = 0; i < textFields.length; i++) {
			textFields[i] = new JTextField();
			textFields[i].setMaximumSize(new Dimension(200, 25));
			textFields[i].setAlignmentX(CENTER_ALIGNMENT);
			labels[i].setAlignmentX(CENTER_ALIGNMENT);
			fieldPanel.add(labels[i]);
			fieldPanel.add(textFields[i]);
		}
	}
	
	private void initTextArea() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setAlignmentX(CENTER_ALIGNMENT);
		textArea.setMaximumSize(new Dimension(500, 200));
		
		JScrollPane scroll = new JScrollPane (textArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll);
	}
	
	private void initGraphPanel() {
		graph = new Graph();
		graph.setBackground(Color.BLACK);
		graph.setMinimumSize(new Dimension(500, 200));
		add(graph);
	}

}
