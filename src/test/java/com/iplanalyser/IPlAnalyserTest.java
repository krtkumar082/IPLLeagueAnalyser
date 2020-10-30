package com.iplanalyser;

import static org.junit.Assert.assertEquals;


import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.exception.IPlAnalyserException;

public class IPlAnalyserTest {
	public String FILE_PATH="C:\\Users\\KIRTI KUMAR\\eclipse-workspace - 3\\IPLLeagueAnalyser\\Batting2.csv";
	public String FILE_PATH_BOWLING_DATA="C:\\Users\\KIRTI KUMAR\\eclipse-workspace - 3\\IPLLeagueAnalyser\\Bowling.csv";
	public IPlLeagueAnalyser IPlLeagueAnalyser;
	
	@Before
	public void setUp() throws IOException {
		IPlLeagueAnalyser=new IPlLeagueAnalyser();
		try {
			IPlLeagueAnalyser.loadBattingDataToList(FILE_PATH);
			IPlLeagueAnalyser.loadBowlingDataToLIst(FILE_PATH_BOWLING_DATA);
		} catch (IPlAnalyserException e) {
			
			e.printStackTrace();
		}
		
	}

	@Test
	public void givenIPLBattingWhenCalculatedBattingAverageShouldReturnExactAnswer() {
		assertEquals(101,IPlLeagueAnalyser.loadCSVData(FILE_PATH));
	}
	
	@Test
	public void givenIPLBattingCSVFileReturnsTop3BattingAverages() throws Exception {
		List<IPLBatting> topBattingAverage = IPlLeagueAnalyser.getTopBattingAverages(FILE_PATH);
		assertEquals(83.2, topBattingAverage .get(0).Average(),0.0);
		
	}
	
	@Test
	public void givenIPLBattingCSVFileReturnsTop3StrikeRates() throws Exception {

		List<IPLBatting> topStrikeRate = IPlLeagueAnalyser.getTopStrikingRates(FILE_PATH);
		assertEquals(333.33, topStrikeRate.get(0).StrikeRate(), 0.0);
		assertEquals(204.81, topStrikeRate.get(1).StrikeRate(), 0.0);
		assertEquals(200.00, topStrikeRate.get(2).StrikeRate(), 0.0);
	}
	
	@Test
	public void givenIPLBattingCSVFileReturnsCricketerWithMax6s() throws Exception {
		
		List<IPLBatting> batmenWithMax6s = IPlLeagueAnalyser.getTopBatmenWithMax6s(FILE_PATH);
		assertEquals("Andre Russell", batmenWithMax6s.get(0).player);
	}
	
	@Test
	public void givenIPLBattingCSVFileReturnsCricketerWithMax4s() throws Exception {
		
		List<IPLBatting> batmenWithMax4s = IPlLeagueAnalyser.getTopBatmenWithMax4s(FILE_PATH);
		assertEquals("Shikhar Dhawan", batmenWithMax4s.get(0).player);
	}
	
	@Test
	public void givenIplDataCSVFileReturnsBestStrikeRatesWith6sAnd4s() throws Exception {
		List<IPLBatting> listOfTopStrikeRate = IPlLeagueAnalyser.getCricketerWithBestStrikingRateWith6sAnd4s();
	    assertEquals("Andre Russell", listOfTopStrikeRate.get(0).player);
	}
	
	@Test
	public void givenIplDataCSVFileReturnsCricketersWithGreatAverageAndBestStrikeRates() throws Exception {
		List<IPLBatting> listOfTopAverageWithBestStrikERate = IPlLeagueAnalyser.getCricketerWithBestStrikingRateWithGreatestAvg();
		assertEquals("MS Dhoni", listOfTopAverageWithBestStrikERate.get(0).player);
	}
	
	@Test
	public void givenIplDataCSVFileReturnsCricketersWithMaximumRunAndBestAverages() throws Exception {
		List<IPLBatting> listOfMaxRunAndGreatestAverage = IPlLeagueAnalyser.getCricketerWithMaximumRunWithGreatestAverage();
		assertEquals("David Warner ", listOfMaxRunAndGreatestAverage.get(0).player);
	}
	
	@Test
	public void givenIplBowlingData_SortBowlingDataAccordingToAverage_ReturnSameList() throws Exception {
		List<IPLBowling> listOfTopBowlingAverage = IPlLeagueAnalyser. getTopBowlingAverages();
		assertEquals("Krishnappa Gowtham",listOfTopBowlingAverage.get(0).player);
		assertEquals("Tim Southee",listOfTopBowlingAverage.get(1).player);
		assertEquals("Prasidh Krishna",listOfTopBowlingAverage.get(2).player);
	}
	
	@Test
	public void givenIplBowlingData_SortBowlingDataAccordingToStrikeRate_ReturnSameList() throws Exception{
		List<IPLBowling> listOfTopBowlingStrikeRate =  IPlLeagueAnalyser.getTopBowlingStrikingRates();
		
		assertEquals("Alzarri Joseph",listOfTopBowlingStrikeRate.get(0).player);
     	assertEquals("Ish Sodhi",listOfTopBowlingStrikeRate.get(1).player);
		assertEquals("Khaleel Ahmed",listOfTopBowlingStrikeRate.get(2).player);
	}
	
	@Test
	public void givenIplBowlingData_SortBowlingDataAccordingToEconomy_ReturnSameList() throws Exception {
		List<IPLBowling> listOfTopBowlersWithBestEconomy = IPlLeagueAnalyser.getTopBowlerWithBestEconomy();
		assertEquals("Shivam Dube",listOfTopBowlersWithBestEconomy.get(0).player);
	}

	@Test
	public void givenIplBowlingData_SortBowlingDataAccordingToBestStrikeRateWithMax4wAnd5w_ReturnSameList() {
		List<IPLBowling> listOfTopBowlersWithBestStrikeRateANdMax4w5w = IPlLeagueAnalyser.getBowlersWithBestStrikeRateWithMax4wAnd5w();
		assertEquals("Kagiso Rabada",listOfTopBowlersWithBestStrikeRateANdMax4w5w.get(0).player);
	}
	
	@Test
	public void givenIplBowlingData_SortBowlingDataAccordingToBestStrikeRateAndAverage_ReturnSameList() throws IOException {
		List<IPLBowling> listOfTopBowlersWithBestStrikeRateAndAverage = IPlLeagueAnalyser.getBowlersWithStrikeRateAndBestAverage();
    	assertEquals("Anukul Roy",listOfTopBowlersWithBestStrikeRateAndAverage.get(0).player);
	}
	
	@Test
	public void givenIplBowlingData_SortWithMaxWicketsAndGreatAverage_ReturnSameList() throws IOException{
		List<IPLBowling> listOfBowlersWithMaximumWicketsAndGreatAverage=IPlLeagueAnalyser.getBowlersWithMaxWicketsAndBestAverage();
		assertEquals("Imran Tahir",listOfBowlersWithMaximumWicketsAndGreatAverage.get(0).player);
	}
	
}
