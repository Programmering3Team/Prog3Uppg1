package backend;

/*
 * Returnera en array list med den h�r klassen.
 */
public abstract class StockData {
	private String currency;
	public abstract int getStockValue();
	public abstract int getDate();
}
