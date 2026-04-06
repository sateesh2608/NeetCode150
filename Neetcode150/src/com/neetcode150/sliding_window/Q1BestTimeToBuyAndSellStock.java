package com.neetcode150.sliding_window;

public class Q1BestTimeToBuyAndSellStock {

	public static void main(String[] args) {

		int[] prices = {7,1,5,3,6,4};
		System.out.println(maxProfit(prices));
	}

	private static int maxProfit(int[] prices) {

		int minBuy = prices[0];
		int profit = 0;
		
		for (int i = 0; i < prices.length; i++) {
			if(minBuy>prices[i]) {
				minBuy = prices[i];
			}
			
			profit = Math.max(profit,prices[i]-minBuy);
		}
		
		return profit;
	}

}
