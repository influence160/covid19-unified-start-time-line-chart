package com.othmen.test.covid19.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@Named
@Singleton
public class DataCache {

	private static final LocalDate MIN_DATE = LocalDate.of(2020, 01, 19);
	private static final LocalDate MAX_DATE = LocalDate.of(2020, 03, 14);
	private static final DateTimeFormatter FILENAMEDATEFORMAT = DateTimeFormatter.ofPattern("MM-dd-YYYY");
	
	public Properties countriesPopulations;
	
	//New contaminations
	public Map<String, List<MesureDeContaminations>> table;
	
	public DataCache() {
		countriesPopulations = new Properties();
		try {
			countriesPopulations.load(DataCache.class.getClassLoader().getResourceAsStream("properties/worldpopulation.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		table = new HashMap<>();
	}
	
	public Map<String, List<MesureDeContaminations>> getTable(String[] countries) {
		List<String> countriess = Arrays.asList(countries);
		loadTable(countries);
		Map<String, List<MesureDeContaminations>> result = table.keySet().stream()
			    .filter(countriess::contains)
			    .collect(Collectors.toMap(Function.identity(), table::get));
		System.out.println(" getTable = " + result);
		return result;
	}

	private void loadTable(String[] countries) {
		List<String> countriesToSearch = new ArrayList<>();
		for (String country : countries) {
			if (!table.containsKey(country)) {
				table.put(country, new ArrayList<MesureDeContaminations>());
				countriesToSearch.add(country);
			}
		}
		
		LocalDate date = MIN_DATE;
		while (date.isBefore(MAX_DATE)) {
			Map<String, Integer> dailyData = loadDailyData(date, countriesToSearch);
			merge(dailyData, date);
			date = date.plusDays(1);
		}
	}

	private void merge(Map<String, Integer> dailyData, LocalDate date) {
		for (String country : dailyData.keySet()) {
			if (!table.containsKey(country)) {
				table.put(country, new ArrayList<>());
			}
			table.get(country).add(new MesureDeContaminations(date, dailyData.get(country)));
		}
		
	}

	private Map<String, Integer> loadDailyData(LocalDate date, List<String> countriesToSearch) {
		Map<String, Integer> result = new HashMap<>();
		
		String fileName = "csv/" + FILENAMEDATEFORMAT.format(date) + ".csv";
		System.out.println("loadDailyData " + date + " " + fileName);
		BufferedReader bis = new BufferedReader(new InputStreamReader(DataCache.class.getClassLoader().getResourceAsStream(fileName)));
		try {
	        CSVParser csvParser = new CSVParser(bis, CSVFormat.DEFAULT);
	        int i = 0;
			 for (CSVRecord csvRecord : csvParser) {
				 if (i == 0) {
					 i = 1;
					 continue;
				 }
					String country = csvRecord.get(1);
					country = mapCsvFileCountryNameToIdName(country);
					if (csvRecord.size() >= 4) {
						String confirmed = csvRecord.get(3);
						if (!confirmed.isEmpty()) {
							if (result.containsKey(country)) {
								result.put(country, result.get(country) + Integer.parseInt(confirmed));
							} else {
								result.put(country, Integer.parseInt(confirmed));
							}
						}
					}
	            }
			 csvParser.close();
			return result;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	private String mapCsvFileCountryNameToIdName(String country) {
		if (country.equals("Mainland China"))
			return "China";
			
		country = country.replace(" ", "");
		return country;
	}


	public static void main(String... args) {
		System.out.println(DataCache.class.getClassLoader().getResourceAsStream("properties/worldpopulation.properties"));
		System.out.println(new DataCache().getTable( new String[] {"China", "Italy", "Iran", "SouthKorea", "Spain", 
			"Germany", "France", "US", "Tunisia"}));
		
	}
}
