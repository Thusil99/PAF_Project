package model;

import java.sql.*;

public class Item {
	
	private Connection connect() 
	{ 
			Connection con = null; 
			
			try
	{ 
				Class.forName("com.mysql.jdbc.Driver"); 
 
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_test", "root", ""); 
	} 
			catch (Exception e) 
			{e.printStackTrace();} 
 
			return con; 
	}

	public String readItems() 
	 { 
			String output = ""; 
			
			try
			{ 
				Connection con = connect(); 
				
				if (con == null) 
				{ 
					return "Error while connecting to the database for reading."; 
				} 
				
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>User ID</th><th>User Name</th> <th>Password</th><th>Address</th>"+
						 "<th>Contact No</th><th>Email</th> <th>Update</th><th>Remove</th></tr>";
							 
	 
							
				String query = "select * from items"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next()) 
				{ 
					String userID = Integer.toString(rs.getInt("userID")); 
					String username = rs.getString("username");
					String password = rs.getString("password"); 
					String address = rs.getString("address");
					String contac_no = Integer.toString(rs.getInt("contac_no")); 
					String email = rs.getString("email"); 
					
					// Add into the html table
					output += "<tr> <td>"+ userID + "</td>" ;
					output += "<td>" + username + "</td>";
					output += "<td>" + password + "</td>";
					output += "<td>" + address + "</td>";
					output += "<td>" + contac_no + "</td>"; 
					output += "<td>" + email + "</td>"; 
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-itemid='" + userID + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-itemid='" + userID + "'></td></tr>"; 
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
	
	public String insertItem(String username, String password, String address, Integer contac_no,String email) 
	
			 { 
			 	String output = ""; 
			 	System.out.println("data insert");
			 	try
			 	{ 
			 		Connection con = connect();
			 		
			 		if (con == null) 
			 		{ 
			 			return "Error while connecting to the database for inserting."; 
			 		} 
			 		
			 		// create a prepared statement
			 		String query = " insert into items (`userID`,`username`,`password`,`address`,`contac_no`,`email`)"
							 		+ " values (?, ?, ?, ?, ?, ?)"; 
	
			 		PreparedStatement preparedStmt = con.prepareStatement(query);
			 		
			 		// binding values
			 		preparedStmt.setInt(1, 0); 
			 		preparedStmt.setString(2, username); 
			 		preparedStmt.setString(3, password); 
			 		preparedStmt.setString(4, address);
			 		preparedStmt.setInt(5, contac_no);
			 		preparedStmt.setString(6, email);
			 		
			 		
			 		// execute the statement
			 		preparedStmt.execute(); 
			 		con.close(); 
			 		
			 		String newItems = readItems(); 
			 		output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
			 	} 
			 	catch (Exception e) 
			 	{ 
			 			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
			 			System.err.println(e.getMessage()); 
			 	} 
			 	
			 	return output; 
			 } 
	
	public String updateItem(Integer userID, String username, String password, String address, Integer contac_no,String email) 
			 { 
			 	String output = ""; 
			 	
			 	try
			 	{ 
			 		Connection con = connect();
			 		
			 		if (con == null) 
			 		{ 
			 			return "Error while connecting to the database for updating."; 
			 		} 
			 		
			 		// create a prepared statement
			 		String query = "UPDATE items SET username=?,password=?,address=?,contac_no=?,email=? WHERE userID=?"; 
			 		PreparedStatement preparedStmt = con.prepareStatement(query); 
			 		
			 		// binding values
			 		preparedStmt.setString(1, username); 
			 		preparedStmt.setString(2, password); 
			 		preparedStmt.setString(3, address); 
			 		preparedStmt.setInt(4, contac_no);
			 		preparedStmt.setString(5, email);
			 		preparedStmt.setInt(6, userID);
			 		
			 		// execute the statement
			 		preparedStmt.execute(); 
			 		con.close(); 
			 		
			 		String newItems = readItems(); 
			 		output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
			 	} 
			 	catch (Exception e) 
			 	{ 
			 		output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
			 		System.err.println(e.getMessage()); 
			 	} 
			 	
			 	return output; 
			 }
	
	public String deleteItem(String userID) 
	 		{ 
				String output = ""; 
				
				System.out.println(userID);
				try
				{ 
					Connection con = connect(); 
					
					if (con == null) 
					{ 
						return "Error while connecting to the database for deleting."; 
					} 
					
					// create a prepared statement
					String query = "delete from items where userID=?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(userID)); 
					
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					
					String newItems = readItems(); 
					output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
				} 
				catch (Exception e) 
				{ 
					output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
					System.err.println(e.getMessage()); 
				} 
				return output; 
	 		}

	 

	
	
}
