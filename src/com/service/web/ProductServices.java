package com.service.web;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/product/service")
public class ProductServices {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductFromDBService() {
		return "In getProductFromDBService";
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductDetailService() {
		System.out.println("hello");
		return "In getProductDetailService";
	}
}
