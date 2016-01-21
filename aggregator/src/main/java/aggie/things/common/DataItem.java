package aggie.things.common;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class DataItem implements Serializable{

	/**
	 *  It is the object used for communication between sensors and servers.
	 */
	private static final long serialVersionUID = 1L;
	Timestamp timestamp;
	byte[] data;
	
	public DataItem(byte[] data) {
		this.data = data;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
