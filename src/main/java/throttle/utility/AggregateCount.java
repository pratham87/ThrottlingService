package throttle.utility;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AggregateCount {
	private String type;
	private int count;
	private long time;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
