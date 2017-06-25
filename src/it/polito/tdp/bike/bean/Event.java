package it.polito.tdp.bike.bean;

import java.time.LocalDateTime;

public class Event implements Comparable<Event>{
	private LocalDateTime time;
	public enum EventType{
		PICK, DROP
	}
	private EventType type;
	private Trip t;
	
	
	public Event(LocalDateTime time, EventType type, Trip t) {
		super();
		this.time = time;
		this.type = type;
		this.t = t;
	}

	

	/**
	 * @return the time
	 */
	public LocalDateTime getTime() {
		return time;
	}



	/**
	 * @param time the time to set
	 */
	public void setTime(LocalDateTime time) {
		this.time = time;
	}



	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(EventType type) {
		this.type = type;
	}



	

	/**
	 * @return the t
	 */
	public Trip getT() {
		return t;
	}



	/**
	 * @param t the t to set
	 */
	public void setT(Trip t) {
		this.t = t;
	}



	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.time);
	}

}
