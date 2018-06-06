package com.service.web;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/product/service")
public class ProductServices {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductDescription() {
		System.out.println("In getProductionMethod");
		return "In getProductDescription Method";
	}
}
