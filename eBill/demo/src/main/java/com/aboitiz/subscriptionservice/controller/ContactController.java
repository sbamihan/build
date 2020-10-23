package com.aboitiz.subscriptionservice.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aboitiz.subscriptionservice.model.Contact;
import com.aboitiz.subscriptionservice.repository.ContactRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ContactController {

	private ContactRepository contactRepository;

	public ContactController(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@GetMapping("/contacts")
	private Flux<Contact> getAllContacts() {
		return Flux.fromIterable(contactRepository.findAll());
	}

	@GetMapping("/contacts/{contactId}")
	private Mono<Contact> getContactById(@PathVariable Integer contactId) {
		Optional<Contact> contact = contactRepository.findById(contactId);

		if (!contact.isPresent()) {
			return Mono.empty();
		}

		return Mono.justOrEmpty(contact.orElse(null));
	}
	
	@GetMapping("/contacts/search/findByType")
	private Flux<Contact> findByType(@RequestParam String typeCode) {
		return Flux.fromIterable(contactRepository.findByContactType_typeCode(typeCode));
	}

}
