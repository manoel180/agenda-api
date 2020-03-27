package br.org.creathus.agenda.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.creathus.agenda.entities.Contact;
import br.org.creathus.agenda.service.ContactService;

@Controller
@RequestMapping(path = "/contact")
public class ContactController {

	@Autowired
	ContactService contactService;

	@PostMapping
	public @ResponseBody String save(@RequestBody Contact c) {

		contactService.save(c);
		return "Saved";
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Contact contact) {
		Optional<Contact> contactObj = contactService.findById(id);

		if (contactObj.isPresent()) {
			Contact _contact = contactObj.get();
			_contact.setName(contact.getName());
			_contact.setPhone(contact.getPhone());
			_contact.setEmail(contact.getEmail());
			_contact.setGender(contact.getGender());

			return new ResponseEntity<>(contactService.save(_contact), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> delete(@PathVariable("id") long id) {
		Optional<Contact> contact = contactService.findById(id);

		if (contact.isPresent()) {
			contactService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping()
	public @ResponseBody ResponseEntity<?> getContacts(@RequestParam(required = false) String name) {
		try {
			List<Contact> contacts = new ArrayList<>();

			if (name == null)
				contactService.findAll().forEach(contacts::add);
			else
				contactService.findByNameContaining(name).forEach(contacts::add);

			if (contacts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(contacts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getContactById(@PathVariable("id") Long id) {
		Optional<Contact> contact = contactService.findById(id);

		if (contact.isPresent()) {
			return new ResponseEntity<>(contact.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
