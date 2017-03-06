package backend;

/*
 * Returnera en array list med den här klassen.
 */
public abstract class StockData {
	private String currency;
	public abstract int getStockValue();
	public abstract int getDate();
}
