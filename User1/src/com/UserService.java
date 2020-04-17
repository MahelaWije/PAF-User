package com;

import model.User;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Users")
public class UserService {
	User userObj = new User();

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

}