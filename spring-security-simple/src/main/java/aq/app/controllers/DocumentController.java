package aq.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import aq.app.models.Document;
import aq.app.services.DocumentService;

@RestController
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@GetMapping("/documents/{code}")
	public Document getDetails(@PathVariable String code) {
		return documentService.getDocument(code);
	}
}
