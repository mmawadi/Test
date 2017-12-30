package com.expedia.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.expedia.models.*;

public class FilterBusiness {

	public List<HotelOfferInfo> FilterHotels(List<HotelOfferInfo> allHotels, FilterOptions filter) {

		if (allHotels == null) {
			return null;
		}

		if (allHotels.size() <= 0) {
			return null;
		}

		if (filter == null) {
			return allHotels;
		}

		List<HotelOfferInfo> filteredHotel = FilterHotelsByGoingTo(allHotels, filter.getGoingTo());
		
		if(filteredHotel == null)
		{
		  return null;
		}
		
		filteredHotel = FilterHotelsByStartDate(filteredHotel, filter.getCheckInDate());
		
		if(filteredHotel == null)
		{
		  return null;
		}
		
		filteredHotel = FilterHotelsByEndDate(filteredHotel, filter.getCheckOutDate());
		
		if(filteredHotel == null)
		{
		  return null;
		}

		filteredHotel = FilterHotelsByLengthOfStay(filteredHotel, filter.getLengthOfStay());

		if (filteredHotel == null) {
			return null;
		}
		
		filteredHotel = FilterHotelsByMinTotalReview(filteredHotel, filter.getMinTotalRate());

		if (filteredHotel == null) {
			return null;
		}
		
		filteredHotel = FilterHotelsByMaxTotalReview(filteredHotel, filter.getMaxTotalRate());

		if (filteredHotel == null) {
			return null;
		}
		
		filteredHotel = FilterHotelsByMinStarRate(filteredHotel, filter.getMinStarRate());

		if (filteredHotel == null) {
			return null;
		}
		
		filteredHotel = FilterHotelsByMaxStarRate(filteredHotel, filter.getMaxStarRate());

		if (filteredHotel == null) {
			return null;
		}
		
		filteredHotel = FilterHotelsByMinGuestRate(filteredHotel, filter.getMinGuestRate());

		if (filteredHotel == null) {
			return null;
		}
		
		filteredHotel = FilterHotelsByMaxGuestRate(filteredHotel, filter.getMaxGuestRate());

		if (filteredHotel == null) {
			return null;
		}
		
		return filteredHotel;
	}

	private List<HotelOfferInfo> FilterHotelsByGoingTo(List<HotelOfferInfo> allHotels, String goingTo) {

		if (allHotels == null) {
			return null;
		}

		if (allHotels.size() <= 0) {
			return null;
		}

		if (goingTo.isEmpty()) {
			return allHotels;
		}

		List<HotelOfferInfo> filteredHotel = new ArrayList<HotelOfferInfo>();
		for(int i= 0; i < allHotels.size(); i++)
		{
			HotelOfferInfo hotel = allHotels.get(i);
			if(hotel.getDestinationName().toUpperCase().contains(goingTo.toUpperCase()) ||
					hotel.getRegionID().equals(goingTo))
			{
				filteredHotel.add(hotel);
			}
					
		}
//		List<HotelOfferInfo> filteredHotel = allHotels.stream() // convert list to stream
//				.filter(hotel -> Pattern.compile(Pattern.quote(goingTo), Pattern.CASE_INSENSITIVE)
//						.matcher(hotel.getDestinationName()).find() || hotel.getRegionID().equals(goingTo))
//				.collect(Collectors.toList()); // collect the output and convert streams to a List

		return filteredHotel;
	}
	
	private List<HotelOfferInfo> FilterHotelsByStartDate(List<HotelOfferInfo> allHotels, Date startDate) {

		if (allHotels == null) {
			return null;
		}

		if (allHotels.size() <= 0) {
			return null;
		}

		if (startDate  == null) {
			return allHotels;
		}

		
		List<HotelOfferInfo> filteredHotel = new ArrayList<HotelOfferInfo>();
		for(int i= 0; i < allHotels.size(); i++)
		{
			HotelOfferInfo hotel = allHotels.get(i);
			if(hotel.getTravelStartDate().after(startDate) && !hotel.getTravelStartDate().before(startDate))
			{
				filteredHotel.add(hotel);
			}
					
		}
		
//		List<HotelOfferInfo> filteredHotel = allHotels.stream() // convert list to stream
//				.filter(hotel -> hotel.getTravelStartDate().after(startDate) && !hotel.getTravelStartDate().before(startDate))
//				.collect(Collectors.toList()); // collect the output and convert streams to a List

		return filteredHotel;
	}
	
