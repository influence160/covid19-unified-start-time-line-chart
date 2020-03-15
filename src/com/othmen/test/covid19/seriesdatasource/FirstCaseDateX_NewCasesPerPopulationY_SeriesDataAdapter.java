package com.othmen.test.covid19.seriesdatasource;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.othmen.test.covid19.data.MesureDeContaminations;

import javafx.collections.FXCollections;
import javafx.scene.chart.XYChart.Series;

@Named
@Scope("prototype")
public class FirstCaseDateX_NewCasesPerPopulationY_SeriesDataAdapter extends SeriesDataAdater{
	
	public static final int POPOLATION_NUMBER_REFERENCE = 10000000;

	@Inject
	public FirstCaseDateX_NewCasesPerPopulationY_SeriesDataAdapter(String[] selectedCountries) {
		super(selectedCountries);
	}

	@Override
	public List<Series<Integer, Double>> getSeriesList() {
		List<Series<Integer, Double>> result = new ArrayList<>(selectedCountries.length);
		for (String selectedCountrie : selectedCountries) {
			result.add(getSeries(selectedCountrie));
		}
		return result;
	}

	@Override
	public Series<Integer, Double> getSeries(String country) {
		Series<Integer, Double> series = new Series<>();
		series.setName(country);
		List<javafx.scene.chart.XYChart.Data<Integer, Double>> seriesData = new ArrayList<>();
		
		int population = getPopulation(country);
		List<MesureDeContaminations> mesures = table.get(country);
		int previousMesureNombre = 0;
		LocalDate debut = null;
		for (MesureDeContaminations mesure : mesures) {
			int nouveauxCas = mesure.getNombre() - previousMesureNombre;
			previousMesureNombre = mesure.getNombre();

			Double totalCasParPopulation = ( (double)mesure.getNombre() / population) * POPOLATION_NUMBER_REFERENCE;
			Double nouveauxCasParPopulation = ( (double)nouveauxCas / population) * POPOLATION_NUMBER_REFERENCE;
			if (totalCasParPopulation < 1 && debut == null) {
				continue;
			} 
			if (debut == null){
				debut = mesure.getDate();
			}
//			int x = (int) Duration.between(mesure.getDate(), debut).get(ChronoUnit.DAYS);
			int x = (int) ChronoUnit.DAYS.between(debut, mesure.getDate());
			javafx.scene.chart.XYChart.Data<Integer, Double> donnees = new javafx.scene.chart.XYChart.Data<>(x, nouveauxCasParPopulation);
			seriesData.add(donnees);
		}
		
		series.setData(FXCollections.observableArrayList(seriesData));
		return series;
	}

}
