package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ReadFile {
	
	private String url;
	private ArrayList<String> values;
	
	public ReadFile(){
		values = new ArrayList<>();
	}
	
	public Boolean giveURL(String stock, String date1, String date2){
		if (date1.split(".").length == 3 && date2.split(".").length == 2) {
			//Bygger upp urlen
			url = "http://ichart.finance.yahoo.com/table.csv?s="
			+ stock +"&a="+ date1.split(".")[0] + "&b=" + date1.split(".")[1] +
			"&c="+ date1.split(".")[2] +"&d="+ date2.split(".")[0] +"&e="+ date2.split(".")[1] 
			+"&f="+ date2.split(".")[2] +"&g=d&ignore=.csv";
			System.out.println(url);
			read();
			return true;
		}else{
			return false;
		}
		
		//http://ichart.finance.yahoo.com/table.csv?s=fb&a=02&b=19&c=2014&d=02&e=26&f=2014&g=d&ignore=.csv
	}
	
	private void read(){
		try {
			FileReader reader = new FileReader(url);
			BufferedReader buf = new BufferedReader(reader);
			String line;
			
			while ((line = buf.readLine()) != null){
				line = line.toLowerCase();
				values.add(line);
			}
			buf.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not find file");
		}
	}
	
	public ArrayList<String> getData(){	//returns the values
		return values;
	}
	
	public void print(){
		for (int i = 0; i < values.size(); i++) {
			System.out.println(values.get(i));
		}
	}
	

}