	private List<HotelOfferInfo> FilterHotelsByEndDate(List<HotelOfferInfo> allHotels, Date endDate) {

		if (allHotels == null) {
			return null;
		}

		if (allHotels.size() <= 0) {
			return null;
		}

		if (endDate  == null) {
			return allHotels;
		}

		
		List<HotelOfferInfo> filteredHotel = new ArrayList<HotelOfferInfo>();
		for(int i= 0; i < allHotels.size(); i++)
		{
			HotelOfferInfo hotel = allHotels.get(i);
			if(hotel.getTravelEndDate().before(endDate) && ! hotel.getTravelEndDate().after(endDate))
			{
				filteredHotel.add(hotel);
			}
					
		}
		
//		List<HotelOfferInfo> filteredHotel = allHotels.stream() // convert list to stream
//				.filter(hotel -> hotel.getTravelEndDate().before(endDate) && ! hotel.getTravelEndDate().after(endDate))
//				.collect(Collectors.toList()); // collect the output and convert streams to a List

		return filteredHotel;
	}
	
	private List<HotelOfferInfo> FilterHotelsByLengthOfStay(List<HotelOfferInfo> allHotels, Integer lengthStay) {

		if (allHotels == null) {
			return null;
		}

		if (allHotels.size() <= 0) {
			return null;
		}

		if (lengthStay  == null) {
			return allHotels;
		}
		
		if(lengthStay <= 0){
			return allHotels;
		}
 		
		List<HotelOfferInfo> filteredHotel = new ArrayList<HotelOfferInfo>();
		for(int i= 0; i < allHotels.size(); i++)
		{
			HotelOfferInfo hotel = allHotels.get(i);
			if(hotel.getLengthOfStay() >= lengthStay)
			{
				filteredHotel.add(hotel);
			}
					
		}
//		List<HotelOfferInfo> filteredHotel = allHotels.stream() // convert list to stream
//				.filter(hotel -> hotel.getLengthOfStay() >= lengthStay)
//				.collect(Collectors.toList()); // collect the output and convert streams to a List


		return filteredHotel;
	}
	
	private List<HotelOfferInfo> FilterHotelsByMaxTotalReview(List<HotelOfferInfo> allHotels, Integer maxTotalReview) {

		if (allHotels == null) {
			return null;
		}

		if (allHotels.size() <= 0) {
			return null;
		}

		if (maxTotalReview  == null) {
			return allHotels;
		}
		
		if(maxTotalReview <= 0){
			return allHotels;
		}
		
		List<HotelOfferInfo> filteredHotel = new ArrayList<HotelOfferInfo>();
		for(int i= 0; i < allHotels.size(); i++)
		{
			HotelOfferInfo hotel = allHotels.get(i);
			if(hotel.getHotelReviewTotal() <= maxTotalReview)
			{
				filteredHotel.add(hotel);
			}
					
		}
 		
//		List<HotelOfferInfo> filteredHotel = allHotels.stream() // convert list to stream
//				.filter(hotel -> hotel.getHotelReviewTotal() <= maxTotalReview)
//				.collect(Collectors.toList()); // collect the output and convert streams to a List

		return filteredHotel;
	}
	
	private List<HotelOfferInfo> FilterHotelsByMinTotalReview(List<HotelOfferInfo> allHotels, Integer minTotalReview) {

		if (allHotels == null) {
			return null;
		}

		if (allHotels.size() <= 0) {
			return null;
		}

		if (minTotalReview  == null) {
			return allHotels;
		}
		
		if(minTotalReview <= 0){
			return allHotels;
		}
		
		List<HotelOfferInfo> filteredHotel = new ArrayList<HotelOfferInfo>();
		for(int i= 0; i < allHotels.size(); i++)
		{
			HotelOfferInfo hotel = allHotels.get(i);
			if(hotel.getHotelReviewTotal() >= minTotalReview)
			{
				filteredHotel.add(hotel);
			}					
		}
 		
//		List<HotelOfferInfo> filteredHotel = allHotels.stream() // convert list to stream
//				.filter(hotel -> hotel.getHotelReviewTotal() >= minTotalReview)
//				.collect(Collectors.toList()); // collect the output and convert streams to a List

		return filteredHotel;
	}
	
