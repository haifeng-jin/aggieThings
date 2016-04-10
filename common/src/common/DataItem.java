package common;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataItem implements Serializable {

	/**
	 * It is the object used for communication between sensors and servers.
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Timestamp> timestamp;
	byte[] data;

	public DataItem() {
		this.data = new byte[1];
		this.timestamp = new ArrayList<Timestamp>();
	}

	public DataItem(byte[] data) {
		this();
		this.data = data;
	}

	public DataItem(String string) {
		this();
		String[] stringArray = string.split("\\|");
		for (int i = 0; i < stringArray.length - 1; i++) {
			this.timestamp.add(Timestamp.valueOf(stringArray[i]));
		}
		this.data = stringArray[stringArray.length - 1].getBytes();
	}

	public void addTimestamp() {
		this.timestamp.add(new Timestamp(System.currentTimeMillis()));
	}

	public void addTimestamp(Timestamp timestamp) {
		this.timestamp.add(timestamp);
	}

	public ArrayList<Timestamp> getTimestamp() {
		return timestamp;
	}

	public Timestamp getTimestamp(int a) {
		return timestamp.get(a);
	}

	public void setTimestamp(ArrayList<Timestamp> timestamp) {
		this.timestamp = timestamp;
	}

	public void setTimestamp(Timestamp timestamp, int a) {
		try {
			this.timestamp.set(a, timestamp);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("TimeStamp ArrayList out of bounds.");
			e.printStackTrace();
		}
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

		if (!timestamp.equals(item.getTimestamp())) {
			return false;
		}

		if (!Arrays.equals(data, item.getData())) {
			System.out.println("**");
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		String ret = new String("");
		for (int i = 0; i < timestamp.size(); i++) {
			ret += timestamp.get(i) + "|";
		}
		return ret + (new String(data));
	}
}
