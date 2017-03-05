package moment;
import java.util.ArrayList;

import backend.*;
import ui.UI;

public class Main {
	
	public static void main(String[] args) {
		
		Stock x = new Stock();
		x.UppdateInfo("aapl", "01.11.2016", "05.11.2016", "EUR");
		String data = x.getData();
		System.out.println(data);
		
		//UI ui = new UI();

	}
}
