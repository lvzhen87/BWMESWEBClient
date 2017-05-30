package com.mes.webclient.controller.vo;

public class PassStationHistoryVO extends BaseUDTVO{

	private String vin ;
	private String csn;
	private String entry_time;
	private String entry_time_end;
	private String order_number;
	
	private String car_type ;
	private String color;
	private String vehicle_configuration;
	private String engine_type;
	
	private String special_order ;
	private String station;
	private String station_end;
	private String username;
	private String entry_date;
	
	
	public String getEntry_time_end() {
		return entry_time_end;
	}
	public void setEntry_time_end(String entry_time_end) {
		this.entry_time_end = entry_time_end;
	}
	public String getStation_end() {
		return station_end;
	}
	public void setStation_end(String station_end) {
		this.station_end = station_end;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getCsn() {
		return csn;
	}
	public void setCsn(String csn) {
		this.csn = csn;
	}
	public String getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(String entry_time) {
		this.entry_time = entry_time;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getVehicle_configuration() {
		return vehicle_configuration;
	}
	public void setVehicle_configuration(String vehicle_configuration) {
		this.vehicle_configuration = vehicle_configuration;
	}
	public String getEngine_type() {
		return engine_type;
	}
	public void setEngine_type(String engine_type) {
		this.engine_type = engine_type;
	}
	public String getSpecial_order() {
		return special_order;
	}
	public void setSpecial_order(String special_order) {
		this.special_order = special_order;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}
	
	
	
}
