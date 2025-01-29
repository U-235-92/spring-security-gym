package aq.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CustomCsrfToken {

	@Id
	@GeneratedValue
	private int id;
	private String identifier;
	private String token;
}
