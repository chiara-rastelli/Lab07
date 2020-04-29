package it.polito.tdp.poweroutages.model;

import java.time.LocalTime;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
	//	System.out.println(model.getNercList());

		int minTemp = model.getNumeroMin(LocalTime.now());
	//	System.out.println(model.getNumeroOre(minTemp));
	//	System.out.println(minTemp/60);
		System.out.println(minTemp);
		
	}
	
}
