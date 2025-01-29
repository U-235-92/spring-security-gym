package aq.app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import aq.app.entities.CustomCsrfToken;

public interface JpaCsrfTokenRepository extends CrudRepository<CustomCsrfToken, Integer> {

	Optional<CustomCsrfToken> findCustomCsrfTokenByIdentifier(String identifier);
}
