package br.org.creathus.agenda.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.org.creathus.agenda.entities.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {

	List<Contact> findByNameContaining(String name);

}
