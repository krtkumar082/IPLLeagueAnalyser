package com.iplanalyser;

import static org.junit.Assert.assertEquals;


import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class IPlAnalyserTest {
	public String FILE_PATH="C:\\Users\\KIRTI KUMAR\\eclipse-workspace - 3\\IPLLeagueAnalyser\\Batting2.csv";
	public IPlLeagueAnalyser IPlLeagueAnalyser;
	
	@Before
	public void setUp() throws IOException {
		IPlLeagueAnalyser=new IPlLeagueAnalyser();
		IPlLeagueAnalyser.loadDataToList(FILE_PATH);
	}

	@Test
	public void givenIplDataWhenCalculatedBattingAverageShouldReturnExactAnswer() {
		assertEquals(101,IPlLeagueAnalyser.loadCSVData(FILE_PATH));
	}
	
	@Test
	public void givenIplDataCSVFileReturnsTop3BattingAverages() throws Exception {
		List<IPLBatting> topBattingAverage = IPlLeagueAnalyser.getTopBattingAverages(FILE_PATH);
		assertEquals(83.2, topBattingAverage .get(0).Average(),0.0);
		
	}
	
	@Test
	public void givenIplDataCSVFileReturnsTop3StrikeRates() throws Exception {

		List<IPLBatting> topStrikeRate = IPlLeagueAnalyser.getTopStrikingRates(FILE_PATH);
		assertEquals(333.33, topStrikeRate.get(0).StrikeRate(), 0.0);
		assertEquals(204.81, topStrikeRate.get(1).StrikeRate(), 0.0);
		assertEquals(200.00, topStrikeRate.get(2).StrikeRate(), 0.0);
	}
}
