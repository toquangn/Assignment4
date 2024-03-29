package com.service.web;

public class Cart {
	private int product_id;
	private String description;
	private double price;
	private int quantity;

	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String toString() {
		return "[ pid =" + this.product_id 
				+ ", description=" + this.description 
				+ ", price=" + this.price 
				+ ", quantity=" + this.quantity 
				+ " ]";
	}
	
	
}
