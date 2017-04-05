package backend;

import java.util.ArrayList;

import general.Constants;

public class CorrelationCalculator {
	
	public static double getCorreltation(Stock stock1, Stock stock2){
		ArrayList<Double> stockListX = convertToDouble(stock1.getOnlyValues(Constants.ADJ_CLOSE));
		ArrayList<Double> stockListY = convertToDouble(stock2.getOnlyValues(Constants.ADJ_CLOSE));
		double averageX = getAverage(stockListX);
		double averageY = getAverage(stockListY);
		double coveriance = getCoveriance(averageX, averageY, stockListX, stockListY);
		double deviationX = getStandardDeviation(averageX, stockListX);
		double deviationY = getStandardDeviation(averageY, stockListY);
		double correlation = coveriance / (deviationX * deviationY);
		
		return correlation;
	}
	
	private static ArrayList<Double> convertToDouble(ArrayList<String> temp){
		ArrayList<Double> toDouble = new ArrayList<>();
		for (int i = 0; i < temp.size(); i++) {
			toDouble.add(Double.parseDouble(temp.get(i)));
		}
		return toDouble;
	}
	
	private static double getAverage(ArrayList<Double> list) {
		double average = 0;
		for (int i = 0; i < list.size(); i++) {
			average = average + list.get(i);
		}
		average = average/list.size();
		
		return average;
	}
	private static double getCoveriance(double averageX, double averageY, ArrayList<Double> stocksX, ArrayList<Double> stocksY){
		double covariance = 0;
		for (int i = 0; i < stocksX.size(); i++) {
			covariance += (stocksX.get(i) - averageX) * (stocksY.get(i) - averageY);
		}
		return covariance;
	}
	
	private static double getStandardDeviation(double average, ArrayList<Double> stocks){
		double deviation = 0;
		for (int i = 0; i < stocks.size(); i++) {
			deviation += Math.pow(stocks.get(i) - average, 2);
		}
		deviation = deviation / stocks.size();
		deviation = Math.sqrt(deviation);
		
		return deviation;
	}

}
