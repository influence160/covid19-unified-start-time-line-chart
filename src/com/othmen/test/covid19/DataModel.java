package com.othmen.test.covid19;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Lookup;

import com.othmen.test.covid19.data.DataCache;
import com.othmen.test.covid19.seriesdatasource.FirstCaseDateX_NewCasesPerPopulationY_SeriesDataAdapter;
import com.othmen.test.covid19.seriesdatasource.FirstCaseDateX_TotalCasesPerPopulationY_SeriesDataAdapter;
import com.othmen.test.covid19.seriesdatasource.SeriesDataAdater;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Series;

@Named
@Singleton
public class DataModel {
	
	public ObservableList<Series<Integer, Double>> observableList;
	DataCache dataCache;
	
	public DataModel() {
		observableList = FXCollections.observableArrayList();
	}
	
	public void refresh(String[] selectedCountries) {
		observableList.clear();
//		SeriesDataAdater seriesDataAdapter = getPopulationFirstCaseRelativeSeriesDataAdapter(selectedCountries);
		SeriesDataAdater seriesDataAdapter = getTotalCasesPopulationFirstCaseRelativeSeriesDataAdapter(selectedCountries);
		List<Series<Integer, Double>> SeriesList = seriesDataAdapter.getSeriesList();
		observableList.addAll(SeriesList);
	}

//	private Series<Integer, Double> getSeries(Map<String, List<MesureDeContaminations>> table, String countryId) {
//		Series<Integer, Double> series = new Series<>();
//		series.setName(countryId);
//		
//		
//		List<javafx.scene.chart.XYChart.Data<Integer, Double>> seriesData = new ArrayList<>();
//		
//
//		series.setData(FXCollections.observableArrayList(seriesData));
//		return series;
//	}

	
	@Lookup()
	public FirstCaseDateX_NewCasesPerPopulationY_SeriesDataAdapter getPopulationFirstCaseRelativeSeriesDataAdapter(String[] selectedCountries){
		throw new RuntimeException("ceci devrais etre overrided");
	}
	
	@Lookup()
	public FirstCaseDateX_TotalCasesPerPopulationY_SeriesDataAdapter getTotalCasesPopulationFirstCaseRelativeSeriesDataAdapter(String[] selectedCountries){
		throw new RuntimeException("ceci devrais etre overrided");
	}
}
