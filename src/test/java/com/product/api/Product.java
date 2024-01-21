package com.product.api;

//Create a response POJO class 
//For response default constructor is mandatory

public class Product {

	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;

	// Default constructor
	public Product() {
	}

	// Constructor with all arguments (method variables)
	public Product(int id, String title, double price, String description, String category, String image,
			Rating rating) {

		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
		this.image = image;
		this.rating = rating;
	}

	// Create Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	// Inner-Class: Create a rating class
	public static class Rating {
		private float rate;
		private int count;

		// Default constructor
		public Rating() {
		}

		// Constructor with variables
		public Rating(float rate, int count) {
			super();
			this.rate = rate;
			this.count = count;
		}

		// Getters & Setters
		public float getRate() {
			return rate;
		}

		public void setRate(float rate) {
			this.rate = rate;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

	}

}
