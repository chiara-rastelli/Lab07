package it.polito.tdp.poweroutages.model;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO dbPwOutage = new PowerOutageDAO();
	List<PowerOutage> daControllare;
	List<PowerOutage> worstCase;
	List<PowerOutage> parziale;
	int totPeopleAffectedWorstCase;
	int maxMin;
	int maxAnni;
	
	public List<String> getListaNerc() {
		
		List<String> daRitornare = new LinkedList<String>();
		for (Nerc n : this.dbPwOutage.getNercList()) {
			daRitornare.add(n.getValue());
		}
		return daRitornare;
	}
	
	PowerOutageDAO podao;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	private List<PowerOutage> getPowerOutageDatoNERC(int idNerc){
		return this.dbPwOutage.getPowerOutageList(idNerc);
	}
	
	public int getNumeroMin(LocalTime time) {
	  return time.getHour()*60+time.getMinute();
	}
	
	//QUANDO CALCOLO IL TEMPO IN MINUTI TUTTO OK MA QUANDO LO CALCOLO IN ORE ARROTONDA
	//PER DIFETTO, DA RIVEDERE
	
	public double getNumeroOre(int min) {
		return min/60;
	}
	
	public int getTempoTot(List<PowerOutage> parziale) {
		int daRitornare = 0;
		for (PowerOutage p : parziale) {
			daRitornare += this.getNumeroMin(p.getTime());
		}
		return daRitornare;
	}
	
	public int getTotPeopleAffected(List<PowerOutage> parziale) {
		int daRitornare = 0;
		for (PowerOutage p : parziale) {
			daRitornare += p.getCustomersAffected();
		}
		return daRitornare;
	}
	
	public List<PowerOutage> calcolaWorstCase(int idNerc, int maxOre, int maxAnni) {
		
		this.daControllare = this.getPowerOutageDatoNERC(idNerc);
		
		this.worstCase = new LinkedList<PowerOutage>();
		this.totPeopleAffectedWorstCase = 0;
		this.maxMin= maxOre*60;
		this.maxAnni= maxAnni;
		this.parziale = new LinkedList<PowerOutage>();
		
		this.cerca(parziale, 0);
		
		return this.worstCase;
	}
	
	public void cerca(List<PowerOutage> parziale, int livello) {
		
		//calcolo casi terminali:
		// A) se il tempo totale supera quello indicato su console, esco
		if (this.getTempoTot(parziale) > this.maxMin)
			return;
		
		// B) se il numero di anni totale supera quello indicato, esco
		if (this.getAnniTot(parziale) > this.maxAnni)
			return;
		
		// C) se arrivo alla fine dell'elenco dei guasti, esco
		if (livello == this.daControllare.size())
			return;
		
		// COME FACCIO A SAPERE CHE MI POSSO FERMARE CIOE' NON E' COME NEL CASO 
		// DEI VOTI NOBEL ECC CHE SO ESATTAMENTE CHE DEVO RAGGIUNGERE UN NUMERO DI CREDITI
		// SO SOLO CHE NON DEVO SUPERARE UN CERTO VALORE 
		
		// se la soluzione parziale e' migliore di quella vecchia, sostituisco
	    if (this.getTotPeopleAffected(parziale) > this.totPeopleAffectedWorstCase) {
	    	this.worstCase = new LinkedList<>(parziale);
	    	this.totPeopleAffectedWorstCase = this.getTotPeopleAffected(parziale);
	    }
		
		//generiamo i sotto-problemi
		//daControllare[livello] è da aggiungere o no? Provo entrambe le cose
				
			//provo ad aggiungerlo
			parziale.add(daControllare.get(livello));
			cerca(parziale, livello+1);
			parziale.remove(daControllare.get(livello));
				
			//provo a non aggiungerlo
			cerca(parziale, livello+1);
		
	}

	private int getAnniTot(List<PowerOutage> parziale) {
		int annoMin = 0;
		int annoMax = 0;
		for (PowerOutage p : parziale) {
			int annoP = p.getYear();
			if (annoMin == 0)
				annoMin = annoP;
			if (annoP<annoMin)
				annoMin = annoP;
			if (annoP > annoMax)
				annoMax = annoP;
		}
		
		return annoMax-annoMin+1;
	}

}
