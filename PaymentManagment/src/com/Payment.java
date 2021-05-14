package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Payment {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/onlinestoref", "root", "");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return con;
	}

	public String ReadPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			
			output = "<table border='1'><tr><th>Product Name</th> " + "<th>Quantity</th>" + "<th>Customer Name</th>"
					+ "<th>Date</th> " + "<th>Amount</th> " + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			
			while (rs.next()) {
				String pID = Integer.toString(rs.getInt("pID"));
				String pName = rs.getString("pName");
				String quantity = Integer.toString(rs.getInt("quantity"));
				String cName = rs.getString("cName");
				String date = rs.getString("date");
				String amount = Double.toString(rs.getDouble("amount"));
				
				// Add into the html table
				
				output += "<tr><td><input id='hidPaymentIDUpdate' " + "name='hidPaymentIDUpdate' "
						+ "type='hidden' value='" + pID + "'>" + pName + "</td>";
				output += "<td>" + quantity + "</td>";
				output += "<td>" + cName + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + amount + "</td>";
				
				// buttons
				
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-paymentid='" + pID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-paymentid='" + pID + "'></td></tr>";
			}
			con.close();
			
			// Complete the html table
			
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
		
	}

	public String InsertPayment(String pName, String quantity, String cName, String date, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			// create a prepared statement
			
			String query = "insert into payment(pID,pName,quantity,cName,date,amount) values (?, ?, ?, ?, ?, ?);";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pName);
			preparedStmt.setInt(3, Integer.parseInt(quantity));
			preparedStmt.setString(4, cName);
			preparedStmt.setString(5, date);
			preparedStmt.setDouble(6, Double.parseDouble(amount));
			
			// execute the statement
			
			preparedStmt.execute();
			con.close();
			String newItems = ReadPayment();
			output = "{\"status\":\"success\", \"data\": \"" + "" + newItems + "\"}";
		} catch (Exception e) {
			System.err.println("ERORR : " + e.getMessage());
			output = "{\"status\":\"error\", \"data\":" + "\"Error while inserting the item.\"}";

		}
		return output;
	}

	public String UpdatePayment(String pID, String pName, String quantity, String cName, String date, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			// create a prepared statement
			
			String query = "UPDATE payment SET " + "pName=?,quantity=?,cName=?,date=?,amount=? WHERE pID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			
			preparedStmt.setString(1, pName);
			preparedStmt.setInt(2, Integer.parseInt(quantity));
			preparedStmt.setString(3, cName);
			preparedStmt.setString(4, date);
			preparedStmt.setDouble(5, Double.parseDouble(amount));
			preparedStmt.setInt(6, Integer.parseInt(pID));
			
			// execute the statement
			
			preparedStmt.execute();
			con.close();
			String newItems = ReadPayment();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": " + "\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String DeletePayment(String pID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			
			String query = "delete from payment where pID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			
			preparedStmt.setInt(1, Integer.parseInt(pID));
			
			// execute the statement
			
			preparedStmt.execute();
			con.close();
			String newItems = ReadPayment();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": " + "\"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
