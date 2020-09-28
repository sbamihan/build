package ph.com.apdu.meterservice.service;

import java.util.Collection;

import ph.com.apdu.meterservice.model.Meter;

/**
 *
 * @author SBAmihan
 */
public interface CacheService {

	public Collection<Meter> populateCache(Collection<Meter> meters);

	public Collection<Meter> addToCache(Meter meter);

	public Collection<Meter> getFromCache();

}
