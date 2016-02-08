package common;

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
	
	public DataItem() {
		
	}
	
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
	
	@Override
	public boolean equals(Object obj) {
		DataItem item = (DataItem) obj;
		if (!timestamp.equals(item.getTimestamp()))
			return false;
		System.out.println(timestamp);
		System.out.println(item.getTimestamp());
		for (int i = 0; i < data.length; i++) {
			System.out.println(item.getData()[i]);
			System.out.println(data[i]);
			if (item.getData()[i] != data[i])
				return false;
		}
		return true;
	}
}
