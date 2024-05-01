package com.shubham.blog.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

	private Integer categoryId;

	@NotBlank
	@Size(min = 4, message ="min size of title is 4")
	@Column(name = "title")
	private String categoryTitle;

	@NotBlank
	@Size(min = 10, message ="min size of title is 10")
	@Column(name = "description")
	private String categoryDescription;
}
