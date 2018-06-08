package com.service.web;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
public class ProductDescription {
	
	private int product_id;
	private String name;
	private double price;
	private String description1;
	private String description2;
	private String description3;
	private String description4;
	private String description5;
	private String img_url1;
	private String img_url2;
	private String img_url3;
	
	public ProductDescription() {
		
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription1() {
		return description1;
	}
	public void setDescription1(String description1) {
		this.description1 = description1;
	}
	public String getDescription2() {
		return description2;
	}
	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	public String getDescription3() {
		return description3;
	}
	public void setDescription3(String description3) {
		this.description3 = description3;
	}
	public String getDescription4() {
		return description4;
	}
	public void setDescription4(String description4) {
		this.description4 = description4;
	}
	public String getDescription5() {
		return description5;
	}
	public void setDescription5(String description5) {
		this.description5 = description5;
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
