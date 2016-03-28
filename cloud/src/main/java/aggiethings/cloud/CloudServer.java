package aggiethings.cloud;

import java.util.ArrayList;

import common.DataItem;

public class CloudServer {

	ArrayList<DataItem> database;
	private int storage;

	public CloudServer() {
		database = new ArrayList<DataItem>();
	}

	/**
	 * Insert the item to the database.
	 * Currently use an ArrayList<DataItem> as the database.
	 * @param item The DataItem to insert.
	 */
	public synchronized void insert(DataItem item) {
		database.add(item);
		storage += item.getData().length;
	}

	/**
	 * 
	 * @return A DataItem whose data is the average of all the bytes received,
	 * whose timestamp is the last DataItem received.
	 */
	public synchronized DataItem query() {
		DataItem ret = new DataItem();
		int temp = 0;
		int cnt = 0;
		for (DataItem item : database) {
			byte[] array = item.getData();
			for (int i = 0; i < array.length; i++)
			{
				temp += array[i];
				cnt++;
			}
		}
		byte[] data = new byte[1];
		data[0] = (byte) (temp / cnt);
		ret.setData(data);
		ret.setTimestamp(database.get(database.size() - 1).getTimestamp());
		return ret;
	}

	/**
	 * 
	 * @return The storage used by the DataItems received.
	 */
	public int getStorageCost() {
		return storage;
	}

}
