package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import backend.Stock;
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
	
	private Diagram diagram;
	
	//Initializes the UI
	public UI() {
        setTitle(TITLE); 
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 
        setLocationRelativeTo(null);
        
        //layout
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); //Layout for the frame
//        setLayout(new BorderLayout());
        
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
		String ticker1 = textFields[0].getText();
		String ticker2 = textFields[1].getText();
		String startDate = textFields[2].getText();
		String endDate = textFields[3].getText();
		
		updateSelectedCurrency();
		
//		Stock stock1 = new Stock();
//		Stock stock2 = new Stock();
		
//		stock1.UppdateInfo(ticker1, startDate, endDate, selectedCurrency);
//		stock2.UppdateInfo(ticker2, startDate, endDate, selectedCurrency);
		
//		textArea.setText(stock1.getData(stock2));
		
		diagram.clear();
//		stock1.withCurr();
//		diagram.drawDiagram(stock1.getOnlyValues(Constants.DATE));
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
		diagram = new Diagram();
		diagram.setBackground(Color.CYAN);
		diagram.setMinimumSize(new Dimension(500, 200));
		add(diagram);
	}

}
