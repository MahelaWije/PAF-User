package com;

//For REST Service
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

//For XML
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.User;
import authentication.userLogin;


@Path("/Users")
public class UserService {
	User userObj = new User();
	userLogin loginObj = new userLogin();


	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers() {
		return userObj.readUsers();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addUserDetails(@FormParam("username") String username, @FormParam("phoneNo") String phoneNo,
			@FormParam("age") String age, @FormParam("address") String address, @FormParam("gender") String gender,
			@FormParam("email") String email) {
		String output = userObj.addUserDetails(username, phoneNo, age, address, gender, email);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUserDetails(String userData) {
		// Convert the input string to a JSON object
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		// Read the values from the JSON object
		String user_id = userObject.get("user_id").getAsString();
		String username = userObject.get("username").getAsString();
		String phoneNo = userObject.get("phoneNo").getAsString();
		String age = userObject.get("age").getAsString();
		String address = userObject.get("address").getAsString();
		String gender = userObject.get("gender").getAsString();
		String email = userObject.get("email").getAsString();
		String output = userObj.updateUserDetails(user_id, username, phoneNo, age, address, gender, email);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUsers(String userData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

		// Read the value from the element <userID>
		String user_id = doc.select("user_id").text();
		String output = userObj.deleteUsers(user_id);
		return output;
	}
	
	@GET
	@Path("/{user_id}")
	@Produces(MediaType.TEXT_HTML)
	public String readSelectedUsers(@PathParam("user_id") String user_id) {
		return userObj.readSelectedUsers(user_id);
		
	}
	
	@GET
	@Path("/UsersPayments/{user_id}")
	@Produces(MediaType.TEXT_HTML)
	public String readUsersPaymentHistory(@PathParam("user_id") String user_id) {
		return userObj.readUsersPaymentHistory(user_id);
		//payment details for selected user
	}
	
	@GET
	@Path("/UsersAppointments/{user_id}")
	@Produces(MediaType.TEXT_HTML)
	public String readUsersAppointmentsHistory(@PathParam("user_id") String user_id) {
		return userObj.readUsersAppointmentsHistory(user_id);
		//appointments details for selected user
	}
	
	@GET
	@Path("/UsersAuthentication/{username}/{phoneNo}")
	@Produces(MediaType.TEXT_HTML)
	public String userLogin (@PathParam("username")String username,@PathParam("phoneNo")String phoneNo) {
		return loginObj.userLogin(username,phoneNo);
		//appointments details for selected user
	}

}