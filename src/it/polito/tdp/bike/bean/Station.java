package it.polito.tdp.bike.bean;

import java.util.Date;

public class Station {

	private int stationId ;//station ID number
	private String name ; //name of station
	private double lat ; //latitude
	private double lng ; //longitude
	private int dockCount ; //number of total docks at station
	private String landmark ; //city (San Francisco, Redwood City, Palo Alto, Mountain View, San Jose)
	private Date installation ; //date that station was installed
	private int totDurata;
	
	public Station(int stationId, String name, double lat, double lng,
			int dockCount, String landmark, Date installation) {
		super();
		this.stationId = stationId;
		this.name = name;
		this.lat = lat;
		this.lng = lng;
		this.dockCount = dockCount;
		this.landmark = landmark;
		this.installation = installation;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public int getDockCount() {
		return dockCount;
	}

	public void setDockCount(int dockCount) {
		this.dockCount = dockCount;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Date getInstallation() {
		return installation;
	}

	public void setInstallation(Date installation) {
		this.installation = installation;
	}

	/**
	 * @return the totDurata
	 */
	public int getTotDurata() {
		return totDurata;
	}

	/**
	 * @param totDurata the totDurata to set
	 */
	public void setTotDurata(int totDurata) {
		this.totDurata = totDurata;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stationId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (stationId != other.stationId)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}
	
	
	
	
}
