package throttle.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class CollectionDataCache implements DataCacheInterface {

	Map<String, List<Long>> trackMap = new HashMap<String, List<Long>>();

	@Override
	public int addType(String type, long time) {
		if (type == null || time < 0) {
			return -1;
		}
		if (trackMap.containsKey(type)) {
			List<Long> val = trackMap.get(type);
			val.add(new Long(time));
		} else {
			List<Long> val = new ArrayList<Long>();
			val.add(new Long(time));
			trackMap.put(type, val);
		}
		return 0;
	}

	// O(1)
	@Override
	public int getTypeCount(String type, int duration, long currentTime) {
		if (trackMap.containsKey(type)) {
			List<Long> val = trackMap.get(type);
			if ((currentTime - duration) < val.get(0)) {
				return val.size();
			}
			if (val.contains(currentTime - duration)) {
				return val.size() - val.indexOf(currentTime - duration);
			}
		}
		return 0;
	}

	// O(n^2) - Alternative way
	public int getTypeCountTest(String type, int duration, long currentTime) {
		int count = 0;
		if (trackMap.containsKey(type)) {
			List<Long> val = trackMap.get(type);
			ListIterator<Long> li = val.listIterator(val.size());
			while (li.hasPrevious()) {
				if ((li.previous()).longValue() < (currentTime - duration)) {
					break;
				}
				count++;
			}
		}
		return count;
	}

}
