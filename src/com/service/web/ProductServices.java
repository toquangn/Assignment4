package com.service.web;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
public class ProductServices {
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductByID(@PathParam("id") int id) throws SQLException {
	    final String servername="jdbc:mysql://localhost/inf124db026?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    final String username = "root";
	    final String password = "";
//		final String servername="jdbc:mysql://localhost:8889/inf124db026?useLegacyDatetimeCode=false&serverTimezone=America/Los_Angeles";
//	    final String username = "root";
//	    final String password = "root";
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
            		pd.setDescription1(d1);
            	}
            	if (d2 != "" && d2 != null) {
            		pd.setDescription2(d2);
            	}
            	if (d3 != "" && d3 != null) {
            		pd.setDescription3(d3);
            	}
            	if (d4 != "" && d4 != null) {
            		pd.setDescription4(d4);
            	}
            	if (d5 != "" && d5 != null) {
            		pd.setDescription5(d5);
            	}
			}
		    con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Response.ok(pd).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getProductDetailService() {
		System.out.println("hello");
		return "In getProductDetailService";
	}
}
