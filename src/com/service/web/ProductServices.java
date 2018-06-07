package com.service.web;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/products")
public class ProductServices {
	
	@GET
	@Path("get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductDescription getProductByID(@PathParam("id") int id) throws SQLException {
	    final String servername="jdbc:mysql://localhost/inf124db026?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    final String username = "root";
	    final String password = "";
	    
	    ProductDescription pd = new ProductDescription();

	    try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(servername, username, password);
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT * FROM products, product_description WHERE products.product_id=" + id + " AND description_id=" + id);
			while (rs.next()) {
				pd.setName(rs.getString("description"));
				pd.setProduct_id(rs.getInt("product_id"));
				pd.setPrice(rs.getDouble("price"));
				pd.setImg_url1(rs.getString("img1"));
				pd.setImg_url2(rs.getString("img2"));
				pd.setImg_url3(rs.getString("img3"));
				
				String d1 = rs.getString("description1");
            	String d2 = rs.getString("description2");
            	String d3 = rs.getString("description3");
            	String d4 = rs.getString("description4");
            	String d5 = rs.getString("description5");

            	if (d1 != "" && d1 != null) {
            		pd.addDescription(d1);
            	}
            	if (d2 != "" && d2 != null) {
            		pd.addDescription(d2);
            	}
            	if (d3 != "" && d3 != null) {
            		pd.addDescription(d3);
            	}
            	if (d4 != "" && d4 != null) {
            		pd.addDescription(d4);
            	}
            	if (d5 != "" && d5 != null) {
            		pd.addDescription(d5);
            	}
			}
		    con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pd;
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductDetailService() {
		System.out.println("hello");
		return "In getProductDetailService";
	}
}
