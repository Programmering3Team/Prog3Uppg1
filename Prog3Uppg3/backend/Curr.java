package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Curr {
	
	//http://ichart.finance.yahoo.com/table.csv?s=SEK=X&a=2&b=7&c=2015&d=3&e=7&f=2015&g=d&ignore=.csv
	private String url;
	private ArrayList<String> currValues;
	
	public Curr(String curr, String[] info1, String[] info2){
		currValues = new ArrayList<>();
		url = "http://ichart.finance.yahoo.com/table.csv?s="
				+ curr +"=X&a="+ info1[1] + "&b=" + info1[0] +
				"&c="+ info1[2] +"&d="+ info2[1] +"&e="+ info2[0] 
				+"&f="+ info2[2] +"&g=d&ignore=.csv";
		System.out.println(url);
	}
	
	public Boolean read(){
		String line = "";
		try {
			URL URL = new URL(url); 
			BufferedReader buf = new BufferedReader(new InputStreamReader(URL.openStream()));
			line = buf.readLine();
			while (line != null) { 
				currValues.add(line); 
				line = buf.readLine();
			}
			buf.close();
			return true;
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not find currency file");
			return false;
		}
		
	}
	
	public void printCurr(){
		for (int i = 1; i < currValues.size(); i++) {
			System.out.println(currValues.get(i));
		}
	}
	
	public ArrayList<String> getCurr() {
		return currValues;
	}

}
