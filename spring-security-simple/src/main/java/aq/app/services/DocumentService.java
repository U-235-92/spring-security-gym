package aq.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import aq.app.models.Document;
import aq.app.repositories.DocumentRepository;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
//	@PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")
	@PreAuthorize("hasPermission(#code, 'aq.app.models.Document', 'ROLE_admin')") 
	public Document getDocument(String code) {
		return documentRepository.findDocument(code);
	}
}
