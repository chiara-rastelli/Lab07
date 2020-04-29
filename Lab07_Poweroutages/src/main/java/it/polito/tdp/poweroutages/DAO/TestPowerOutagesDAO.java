package it.polito.tdp.poweroutages.DAO;

import java.util.List;
import java.sql.Connection;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class TestPowerOutagesDAO {

	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			dao.getNercList();
			
			List<PowerOutage> lista = dao.mappaNerc.get(2);
		//	List<PowerOutage> lista = dao.getPowerOutageList(8);
			for(PowerOutage p : lista) {
			System.out.println(p);
			}
			
		} catch (Exception e) {
			System.err.println("Test FAILED");
		}
		
	}

}
