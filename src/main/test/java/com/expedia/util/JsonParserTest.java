package com.expedia.util;

import junit.framework.TestCase;

import java.util.List;

import com.expedia.models.HotelOfferInfo;

public class JsonParserTest extends TestCase {

	 public void testParseJsonInvalidUrl() {
		 List<HotelOfferInfo> offeredList = JsonParser.ParseJson("https://offersvc.expedia.com/offers/v2/get");
		 assert(offeredList.isEmpty());
	 }

	 public void testParseJsonNullUrl() {		
			List<HotelOfferInfo> offeredList = JsonParser.ParseJson(null);
			assert(offeredList.isEmpty());			
			}
	 
	public void testParseJsonEmptyUrl() {		
		List<HotelOfferInfo> offeredList = JsonParser.ParseJson("");
		assert(offeredList.isEmpty());			
		}
	
	public void testParseJsonDiffStruct() {
		List<HotelOfferInfo> offeredList = JsonParser.ParseJson("http://ip.jsontest.com/?callback=showIP");
		 assert(offeredList.isEmpty());
	}
	
	public void testParseJsonRightUrl() {
		List<HotelOfferInfo> offeredList = JsonParser.ParseJson("https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=H");
		assert(!offeredList.isEmpty());		
	}

}
