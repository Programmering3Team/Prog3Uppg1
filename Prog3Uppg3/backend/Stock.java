package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import general.Constants;

public class Stock {
	private String url;
	private ArrayList<String> values;
	private String stock;
	private Curr currency;
	
	public Stock(String stock, String date1, String date2, String curr) throws Exception {
		values = new ArrayList<>();
		this.stock = stock;
		String[] info1 = date1.split("\\.");
		String[] info2 = date2.split("\\.");
		if (info1.length == 3 && info2.length == 3) {
			info1[1] = fixDate(info1);
			info2[1] = fixDate(info2);
			currency = new Curr(curr, info1, info2);
			//Bygger upp urlen
			url = "http://ichart.finance.yahoo.com/table.csv?s="
			+ stock +"&a="+ info1[1] + "&b=" + info1[0] +
			"&c="+ info1[2] +"&d="+ info2[1] +"&e="+ info2[0] 
			+"&f="+ info2[2] +"&g=d&ignore=.csv";
			read();
			if (curr == null) {
				currency.makeDollar(values.size());
			}else{
				currency.read();
			}
			
		}else{
			throw new InvalidDateExeption();
		}
	}
	
	
	private void read() throws IOException{
		String line = "";
		URL URL = new URL(url); 
		BufferedReader buf = new BufferedReader(new InputStreamReader(URL.openStream()));
		line = buf.readLine();
		while (line != null) { 
			values.add(line); 
			line = buf.readLine();
		}
		buf.close();
	}
	
	private String fixDate(String[] date) throws InvalidDateExeption{
		String mon = "";
		try {
			for (int i = 0; i < date.length; i++) {
				int monInt = Integer.parseInt(date[i]);
				if (i == 1) {
					monInt--;
					if (monInt < 0) {
						throw new InvalidDateExeption();
					}
					mon = "" + monInt;
				}
			}
			
		} catch (Exception e) {
			throw new InvalidDateExeption();
		}
		return mon;
	}
	
	public ArrayList<String> getValues(){	//returns the raw csv file
		return values;
	}
	
	public String getStockName(){
		return stock;
	}
	
	//makes a list like the one in mom1
	public String getData(Stock theOtherOne){
		ArrayList<Double> valuesNew = withCurr();
		ArrayList<Double> valuesNew2 = theOtherOne.withCurr();
		String out = "";
		for (int i = valuesNew.size()-1; i >= 0; i--) {
			String[] temp = values.get(i + 1).split(",");
			out = out + temp[0] + " Stock: " + stock + " close: " + valuesNew.get(i) + " - Stock: " + theOtherOne.getStockName() + " close: " + valuesNew2.get(i) + "\n";
		}
		return out;
	}
	//The csv file has a lot of different values. type in the type field what you want
	//0 = date, 1 = open, 2 = high, 3 = low, 4 = close, 5 = volume, 6 = adjusted close
	//it can return the date and all the above but only as a list of strings and not adjusted to the given currency.		
	public ArrayList<String> getOnlyValues(int type){
		ArrayList<String> temp = new ArrayList<>();
		for (int i = 1; i < values.size(); i++) {
			temp.add(values.get(i).split(",")[type]);
		}
		return temp;
	}
	
	//Returns a list of doubles that are adjusted with the given currency.

	public ArrayList<Double> withCurr(){
		ArrayList<String> curr = currency.getCurr();
		ArrayList<Double> temp = new ArrayList<>();
		for (int i = 1; i < values.size(); i++) {
			double v = Double.parseDouble(values.get(i).split(",")[4]);
			double c = Double.parseDouble(curr.get(i).split(",")[4]);
			temp.add(round(v*c, 2));
		}
		return temp;
	}
	//Returns the same string as getData but only one line.
	public String getSingleData(int orgIndex) {
		int index = orgIndex;
		if (index > values.size()-1) index = values.size()-1;
		
//		System.out.println("Index : " + index + " / " + values.size());
		
		String out = "";
		ArrayList<String> curr = currency.getCurr();
		double v = Double.parseDouble(values.get(index).split(",")[4]);
		double l = Double.parseDouble(curr.get(index).split(",")[3]);
		double h = Double.parseDouble(curr.get(index).split(",")[2]);
		h = round(h * v, 2);
		l = round(l*v, 2);
		String[] temp = values.get(index).split(",");
		out = temp[0] + " Stock: " + stock + " High: " + h + " Low: " + l + " Change: "
		+ calculateProcent(index) + "%";
		return out;
	}
	
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	private double calculateProcent(int place){
		double procentualDifference = 0;
		double startValue = Double.parseDouble(values.get(1).split(",")[4]);
		double newValue  = Double.parseDouble(values.get(place).split(",")[4]);
		procentualDifference = startValue / 100;
		procentualDifference = (newValue/procentualDifference) - 100;
		return procentualDifference; 
	}
	
	//Custom exeptions
	public class InvalidDateExeption extends Exception { }
}
