package aq.app.entities;

import lombok.Data;

@Data
//@Entity
public class User {

//	@Id
	private int id;
	private String username;
	private String password;
	private String authority;
}
