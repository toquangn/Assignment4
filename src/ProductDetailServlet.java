import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
//import java.util.Enumeration;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// JSON Parsing Imports
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;

import com.service.web.Cart;
import com.service.web.ProductDescription;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDescription productDescription = null;

       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pid = request.getParameter("pid");
		String addtoCart = request.getParameter("prodId");
		// Session tracking for Hats in Cart
        HttpSession session = request.getSession(true);
        
        // Print session info
        Date created = new Date(session.getCreationTime());
        Date accessed = new Date(session.getLastAccessedTime());
        System.out.println("ID " + session.getId());
        System.out.println("Created: " + created);
        System.out.println("Last Accessed: " + accessed);
        
		// HTML constant declarations
		String declaration =  "<html><title>Product Details</title>";
		String styleSheet = "<head><link rel=\"stylesheet\" type=\"text/css\" href=\"product_description.css\"></head>";
		String javaScript = "<script>function addValue() {document.getElementById(\"prodId\").value=\"hidden\"}</script>";
		String logo = "<a href=\"products\"><img style=\"width:400px\" src=\"images/HatsRUsLogo.png\"></a><br>";
		// Add Links to navigation bar here
	    String nav ="<ul class=\"header\">"
	    		+ "<li class=\"header\"><a href=\"products\">Home</a></li>"
	    		+ "<li class=\"header\"><a href=\"productdetailservlet\">Product Details</a></li>" 
	    		+ "<li class=\"header\"><a href=\"checkout\">Checkout</a></li>"
	    		+ "</ul>";
//	    "<br><form method=\"get\" action=\"http://localhost:8080/HelloWorldJSP/productdetailservlet\">"+
	    String form = "<br><form method=\"get\" action=\"http://localhost:8080/Assignment_4/productdetailservlet\">"+
				"Product Identifier: <input type=\"text\" name=\"pid\" pattern=\"[1-9]|10\"/><br><br>" +
				"<input type=\"submit\" value=\"Show More Details\"/>" +
			"</form>";
		String close = "</body></html>";
		// Print Form
		PrintWriter out = response.getWriter();
		if (pid == null) {
			response.setContentType("text/html");
			out.println( declaration + styleSheet + "<body>" + logo + nav + form);
		}
		// Initialize Content
		if (pid != null) {
			// Session tracking for recently viewed hats
//			@SuppressWarnings("unchecked")
//			ArrayList<String> currentViewedProductsList = (ArrayList<String>) session.getAttribute("viewedProductsList");
//			ArrayList<String> updatedViewedProductsList = updateList(currentViewedProductsList, pid);
//			session.setAttribute("viewedProductsList", updatedViewedProductsList);
			
			// Set pid session value
			session.setAttribute("product_id", pid);
			System.out.println("PID IS NOT EQUAL TO NULL: "+pid);
			// Print page
			// Build client
			ClientConfig config = new ClientConfig();
	        Client client = ClientBuilder.newClient(config);
	        WebTarget target = client.target(getBaseURI());
			// Retrieve String with Json response of a product in rest service.
//			String jsonResponse = /api/products/"+pid;
	        String jsonResponse = target.path("api").path("products").path(pid).
	                        request(). //send a request
	                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
	                        get(String.class);
	        System.out.println(jsonResponse);
	        // Format JSON Response as a product Description
	        ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library
	        productDescription = objectMapper.readValue(jsonResponse, new TypeReference<ProductDescription>(){});
	        // Print Page with Json response
	        out.print(declaration + styleSheet + "<body>" + logo + nav);
			out.println("<h1 class='description-header'>" + productDescription.getName() + " Product Description</h1>");
			out.println("<p class='description-header'> <b>Product Identifier</b>: " + productDescription.getProduct_id() + "| <b>Price</b>: $" + productDescription.getPrice() + "</p>");
			out.println("<div id='images'>");
				out.println("<div class='row'>");
					out.println("<div class='zoom'>");
			           	out.println("<img class=thumbnail src='" + productDescription.getImg_url1() + "'>");
			           out.println("</div>");
			           out.println("<div class='zoom'>");
		            	out.println("<img class=thumbnail src='" + productDescription.getImg_url2() + "'>");
		            out.println("</div>");
		           	out.println("<div class='zoom'>");
		           		out.println("<img class=thumbnail src='" + productDescription.getImg_url3() + "'>");
		           	out.println("</div>");
            		out.println("</div>");
            	out.println("</div>");	
            	
            	out.println("<ul>");
	            	String d1 = productDescription.getDescription1();
	            	String d2 = productDescription.getDescription2();
	            	String d3 = productDescription.getDescription3();
	            	String d4 = productDescription.getDescription4();
	            	String d5 = productDescription.getDescription5();

	            	if (d1 != "" && d1 != null) {
	            		out.println("<li>" + d1 + "</li>");
	            	}
	            	if (d2 != "" && d2 != null) {
	            		out.println("<li>" + d2 + "</li>");
	            	}
	            	if (d3 != "" && d3 != null) {
	            		out.println("<li>" + d3 + "</li>");
	            	}
	            	if (d4 != "" && d4 != null) {
	            		out.println("<li>" + d4 + "</li>");
	            	}
	            	if (d5 != "" && d5 != null) {
	            		out.println("<li>" + d5 + "</li>");
	            	}
	            	out.print("</ul>");
	        
	        // Session tracking for Cart
//	        out.print("<form action=\"http://localhost:8080/Assignment_4/api/products/" + pid + "\" method=\"post\">" 
	    	out.print("<form name=\"cart_form\">" 
					+ "<input name=\"prodId\" id=\"prodId\" type=\"hidden\" value=\""+ pid + "\">"
					+ "<input name=\"cart_submit\" type=\"submit\" value=\"Add to Cart\" align=\"center\" style=\"width:15%; background-color:red; color:white; font-size:14px; font\" onsubmit=\"addValue()\"></submit>");
	        
	        out.print(close);
	}
        if (productDescription != null && request.getParameter("cart_submit") != null) {
        	Cart c = new Cart();
        	c.setProduct_id(Integer.parseInt(addtoCart));
        	c.setDescription(productDescription.getName());
        	c.setPrice(productDescription.getPrice());
        	c.setQuantity(1);        
        	
        	ClientConfig config = new ClientConfig();
	        Client client = ClientBuilder.newClient(config);
	        WebTarget target = client.target(getBaseURI());
	        Response callResult = target.path("api").path("products").path("post").
	        		request().post(Entity.entity(c, MediaType.APPLICATION_JSON));

        }
	}
	
    private static URI getBaseURI() {
        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://localhost:8080/Assignment_4").build();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
//	private ArrayList<String> updateList(ArrayList<String> productsList, String id){
//		productsList.add(id);
//		System.out.println(id + "added to the products list " + "with a size of " + productsList.size());
//		if (productsList.size() > 5) {
//			productsList.remove(0);
//		}
//		return productsList;
//	}

}
