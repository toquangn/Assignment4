package com.service.web;

import java.util.ArrayList;

public class ProductDescription {
	private int product_id;
	private String name;
	
	private ArrayList<String> descriptions;
	
	private double price;
	private String img_url1;
	private String img_url2;
	private String img_url3;
	
	public ProductDescription() {
		this.descriptions = new ArrayList<String>();
	}
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addDescription(String d) {
		descriptions.add(d);
	}
	public ArrayList<String> getDescriptions(){
		return descriptions;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImg_url1() {
		return img_url1;
	}
	public void setImg_url1(String img_url1) {
		this.img_url1 = img_url1;
	}
	public String getImg_url2() {
		return img_url2;
	}
	public void setImg_url2(String img_url2) {
		this.img_url2 = img_url2;
	}
	public String getImg_url3() {
		return img_url3;
	}
	public void setImg_url3(String img_url3) {
		this.img_url3 = img_url3;
	}
	
	

	
	

	
	

}
