package com.expedia.models;

import java.util.Date;

public class FilterOptions {
	
	private String goingTo;
	private Date checkInDate;
	private Date checkOutDate;
	private Integer lengthOfStay;
	private Double minStarRate;
	private Double maxStarRate;
	private Integer minTotalRate;
	private Integer maxTotalRate;
	private Double minGuestRate;
	private Double maxGuestRate;
	
	public String getGoingTo() {
		return goingTo;
	}
	public void setGoingTo(String goingTo) {
		this.goingTo = goingTo;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public Integer getLengthOfStay() {
		return lengthOfStay;
	}
	public void setLengthOfStay(Integer lengthOfStay) {
		this.lengthOfStay = lengthOfStay;
	}
	public Double getMinStarRate() {
		return minStarRate;
	}
	public void setMinStarRate(Double minStarRate) {
		this.minStarRate = minStarRate;
	}
	public Double getMaxStarRate() {
		return maxStarRate;
	}
	public void setMaxStarRate(Double maxStarRate) {
		this.maxStarRate = maxStarRate;
	}
	public Integer getMinTotalRate() {
		return minTotalRate;
	}
	public void setMinTotalRate(Integer minTotalRate) {
		this.minTotalRate = minTotalRate;
	}
	public Integer getMaxTotalRate() {
		return maxTotalRate;
	}
	public void setMaxTotalRate(Integer maxTotalRate) {
		this.maxTotalRate = maxTotalRate;
	}
	public Double getMinGuestRate() {
		return minGuestRate;
	}
	public void setMinGuestRate(Double minGuestRate) {
		this.minGuestRate = minGuestRate;
	}
	public Double getMaxGuestRate() {
		return maxGuestRate;
	}
	public void setMaxGuestRate(Double maxGuestRate) {
		this.maxGuestRate = maxGuestRate;
	}
}
