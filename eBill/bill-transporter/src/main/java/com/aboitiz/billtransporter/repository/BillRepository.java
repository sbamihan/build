package com.aboitiz.billtransporter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aboitiz.billtransporter.model.Bill;

@Repository
public interface BillRepository extends ReactiveMongoRepository<Bill, Long> {

	List<Bill> findByBatchNo(@Param("batchNo") Long batchNo);
	
}
