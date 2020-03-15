package com.othmen.test.covid19;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;

@Named
@Singleton
public class MainController implements Initializable {
	
	private static final String[] INITIAL_SELECTED_COUNTRIES = new String[] {"China", "Italy", "Iran", "SouthKorea", "Spain", 
			"Germany", "France", "US", "Tunisia"};
	
//	private static final String[] INITIAL_SELECTED_COUNTRIES = new String[] {"China", "Italy", "Switzerland", "Sweden", "Denmark", "Iceland", "Ireland"};
//	private static final String[] INITIAL_SELECTED_COUNTRIES = new String[] {"China", "UnitedArabEmirates", "Taiwan", "Finland", "Japan", "France" , "Thailand"};
	
	@Inject
	DataModel dataModel;
	
	@FXML
	LineChart<Integer, Double> lineChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		data = new Data();
		refreshData();
		lineChart.setData(dataModel.observableList);
		lineChart.getYAxis().setLabel("New Cases/10 000 000 Population");
//		lineChart.getYAxis().setLabel("Total cases/10 000 000 Population");
		
	}

	private void refreshData() {
		String[] selectedCountries = getSelectedCountries();
		dataModel.refresh(selectedCountries);
	}

	private String[] getSelectedCountries() {
		return INITIAL_SELECTED_COUNTRIES;
	}

}
