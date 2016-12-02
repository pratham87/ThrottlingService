package throttle.utility;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestData {
	private String type;
	private long time;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "type: " + this.type + " time: " + this.time;
	}

}
