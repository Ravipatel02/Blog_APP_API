package com.masai.blog.payloads;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ApiResponce {
	private String message;
	private Boolean success;
	public ApiResponce(String message, Boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	
	
	

}
