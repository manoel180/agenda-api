package br.org.creathus.agenda.service;

import java.util.List;
import java.util.Optional;

import br.org.creathus.agenda.entities.Contact;

public interface ContactService {

	public Contact save(Contact c);

	public Optional<Contact> findById(Long id);

	public void deleteById(Long id);

	public List<Contact> findAll();

	public List<Contact> findByNameContaining(String name);

}
