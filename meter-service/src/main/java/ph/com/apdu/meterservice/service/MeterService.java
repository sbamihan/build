package ph.com.apdu.meterservice.service;

import java.util.List;

import ph.com.apdu.meterservice.model.Meter;

public interface MeterService {

	Meter findBySerialNbr(String serialNbr);

	List<Meter> findByBadgeNbr(String badgeNbr);

	List<Meter> findByServiceType(String serviceType);

	List<Meter> findByServiceTypeAndMtrTypeCd(String serviceType, String mtrTypeCd);

	List<Meter> findByServiceTypeAndModelCd(String serviceType, String modelCd);

	List<Meter> findByServiceTypeAndMfgCd(String serviceType, String mfgCd);

	List<Meter> findScheduledMeters(String serviceType, String mtrTypeCd, String mfgCd, String modelCd);
}
