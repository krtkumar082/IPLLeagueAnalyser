package com.iplanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.csv.OpenCSVBuilder;
import com.exception.IPlAnalyserException;
import com.opencsv.bean.CsvToBeanBuilder;

public class IPlLeagueAnalyser {
 public static List<IPLBatting> IPLBattingList;
 public static List<IPLBowling> IplBowlingList;
	public int loadCSVData(String csvFile) {
		int numOfEntries=0;
		try {
			
			Reader reader=Files.newBufferedReader(Paths.get(csvFile));
			Iterator<IPLBatting> iterator=new OpenCSVBuilder().getCSVFileIterator(reader,IPLBatting.class);
			Iterable<IPLBatting> csvIterable = () -> iterator;
			numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return numOfEntries;
	}
	
	public void loadBattingDataToList(String csvFile) throws IPlAnalyserException {
		try {
			Reader reader=Files.newBufferedReader(Paths.get(csvFile));
			IPLBattingList = new CsvToBeanBuilder(reader).withType(IPLBatting.class).build().parse();
		}
		catch(IOException e) {
			throw new IPlAnalyserException("File path is incorrect",IPlAnalyserException.ExceptionType.FILE_INCORRECT);
		}
	}
	
	public void loadBowlingDataToLIst(String csvFile)throws IPlAnalyserException{
		try {
			Reader reader=Files.newBufferedReader(Paths.get(csvFile));
		    IplBowlingList = new CsvToBeanBuilder(reader).withType(IPLBowling.class).build().parse();
		}
		catch(IOException e) {
			throw new IPlAnalyserException("File path is incorrect",IPlAnalyserException.ExceptionType.FILE_INCORRECT);
		}
	}
	
	public List<IPLBowling> getTopBowlingAverages()throws Exception{
		List<IPLBowling> sortedAvgBowlingList = IplBowlingList.stream()
				.sorted((player1, player2) -> Double.compare(player1.Average(), player2.Average()))
				.collect(Collectors.toList());
		Collections.reverse(sortedAvgBowlingList);
		return sortedAvgBowlingList;
	}
	
	public List<IPLBowling> getTopBowlingStrikingRates() throws Exception {
		List<IPLBowling> sortedAvgList = this.IplBowlingList.stream()
				.sorted((player1, player2) -> Double.compare(player1.StrikeRate(), player2.StrikeRate()))
				.collect(Collectors.toList());
		
		return sortedAvgList;
	}
	
	public List<IPLBowling> getTopBowlerWithBestEconomy() throws Exception {
		List<IPLBowling> sortedAvgList = this.IplBowlingList.stream()
				.sorted((player1, player2) -> Double.compare(player1.Economy(), player2.Economy()))
				.collect(Collectors.toList());
		
		return sortedAvgList;
	}
	
public List<IPLBowling> getBowlersWithBestStrikeRateWithMax4wAnd5w() {
	  
	int max4wAnd5w = IplBowlingList.stream()
			.map(player -> (player.no_of_4w()+player.no_of_5w()))
			.max(Integer::compare)
			.get();
	List<IPLBowling> bowlerWithMax4wAnd5w = IplBowlingList.stream()
			.filter((player -> (player.no_of_4w()+player.no_of_5w())==max4wAnd5w))
			.collect(Collectors.toList());
	
	double bestStrikingRate=bowlerWithMax4wAnd5w.stream()
			.map(player -> player.StrikeRate())
			.min(Double::compare)
			.get();
	
	List<IPLBowling> bowlerBestStrikingRateWithMax4wAnd5w = bowlerWithMax4wAnd5w.stream()
			.filter(player->player.StrikeRate()==bestStrikingRate)
			.collect(Collectors.toList());
	
	return bowlerBestStrikingRateWithMax4wAnd5w ;
		
	}
	public List<IPLBowling> getBowlersWithStrikeRateAndBestAverage(){
		List<IPLBowling> sortedStrikeRateAndAverageList = IplBowlingList.stream()
				.filter(player->player.Average()!=0 && player.StrikeRate()!=0)
				.sorted((player1, player2) -> Double.compare(player1.StrikeRate()+player1.Average(), player2.StrikeRate()+player2.Average()))
				.collect(Collectors.toList());
		return sortedStrikeRateAndAverageList;
	}

	public List<IPLBowling> getBowlersWithMaxWicketsAndBestAverage() {
		int maxWicks = IplBowlingList.stream()
				.map(player -> (player.Wickets()))
				.max(Double::compare)
				.get();
		List<IPLBowling> bowlerWithMaxwicks = IplBowlingList.stream()
				.filter((player -> (player.Wickets())==maxWicks))
				.collect(Collectors.toList());
		Double greatestAvg= bowlerWithMaxwicks.stream()
				.map(player -> player.Average())
				.max(Double::compare)
				.get();
		
		List<IPLBowling> bowlerMaxWicksWithGreatestAvg = bowlerWithMaxwicks.stream()
				.filter(player->player.Average()==greatestAvg)
				.collect(Collectors.toList());
		
		return bowlerMaxWicksWithGreatestAvg;
	}
	
	//Batting side
	public List<IPLBatting> getTopBattingAverages(String csvFile) throws Exception {
		List<IPLBatting> sortedAvgList = this.IPLBattingList.stream()
				.sorted((player1, player2) -> Double.compare(player1.Average(), player2.Average()))
				.collect(Collectors.toList());
		Collections.reverse(sortedAvgList);
		return sortedAvgList;
	}
	
	
	
	public List<IPLBatting> getTopStrikingRates(String csvFile) throws Exception {
		List<IPLBatting> sortedAvgList = this.IPLBattingList.stream()
				.sorted((player1, player2) -> Double.compare(player1.StrikeRate(), player2.StrikeRate()))
				.collect(Collectors.toList());
		Collections.reverse(sortedAvgList);
		return sortedAvgList;
	}
	
	public List<IPLBatting> getTopBatmenWithMax6s(String csvFile) throws Exception {
		List<IPLBatting> sortedAvgList = this.IPLBattingList.stream()
				.sorted((player1, player2) ->Integer.compare(player1.no_of_6s(), player2.no_of_6s()))
				.collect(Collectors.toList());
		Collections.reverse(sortedAvgList);
		return sortedAvgList;
	}
	public List<IPLBatting> getTopBatmenWithMax4s(String csvFile) throws Exception {
		List<IPLBatting> sortedAvgList = this.IPLBattingList.stream()
				.sorted((player1, player2) -> Integer.compare(player1.no_of_4s(), player2.no_of_4s()))
				.collect(Collectors.toList());
		Collections.reverse(sortedAvgList);
		return sortedAvgList;
	}
	
	public List<IPLBatting> getCricketerWithBestStrikingRateWith6sAnd4s()throws IOException{
		int max4sAnd6s = IPLBattingList.stream()
				.map(player -> (player.no_of_4s()+player.no_of_6s()))
				.max(Integer::compare)
				.get();
		List<IPLBatting> batmenWithMax4sAnd6s = IPLBattingList.stream()
				.filter((player -> (player.no_of_6s()+player.no_of_4s())==max4sAnd6s))
				.collect(Collectors.toList());
		
		double bestStrikingRate=batmenWithMax4sAnd6s.stream()
				.map(player -> player.StrikeRate())
				.max(Double::compare)
				.get();
		
		List<IPLBatting> batmenBestStrikingRateWithMax4sAnd6s = batmenWithMax4sAnd6s.stream()
				.filter(player->player.StrikeRate()==bestStrikingRate)
				.collect(Collectors.toList());
		
		return batmenBestStrikingRateWithMax4sAnd6s ;
	}
	
	public List<IPLBatting> getCricketerWithBestStrikingRateWithGreatestAvg()throws IOException{
		Double greatestAvg = IPLBattingList.stream()
				.map(player -> (player.Average()))
				.max(Double::compare)
				.get();
		List<IPLBatting> batmenWithgreatestAvg = IPLBattingList.stream()
				.filter((player -> (player.Average())==greatestAvg))
				.collect(Collectors.toList());
		
		double bestStrikingRate=batmenWithgreatestAvg.stream()
				.map(player -> player.StrikeRate())
				.max(Double::compare)
				.get();
		
		List<IPLBatting> batmenBestStrikingRateWithGreatestAvg = batmenWithgreatestAvg.stream()
				.filter(player->player.StrikeRate()==bestStrikingRate)
				.collect(Collectors.toList());
		
		return batmenBestStrikingRateWithGreatestAvg ;
	}

	public List<IPLBatting> getCricketerWithMaximumRunWithGreatestAverage() {
		int maxRuns = IPLBattingList.stream()
				.map(player -> (player.Runs()))
				.max(Double::compare)
				.get();
		List<IPLBatting> batmenWithMaxRuns = IPLBattingList.stream()
				.filter((player -> (player.Runs())==maxRuns))
				.collect(Collectors.toList());
		Double greatestAvg= batmenWithMaxRuns.stream()
				.map(player -> player.Average())
				.max(Double::compare)
				.get();
		
		List<IPLBatting> batmenMaxRunsWithGreatestAvg = batmenWithMaxRuns.stream()
				.filter(player->player.Average()==greatestAvg)
				.collect(Collectors.toList());
		
		return batmenMaxRunsWithGreatestAvg;
	}

	
public List<String> getBestBattingAndBowlingAverage(){
		
		List<String> bestBattingAndBowlingAverage=new ArrayList<>();
		
		List<IPLBatting> bestBattingAvg = IPLBattingList.stream()
				.sorted((player1, player2) -> Double.compare(player1.Average(),player2.Average()))
				.collect(Collectors.toList());
		Collections.reverse(bestBattingAvg);
		
		List<IPLBowling> bestBowlingAvg=IplBowlingList.stream()
				.filter(player->player.Average()!=0)
				.sorted((player1, player2) -> Double.compare(player1.Average(), player2.Average()))
				.collect(Collectors.toList());
		
		for (IPLBatting batter : bestBattingAvg) {
			for (IPLBowling bowler : bestBowlingAvg) {
				if (batter.player.equals(bowler.player)) {
					bestBattingAndBowlingAverage.add(batter.player);
				}
			}
		}
		return bestBattingAndBowlingAverage;		
	}
	
public List<String> getPlayerWithMostRunAndMostWicket(){
	
	List<String> allRounderList=new ArrayList<>();
	
	List<IPLBatting> maxRuns = IPLBattingList.stream()
			.sorted((player1, player2) -> Double.compare(player1.Runs(),player2.Runs()))
			.collect(Collectors.toList());
	Collections.reverse(maxRuns);
	
	List<IPLBowling> mostWickets=IplBowlingList.stream()
			.filter(player->player.Wickets()!=0)
			.sorted((player1, player2) -> Double.compare(player1.Wickets(), player2.Wickets()))
			.collect(Collectors.toList());
	Collections.reverse(mostWickets);
	
	for (IPLBatting batter : maxRuns) {
		for (IPLBowling bowler : mostWickets) {
			if (batter.player.equals(bowler.player)) {
				allRounderList.add(batter.player);
			}
		}
	}
	return allRounderList;		
 }

public List<IPLBatting> getPlayerWithMax100AndBestBattingAverage() {
	int maxHuns = IPLBattingList.stream()
			.map(player -> (player.Hundreds()))
			.max(Double::compare)
			.get();
	List<IPLBatting> batmenWithMaxHundreds = IPLBattingList.stream()
			.filter((player -> (player.Hundreds())==maxHuns))
			.collect(Collectors.toList());
	Double greatestAvg= batmenWithMaxHundreds.stream()
			.map(player -> player.Average())
			.max(Double::compare)
			.get();
	
	List<IPLBatting> batmenMaxHundredsWithGreatestAvg = batmenWithMaxHundreds.stream()
			.filter(player->player.Average()==greatestAvg)
			.collect(Collectors.toList());
	
	return batmenMaxHundredsWithGreatestAvg;
}

public List<IPLBatting> getPlayerWithZeroCenturyOrHalfCenturyAndBestBattingAverage(){
	
	List<IPLBatting> listZero100s=IPLBattingList.stream()
			.filter(player->player.Hundreds()==0)
			.collect(Collectors.toList());
	List<IPLBatting> listZero100s50s=listZero100s.stream()
			.filter(player->player.Fifties()==0)
			.collect(Collectors.toList());
	
	Double greatestAvg= listZero100s50s.stream()
			.map(player -> player.Average())
			.max(Double::compare)
			.get();
	
	List<IPLBatting> batmenWithZero100s50sWithBestAvg = listZero100s50s.stream()
			.filter(player->player.Average()==greatestAvg)
			.collect(Collectors.toList());
	return batmenWithZero100s50sWithBestAvg;		
}

}
