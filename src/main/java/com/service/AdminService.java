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
import com.model.Admin;

@Path("/Admin")
public class AdminService {
	Admin adminobj = new Admin();
	
	//Read Admin Details
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML) 
		
		public String readAdmin()
		{
			return adminobj.readAdmin();
		} 
	
	//Add Admin Details
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertAdmin(@FormParam("adminName") String adminName,
		 @FormParam("adminEmail") String adminEmail,
		 @FormParam("adminAge") String adminAge,
		 @FormParam("password") String password,
		 @FormParam("phone") String phone,
		 @FormParam("nic") String nic)
		 
		
	
		{
		 String output = adminobj.insertAdminDetails(adminName,
				 adminEmail,adminAge,password, phone,nic);
		return output;
		}
		
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateAdmin(String adminData)
		{
		//Convert the input string to a JSON object
		 JsonObject adminnobj = new JsonParser().parse(adminData).getAsJsonObject();
		//Read the values from the JSON object
		 String adminNumber = adminnobj.get("adminNumber").getAsString();
		 String adminName = adminnobj.get("adminName").getAsString();
		 String adminEmail = adminnobj.get("adminEmail").getAsString();
		 String adminAge = adminnobj.get("adminAge").getAsString();
		 String password = adminnobj.get("password").getAsString();
		 String phone = adminnobj.get("phone").getAsString();
		 String nic = adminnobj.get("nic").getAsString();
		 String output = adminobj.updateAdmin(adminNumber, adminName, adminEmail, adminAge, password, phone, nic);
		return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteAdmin(String adminData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(adminData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String adminNumber = doc.select("adminNumber").text();
		 String output = adminobj.deleteAdmin(adminNumber);
		return output;
		}
		
		// view profile details
				@GET
				@Path("/profile/{adminNumber}")
				@Consumes(MediaType.APPLICATION_JSON)
				@Produces(MediaType.TEXT_PLAIN)
				//@Produces(MediaType.TEXT_HTML)
				public String readprofile(@PathParam("adminNumber") String adminNumber) {

					return adminobj.viewProfile(adminNumber);


				}

}