package it.polito.tdp.poweroutages.model;

import java.time.LocalTime;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
	//	System.out.println(model.getNercList());

	//	int minTemp = model.getNumeroMin(LocalTime.now());
	//	System.out.println(model.getNumeroOre(minTemp));
	//	System.out.println(minTemp/60);
	//	System.out.println(minTemp);
		
		List<PowerOutage> risultatoRicorsione = model.calcolaWorstCase(1, 200, 4);
		System.out.println("Tot people affected: "+model.getTotPeopleAffected(risultatoRicorsione));
		System.out.println("Tot hours: "+(float)model.getTempoTot(risultatoRicorsione)/60);
		for(PowerOutage p : risultatoRicorsione) {
			System.out.println(p);
		}
		
	}
	
}
