package com;
import model.Item; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Items") 
public class ItemService 
{ 
 Item itemObj = new Item(); 
 @GET
 @Path("/") 
 @Produces(MediaType.TEXT_HTML) 
 public String readItems() 
  { 
  return itemObj.readItems(); 
 }
 
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertItem(@FormParam("username") String username, 
			@FormParam("password") String password, 
			@FormParam("address") String address, 
			@FormParam("contac_no") Integer contac_no,
			@FormParam("email")String email) 
 { 
  String output = itemObj.insertItem(username, password, address, contac_no, email); 
 return output; 
 }

 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateItem(String itemData) 
 { 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	//Read the values from the JSON object
	 Integer userID = itemObject.get("userID").getAsInt(); 
	 String username = itemObject.get("user_name").getAsString(); 
	 String password = itemObject.get("password").getAsString(); 
	 String address = itemObject.get("address").getAsString(); 
	 Integer contac_no = itemObject.get("contac_no").getAsInt();
	 String email = itemObject.get("email").getAsString();
  String output = itemObj.updateItem(userID, username, password, address, contac_no, email); 
 return output; 
 }

 @DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteItem(String itemData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
  
 
 
//Read the value from the element <itemID>
 String userID = doc.select("userID").text();
  String output = itemObj.deleteItem(userID); 
 return output; 
 }
}
