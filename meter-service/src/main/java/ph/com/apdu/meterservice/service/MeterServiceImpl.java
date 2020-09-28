package ph.com.apdu.meterservice.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ph.com.apdu.meterservice.model.Meter;
import ph.com.apdu.meterservice.repository.MeterRepository;

@Service("meterService")
@Transactional
public class MeterServiceImpl implements MeterService {

	@Autowired
	MeterRepository meterRepository;

	@Autowired
	CacheService cacheService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public Meter findBySerialNbr(String serialNbr) {
		return meterRepository.findBySerialNbr(serialNbr);
	}

	public List<Meter> findByBadgeNbr(String badgeNbr) {
		return meterRepository.findByBadgeNbr(badgeNbr);
	}

	public List<Meter> findByServiceType(String serviceType) {
		return meterRepository.findByServiceType(serviceType);
	}

	public List<Meter> findByServiceTypeAndMtrTypeCd(String serviceType, String mtrTypeCd) {
		return meterRepository.findByServiceTypeAndMtrTypeCd(serviceType, mtrTypeCd);
	}

	public List<Meter> findByServiceTypeAndModelCd(String serviceType, String modelCd) {
		return meterRepository.findByServiceTypeAndModelCd(serviceType, modelCd);
	}

	public List<Meter> findByServiceTypeAndMfgCd(String serviceType, String mfgCd) {
		return meterRepository.findByServiceTypeAndMfgCd(serviceType, mfgCd);
	}

	@Override
	@HystrixCommand(fallbackMethod = "findScheduledMetersFallback")
	public List<Meter> findScheduledMeters(String serviceType, String mtrTypeCd, String mfgCd, String modelCd) {
		List<Meter> meters = meterRepository.findScheduledMeters(Arrays.asList(serviceType.split("\\,|\\s+")),
				Arrays.asList(mtrTypeCd.split("\\,|\\s+")), Arrays.asList(mfgCd.split("\\,|\\s+")),
				Arrays.asList(modelCd.split("\\,|\\s+")));

		cacheService.populateCache(meters);

		return meters;
	}

	public List<Meter> findScheduledMetersFallback(String serviceType, String mtrTypeCd, String mfgCd, String modelCd) {
		log.info("findScheduledMetersFallback(" + serviceType + "," + mtrTypeCd + "," + mfgCd + "," + modelCd + "')");

		return (List<Meter>) cacheService.getFromCache();
	}

}
