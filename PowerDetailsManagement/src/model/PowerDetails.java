package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PowerDetails {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electricitypower?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	} 

	public String insertPowerDetails(String pdCusName, String pdAccNo, String psUnit, String pdDate, String pdPay) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into powermanage(`pdID`,`pdCusName`,`pdAccNo`,`psUnit`,`pdDate`,`pdPay`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pdCusName);
			preparedStmt.setString(3, pdAccNo);
			preparedStmt.setString(4, psUnit);
			preparedStmt.setString(5, pdDate);
			preparedStmt.setString(6, pdPay);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Power Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPowerDetails() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer Name</th>"+"<th>Account No</th>"+"<th>Unit Amount</th>"+"<th>Date</th>"+"<th>Total Price </th>"+"<th>Update</th><th>Remove</th></tr>";
			String query = "select * from powermanage";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String pdID = Integer.toString(rs.getInt("pdID"));
				String pdCusName = rs.getString("pdCusName");
				String pdAccNo = rs.getString("pdAccNo");
				String psUnit = rs.getString("psUnit");
				String pdDate = rs.getString("pdDate");
				String pdPay = rs.getString("pdPay");

				// Add into the html table
				output += "<tr><td><input id='hidpdIDUpdate' name='hidpdIDUpdate' type='hidden' value='"+pdID+"'>" + pdCusName + "</td>";
				output += "<td>" + pdAccNo + "</td>";
				output += "<td>" + psUnit + "</td>";
				output += "<td>" + pdDate + "</td>";
				output += "<td>" + pdPay + "</td>";
				
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-sm btn-secondary'></td>" 
        				+ "<td><form method='post' action='items.jsp'>"
        				+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-sm btn-danger'>"
        				+ "<input name='hidpdIDDelete' type='hidden' value='" + pdID + "'>"
        				+ "</form></td></tr>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Power Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePowerDetails(String pdID, String pdCusName, String pdAccNo, String psUnit, String pdDate, String pdPay) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE powermanage SET pdCusName=?,pdAccNo=?,psUnit=?,pdDate=?,pdPay=?" + "WHERE pdID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, pdCusName);
			preparedStmt.setString(2, pdAccNo);
			preparedStmt.setString(3, psUnit);
			preparedStmt.setString(4, pdDate);
			preparedStmt.setString(5, pdPay);
			preparedStmt.setInt(6, Integer.parseInt(pdID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Power Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deletePowerDetails(String pdID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from powermanage where pdID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pdID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Power Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
