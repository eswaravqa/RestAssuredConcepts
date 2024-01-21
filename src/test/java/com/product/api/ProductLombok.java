package com.product.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder  //This annotation is responsible for builder pattern
public class ProductLombok {
	
	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	//Rating: Inner Class
	public static class Rating {
		private float rate;
		private int count;
	}
	
}
