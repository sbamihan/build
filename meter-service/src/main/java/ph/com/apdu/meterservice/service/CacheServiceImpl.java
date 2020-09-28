package ph.com.apdu.meterservice.service;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import ph.com.apdu.meterservice.model.Meter;

/**
 *
 * @author SBAmihan
 */
@Service
public class CacheServiceImpl implements CacheService {

	Collection<Meter> cachedMeters = new ArrayList<>();

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	@CachePut(cacheNames = "cachedMeters")
	public Collection<Meter> populateCache(Collection<Meter> meters) {
		log.info("Populate meters to cache");
		cachedMeters.clear();
		cachedMeters.addAll(meters);
		return cachedMeters;
	}

	@Override
	@CachePut(cacheNames = "cachedMeters")
	public Collection<Meter> addToCache(Meter meter) {
		log.info("Adding meter to cache");
		cachedMeters.add(meter);
		return cachedMeters;
	}

	@Override
	@Cacheable(cacheNames = "cachedMeters")
	public Collection<Meter> getFromCache() {
		log.info("Getting meters from cache");
		return cachedMeters;
	}

}
