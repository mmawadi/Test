package com.expedia.util;

import com.expedia.models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonParser {

	
	private static String GetJsonDataFromUrl(String jsonUrl) {

		// this function will open jsonUrl and read the data as string
		String jsonData = "";

		if (jsonUrl.isEmpty()) {
			return jsonData;
		}
		
		BufferedReader reader = null;
		try {
			String line;

			URL urlToParse = new URL(jsonUrl);
			URLConnection connection = urlToParse.openConnection();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((line = reader.readLine()) != null) {
				jsonData += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return jsonData;
	}

	public static List<HotelOfferInfo> ParseJson(String jsonUrl) {

		// Initialize a list of HotelOfferInfo to prevent returning a null list
		List<HotelOfferInfo> hotelsData = new ArrayList<HotelOfferInfo>();

		if (jsonUrl.isEmpty()) {
			return hotelsData;
		}
		try {

			// Read the JSON data from jsonUrl
			String jsonData = JsonParser.GetJsonDataFromUrl(jsonUrl);
			
			if (jsonData.isEmpty()) {
				return hotelsData;
			}

			/************************************************************************
			 *   JSON Structure (with the data that parsed) 
			 * 
			 *   "offers":{ 
			 *         "Hotel":[ 
			 *               offerDateRange: {"travelStartDate":[2018,5,11],"travelEndDate":[2018,5,13],"lengthOfStay":2}
			 *               destination: {"regionID":"178286", "longName":{....}, ....}, 
			 *               hotelInfo :{"hotelName":"hotelName","hotelImageUrl":"...", "hotelStarRating": "4","hotelGuestReviewRating": "3.4","hotelReviewTotal":"3251", ...}
			 * 				 hotelUrgencyInfo: {"numberOfRoomsLeft": "2", ... },
			 * 
			 * ]}
			 ************************************************************************/

			// Initialize the parser
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(jsonData);

			// get offers node cause he the parent node of hotels details
			JSONObject offersObj = (JSONObject) obj.get("offers");

			// make sure the offers node is valid
			if (offersObj == null) {
				System.out.println("offersObj is null");
				return hotelsData;// return an empty list cause we failed to get the offers node
			}

			// get the hotel array from offers node
			JSONArray hotelsArray = (JSONArray) offersObj.get("Hotel");

			// make sure that hotel array is valid
			if (hotelsArray == null) {
				System.out.println("Hotel array is null");
				return hotelsData;
			}

			// parse the hotel info by parsing each node in JSON
			for (int i = 0; i < hotelsArray.size(); i++) {

				JSONObject hotel = (JSONObject) hotelsArray.get(i);

				//////////////////////////////////////////////////////////////
				// Parse offerDateRange node
				JSONObject offerDateRange = (JSONObject) hotel.get("offerDateRange");

				String travelStartDateStr = offerDateRange.get("travelStartDate").toString();

				Date travelStartDate = new Date();
				try {
					travelStartDate = (Date) new SimpleDateFormat("[yyyy,MM,dd]").parse(travelStartDateStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				String travelEndDateStr = offerDateRange.get("travelEndDate").toString();

				Date travelEndDate = new Date();
				try {
					travelEndDate = (Date) new SimpleDateFormat("[yyyy,MM,dd]").parse(travelEndDateStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}


				Integer lengthOfStay = Integer.parseInt( offerDateRange.get("lengthOfStay").toString());

				//////////////////////////////////////////////////////////////
				// Parse destination node

				JSONObject destination = (JSONObject) hotel.get("destination");

				String regionID = destination.get("regionID").toString();
				String destinationName = destination.get("longName").toString();
				String city = destination.get("city").toString();

				
				//////////////////////////////////////////////////////////////
				// Parse hotelInfo node

				JSONObject hotelInfo = (JSONObject) hotel.get("hotelInfo");

				String hotelName = hotelInfo.get("hotelName").toString();
				String hotelImageUrl = hotelInfo.get("hotelImageUrl").toString();
				Double hotelStarRating = Double.parseDouble(hotelInfo.get("hotelStarRating").toString());
				Double hotelGuestReviewRating = Double.parseDouble(hotelInfo.get("hotelGuestReviewRating").toString());
				
				Integer hotelReviewTotal = Integer.parseInt(hotelInfo.get("hotelReviewTotal").toString());

				//////////////////////////////////////////////////////////////
				// Parse hotelUrgencyInfo node

				JSONObject hotelUrgencyInfo = (JSONObject) hotel.get("hotelUrgencyInfo");

				Integer numberOfRoomsLeft = Integer.parseInt(hotelUrgencyInfo.get("numberOfRoomsLeft").toString());

				
				
				//////////////////////////////////////////////////////////////
				// Parse hotelPricingInfo node

				JSONObject hotelPricingInfo = (JSONObject) hotel.get("hotelPricingInfo");
												
				Double averagePriceValue = Double.parseDouble(hotelPricingInfo.get("averagePriceValue").toString());
			
				// Fill the parsed data into HotelOfferInfo object
				HotelOfferInfo offerInfo = new HotelOfferInfo();
				offerInfo.setTravelStartDate(travelStartDate);
				offerInfo.setTravelEndDate(travelEndDate);
				offerInfo.setLengthOfStay(lengthOfStay);
				offerInfo.setRegionID(regionID);
				offerInfo.setDestinationName(destinationName);
				offerInfo.setCity(city);
				offerInfo.setHotelName(hotelName);
				offerInfo.setHotelImageUrl(hotelImageUrl);
				offerInfo.setNumberOfRoomsLeft(numberOfRoomsLeft);
				offerInfo.setHotelStarRating(hotelStarRating);
				offerInfo.setHotelGuestReviewRating(hotelGuestReviewRating);
				offerInfo.setHotelReviewTotal(hotelReviewTotal);
				offerInfo.setAveragePriceValue(averagePriceValue);
								
				hotelsData.add(offerInfo);
				
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		}

		return hotelsData;
	}

}
