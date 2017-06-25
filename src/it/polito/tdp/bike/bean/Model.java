package it.polito.tdp.bike.bean;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.bike.db.BikeDAO;

public class Model {
	
	Map<Integer, Station> stationMap;
	public Collection<Station> getAllStation(){
		
		if(stationMap==null){
			this.stationMap = new HashMap<>();
			BikeDAO dao = new BikeDAO();
			for(Station s : dao.getAllStations()){
				this.stationMap.put(s.getStationId(), s);
			}
		}
		return stationMap.values();
	}
	
	
	public List<String> getLandmarks(){
		BikeDAO dao = new BikeDAO();
		List<String> l = dao.getAllLandmarks();
		Collections.sort(l);
		return l;
	}
	
	public List<Station> getElencoStazioni(String landmark){
		BikeDAO dao = new BikeDAO();
		this.getAllStation();
		return dao.getStationOfCity(landmark, stationMap);
	}

	public List<Trip> getAllTrips(LocalDate d){
		BikeDAO dao = new BikeDAO();
		return dao.getAllTrips(d);
		
	}
	
	public String simula(LocalDate d){
		String res = "";
		this.getAllStation();
		Simulator s = new Simulator(this.getAllTrips(d),this.stationMap);
		s.run();
		res= String.format("Prese mancate: %d\nRitorni mancati: %d", s.getLostPick(),s.getLostDrop());
		
		return res;
	}
	
	
}
