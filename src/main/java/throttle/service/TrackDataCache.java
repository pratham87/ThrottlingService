package throttle.service;

import java.util.Properties;

import throttle.cache.DataCacheInterface;

public class TrackDataCache {

	private static TrackDataCache instance = null;
	private static DataCacheInterface cacheImpl = null;
	private static Properties prop;

	private TrackDataCache() {
	}

	public static TrackDataCache getInstance() {
		if (instance == null) {
			instance = new TrackDataCache();
			loadDataCache();
		}
		return instance;
	}

	private static void loadDataCache() {
		try {
			prop = new Properties();
			prop.load(TrackDataCache.class.getResourceAsStream("/config.properties"));
			cacheImpl = CacheFactory.getDataCacheInterface(prop.getProperty("cacheType"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int addTrackData(String type, long time) {
		return cacheImpl.addType(type, time);
	}

	public int calculateCount(String type, int duration, long time) {
		return cacheImpl.getTypeCount(type, duration, time);
	}

	public static void main(String s[]) {
		TrackDataCache track = TrackDataCache.getInstance();
		System.out.println(track.addTrackData("a", System.currentTimeMillis()));
		System.out.println(track.addTrackData("a", System.currentTimeMillis()));
		System.out.println(track.calculateCount("a", 5, System.currentTimeMillis()));

	}

}
