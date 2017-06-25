package it.polito.tdp.bike.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import it.polito.tdp.bike.bean.Event.EventType;

public class Simulator {

	//Parametri di simulazione --> impostati all'inizio da chi fa simulazione
	Map<Integer,Station> stations;
	
		
	//Stato del modello
	private Map<Station, Integer> occupazione;
	
	//Variabili di interesse
	private int lostPick = 0;
	private int lostDrop = 0;
	
	//Lista degli eventi
	private PriorityQueue<Event> queue;
	
	public Simulator(List<Trip> trips, Map<Integer,Station> stations){
		this.stations = stations;
		this.queue = new PriorityQueue<>();
		this.occupazione = new HashMap<>();
		//riempo mappa
		for(Station s : stations.values()){
			int occupazione = s.getDockCount()/2;
			this.occupazione.put(s, occupazione);
		}
		//riempo coda degli eventi --> solo pick
		for(Trip t : trips){
			Event e = new Event(t.getStartDate(),EventType.PICK,t);
			queue.add(e);
		}
	}
	
	public void run(){
		System.out.println(occupazione);
		while(!queue.isEmpty()){
			Event e = queue.poll();
			int o = occupazione.get(stations.get(e.getT().getStartTerminal()));
			switch(e.getType()){
			case PICK:
				
				if(o>0){
					o--;
					occupazione.put(stations.get(e.getT().getStartTerminal()), o);
					queue.add(new Event(e.getT().getEndDate(),EventType.DROP,e.getT()));
				}else{
					this.lostPick++;
				}
				System.out.println("Pick "+o+" "+lostPick);
				break;
			case DROP:
				o=occupazione.get(stations.get(e.getT().getEndTerminal()));
				if(o<stations.get(e.getT().getEndTerminal()).getDockCount()){
					o++;
					occupazione.put(stations.get(e.getT().getEndTerminal()), o);
				}else{
					//stazione piena
					this.lostDrop++;
				}
				System.out.println("Drop "+o+" "+lostDrop);
				break;
			default:
				break;
			}
		}
		System.out.println(occupazione);
	}
	
	public int getLostPick(){
		return this.lostPick;
	}
	public int getLostDrop(){
		return this.lostDrop;
	}
	
	
	
}
