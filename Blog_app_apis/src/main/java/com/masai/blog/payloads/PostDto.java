package com.masai.blog.payloads;

import java.util.Date;

import com.masai.blog.entities.Category;
import com.masai.blog.entities.User;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private Integer postId;
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	@NotEmpty
	private String postImage;
	@NotEmpty
	private Date date;

	private Category category;
	
	private User user;
	

}
