package com.aboitiz.subscriptionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aboitiz.subscriptionservice.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	List<Account> findByAccountSubscriptions_subscriptionType_typeCode(@Param("typeCode") String typeCode);

	List<Account> findByAccountIdAndAccountSubscriptions_subscriptionType_typeCode(@Param("accountId") String accountId,
			@Param("typeCode") String typeCode);

}
