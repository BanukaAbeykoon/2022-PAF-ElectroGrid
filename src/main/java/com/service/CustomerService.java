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
import com.model.Customer;

@Path("/Customer")
public class CustomerService {
Customer cusobj = new Customer();
	
	//Read Details of customers
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML) 
		
		public String readCustomer()
		{
			return cusobj.readCustomer();
		} 
	
	//Add Details of customers
		@POST 
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertEmployee(@FormParam("customerName") String customerName,
		 @FormParam("customerEmail") String customerEmail,
		 @FormParam("cusAge") String cusAge,
		 @FormParam("password") String password,
		 @FormParam("phone") String phone,
		 @FormParam("nic") String nic)
		 
		
	
		{
		 String output = cusobj.insertCustomerDetails(customerName, customerEmail, cusAge, password, phone,nic);
		return output;
		}
		
		//Update Details of customers
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateCustomer(String customerData)
		{
		//Convert the input string to a JSON object
		 JsonObject customerobj = new JsonParser().parse(customerData).getAsJsonObject();
		//Read the values from the JSON object
		 String customerNumber = customerobj.get("customerNumber").getAsString();
		 String customerName = customerobj.get("customerName").getAsString();
		 String customerEmail = customerobj.get("customerEmail").getAsString();
		 String cusAge = customerobj.get("cusAge").getAsString();
		 String password = customerobj.get("password").getAsString();
		 String phone = customerobj.get("phone").getAsString();
		 String nic = customerobj.get("nic").getAsString();
		 String output = cusobj.updateCustomer(customerNumber, customerName, customerEmail, cusAge, password, phone, nic);
		return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteCustomer(String customerData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String customerNumber = doc.select("customerNumber").text();
		 String output = cusobj.deleteCustomer(customerNumber);
		return output;
		}
		
		// view profile details
				@GET
				@Path("/profile/{customerNumber1}")
				@Consumes(MediaType.APPLICATION_JSON)
				@Produces(MediaType.TEXT_PLAIN)
				//@Produces(MediaType.TEXT_HTML)
				public String readprofile(@PathParam("customerNumber1") String customerNumber1) {

					return cusobj.viewProfile(customerNumber1);


				}

}