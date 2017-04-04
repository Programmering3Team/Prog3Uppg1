package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import backend.Stock;
import backend.Stock.InvalidDateExeption;
import general.Constants;
import ui.UI;

public class QueryButtonListener implements ActionListener {
	private UI ui;
	
	public QueryButtonListener(UI ui) {
		this.ui = ui;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		buttonClicked();
	}
	
	private void buttonClicked() {
		//If text aren't filled, break
		if (ui.textFieldsAreFilled() == false) {
			ui.getTextArea().setText("One or more text fields are empty.\nPlease enter the information and try again.");
			return;
		}
		String ticker1 = ui.getTextFields()[0].getText();
		String ticker2 = ui.getTextFields()[1].getText();
		String startDate = ui.getTextFields()[2].getText();
		String endDate = ui.getTextFields()[3].getText();
		ui.updateSelectedCurrency();
		try {
			Stock stock1 = new Stock(ticker1, startDate, endDate, ui.getSelectedCurrency());
			Stock stock2 = new Stock(ticker2, startDate, endDate, ui.getSelectedCurrency());
			ui.getGraph().setStock1(stock1);
			ui.getGraph().setStock2(stock2);
			ui.getTextArea().setText(stock1.getData(stock2));
			
			ui.getGraph().clear();
			ui.getGraph().drawDiagram(stock1.withCurr(), stock1.getOnlyValues(Constants.DATE), Color.RED);
			ui.getGraph().drawDiagram(stock2.withCurr(), stock2.getOnlyValues(Constants.DATE), Color.GREEN);
		} catch (InvalidDateExeption e) {
			ui.getTextArea().setText("Invalid date\nEnter the date in the specified format.");
		} catch (IOException e2) {
			ui.getTextArea().setText("Could not find data.\nTicker entered is invalid or there is no internet connection");
		} catch (IndexOutOfBoundsException e) {
			ui.getTextArea().setText("Could not find the currency data or the stock data for the given time\nChange the currency or date.");
		} catch (Exception e) {
			ui.getTextArea().setText(e.getMessage());
		}
	}
}
