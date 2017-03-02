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
	
	public Stock(){
		values = new ArrayList<>();
	}
	
	public Boolean giveURL(String stock, String date1, String date2){
		//january is 00 and december is 11
		this.stock = stock;
		String[] info1 = date1.split("\\.");
		String[] info2 = date2.split("\\.");
		if (info1.length == 3 && info2.length == 3) {
			//Bygger upp urlen
			url = "http://ichart.finance.yahoo.com/table.csv?s="
			+ stock +"&a="+ info1[1] + "&b=" + info1[0] +
			"&c="+ info1[2] +"&d="+ info2[1] +"&e="+ info2[0] 
			+"&f="+ info2[2] +"&g=d&ignore=.csv";
			//System.out.println(url);
			read();
			return true;
		}else{
			return false;
		}
		
		//http://ichart.finance.yahoo.com/table.csv?s=fb&a=02&b=19&c=2014&d=02&e=26&f=2014&g=d&ignore=.csv
	}
	
	private void read(){
		String line = "";
		try {
			URL URL = new URL(url); // hämtar från objektet x från dess varaibel input (som är textfield) texten, alltså urlen
			BufferedReader buf = new BufferedReader(new InputStreamReader(URL.openStream()));
			line = buf.readLine(); // första med data
			while (line != null) { 
				values.add(line); 
				line = buf.readLine();
			}
			buf.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not find file");
		}
		
	}
	
	public ArrayList<String> getData(){	//returns the values
		return values;
	}
	
	public String getStockName(){
		return stock;
	}
	
	public void print(){
		//prints the csv file as it is
		for (int i = 0; i < values.size(); i++) {
			System.out.println(values.get(i));
		}
	}
	
	public ArrayList<String> makeReadable(){
		//makes a list like the one in mom1
		ArrayList<String> out = new ArrayList<>();
		for (int i = 1; i < values.size(); i++) {
			String[] temp = values.get(i).split(",");
			out.add(temp[0] + " stock: " + stock + " close: " + temp[4]);
		}
		return out;
	}
	
	public ArrayList<String> getOnlyValues(int type){
		//The csv file has a lot of different values. type in the type field what you want
		//0 = date, 1 = open, 2 = high, 3 = low, 4 = close, 5 = volume, 6 = adjusted close
		ArrayList<String> temp = new ArrayList<>();
		for (int i = 0; i < values.size(); i++) {
			temp.add(values.get(i).split(",")[type]);
		}
		return temp;
	}
	

}
