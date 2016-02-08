package aggiethings.cloud;

import java.util.ArrayList;

import common.DataItem;

public class CloudServer {

	private ArrayList<DataItem> database;

	public CloudServer() {
		database = new ArrayList<DataItem>();
	}

	public synchronized void insert(DataItem item) {
		database.add(item);
	}

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

}
