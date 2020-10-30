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
	public void givenIplBowlingData_SortBowlingDataAccordingToAverage_ReturnSameList() {
		List<IPLBowling> listOfTopBowlingAverage = IPlLeagueAnalyser. getTopBowlingAverages();
		System.out.println(listOfTopBowlingAverage);
		assertEquals("Krishnappa Gowtham",listOfTopBowlingAverage.get(0).player);
		assertEquals("Tim Southee",listOfTopBowlingAverage.get(1).player);
		assertEquals("Prasidh Krishna",listOfTopBowlingAverage.get(2).player);
	}
	
}
