package com.expedia.business;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.expedia.models.*;

public class FilterBusinessTest extends TestCase {

	private List<HotelOfferInfo> GetListOfHotels() {
		List<HotelOfferInfo> offeredList = new ArrayList<HotelOfferInfo>();
		HotelOfferInfo obj = new HotelOfferInfo();
		obj.setAveragePriceValue(200.0);
		obj.setCity("Amman");
		obj.setDestinationName("Jordan, Amman");
		obj.setHotelGuestReviewRating(3.6);
		obj.setHotelImageUrl("");
		obj.setHotelName("ibes");
		obj.setHotelReviewTotal(500);
		obj.setHotelStarRating(3.2);
		obj.setLengthOfStay(6);
		obj.setNumberOfRoomsLeft(12);
		obj.setRegionID("123654");
		obj.setTravelEndDate(new Date());
		obj.setTravelStartDate(new Date());

		offeredList.add(obj);
		return offeredList;
	}

	public void testFilterHotelsNullParams() {
		FilterBusiness filterHotels = new FilterBusiness();
		assertEquals(null, filterHotels.FilterHotels(null, null));
	}

	public void testFilterHotelsNullHotelList() {
		FilterBusiness filterHotels = new FilterBusiness();
		FilterOptions filterSettings = new FilterOptions();
		filterSettings.setGoingTo("Amman");
		assertEquals(null, filterHotels.FilterHotels(null, filterSettings));
	}

	public void testFilterHotelsNullFilterOptions() {
		FilterBusiness filterHotels = new FilterBusiness();

		List<HotelOfferInfo> allHotels = GetListOfHotels();

		List<HotelOfferInfo> offeredList = filterHotels.FilterHotels(allHotels, null);
		assertEquals(allHotels, offeredList);
	}

	public void testFilterHotelsNullException() {
		FilterBusiness filterHotels = new FilterBusiness();
		FilterOptions filterSettings = new FilterOptions();
		List<HotelOfferInfo> allHotels = GetListOfHotels();

		// null exception is expected, in this case the original list of offered should
		// be returned
		List<HotelOfferInfo> offeredList = filterHotels.FilterHotels(allHotels, filterSettings);
		assertEquals(allHotels, offeredList);
	}

	public void testFilterHotelsByCheckInDate() {
		FilterBusiness filterHotels = new FilterBusiness();
		FilterOptions filterSettings = new FilterOptions();
		filterSettings.setCheckInDate(new Date());

		List<HotelOfferInfo> allHotels = GetListOfHotels();

		List<HotelOfferInfo> offeredList = filterHotels.FilterHotels(allHotels, filterSettings);

		assert (!offeredList.isEmpty());
	}

	public void testFilterHotelsByGoingTo() {
		FilterBusiness filterHotels = new FilterBusiness();
		FilterOptions filterSettings = new FilterOptions();
		filterSettings.setGoingTo("Amman");

		List<HotelOfferInfo> allHotels = GetListOfHotels();

		List<HotelOfferInfo> offeredList = filterHotels.FilterHotels(allHotels, filterSettings);
		assertEquals("Amman", offeredList.get(0).getCity());
	}

	public void testFilterHotelsByGoingTo2() {
		FilterBusiness filterHotels = new FilterBusiness();
		FilterOptions filterSettings = new FilterOptions();
		filterSettings.setGoingTo("Aqaba");

		List<HotelOfferInfo> allHotels = GetListOfHotels();

		List<HotelOfferInfo> offeredList = filterHotels.FilterHotels(allHotels, filterSettings);
		assertEquals(null, offeredList);
	}

	public void testFilterHotelsByStayLength() {
		FilterBusiness filterHotels = new FilterBusiness();
		FilterOptions filterSettings = new FilterOptions();
		filterSettings.setLengthOfStay(6);

		List<HotelOfferInfo> allHotels = GetListOfHotels();

		List<HotelOfferInfo> offeredList = filterHotels.FilterHotels(allHotels, filterSettings);
		assert (!offeredList.isEmpty());
	}

	public void testFilterHotelsByStayLength2() {
		FilterBusiness filterHotels = new FilterBusiness();
		FilterOptions filterSettings = new FilterOptions();
		filterSettings.setLengthOfStay(7);

		List<HotelOfferInfo> allHotels = GetListOfHotels();

		List<HotelOfferInfo> offeredList = filterHotels.FilterHotels(allHotels, filterSettings);
		assertEquals(null, offeredList);
	}

	public void testFilterHotelsByStarRating() {
		FilterBusiness filterHotels = new FilterBusiness();
		FilterOptions filterSettings = new FilterOptions();
		filterSettings.setMinStarRate(2.9);
		filterSettings.setMaxStarRate(4.9);

		List<HotelOfferInfo> allHotels = GetListOfHotels();

		List<HotelOfferInfo> offeredList = filterHotels.FilterHotels(allHotels, filterSettings);
		assert (!offeredList.isEmpty());
	}

	public void testFilterHotelsByGuestRating() {
		FilterBusiness filterHotels = new FilterBusiness();
		FilterOptions filterSettings = new FilterOptions();
		filterSettings.setMinGuestRate(2.9);
		filterSettings.setMaxGuestRate(4.9);

		List<HotelOfferInfo> allHotels = GetListOfHotels();

		List<HotelOfferInfo> offeredList = filterHotels.FilterHotels(allHotels, filterSettings);
		assert (!offeredList.isEmpty());
	}

	public void testFilterHotelsByTotalRating() {
		FilterBusiness filterHotels = new FilterBusiness();
		FilterOptions filterSettings = new FilterOptions();
		filterSettings.setMinTotalRate(100);
		filterSettings.setMaxTotalRate(1000);

		List<HotelOfferInfo> allHotels = GetListOfHotels();

		List<HotelOfferInfo> offeredList = filterHotels.FilterHotels(allHotels, filterSettings);
		assert (!offeredList.isEmpty());
	}

}
