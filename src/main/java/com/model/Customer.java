package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {
	
	private Connection connect()
	{
		 Connection con = null;
		 
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro", "root", "root");//electro is the database name and password is root
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 
		 return con;
	}
	
	
	//Insert Customers
	public String insertCustomerDetails(String customerName, String customerEmail, String cusAge, String password, String phone, String nic)
	{
		String output = "";
		
		try
		{
   		   Connection con = connect();
   		   
		   if (con == null)
           {return "Error while connecting to the database for inserting."; }
		   
			 // create a prepared statement
			 String query = " insert into customer (`customerNumber`,`customerName`,`customerEmail`,`cusAge`,`password`,`phone`,`nic`)"
			 + " values (?, ?, ?, ?, ?, ?, ?)";
			 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerName);
			preparedStmt.setString(3, customerEmail);
			preparedStmt.setInt(4, Integer.parseInt(cusAge));
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
			 output = "Error while inserting the Customers.";
			 System.err.println(e.getMessage());
		 }
		    return output;
	}
	
	
	//Read Customers
		public String readCustomer()
	    {
		   String output = "";
		   try
		   {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
		     // Prepare the html table to be displayed
		     output = "<table border='1'><tr><th>Customer No</th>"
		     		+ "<th>Customer Name</th>" +
					   "<th>Customer Email</th>" +
					   "<th>Customer Age</th>" +
					  
					   "<th>password</th>" +
					   "<th>phone no</th>" +
					   "<th>Nic no</th>" +
					   "<th>Update</th><th>Remove</th></tr>";
		     

			 String query = "select * from customer";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String customerNumber = Integer.toString(rs.getInt("customerNumber"));//see
				 String customerName = rs.getString("customerName");
				 String customerEmail = rs.getString("customerEmail");
				 String cusAge = rs.getString("cusAge");
				 String password = rs.getString("password");
				 String phone = Integer.toString(rs.getInt("phone"));
				 String nic = rs.getString("nic");
				
				 // Add into the html table
				 output += "<tr><td>" + customerNumber + "</td>";
				 output += "<td>" + customerName + "</td>";
				 output += "<td>" + customerEmail + "</td>";
				 output += "<td>" + cusAge + "</td>";
				 output += "<td>" + password + "</td>";
				 output += "<td>" + phone + "</td>";
				 output += "<td>" + nic + "</td>";
				
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							 + "<td><form method='post' action='payment.jsp'>"
							 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							 + "<input name='itemID' type='hidden' value='" + customerNumber
							 + "'>" + "</form></td></tr>";
			 }
			 
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
		   }
		   catch (Exception e)
		   {
			 output = "Error while reading the Customers.";
			 System.err.println(e.getMessage());
		   }
		   
		   return output;
	    }
		
		//update customers
		public String updateCustomer(String customerNumber, String customerName, String customerEmail, String cusAge, String password, String phone, String nic)
		 
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE customer SET customerName=?,customerEmail=?,cusAge=?,password=?,phone=?,nic=?  WHERE customerNumber=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, customerName);
		 preparedStmt.setString(2, customerEmail);
		 preparedStmt.setDouble(3, Integer.parseInt(cusAge));
		 preparedStmt.setString(4, password);
		 preparedStmt.setInt(5, Integer.parseInt(phone));
		 preparedStmt.setString(6, nic);
		 preparedStmt.setInt(7, Integer.parseInt(customerNumber));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Customer Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the Customer.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		//delete customer
		public String deleteCustomer(String customerNumber)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from customer where customerNumber=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(customerNumber));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted customer successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the Customer.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		//view profile
		public String viewProfile(String customerNumber) {


			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				output = "<table border=\"1\"><tr><th>Customer Number</th><th>Customer Name</th><th>Customer Email</th><th>Age</th><th>Password</th><th>Phone</th><th>NIC</th><th>Update</th><th>Delete</th></tr>";

				String query = "select *  from customer where customerNumber=' " + customerNumber + "'" ;


				Statement stmt = con.createStatement();


				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					 String customerNumber1 = Integer.toString(rs.getInt("customerNumber"));//see the search
					 String customerName = rs.getString("customerName");
					 String customerEmail = rs.getString("customerEmail");
					 String cusAge = rs.getString("cusAge");
					 String password = rs.getString("password");
					 String phone = Integer.toString(rs.getInt("phone"));
					 String nic = rs.getString("nic");
					



					output += "<tr><td>" + customerNumber1 + "</td>";
					output += "<td>" + customerName + "</td>";
					output += "<td>" + customerEmail + "</td>";
					output += "<td>" + cusAge + "</td>";
					output += "<td>" + password + "</td>";
					output += "<td>" + phone + "</td>";
					output += "<td>" + nic + "</td>";
					



/*


					output +=
							"<td><input name=\"btnUpdate\" type=\"button\"  value=\"Update\" class=\"btn btn-secondary\"></td>"
									+ "<td><form method=\"post\" action=\"UsertReg.jsp\">" +
									"<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"  class=\"btn btn-danger\">"
									+ "<input name=\"UserID\" type=\"hidden\" value=\"" + employee + "\">" +
									"</form></td></tr>";
*/
				}

				con.close();

				output += "</table>";


			} catch (Exception e) 
			{

				output = "Error while Viewing the customer profile.";
				System.err.println(e.getMessage());
			}

			return output;
		}

}