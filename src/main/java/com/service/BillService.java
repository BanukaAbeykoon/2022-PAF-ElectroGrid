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
import com.model.Bill;

@Path("/Bill")
public class BillService {
Bill billobj = new Bill();
	
	//Read Bill Details
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML) 
		
		public String readBill()
		{
			return billobj.readBill();
		} 
	
	//Add Bill Details
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertBill(@FormParam("billCode") String billCode,
		 @FormParam("cusID") String cusID,
		 @FormParam("units") String units,
		 @FormParam("kwhCharge") String kwhCharge,
		 @FormParam("fixCharge") String fixCharge,
		 @FormParam("total") String total)
		 
		
	
		{
		 String output = billobj.insertBillDetails(billCode,cusID,units,kwhCharge,fixCharge,total);
				 
		return output;
		}
		
		//Update Bill details
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateBill(String billData)
		{
		//Convert the input string to a JSON object
		 JsonObject Billobj = new JsonParser().parse(billData).getAsJsonObject();
		//Read the values from the JSON object
		 String billID = Billobj.get("billID").getAsString();
		 String billCode = Billobj.get("billCode").getAsString();
		 String cusID = Billobj.get("cusID").getAsString();
		 String units = Billobj.get("units").getAsString();
		 String kwhCharge = Billobj.get("kwhCharge").getAsString();
		 String fixCharge = Billobj.get("fixCharge").getAsString();
		 String total = Billobj.get("total").getAsString();
		 String output = billobj.updateBill(billID, billCode, cusID, units, kwhCharge, fixCharge, total);
		return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteBill(String billData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String billID = doc.select("billID").text();
		 String output = billobj.deleteBill(billID);
		return output;
		}
		// view profile details
				@GET
				@Path("/profile/{billID}")
				@Consumes(MediaType.APPLICATION_JSON)
				@Produces(MediaType.TEXT_PLAIN)
				//@Produces(MediaType.TEXT_HTML)
				public String readprofile(@PathParam("billID") String billID) {

					return billobj.viewProfile(billID);


				}

}