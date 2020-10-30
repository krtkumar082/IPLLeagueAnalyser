package com.iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLBatting {
	@CsvBindByName(column = "POS")
	public String pos;
	@CsvBindByName(column = "PLAYER")
	public String player;
	@CsvBindByName(column = "Mat")
	public String matches;
	@CsvBindByName(column = "inns")
	public String innings;
	@CsvBindByName(column = "NO")
	public String notOuts;
	@CsvBindByName(column = "Runs")
	public String runs;
	@CsvBindByName(column = "HS")
	public String highestScore;
	@CsvBindByName(column = "Avg")
	public String avg;
	@CsvBindByName(column = "BF")
	public String ballsFaced;
	@CsvBindByName(column = "SR")
	public String strikeRate;
	@CsvBindByName(column = "100")
	public String hundreds;
	@CsvBindByName(column = "50")
	public String fifties;
	@CsvBindByName(column = "4s")
	public String fours;
	@CsvBindByName(column = "6s")
	public String sixes;

	public double Average() {
		if(avg==null)
			this.avg = "0.0";
		else if (avg.equals("-"))
			this.avg = "0.0";
		double average = Double.parseDouble(this.avg);
		return average;
	}
	
	public double StrikeRate() {
		if (strikeRate.equals("-") || strikeRate == "")
			this.strikeRate = "0";
		double sr = Double.parseDouble(this.strikeRate);
		return sr;
	}
	
	public int no_of_6s() {
		if(sixes.equals("-") || sixes == "")
			this.sixes="0";
		int six=Integer.parseInt(this.sixes);
		return six;
	}
	
	public int no_of_4s() {
		if(fours.equals("-") || fours == "")
			this.fours="0";
		int four=Integer.parseInt(this.fours);
		return four;
	}
  
	public int Runs() {
		if (runs.equals("-") || runs == "")
			this.runs = "0";
		int run = Integer.parseInt(this.runs);
		return run;
	}
	@Override
	public String toString() {
		return "Batsman-- \nPosition: " + pos + " Player :" + player + " Matches: " + matches + " Innings: " + innings
				+ " Not Outs: " + notOuts + " Runs: " + runs + " Highest Score: " + highestScore + " Average: " + avg
				+ " Balls Faced: " + ballsFaced + " Strike Rate: " + strikeRate + " No. of 100s: " + hundreds
				+ " No. of 50s: " + fifties + " No. of 4s: " + fours + " No. of 6s: " + sixes;
	}
}
