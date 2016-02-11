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
		this.data = new byte[1];
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}
	
	public DataItem(byte[] data) {
		this.data = data;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

	public DataItem(String string) {
		String[] stringArray = string.split("\\|"); 
		this.timestamp = Timestamp.valueOf(stringArray[0]);
		this.data = stringArray[1].getBytes();

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
		if (data.length != item.getData().length)
			return false;
		for (int i = 0; i < data.length; i++) {
			if (item.getData()[i] != data[i])
				return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return timestamp.toString() + "|" + (new String(data));
	}
}
