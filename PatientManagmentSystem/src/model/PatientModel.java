package model;

import java.sql.*;

public class PatientModel { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3000/patient", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insert(String email, String name, String age, String diseas , String address,String gender) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
// create a prepared statement
			String query = " insert into patient_table(`patientID`,`patientEmail`,`patientName`,`patientAge`,`patientdis`,`patientAdds`,`patientgen`)"
					 + " values (?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(age));
			preparedStmt.setString(5, diseas);
            preparedStmt.setString(6, address);
            preparedStmt.setString(7, gender);

// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String patientItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Patient Email</th><th>Patient Name</th><th>Patient Age</th><th>Patient Diseas</th>><th>Patient Address</th>><th>Patient Gender</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from patient_table";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String patientID = Integer.toString(rs.getInt("patientID"));
				String patientEmail = rs.getString("patientEmail");
				String patientName = rs.getString("patientName");
				String patientAge = Double.toString(rs.getDouble("patientAge"));
				String patientdis = rs.getString("patientdis");
                String patientAdds = rs.getString("patientAdds");
                String patientgen = rs.getString("patientgen");
				// Add into the html table
				output += "<tr><td>" + patientEmail+ "</td>";
				output += "<td>" + patientName + "</td>";
				output += "<td>" + patientAge+ "</td>";
				output += "<td>" + patientdis+ "</td>";
                output += "<td>" + patientAdds+ "</td>";
                output += "<td>" + patientgen+ "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"Patient.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"patientID \" type=\"hidden\" value=\"" + patientID + "\">" + "</form></td></tr>";
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

	public String updateItem(String ID, String email, String name, String age, String diseas ,String address,String gender) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE patient_table SET patientEmail=?,patientName=?,patientAge=?,patientdis=?,patientAdds=?,patientgen=? WHERE patientID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, email);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(age));
			preparedStmt.setString(4, diseas);
            preparedStmt.setString(5, address);
            preparedStmt.setString(5, gender);


			preparedStmt.setInt(6, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePatient(String patientID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from item_table where patientID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(patientID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}