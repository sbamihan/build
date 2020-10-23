package com.aboitiz.subscriptionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aboitiz.subscriptionservice.model.ContactType;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, String> {

}
