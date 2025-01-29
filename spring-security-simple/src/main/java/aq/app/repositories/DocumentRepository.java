package aq.app.repositories;

import java.util.Map;

import org.springframework.stereotype.Repository;

import aq.app.models.Document;

@Repository
public class DocumentRepository {

	private Map<String, Document> documents = Map.of(
			"abc123", new Document("julien"), 
			"qwe123", new Document("julien"), 
			"asd555", new Document("nikolai"));

	public Document findDocument(String code) { 
		return documents.get(code); 
	}
}
