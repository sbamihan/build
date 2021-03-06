package com.aboitiz.kafkaspringboottutorial.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aboitiz.kafkaspringboottutorial.model.Header;

@RepositoryRestResource(exported = true)
public interface HeaderRepository extends JpaRepository<Header, Long> {

	List<Header> findByBillDate(@Param("billDate") Date billDate);

	List<Header> findByBillDateAndAcctNo(@Param("billDate") Date billDate, @Param("acctNo") String acctNo);

	List<Header> findByTranNo(@Param("tranNo") Long tranNo);

	List<Header> findByBatchNo(@Param("batchNo") Long batchNo);

	List<Header> findByBatchNoAndAcctNo(@Param("batchNo") Long batchNo, @Param("acctNo") String acctNo);

}
