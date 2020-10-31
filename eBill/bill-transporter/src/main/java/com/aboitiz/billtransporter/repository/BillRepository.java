package com.aboitiz.billtransporter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aboitiz.billtransporter.model.Bill;

import reactor.core.publisher.Flux;

@RepositoryRestResource
public interface BillRepository extends ReactiveMongoRepository<Bill, String> {

	Flux<Bill> findByBatchNo(@Param("batchNo") Long batchNo);

	Flux<Bill> findByUuid(@Param("uuid") String uuid);

}
