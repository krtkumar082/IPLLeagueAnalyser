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
import com.opencsv.bean.CsvToBeanBuilder;

public class IPlLeagueAnalyser {
public static List<IPLBatting> IPLBattingList;
	
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
	
	public void loadDataToList(String csvFile) throws IOException {
		Reader reader=Files.newBufferedReader(Paths.get(csvFile));
	    IPLBattingList =  new CsvToBeanBuilder(reader).withType(IPLBatting.class).withIgnoreLeadingWhiteSpace(true).build().parse();
	}
	
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
	
}
