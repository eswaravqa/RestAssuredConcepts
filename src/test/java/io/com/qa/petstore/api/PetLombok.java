package io.com.qa.petstore.api;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetLombok {

	@JsonProperty("id") //This can be written to all variables/parameters 
	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tag> tags;
	private String status;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Category {
		private Integer id;
		private String name;	
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Tag {
		private Integer id;
		private String name;
	}
}
