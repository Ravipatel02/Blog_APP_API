package com.masai.blog.payloads;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {
	private Integer id;
    @NotEmpty
    @Size(min=4 , message="Name is minmum 4 character !!")
	private String name;
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
	private String email;
    @NotEmpty
    @Size(min = 6 , max = 12 , message = "password length min 6 and max length 12 !!")
	private String password;
    @NotEmpty
    @Size(min=10 , max=550)
	private String About;

}
