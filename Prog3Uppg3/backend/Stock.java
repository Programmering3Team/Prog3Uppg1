package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Stock {
	
	private String url;
	private ArrayList<String> values;
	private String stock;
	private Curr currency;
	public Stock(){
		values = new ArrayList<>();
	}
	
	public Boolean UppdateInfo(String stock, String date1, String date2, String curr){
		//date comes in the following form: dd.mm.yyyy
		//january is 00 and december is 11
		this.stock = stock;
		String[] info1 = date1.split("\\.");
		String[] info2 = date2.split("\\.");
		if (info1.length == 3 && info2.length == 3) {
			currency = new Curr(curr, info1, info2);
			if (!currency.read()) {
				return false;
			}
			//Bygger upp urlen
			url = "http://ichart.finance.yahoo.com/table.csv?s="
			+ stock +"&a="+ info1[1] + "&b=" + info1[0] +
			"&c="+ info1[2] +"&d="+ info2[1] +"&e="+ info2[0] 
			+"&f="+ info2[2] +"&g=d&ignore=.csv";
			read();
			return true;
		}else{
			return false;
		}
		//http://ichart.finance.yahoo.com/table.csv?s=SEK=X&a=2&b=7&c=2015&d=3&e=7&f=2015&g=d&ignore=.csv
		//http://ichart.finance.yahoo.com/table.csv?s=fb&a=02&b=19&c=2014&d=02&e=26&f=2014&g=d&ignore=.csv
	}
	
	private void read(){
		String line = "";
		try {
			URL URL = new URL(url); 
			BufferedReader buf = new BufferedReader(new InputStreamReader(URL.openStream()));
			line = buf.readLine();
			while (line != null) { 
				values.add(line); 
				line = buf.readLine();
			}
			buf.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not find file");
		}
		
	}
	
	public ArrayList<String> getValues(){	//returns the values
		return values;
	}
	
	public String getStockName(){
		return stock;
	}
	
	public void print(){
		//prints the csv file as it is
		for (int i = 1; i < values.size(); i++) {
			System.out.println(values.get(i));
		}
		System.out.println("\n\n\n\n");
		currency.printCurr();
	}
	
	public String getData(){
		//makes a list like the one in mom1
		ArrayList<Double> valuesNew = withCurr();
		String out = "";
		for (int i = 0; i < valuesNew.size(); i++) {
			String[] temp = values.get(i + 1).split(",");
			out = out + temp[0] + " stock: " + stock + " close: " + valuesNew.get(i) + "\n";
		}
		return out;
	}
	
	public ArrayList<String> getOnlyValues(int type){
		//The csv file has a lot of different values. type in the type field what you want
		//0 = date, 1 = open, 2 = high, 3 = low, 4 = close, 5 = volume, 6 = adjusted close
		ArrayList<String> temp = new ArrayList<>();
		for (int i = 1; i < values.size(); i++) {
			temp.add(values.get(i).split(",")[type]);
		}
		return temp;
	}
	
	public ArrayList<Double> withCurr(){
		ArrayList<String> curr = currency.getCurr();
		ArrayList<Double> temp = new ArrayList<>();
		for (int i = 1; i < values.size(); i++) {
			double v = Double.parseDouble(values.get(i).split(",")[2]);
			//System.out.println(curr.get(i).split(",")[2] + "\n");
			double c = Double.parseDouble(curr.get(i).split(",")[2]);
			temp.add(v*c);
		}
		return temp;
	}
	

}
