package com.vegancakes;

public class Product {
	private int id;
	private String cake_image;
	private String cake_name;
	private String cake_description;
	private String cake_category;
	private int cake_quantity;
	private int cake_price;
	
	public Product() {
		super();
	}

	public Product(int id, String cake_image, String cake_name, String cake_description, String cake_category,
		int cake_quantity, int cake_price) {
		super();
		this.id = id;
		this.cake_image = cake_image;
		this.cake_name = cake_name;
		this.cake_description = cake_description;
		this.cake_category = cake_category;
		this.cake_quantity = cake_quantity;
		this.cake_price = cake_price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCake_image() {
		return cake_image;
	}

	public void setCake_image(String cake_image) {
		this.cake_image = cake_image;
	}

	public String getCake_name() {
		return cake_name;
	}

	public void setCake_name(String cake_name) {
		this.cake_name = cake_name;
	}

	public String getCake_description() {
		return cake_description;
	}

	public void setCake_description(String cake_description) {
		this.cake_description = cake_description;
	}

	public String getCake_category() {
		return cake_category;
	}

	public void setCake_category(String cake_category) {
		this.cake_category = cake_category;
	}

	public int getCake_quantity() {
		return cake_quantity;
	}

	public void setCake_quantity(int cake_quantity) {
		this.cake_quantity = cake_quantity;
	}

	public int getCake_price() {
		return cake_price;
	}

	public void setCake_price(int cake_price) {
		this.cake_price = cake_price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", cake_image=" + cake_image + ", cake_name=" + cake_name + ", cake_description="
				+ cake_description + ", cake_category=" + cake_category + ", cake_quantity=" + cake_quantity
				+ ", cake_price=" + cake_price + "]";
	}
	
}