	private List<HotelOfferInfo> FilterHotelsByMinStarRate(List<HotelOfferInfo> allHotels, Double minStarRate) {

		if (allHotels == null) {
			return null;
		}

		if (allHotels.size() <= 0) {
			return null;
		}

		if (minStarRate  == null) {
			return allHotels;
		}
		
		if(minStarRate <= 0){
			return allHotels;
		}
 		
		List<HotelOfferInfo> filteredHotel = new ArrayList<HotelOfferInfo>();
		for(int i= 0; i < allHotels.size(); i++)
		{
			HotelOfferInfo hotel = allHotels.get(i);
			if(hotel.getHotelStarRating() >= minStarRate)
			{
				filteredHotel.add(hotel);
			}					
		}
		
//		List<HotelOfferInfo> filteredHotel = allHotels.stream() // convert list to stream
//				.filter(hotel -> hotel.getHotelStarRating() >= minStarRate)
//				.collect(Collectors.toList()); // collect the output and convert streams to a List

		return filteredHotel;
	}
	
	private List<HotelOfferInfo> FilterHotelsByMaxStarRate(List<HotelOfferInfo> allHotels, Double maxStarRate) {

		if (allHotels == null) {
			return null;
		}

		if (allHotels.size() <= 0) {
			return null;
		}

		if (maxStarRate  == null) {
			return allHotels;
		}
		
		if(maxStarRate <= 0){
			return allHotels;
		}
 		
		List<HotelOfferInfo> filteredHotel = new ArrayList<HotelOfferInfo>();
		for(int i= 0; i < allHotels.size(); i++)
		{
			HotelOfferInfo hotel = allHotels.get(i);
			if(hotel.getHotelStarRating() <= maxStarRate)
			{
				filteredHotel.add(hotel);
			}					
		}
		
//		List<HotelOfferInfo> filteredHotel = allHotels.stream() // convert list to stream
//				.filter(hotel -> hotel.getHotelStarRating() <= maxStarRate)
//				.collect(Collectors.toList()); // collect the output and convert streams to a List

		return filteredHotel;
	}
	
	private List<HotelOfferInfo> FilterHotelsByMinGuestRate(List<HotelOfferInfo> allHotels, Double minGuestRate) {

		if (allHotels == null) {
			return null;
		}

		if (allHotels.size() <= 0) {
			return null;
		}

		if (minGuestRate  == null) {
			return allHotels;
		}
		
		if(minGuestRate <= 0){
			return allHotels;
		}
 		
		List<HotelOfferInfo> filteredHotel = new ArrayList<HotelOfferInfo>();
		for(int i= 0; i < allHotels.size(); i++)
		{
			HotelOfferInfo hotel = allHotels.get(i);
			if(hotel.getHotelGuestReviewRating() >= minGuestRate)
			{
				filteredHotel.add(hotel);
			}					
		}
		
//		List<HotelOfferInfo> filteredHotel = allHotels.stream() // convert list to stream
//				.filter(hotel -> hotel.getHotelGuestReviewRating() >= minGuestRate)
//				.collect(Collectors.toList()); // collect the output and convert streams to a List

		return filteredHotel;
	}
	
	private List<HotelOfferInfo> FilterHotelsByMaxGuestRate(List<HotelOfferInfo> allHotels, Double maxGuestRate) {

		if (allHotels == null) {
			return null;
		}

		if (allHotels.size() <= 0) {
			return null;
		}

		if (maxGuestRate  == null) {
			return allHotels;
		}
		
		if(maxGuestRate <= 0){
			return allHotels;
		}
 		
		List<HotelOfferInfo> filteredHotel = new ArrayList<HotelOfferInfo>();
		for(int i= 0; i < allHotels.size(); i++)
		{
			HotelOfferInfo hotel = allHotels.get(i);
			if(hotel.getHotelGuestReviewRating() <= maxGuestRate)
			{
				filteredHotel.add(hotel);
			}					
		}
		
//		List<HotelOfferInfo> filteredHotel = allHotels.stream() // convert list to stream
//				.filter(hotel -> hotel.getHotelGuestReviewRating() <= maxGuestRate)
//				.collect(Collectors.toList()); // collect the output and convert streams to a List

		return filteredHotel;
	}
}
