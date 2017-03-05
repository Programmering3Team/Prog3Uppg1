package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import backend.Stock;

public class UI extends JFrame {
	private final String TITLE = "Aktieanalys";
	private final int WINDOW_WIDTH = 500;
	private final int WINDOW_HEIGHT = 500;
	
	private final String EUR = "EUR", SEK = "SEK", USD = "USD";
	private String selectedCurrency;
	
	private JTextField[] textFields;
	private JRadioButton[] radioButtons;
	private JButton button;
	private JTextArea textArea;
	
	//Initializes the UI
	public UI() {
        setTitle(TITLE); 
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 
        setLocationRelativeTo(null);
        
        //layout
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        
        initTextFields();
        initRadioButtons();
        initButtons();
        initTextArea();
//        initGraphPanel();
        
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
				String ticker = textFields[0].getText();
				String startDate = textFields[2].getText();
				String endDate = textFields[3].getText();
				
				updateSelectedCurrency();
				
//				Stock stock = new Stock();
//				stock.UppdateInfo(ticker, startDate, endDate, selectedCurrency);
//				System.out.println(stock.UppdateInfo(ticker, startDate, endDate, selectedCurrency));
				
//				textArea.setText(stock.getData()); //Writes out the output to the text field
			}
		});
		add(button);
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
		selectedCurrency = EUR;
		add(panel);
	}
	
	//Updates the selected currency 
	private void updateSelectedCurrency() {
		if (radioButtons[0].isSelected()) selectedCurrency = EUR;
		if (radioButtons[1].isSelected()) selectedCurrency = USD;
		if (radioButtons[2].isSelected()) selectedCurrency = SEK;
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
			add(labels[i]);
			add(textFields[i]);
		}
	}
	
	private void initTextArea() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setAlignmentX(CENTER_ALIGNMENT);
		add(textArea);
	}
	
	private void initGraphPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		add(panel);
	}

}
