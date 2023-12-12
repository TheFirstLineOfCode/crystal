package com.thefirstlineofcode.crystal.examples.plugins.crud;

import javax.persistence.Embeddable;

@Embeddable
public class Geo {
	private float lat;
	private float lng;
	
	public float getLat() {
		return lat;
	}
	
	public void setLat(float lat) {
		this.lat = lat;
	}
	
	public float getLng() {
		return lng;
	}
	
	public void setLng(float lng) {
		this.lng = lng;
	}
}
