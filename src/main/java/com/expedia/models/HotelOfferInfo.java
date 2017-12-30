package com.expedia.models;

import java.util.Date;

public class HotelOfferInfo {
	
	public HotelOfferInfo() {
		super();
	}
	
	private String regionID;
	private String destinationName;
	private String city;
	private Date travelStartDate;
	private Date travelEndDate;
	private Integer lengthOfStay;
	private String hotelName;
	private String hotelImageUrl;
	private Integer numberOfRoomsLeft;
	private Double hotelStarRating;
	private Double hotelGuestReviewRating;
	private Integer hotelReviewTotal;
	
	private Double averagePriceValue;
	
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getAveragePriceValue() {
		return averagePriceValue;
	}

	public void setAveragePriceValue(Double averagePriceValue) {
		this.averagePriceValue = averagePriceValue;
	}

	@Override
	public String toString() {
		return "HotelOfferInfo [regionID=" + regionID + ", destinationName=" + destinationName + ", city=" + city
				+ ", travelStartDate=" + travelStartDate + ", travelEndDate=" + travelEndDate + ", lengthOfStay="
				+ lengthOfStay + ", hotelName=" + hotelName + ", hotelImageUrl=" + hotelImageUrl
				+ ", numberOfRoomsLeft=" + numberOfRoomsLeft + ", hotelStarRating=" + hotelStarRating
				+ ", hotelGuestReviewRating=" + hotelGuestReviewRating + ", hotelReviewTotal=" + hotelReviewTotal
				+ ", averagePriceValue=" + averagePriceValue + "]";
	}
	
	public String getRegionID() {
		return regionID;
	}
	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public Date getTravelStartDate() {
		return travelStartDate;
	}
	public void setTravelStartDate(Date travelStartDate) {
		this.travelStartDate = travelStartDate;
	}
	public Date getTravelEndDate() {
		return travelEndDate;
	}
	public void setTravelEndDate(Date travelEndDate) {
		this.travelEndDate = travelEndDate;
	}
	public Integer getLengthOfStay() {
		return lengthOfStay;
	}
	public void setLengthOfStay(Integer lengthOfStay) {
		this.lengthOfStay = lengthOfStay;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelImageUrl() {
		return hotelImageUrl;
	}
	public void setHotelImageUrl(String hotelImageUrl) {
		this.hotelImageUrl = hotelImageUrl;
	}
	public Integer getNumberOfRoomsLeft() {
		return numberOfRoomsLeft;
	}
	public void setNumberOfRoomsLeft(Integer numberOfRoomsLeft) {
		this.numberOfRoomsLeft = numberOfRoomsLeft;
	}
	public Double getHotelStarRating() {
		return hotelStarRating;
	}
	public void setHotelStarRating(Double hotelStarRating) {
		this.hotelStarRating = hotelStarRating;
	}
	public Double getHotelGuestReviewRating() {
		return hotelGuestReviewRating;
	}
	public void setHotelGuestReviewRating(Double hotelGuestReviewRating) {
		this.hotelGuestReviewRating = hotelGuestReviewRating;
	}
	public Integer getHotelReviewTotal() {
		return hotelReviewTotal;
	}
	public void setHotelReviewTotal(Integer hotelReviewTotal) {
		this.hotelReviewTotal = hotelReviewTotal;
	}

}
