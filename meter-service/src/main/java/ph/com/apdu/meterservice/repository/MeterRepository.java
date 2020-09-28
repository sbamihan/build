/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.com.apdu.meterservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.annotations.Api;
import ph.com.apdu.meterservice.model.Meter;

/**
 *
 * @author SBAmihan
 */
@Api(tags = "Meter Entity")
@RepositoryRestResource(exported = false)
@Transactional(timeout = 120)
public interface MeterRepository extends JpaRepository<Meter, String> {

	Meter findBySerialNbr(@Param("serialNbr") String serialNbr);

	List<Meter> findByBadgeNbr(@Param("badgeNbr") String badgeNbr);

	List<Meter> findByServiceType(@Param("serviceType") String serviceType);

	List<Meter> findByServiceTypeAndMtrTypeCd(@Param("serviceType") String serviceType,
			@Param("mtrTypeCd") String mtrTypeCd);

	List<Meter> findByServiceTypeAndModelCd(@Param("serviceType") String serviceType, @Param("modelCd") String modelCd);

	List<Meter> findByServiceTypeAndMfgCd(@Param("serviceType") String serviceType, @Param("mfgCd") String mfgCd);

	List<Meter> findByServiceTypeAndSerialNbr(@Param("serviceType") String serviceType,
			@Param("serialNbr") String serialNbr);

	List<Meter> findByServiceTypeAndMtrTypeCdAndMfgCdAndModelCd(@Param("serviceType") String serviceType,
			@Param("mtrTypeCd") String mtrTypeCd, @Param("mfgCd") String mfgCd, @Param("modelCd") String modelCd);

	@Query("SELECT m FROM Meter m WHERE UPPER(m.serviceType) IN :serviceType AND UPPER(m.mtrTypeCd) IN :mtrTypeCd AND UPPER(m.mfgCd) IN :mfgCd AND UPPER(m.modelCd) IN :modelCd")
	List<Meter> findScheduledMeters(@Param("serviceType") List<String> serviceType,
			@Param("mtrTypeCd") List<String> mtrTypeCd, @Param("mfgCd") List<String> mfgCd,
			@Param("modelCd") List<String> modelCd);

}
