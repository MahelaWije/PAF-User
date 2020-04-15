package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf?useTimezone=true&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String addUserDetails(String name, String phone, String age, String address, String gender, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into users(`user_id`,`username`,`phoneNo`,`age`,`address`,`gender`,`email`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, phone);
			preparedStmt.setInt(4, Integer.parseInt(age));
			preparedStmt.setString(5, address);
			preparedStmt.setString(6, gender);
			preparedStmt.setString(7, email);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUsers() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>User Name</th><th>Phone No</th><th>Age</th><th>Address</th><th>Gender</th><th>Email</th></tr>";
			String query = "select * from users";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String userID = Integer.toString(rs.getInt("user_id"));
				String userName = rs.getString("username");
				String phoneNo = rs.getString("phoneNo");
				String age = Integer.toString(rs.getInt("age"));
				String address = rs.getString("address");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				// Add into the html table
				output += "<tr><td>" + userName + "</td>";
				output += "<td>" + phoneNo + "</td>";
				output += "<td>" + age + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + email + "</td>";
				// buttons
				/*
				 * output +=
				 * "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
				 * + "<td><form method=\"post\" action=\"items.jsp\">" +
				 * "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
				 * + "<input name=\"userID\" type=\"hidden\" value=\"" + userID + "\">" +
				 * "</form></td></tr>";
				 */
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the users.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUserDetails(String ID, String name, String phone, String age, String address, String gender,
			String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE users SET username=?,phoneNo=?,age=?,address=?,gender=?,email=?WHERE user_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, phone);
			preparedStmt.setInt(3, Integer.parseInt(age));
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, gender);
			preparedStmt.setString(6, email);
			preparedStmt.setInt(7, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUsers(String user_id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from users where user_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(user_id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}

//Model Class
