package com.aboitiz.billretriever.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.aboitiz.billretriever.model.Bill;

@RepositoryRestResource(exported = true)
@Transactional
public interface HeaderRepository extends JpaRepository<Bill, Long> {

	List<Bill> findTop12ByAcctNoOrderByBillDateDesc(@Param("acctNo") String acctNo);

	List<Bill> findByBillDate(@Param("billDate") Date billDate);

	List<Bill> findByBillDateAndAcctNo(@Param("billDate") Date billDate, @Param("acctNo") String acctNo);

	List<Bill> findByTranNo(@Param("tranNo") Long tranNo);

	List<Bill> findByBatchNo(@Param("batchNo") Long batchNo);

	List<Bill> findByBatchNoAndAcctNo(@Param("batchNo") Long batchNo, @Param("acctNo") String acctNo);

}
