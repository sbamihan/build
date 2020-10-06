package com.aboitiz.billstager.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aboitiz.billstager.model.Bill;

import reactor.core.publisher.Flux;

@Repository
public interface BillRepository extends ReactiveMongoRepository<Bill, Long> {

	Flux<Bill> findByUuidAndAcctNo(@Param("batchNo") String uuid, @Param("acctNo") String acctNo);

}
