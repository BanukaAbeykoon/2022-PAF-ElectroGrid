package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin {
	
	private Connection connect()
	{
		 Connection con = null;
		 
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro", "root", "root");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 
		 return con;
	}
	
	
	//Insert Admin Item
	public String insertAdminDetails(String adminName, String adminEmail, String adminAge, String password, String phone, String nic)
	{
		String output = "";
		
		try
		{
   		   Connection con = connect();
   		   
		   if (con == null)
           {return "Error while connecting to the database for inserting."; }
		   
			 // create a prepared statement
			 String query = " insert into admin (`adminNumber`,`adminName`,`adminEmail`,`adminAge`,`password`,`phone`,`nic`)"
			 + " values (?, ?, ?, ?, ?, ?, ?)";
			 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, adminName);
			preparedStmt.setString(3, adminEmail);
			preparedStmt.setInt(4, Integer.parseInt(adminAge));
			preparedStmt.setString(5, password);
			preparedStmt.setInt(6, Integer.parseInt(phone));
			preparedStmt.setString(7, nic);
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		 catch (Exception e)
		 {
			 output = "Error while inserting the item.";
			 System.err.println(e.getMessage());
		 }
		    return output;
	}
	
	
	//Read Item
		public String readAdmin()
	    {
		   String output = "";
		   try
		   {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
		     // Prepare the html table to be displayed
		     output = "<table border='1'><tr><th>Admin No</th><th>Admin Name</th>" +
					   "<th>Admin Email</th>" +
					   "<th>Admin Age</th>" +
					  
					   "<th>password</th>" +
					   "<th>phone no</th>" +
					   "<th>Nic no</th>" +
					   "<th>Update</th><th>Delete</th></tr>";
		     

			 String query = "select * from admin";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String adminNumber = Integer.toString(rs.getInt("adminNumber"));//see
				 String adminName = rs.getString("adminName");
				 String adminEmail = rs.getString("adminEmail");
				 String adminAge = rs.getString("adminAge");
				 String password = rs.getString("password");
				 String phone = Integer.toString(rs.getInt("phone"));
				 String nic = rs.getString("nic");
				
				 // Add into the html table
				 output += "<tr><td>" + adminNumber + "</td>";
				 output += "<td>" + adminName + "</td>";
				 output += "<td>" + adminEmail + "</td>";
				 output += "<td>" + adminAge + "</td>";
				 output += "<td>" + password + "</td>";
				 output += "<td>" + phone + "</td>";
				 output += "<td>" + nic + "</td>";
				
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							 + "<td><form method='post' action='payment.jsp'>"
							 + "<input name='btnDelete' type='submit' value='Delete'class='btn btn-danger'>"
							 + "<input name='itemID' type='hidden' value='" + adminNumber
							 + "'>" + "</form></td></tr>";
			 }
			 
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
		   }
		   catch (Exception e)
		   {
			 output = "Error while reading the items.";
			 System.err.println(e.getMessage());
		   }
		   
		   return output;
	    }
		
		//update
		public String updateAdmin(String adminNumber, String adminName, String adminEmail, String adminAge, String password, String phone, String nic)
		 
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE admin SET adminName=?,adminEmail=?,adminAge=?,password=?,phone=?,nic=?  WHERE adminNumber=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, adminName);
		 preparedStmt.setString(2, adminEmail);
		 preparedStmt.setDouble(3, Integer.parseInt(adminAge));
		 preparedStmt.setString(4, password);
		 preparedStmt.setInt(5, Integer.parseInt(phone));
		 preparedStmt.setString(6, nic);
		 preparedStmt.setInt(7, Integer.parseInt(adminNumber));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		//delete
		public String deleteAdmin(String adminNumber)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from admin where adminNumber=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(adminNumber));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		//serch Admin details
		
		public String viewProfile(String adminNumber) {


			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				output = "<table border=\"1\"><tr><th>Admin Number</th><th>Admin Name</th><th>Admin Email</th><th>Admin Age</th><th>Password</th><th>phone</th><th>Nic</th><th>Update</th><th>Delete</th></tr>";

				String query = "select *  from admin where adminNumber=' " + adminNumber + "'" ;


				Statement stmt = con.createStatement();


				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					 String adminNumber1 = Integer.toString(rs.getInt("adminNumber"));//see
					 String adminName = rs.getString("adminName");
					 String adminEmail = rs.getString("adminEmail");
					 String adminAge = rs.getString("adminAge");
					 String password = rs.getString("password");
					 String phone = Integer.toString(rs.getInt("phone"));
					 String nic = rs.getString("nic");
					 



					output += "<tr><td>" + adminNumber1 + "</td>";
					output += "<td>" + adminName + "</td>";
					output += "<td>" + adminEmail + "</td>";
					output += "<td>" + adminAge + "</td>";
					output += "<td>" + password + "</td>";
					output += "<td>" + phone + "</td>";
					output += "<td>" + nic + "</td>";
					

				
	/*				

					output +=
							"<td><input name=\"btnUpdate\" type=\"button\"  value=\"Update\" class=\"btn btn-secondary\"></td>"
									+ "<td><form method=\"post\" action=\"UsertReg.jsp\">" +
									"<input name=\"btnDelete\" type=\"submit\" value=\"Delete\"  class=\"btn btn-danger\">"
									+ "<input name=\"UserID\" type=\"hidden\" value=\"" + adminNumber1 + "\">" +
									"</form></td></tr>";
*/
				}

				con.close();

				output += "</table>";


			} catch (Exception e) 
			{

				output = "Error while Viewing the user profile.";
				System.err.println(e.getMessage());
			}

			return output;
		}

}
