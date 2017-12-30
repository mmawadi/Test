package com.expedia.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expedia.business.FilterBusiness;
import com.expedia.models.*;
import com.expedia.util.JsonParser;


/**
 * Servlet implementation class BrowseHotels
 */
@WebServlet("/BrowseHotels")
public class BrowseHotels extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BrowseHotels() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// initialize the filter values in the jsp file
		request.setAttribute("going-to", "");
		request.setAttribute("check-in", "");
		request.setAttribute("check-out", "");
		request.setAttribute("length-stay", "");
		request.setAttribute("min-star-rate", "");
		request.setAttribute("max-star-rate", "");
		request.setAttribute("min-total-rate", "");
		request.setAttribute("max-total-rate", "");
		request.setAttribute("min-guest-rate", "");
		request.setAttribute("max-guest-rate", "");

		String jsonUrl = "https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel";
		List<HotelOfferInfo> offersList = JsonParser.ParseJson(jsonUrl);

		ServletContext sc = this.getServletContext();
		if (offersList != null) {
			System.out.println("Size = " + offersList.size());

			sc.setAttribute("hotelOffers", offersList);
		}

		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Views/browse_hotels.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get filter data from jsp file
		String goingTo = request.getParameter("going-to");
		if (!goingTo.isEmpty()) {

			request.setAttribute("going-to", goingTo);
		} else {
			request.setAttribute("going-to", "");
		}

		String checkIn = request.getParameter("check-in");
		if (!checkIn.isEmpty()) {

			request.setAttribute("check-in", checkIn);
		} else {
			request.setAttribute("check-in", "");
		}

		String checkOut = request.getParameter("check-out");
		if (!checkOut.isEmpty()) {

			request.setAttribute("check-out", checkOut);
		} else {
			request.setAttribute("check-out", "");
		}

		String lengthStay = request.getParameter("length-stay");
		if (!lengthStay.isEmpty()) {

			request.setAttribute("length-stay", lengthStay);
		} else {
			request.setAttribute("length-stay", "");
		}
		String minStarRate = request.getParameter("min-star-rate");
		if (!minStarRate.isEmpty()) {

			request.setAttribute("min-star-rate", minStarRate);
		} else {
			request.setAttribute("min-star-rate", "");
		}

		String maxStarRate = request.getParameter("max-star-rate");
		if (!maxStarRate.isEmpty()) {

			request.setAttribute("max-star-rate", maxStarRate);
		} else {
			request.setAttribute("max-star-rate", "");
		}

		String minTotalRate = request.getParameter("min-total-rate");
		if (!minTotalRate.isEmpty()) {

			request.setAttribute("min-total-rate", minTotalRate);
		} else {
			request.setAttribute("min-total-rate", "");
		}

		String maxTotalRate = request.getParameter("max-total-rate");
		if (!maxTotalRate.isEmpty()) {

			request.setAttribute("max-total-rate", maxTotalRate);
		} else {
			request.setAttribute("max-total-rate", "");
		}

		String minGuestRate = request.getParameter("min-guest-rate");
		if (!minGuestRate.isEmpty()) {

			request.setAttribute("min-guest-rate", minGuestRate);
		} else {
			request.setAttribute("min-guest-rate", "");
		}

		String maxGusetRate = request.getParameter("max-guest-rate");
		if (!maxGusetRate.isEmpty()) {

			request.setAttribute("max-guest-rate", maxGusetRate);
		} else {
			request.setAttribute("max-guest-rate", "");
		}

		////////////////////////////////////////////////////
		// fill the filter options
		FilterOptions filter = new FilterOptions();
		filter.setGoingTo(goingTo);

		if (!checkIn.isEmpty()) {
			Date travelStartDate = new Date();
			try {
				travelStartDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(checkIn);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			filter.setCheckInDate(travelStartDate);
		}

		if (!checkOut.isEmpty()) {
			Date travelEndDate = new Date();
			try {
				travelEndDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(checkOut);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			filter.setCheckOutDate(travelEndDate);
		}

		if (!lengthStay.isEmpty()) {
			filter.setLengthOfStay(Integer.parseInt(lengthStay));
		}

		if (!minStarRate.isEmpty()) {

			filter.setMinStarRate(Double.parseDouble(minStarRate));
		}

		if (!maxStarRate.isEmpty()) {

			filter.setMaxStarRate(Double.parseDouble(maxStarRate));
		}

		if (!minTotalRate.isEmpty()) {

			filter.setMinTotalRate(Integer.parseInt(minTotalRate));
		}

		if (!maxTotalRate.isEmpty()) {

			filter.setMaxTotalRate(Integer.parseInt(maxTotalRate));
		}

		if (!minGuestRate.isEmpty()) {

			filter.setMinGuestRate(Double.parseDouble(minGuestRate));
		}

		if (!maxGusetRate.isEmpty()) {

			filter.setMaxGuestRate(Double.parseDouble(maxGusetRate));
		}

		////////////////////////////////////////////////////
		// get the latest offers from json
		
		String jsonUrl = "https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel";//getServletContext().getInitParameter("JsonUrl");
		List<HotelOfferInfo> offersList = JsonParser.ParseJson(jsonUrl);
		
		////////////////////////////////////////////////////
		//call the filter		
		
		FilterBusiness filterHotels = new FilterBusiness();
		List<HotelOfferInfo> filteredList = filterHotels.FilterHotels(offersList, filter);
		
        ////////////////////////////////////////////////////
        //store the filtered list in the servlet context
		
		ServletContext sc = this.getServletContext();
		sc.setAttribute("hotelOffers", filteredList);

		////////////////////////////////////////////////////
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Views/browse_hotels.jsp");
		view.forward(request, response);
	}

}
