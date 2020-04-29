package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class PowerOutageDAO {
	
	Map<Integer, List<PowerOutage>> mappaNerc = new TreeMap<>();
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				int nercId = res.getInt("id");
				Nerc n = new Nerc(nercId, res.getString("value"));
				if (!mappaNerc.containsKey(nercId))
					this.mappaNerc.put(nercId, this.getPowerOutageList(nercId));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutage> getPowerOutageList(int codNerc) {

		String sql = "SELECT id, customers_affected, YEAR(date_event_began) AS 'anno', timediff (date_event_finished,date_event_began) AS 'durata' FROM poweroutages WHERE nerc_id=?";
		List<PowerOutage> powerOutageList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, codNerc);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				LocalTime durata = res.getTime("durata").toLocalTime();
				PowerOutage n = new PowerOutage(res.getInt("id"), res.getInt("customers_affected"), durata, res.getInt("anno"));
				powerOutageList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powerOutageList;
	}

}
