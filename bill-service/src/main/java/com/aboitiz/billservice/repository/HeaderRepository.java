package com.aboitiz.billservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aboitiz.billservice.model.Header;

@RepositoryRestResource
public interface HeaderRepository extends JpaRepository<Header, Long> {

	List<Header> findByBillDate(@Param("billDate") Date billDate);
	
	List<Header> findByTranNo(@Param("tranNo") Long tranNo);

}
