<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.codehaus.jackson.map.ObjectMapper" %>
<%@ page import="org.codehaus.jackson.type.TypeReference" %>
<%@ page import="org.glassfish.jersey.client.ClientConfig" %>
<%@ page import="javax.ws.rs.client.Client" %>
<%@ page import="javax.ws.rs.client.ClientBuilder" %>
<%@ page import="javax.ws.rs.client.Entity" %>
<%@ page import="javax.ws.rs.client.WebTarget" %>
<%@ page import="javax.ws.rs.core.MediaType" %>
<%@ page import="javax.ws.rs.core.Response" %>
<%@ page import="javax.ws.rs.core.UriBuilder" %>
<%@ page import="java.net.URI" %>
<%@ page import="com.service.web.Product" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Hats</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="fetch_products.css">
</head>
<a href="Products"><img style="width:400px" src="images/HatsRUsLogo.png"></a><br>
<ul>
<li><a href="Products">Home</a></li>
<li><a href="productdetailservlet">Product Details</a></li>
<!-- <li><a href="checkout">Checkout</a></li>  -->
</ul>
<body>
<table>
	<tr>
		<td><b>Hat</b></td>
		<td><b>Description</b></td>
		<td><b>Price</b></td>
		<td><b>Color</b></td>
		<td><b>Material</b></td>
		<td><b>Product Identifier</b></td>
	</tr>
	<%
	ClientConfig myConfig = new ClientConfig();
    Client client = ClientBuilder.newClient(myConfig);
    WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/Assignment_4/").build());
    String jsonResponse = target.path("api").path("products").path("getAll").
            request(). //send a request
            accept(MediaType.APPLICATION_JSON). //specify the media type of the response
            get(String.class);
    
    ObjectMapper objectMapper = new ObjectMapper();
    List<Product> productList = objectMapper.readValue(jsonResponse, 
    										new TypeReference<List<Product>>(){});
    for (Product p : productList){ %>
    	
    	<tr>
	    	<td><div class='zoom'><img src="<%= p.getImage_url() %> "></div></td>
	    	<td><%= p.getDescription() %></td>
	    	<td><%= String.format("%.2f", p.getPrice()) %></td>
	    	<td><%= p.getColor() %></td>
	    	<td><%= p.getMaterial() %></td>
	    	<td><%= p.getProduct_id() %></td>
    	</tr>
    <% } %>
</table>
</body>
</html>