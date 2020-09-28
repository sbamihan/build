package ph.com.apdu.meterservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.com.apdu.meterservice.model.Meter;
import ph.com.apdu.meterservice.service.MeterService;

@RestController
@RequestMapping("/meters")
public class MeterController {

	@Autowired
	MeterService meterService;

	@GetMapping("/search/findBySerialNbr")
	public Meter findBySerialNbr(@RequestParam("serialNbr") String serialNbr) {
		return meterService.findBySerialNbr(serialNbr);
	}

	@GetMapping("/search/findByBadgeNbr")
	public List<Meter> findByBadgeNbr(@Param("badgeNbr") String badgeNbr) {
		return null;
	}

	@GetMapping("/search/findByServiceType")
	public List<Meter> findByServiceType(@RequestParam("serviceType") String serviceType) {
		return null;
	}

	@GetMapping("/search/findByServiceTypeAndMtrTypeCd")
	public List<Meter> findByServiceTypeAndMtrTypeCd(@RequestParam("serviceType") String serviceType,
			@RequestParam("mtrTypeCd") String mtrTypeCd) {
		return null;
	}

	@GetMapping("/search/findByServiceTypeAndModelCd")
	public List<Meter> findByServiceTypeAndModelCd(@RequestParam("serviceType") String serviceType,
			@RequestParam("modelCd") String modelCd) {
		return null;
	}

	@GetMapping("/search/findByServiceTypeAndMfgCd")
	public List<Meter> findByServiceTypeAndMfgCd(@RequestParam("serviceType") String serviceType,
			@RequestParam("mfgCd") String mfgCd) {
		return null;
	}

	@GetMapping("/search/findScheduledMeters")
	List<Meter> findScheduledMeters(@Param("serviceType") String serviceType, @Param("mtrTypeCd") String mtrTypeCd,
			@Param("mfgCd") String mfgCd, @Param("modelCd") String modelCd) {

		return meterService.findScheduledMeters(serviceType, mtrTypeCd, mfgCd, modelCd);
	}

}
