<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.util.ArrayList" %>
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
	<%
/* 	final String servername="jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db026?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    final String username = "inf124db026";
    final String password = "VXIQ!ymo!v@G";	 */	
/* 	final String servername="jdbc:mysql://localhost:8889/inf124db026?useLegacyDatetimeCode=false&serverTimezone=America/Los_Angeles";
    final String username = "root";
    final String password = "root"; */
	final String servername="jdbc:mysql://localhost/inf124db026?useLegacyDatetimeCode=false&serverTimezone=America/Los_Angeles";
    final String username = "root";
    final String password = "";
    try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(servername, username, password);
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM products");
		while (rs.next()) {
			out.println("<tr>");
			out.println("<td><div class='zoom'><img src=" + rs.getString("image_url") + "></div></td>");
			out.println("<td>" + rs.getString("description") + "</td>");
			out.println("<td>" + String.format("%.2f", rs.getDouble("price")) + "</td>");
			out.println("<td>" + rs.getString("color") + "</td>");
			out.println("<td>" + rs.getString("material") + "</td>");
			out.println("<td>" + rs.getInt("product_id") + "</td>");
			out.println("</tr>");
		}
		con.close();
    }catch (ClassNotFoundException e) {
		System.out.println("JDBC Driver Not Connected");
		e.printStackTrace();
		return;
	}catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Did not connect to db");
	}
    System.out.println("Successfully connected to db");
	%>
</table>
</body>
</html>