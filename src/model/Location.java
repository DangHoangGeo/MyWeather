package model;

import java.io.Serializable;

public class Location implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cityName;
	private String coutryCode;
	private double lat;
	private double lon;
	
	public Location() {
		// TODO Auto-generated constructor stub
	}
	
	public Location(String cityName, String coutryCode, double lat, double lon) {
		super();
		this.cityName = cityName;
		this.coutryCode = coutryCode;
		this.lat = lat;
		this.lon = lon;
	}

	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCoutryCode() {
		return coutryCode;
	}
	public void setCoutryCode(String coutryCode) {
		this.coutryCode = coutryCode;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
}
