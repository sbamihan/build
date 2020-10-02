package com.aboitiz.billstager.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.aboitiz.billstager.model.Bill;

@Repository
public interface BillRepository extends ReactiveMongoRepository<Bill, Long> {

}
