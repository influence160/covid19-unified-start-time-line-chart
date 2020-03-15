package com.othmen.test.covid19.data;

import java.time.LocalDate;

public class MesureDeContaminations {
	
	LocalDate date;
	int nombre;
	
	public MesureDeContaminations(LocalDate date, int nombre) {
		this.date = date;
		this.nombre = nombre;
	}

	public String toString() {
		return "M[" + date.getDayOfMonth() + "-" + date.getMonthValue() + ":" + nombre+"]";
	}

	public LocalDate getDate() {
		return date;
	}

	public int getNombre() {
		return nombre;
	}
	
	
}
