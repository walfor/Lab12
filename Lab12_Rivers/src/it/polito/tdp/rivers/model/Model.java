package it.polito.tdp.rivers.model;

import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	RiversDAO dao;
	RiverIdMap riverIdMap ;
	FlowIdMap flowIdMap ;
	Simulazione sim ;

	public Model() {
		dao = new RiversDAO();
		riverIdMap = new RiverIdMap();
		flowIdMap = new FlowIdMap();
		
	}

	
	
	
	
	
	
	
	
	
	public List<River> getAllRivers() {
		return dao.getAllRivers(riverIdMap);
		
	}










	public List<Flow> getListOfFlowByRiver(River selected) {
		//aggiungo la lista di flow al river
		selected.addAll(dao.getListOfFlowByRiver(selected, flowIdMap, riverIdMap));
		
		return this.dao.getListOfFlowByRiver(selected, flowIdMap, riverIdMap);
	}










	public float calcolaMedia(List<Flow> flows) {
		float num=0;
		
		for(Flow f : flows) {
			num+=f.getFlow();
		}
		return num/(float)(flows.size());
	}
	
	public void creaSimulazione(float f_med,int k) {
		sim= new Simulazione(f_med,k);
	}
}
