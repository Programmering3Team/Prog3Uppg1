package moment;
import java.util.ArrayList;

import backend.*;
import ui.UI;

public class Main {
	
	public static void main(String[] args) {
		
		Stock aapl = new Stock();
		aapl.giveURL("aapl", "01.11.2016", "05.11.2016");
		ArrayList<String> list = aapl.getOnlyValues(0);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		//UI ui = new UI();

	}
}
