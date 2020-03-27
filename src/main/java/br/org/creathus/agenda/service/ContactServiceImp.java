package br.org.creathus.agenda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.creathus.agenda.entities.Contact;
import br.org.creathus.agenda.repositories.ContactRepository;

@Service
public class ContactServiceImp implements ContactService {

	@Autowired
	ContactRepository contactRepository;

	@Override
	public Contact save(Contact c) {

		return contactRepository.save(c);
	}

	@Override
	public Optional<Contact> findById(Long id) {
		
		return contactRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		contactRepository.deleteById(id);	
	}

	@Override
	public List<Contact> findAll() {
		return (List<Contact>) contactRepository.findAll();
	}

	@Override
	public List<Contact> findByNameContaining(String name) {
		return contactRepository.findByNameContaining(name);
	}

	
}
