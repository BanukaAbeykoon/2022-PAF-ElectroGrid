package com.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.model.Payment;

@Path("/Payment")
public class PaymentService {
Payment payobj = new Payment();
	
	//Read Details
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML) 
		
		public String readPayment()
		{
			return payobj.readPayment();
		} 
	
	//Add Details
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPayment(
		 @FormParam("paydate") String paydate,
		 @FormParam("payamt") String payamt,
		 @FormParam("paytype") String paytype,
		 @FormParam("paycus") String paycus,
		 @FormParam("paycusid") String paycusid)
		
		 
		
	
		{
		 String output = payobj.insertPaymentDetails(paydate,
				 payamt,paytype,paycus, paycusid);
		return output;
		}
		
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String paymentData)
		{
		//Convert the input string to a JSON object
		 JsonObject paymenteobj = new JsonParser().parse(paymentData).getAsJsonObject();
		//Read the values from the JSON object
		 String payid = paymenteobj.get("payid").getAsString();
		 String paydate = paymenteobj.get("paydate").getAsString();
		 String payamt = paymenteobj.get("payamt").getAsString();
		 String paytype = paymenteobj.get("paytype").getAsString();
		 String paycus = paymenteobj.get("paycus").getAsString();
		 String paycusid = paymenteobj.get("paycusid").getAsString();
		
		 String output = payobj.updatePayment(payid, paydate, payamt, paytype, paycus, paycusid);
		return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(String paymentData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String payid = doc.select("payid").text();
		 String output = payobj.deletePayment(payid);
		return output;
		}
		
		// search
		@GET
		@Path("/profile/{payid}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		//@Produces(MediaType.TEXT_HTML)
		public String readprofile(@PathParam("payid") String payid) {

			return payobj.viewProfile(payid);


		}


}