package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
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
	
	
	//Insert Payment
	public String insertPaymentDetails( String paydate, String payamt, String paytype, String paycus, String paycusid)
	{
		String output = "";
		
		try
		{
   		   Connection con = connect();
   		   
		   if (con == null)
           {return "Error while connecting to the database for inserting."; }
		   
			 // create a prepared statement
			 String query = " insert into payment (`payid`,`paydate`,`payamt`,`paytype`,`paycus`,`paycusid`)"
			 + " values (?, ?, ?, ?, ?, ?)";
			 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, paydate);
			preparedStmt.setString(3, payamt);
			preparedStmt.setString(4, paytype);
			preparedStmt.setString(5, paycus);
			preparedStmt.setString(6, paycusid);
			
			
			
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
		public String readPayment()
	    {
		   String output = "";
		   try
		   {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
		     // Prepare the html table to be displayed
		     output = "<table border='1'><tr><th>Payment ID</th><th>Payment Date</th>" +
					   "<th>Payment Amount</th>" +
					   "<th>Payment Type</th>" +
					  
					   "<th>Payment Customer</th>" +
					   "<th>Payment Customer ID</th>" +
					  
					   "<th>Update</th><th>Remove</th></tr>";
		     

			 String query = "select * from payment";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String payid = Integer.toString(rs.getInt("payid"));//see
				 String paydate = rs.getString("paydate");
				 String payamt = rs.getString("payamt");
				 String paytype = rs.getString("paytype");
				 String paycus = rs.getString("paycus");
				 String paycusid =rs.getString("paycusid");
				 
				
				 // Add into the html table
				 output += "<tr><td>" + payid + "</td>";
				 output += "<td>" + paydate + "</td>";
				 output += "<td>" + payamt + "</td>";
				 output += "<td>" + paytype + "</td>";
				 output += "<td>" + paycus + "</td>";
				 output += "<td>" + paycusid + "</td>";
			
				
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							 + "<td><form method='post' action='payment.jsp'>"
							 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							 + "<input name='itemID' type='hidden' value='" + payid
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
		public String updatePayment(String payid, String paydate, String payamt, String paytype, String paycus, String paycusid)
		 
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE payment SET paydate=?,payamt=?, paytype=?,paycus=?,paycusid=?  WHERE payid=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, paydate);
		 preparedStmt.setString(2, payamt);
		 preparedStmt.setString(3, paytype);
		 preparedStmt.setString(4, paycus);
		 preparedStmt.setString(5, paycusid);
		 preparedStmt.setInt(6, Integer.parseInt(payid));
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
		public String deletePayment(String payid)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from payment where payid=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(payid));
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
		
		
		
		//view profile
		public String viewProfile(String payid) {


			String output = "";
			
			//try catch

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				  output = "<table border='1'><tr><th>Payment ID</th><th>Payment Date</th>" +
						   "<th>Payment Amount</th>" +
						   "<th>Payment Type</th>" +
						  
						   "<th>Payment Customer</th>" +
						   "<th>Payment Customer ID</th>" +
						  
						   "<th>Update</th><th>Remove</th></tr>";
			     

				String query = "select *  from payment where payid=' " + payid + "'" ;


				Statement stmt = con.createStatement();


				ResultSet rs = stmt.executeQuery(query);

				 while (rs.next())
				 {
					 String payid1 = Integer.toString(rs.getInt("payid"));//see
					 String paydate = rs.getString("paydate");
					 String payamt = rs.getString("payamt");
					 String paytype = rs.getString("paytype");
					 String paycus = rs.getString("paycus");
					 String paycusid =rs.getString("paycusid");
					 
					
					 // Add into the html table
					 output += "<tr><td>" + payid1 + "</td>";
					 output += "<td>" + paydate + "</td>";
					 output += "<td>" + payamt + "</td>";
					 output += "<td>" + paytype + "</td>";
					 output += "<td>" + paycus + "</td>";
					 output += "<td>" + paycusid + "</td>";
				
					


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