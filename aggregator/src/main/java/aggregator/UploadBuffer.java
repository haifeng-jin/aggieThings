package aggregator;

import java.util.LinkedList;
import java.util.Queue;

import common.DataItem;

public class UploadBuffer {
	Queue<DataItem> buffer;

	UploadBuffer() {

	}

	public UploadBuffer(int bufferSize) {
		buffer = new LinkedList<DataItem>();
	}

	public synchronized DataItem take() {
		return buffer.poll();
	}

	public synchronized void add(DataItem item) {
		buffer.add(item);
	}
}
