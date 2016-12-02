package throttle.cache;

public interface DataCacheInterface {

	public int addType(String type, long time);

	public int getTypeCount(String type, int duration, long time);

}
