package com.aboitiz.subscriptionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aboitiz.subscriptionservice.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	List<Contact> findByContactType_typeCode(@Param("typeCode") String typeCode);
	
}
