package com.othmen.test.covid19.seriesdatasource;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;

import com.othmen.test.covid19.data.DataCache;
import com.othmen.test.covid19.data.MesureDeContaminations;

import javafx.scene.chart.XYChart.Series;

public abstract class SeriesDataAdater {

	@Inject
	protected DataCache cache;
	
	protected Map<String, List<MesureDeContaminations>> table;
	
	protected String[] selectedCountries;

	public SeriesDataAdater(String[] selectedCountries) {

		this.selectedCountries = selectedCountries;

	}
	
	@PostConstruct
	public void init() {
		table = cache.getTable(selectedCountries);
	}
	
	protected int getPopulation(String country) {
		return Integer.parseInt((String)cache.countriesPopulations.get(country));
	}

	public abstract List<Series<Integer, Double>> getSeriesList();
	
	public abstract Series<Integer, Double> getSeries(String country);

}
