package com.iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLBowling {
	@CsvBindByName(column = "POS")
	public String pos;
	@CsvBindByName(column = "PLAYER")
	public String player;
	@CsvBindByName(column = "Mat")
	public String matches;
	@CsvBindByName(column = "inns")
	public String innings;
	@CsvBindByName(column = "Ov")
	public String overs;
	@CsvBindByName(column = "Runs")
	public String runs;
	@CsvBindByName(column = "Wkts")
	public String wickets;
	@CsvBindByName(column = "BBI")
	public String bestBowling;
	@CsvBindByName(column = "Avg")
	public String avg;
	@CsvBindByName(column = "Econ")
	public String economyRate;
	@CsvBindByName(column = "SR")
	public String strikeRate;
	@CsvBindByName(column = "4w")
	public String fourWickets;
	@CsvBindByName(column = "5w")
	public String fiveWickets;

	public double Average() {
		if (avg.equals("-") || avg == "")
			this.avg = "0.0";
		double average = Double.parseDouble(this.avg);
		return average;
	}
	public double StrikeRate() {
		if (strikeRate.equals("-") || strikeRate == "")
			this.strikeRate = "9999";
		double sr = Double.parseDouble(this.strikeRate);
		return sr;
	}
	
	public double Economy() {
		if (economyRate.equals("-") || economyRate == "")
			this.strikeRate = "9999";
		double ec = Double.parseDouble(this.economyRate);
		return ec;
	}
	
	public int no_of_4w() {
		if(fourWickets.equals("-") || fourWickets == "")
			this.fourWickets="0";
		int fourW=Integer.parseInt(this.fourWickets);
		return fourW;
	}
	
	public int no_of_5w() {
		if(fiveWickets.equals("-") || fiveWickets == "")
			this.fiveWickets="0";
		int fiveW=Integer.parseInt(this.fiveWickets);
		return fiveW;
	}
	
	@Override
	public String toString() {
		return "Bowlers-- \nPosition: " + pos + " Player :" + player + " Matches: " + matches + " Innings: " + innings
				+ " Overs: " + overs + " Runs: " + runs + " Wickets: " + wickets + " Average: " + avg
				+ " Best Bowling in innings: " + bestBowling + " Strike Rate: " + strikeRate + " Economy rate: "
				+ economyRate + " No. of 4 wicktes in an inning: " + fourWickets + " No. of 5 wickets in an inning:: "
				+ fiveWickets;
	}
}
