package throttle.service;

import throttle.cache.CollectionDataCache;
import throttle.cache.DataCacheInterface;
import throttle.cache.DataEhcache;

public class CacheFactory {
	public static DataCacheInterface getDataCacheInterface(String cacheType) {
		if (cacheType.equals("ehcache")) {
			return new DataEhcache();
		}
		return new CollectionDataCache();
	}

}
