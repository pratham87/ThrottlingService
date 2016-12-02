package throttle.cache;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class DataEhcache implements DataCacheInterface {

	CacheManager cm = CacheManager.getInstance();
	private Cache tcache = null;

	public DataEhcache() {
		tcache = cm.getCache("throttleCache");
	}

	@Override
	public int getTypeCount(String type, int duration, long currentTime) {
		if (tcache.isKeyInCache(type)) {
			Element ele = tcache.get(type);

			@SuppressWarnings("unchecked")
			List<Long> val = (List<Long>) ele.getObjectValue();

			if ((currentTime - duration) < val.get(0)) {
				return val.size();
			}
			if (val.contains(currentTime - duration)) {
				return val.size() - val.indexOf(currentTime - duration);
			}
		}
		return 0;
	}

	@Override
	public int addType(String type, long time) {
		if (type == null || time < 0)
			return -1;
		if (tcache.isKeyInCache(type)) {
			Element ele = tcache.get(type);

			if (null == ele) {
				List<Long> val = new ArrayList<Long>();
				val.add(new Long(time));
				tcache.put(new Element(type, val));

			} else {
				@SuppressWarnings("unchecked")
				List<Long> val = (List<Long>) ele.getObjectValue();
				val.add(new Long(time));
			}

		} else {
			List<Long> val = new ArrayList<Long>();
			val.add(new Long(time));
			tcache.put(new Element(type, val));
		}
		return 0;
	}

}
