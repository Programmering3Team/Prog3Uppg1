package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Curr {
	private String url;
	private ArrayList<String> currValues;
	
	public Curr(String curr, String[] info1, String[] info2){
		currValues = new ArrayList<>();
		url = "http://ichart.finance.yahoo.com/table.csv?s="
				+ curr +"=X&a="+ info1[1] + "&b=" + info1[0] +
				"&c="+ info1[2] +"&d="+ info2[1] +"&e="+ info2[0] 
				+"&f="+ info2[2] +"&g=d&ignore=.csv";
	}
	
	public void read() throws IOException {
		String line = "";
		URL URL = new URL(url); 
		BufferedReader buf = new BufferedReader(new InputStreamReader(URL.openStream()));
		line = buf.readLine();
		while (line != null) { 
			currValues.add(line); 
			line = buf.readLine();
		}
		buf.close();
	}
	
	public void printCurr(){
		for (int i = 1; i < currValues.size(); i++) {
			System.out.println(currValues.get(i));
		}
	}
	
	public ArrayList<String> getCurr() {
		return currValues;
	}
	
	public void makeDollar(int size) {
		for (int i = 0; i < size; i++) {
			currValues.add("1.00,1.00,1.00,1.00,1.00,1.00");
		}
	}
}
