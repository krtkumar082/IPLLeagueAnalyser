package com.iplanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	    IPLBattingList = new CsvToBeanBuilder(reader).withType(IPLBatting.class).build().parse();
	}
	
	
}
