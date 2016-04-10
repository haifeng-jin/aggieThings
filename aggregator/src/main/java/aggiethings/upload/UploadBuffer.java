package aggiethings.upload;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Queue;

import aggiethings.common.DataItem;

public class UploadBuffer {
	Queue<DataItem> buffer;
	int maxStorage;
	int traffic;
	int storage;

	UploadBuffer() {
		maxStorage = 0;
		traffic = 0;
		storage = 0;
	}

	public UploadBuffer(int bufferSize) {
		buffer = new LinkedList<DataItem>();
	}

	public synchronized DataItem take() {
		if (buffer.size() > 0) {
			traffic += buffer.element().getData().length;
			storage -= buffer.element().getData().length;
		}
		return buffer.poll();
	}

	public synchronized void add(DataItem item) {
		item.addTimestamp(new Timestamp(System.currentTimeMillis()));
		buffer.add(item);
		storage += item.getData().length;
		if (storage > maxStorage) {
			maxStorage = storage;
		}
	}

	public synchronized int getMaxStorage() {
		return maxStorage;
	}

	public synchronized int getTraffic() {
		return traffic;
	}
}
