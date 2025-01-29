package aq.app.security.evaluators;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import aq.app.models.Document;
import aq.app.repositories.DocumentRepository;

@Component
public class DocumentPermissionEvaluator implements PermissionEvaluator {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		Document document = (Document) targetDomainObject;
		String perm = (String) permission;
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(perm));
		return isAdmin || document.getOwner().equals(authentication.getName());
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		String code = targetId.toString();
		Document document = documentRepository.findDocument(code);
		String perm = (String) permission;
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(perm));
		return isAdmin || document.getOwner().equals(authentication.getName());
	}
}
