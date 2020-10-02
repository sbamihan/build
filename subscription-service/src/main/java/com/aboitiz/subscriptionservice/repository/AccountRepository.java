package com.aboitiz.subscriptionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aboitiz.subscriptionservice.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

}
