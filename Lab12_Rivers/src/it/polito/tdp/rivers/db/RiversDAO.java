package it.polito.tdp.rivers.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.FlowIdMap;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.RiverIdMap;

public class RiversDAO {

	public List<River> getAllRivers(RiverIdMap riverIdMap) {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(riverIdMap.get(new River(res.getInt("id"), res.getString("name"))));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	

	public List<River> getAllFlowsByRiver(River river, RiverIdMap riverIdMap) {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}


	public List<Flow> getListOfFlowByRiver(River selected, FlowIdMap flowIdMap ,RiverIdMap riverIdMap) {
		final String sql = "select day ,flow, river " + 
				"from flow , river " + 
				"where flow.river=river.id " + 
				"and flow.river = ?";

		List<Flow> flows = new ArrayList<Flow>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, selected.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Date date = res.getDate("day");
				LocalDate data = date.toLocalDate();
				//eventuale aggiornamento mappa river
				River river = riverIdMap.get(selected);
				//al flow setto il river
				flowIdMap.get(new Flow(data, res.getFloat("flow"),river)).setRiver(river);
				//aggiungo il flow alla lista
				flows.add(flowIdMap.get(new Flow(data, res.getFloat("flow"),river)));
				
				
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return flows;
		}
}
