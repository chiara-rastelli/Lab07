package it.polito.tdp.poweroutages.model;

import java.time.LocalTime;

public class PowerOutage {
	
	int id;
	int customersAffected;
	LocalTime time;
	int year;
	
	public PowerOutage(int id, int customersAffected, LocalTime time, int year) {
		this.id = id;
		this.customersAffected = customersAffected;
		this.time = time;
		this.year = year;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "PowerOutage [id=" + id + ", customersAffected=" + customersAffected + ", time=" + time + ", year="
				+ year + "]";
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomersAffected() {
		return customersAffected;
	}

	public void setCustomersAffected(int customersAffected) {
		this.customersAffected = customersAffected;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
