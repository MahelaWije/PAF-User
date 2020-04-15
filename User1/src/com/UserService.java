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
	User itemObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers() {
		return itemObj.readUsers();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addUserDetails(@FormParam("username") String username, @FormParam("phoneNo") String phoneNo,
			@FormParam("age") String age, @FormParam("address") String address, @FormParam("gender") String gender,
			@FormParam("email") String email) {
		String output = itemObj.addUserDetails(username, phoneNo, age, address, gender, email);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUserDetails(String userData) {
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(userData).getAsJsonObject();
		// Read the values from the JSON object
		String user_id = itemObject.get("user_id").getAsString();
		String username = itemObject.get("username").getAsString();
		String phoneNo = itemObject.get("phoneNo").getAsString();
		String age = itemObject.get("age").getAsString();
		String address = itemObject.get("address").getAsString();
		String gender = itemObject.get("gender").getAsString();
		String email = itemObject.get("email").getAsString();
		String output = itemObj.updateUserDetails(user_id, username, phoneNo, age, address, gender, email);
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
		String output = itemObj.deleteUsers(user_id);
		return output;
	}

}

