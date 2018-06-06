

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB Credentials
	    final String servername="jdbc:mysql://localhost/inf124db026?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    final String username = "root";
	    final String password = "";
//		final String password = "root";

		String pid = request.getParameter("pid");
		String addtoCart = request.getParameter("prodId");
		// Session tracking for Hats in Cart
        HttpSession session = request.getSession(true);
        
     // print session info
        Date created = new Date(session.getCreationTime());
        Date accessed = new Date(session.getLastAccessedTime());
        System.out.println("ID " + session.getId());
        System.out.println("Created: " + created);
        System.out.println("Last Accessed: " + accessed);
		// Html constant declarations
		String declaration =  "<html><title>Product Details</title>";
		String styleSheet = "<head><link rel=\"stylesheet\" type=\"text/css\" href=\"product_description.css\"></head>";
		String javaScript = "<script>function addValue() {document.getElementById(\"prodId\").value=\"hidden\"}</script>";
		String logo = "<a href=\"products\"><img style=\"width:400px\" src=\"images/HatsRUsLogo.png\"></a><br>";
		// Add Links to navigation bar here
	    String nav ="<ul class=\"header\">"
	    		+ "<li class=\"header\"><a href=\"products\">Home</a></li>"
	    		+ "<li class=\"header\"><a href=\"productdetail\">Product Details</a></li>" 
	    		+ "<li class=\"header\"><a href=\"checkout\">Checkout</a></li>"
	    		+ "</ul>";
	    String form = "<br><form action=\"productdetail\" method=\"post\">"+
				"Product Identifier: <input type=\"text\" name=\"pid\" pattern=\"[1-9]|10\"/><br><br>" +
				"<input type=\"submit\" value=\"Show More Details\"/>" +
			"</form>";
		String close = "</body></html>";
		// Initialize Content
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println( declaration + styleSheet + "<body>" + logo + nav + form);
		try {
			if (pid != null) {
			// Session tracking for recently viewed hats
			/*
			ServletContext servContext = getServletContext();
			System.out.println(servContext.getAttribute("viewedProductsList").getClass());
			ArrayList<String> currentViewedProductsList = (ArrayList<String>) servContext.getAttribute("viewedProductsList");
			ArrayList<String> updatedViewedProductsList = updateList(currentViewedProductsList, pid);
			servContext.setAttribute("viewedProductsList", updatedViewedProductsList);
			 */
			@SuppressWarnings("unchecked")
			ArrayList<String> currentViewedProductsList = (ArrayList<String>) session.getAttribute("viewedProductsList");
			ArrayList<String> updatedViewedProductsList = updateList(currentViewedProductsList, pid);
			session.setAttribute("viewedProductsList", updatedViewedProductsList);
			
			// Session tracking for products
			session.setAttribute("product_id", pid);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(servername, username, password);
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM products, product_description WHERE products.product_id=" + pid + " AND description_id=" + pid);
			while (rs.next()) {
				out.println("<h1 class='description-header'>" + rs.getString("description") + " Product Description</h1>");
				out.println("<p class='description-header'> <b>Product Identifier</b>: " + rs.getInt("product_id") + "| <b>Price</b>: $" + rs.getInt("price") + "</p>");
				out.println("<div id='images'>");
					out.println("<div class='row'>");
						out.println("<div class='zoom'>");
				           	out.println("<img class=thumbnail src='" + rs.getString("img1") + "'>");
				           out.println("</div>");
				           out.println("<div class='zoom'>");
			            	out.println("<img class=thumbnail src='" + rs.getString("img2") + "'>");
			            out.println("</div>");
			           	out.println("<div class='zoom'>");
			           		out.println("<img class=thumbnail src='" + rs.getString("img3") + "'>");
			           	out.println("</div>");
	            		out.println("</div>");
	            	out.println("</div>");	
	            	
	            	out.println("<ul>");
		            	String d1 = rs.getString("description1");
		            	String d2 = rs.getString("description2");
		            	String d3 = rs.getString("description3");
		            	String d4 = rs.getString("description4");
		            	String d5 = rs.getString("description5");
	
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
	            	out.println("</ul>");
				}
				out.println("<form action=\"ProductDetailServlet\" method=\"post\">"
						+ "<input name=\"prodId\" id=\"prodId\" type=\"hidden\" value=\""+"pid"+"\">"
						+ "<input type=\"submit\" value=\"Add to Cart\" align=\"center\" style=\"width:15%; background-color:red; color:white; font-size:14px; font\" onsubmit=\"addValue()\"></submit>");
				con.close();
			}
			else if (addtoCart != null) {
				String value = session.getAttribute("product_id").toString();
				int counter = 0;
		        Enumeration en = session.getAttributeNames();
		        while (en.hasMoreElements()) {
		            String name2 = (String)en.nextElement();
		            String value2 = session.getAttribute(name2).toString();
		            System.out.println(name2 + " = " + value2);
		            counter+=1;
		        }
				String itemname = "item" + counter;
				session.setAttribute(itemname, value);
				RequestDispatcher rd=request.getRequestDispatcher("productdetailCart");  
	        	rd.forward(request, response);
			}
			else {
				out.println("Please enter a valid product id number.");
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		out.println(close);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private ArrayList<String> updateList(ArrayList<String> productsList, String id){
		productsList.add(id);
		System.out.println(id + "added to the products list " + "with a size of " + productsList.size());
		if (productsList.size() > 5) {
			productsList.remove(0);
		}
		return productsList;
	}

}
