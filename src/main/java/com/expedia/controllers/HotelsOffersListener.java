package com.expedia.controllers;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.expedia.models.HotelOfferInfo;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * Application Lifecycle Listener implementation class HotelsOffersListener
 *
 */
@WebListener
public class HotelsOffersListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public HotelsOffersListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 

    	ServletContext sc = event.getServletContext();
    	
    	@SuppressWarnings("unchecked")
		ArrayList<HotelOfferInfo> offersList = (ArrayList<HotelOfferInfo>)sc.getAttribute("hotelOffers");
    	
    	if(offersList == null)
    	{
    		offersList = new ArrayList<HotelOfferInfo>();
    		sc.setAttribute("hotelOffers", offersList);
    	}
    }
	
}
