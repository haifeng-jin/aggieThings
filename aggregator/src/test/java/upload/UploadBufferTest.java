package upload;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import common.DataItem;
import upload.UploadBuffer;

public class UploadBufferTest {
	
	public UploadBuffer buffer;

	@Before
	public void before() {
		buffer = new UploadBuffer(100);
	}
	@Test
	public void test() {
		DataItem item = new DataItem(new byte[1]);
		buffer.add(item);
		DataItem response = buffer.take();
		assertEquals(item.getData()[0], response.getData()[0]);
	}
	@Test
	public void getTrafficTest() {
		buffer.add(new DataItem(new byte[100]));
		buffer.add(new DataItem(new byte[200]));
		buffer.add(new DataItem(new byte[300]));
		buffer.add(new DataItem(new byte[400]));
		buffer.take();
		buffer.take();
		buffer.take();
		buffer.take();
		assertEquals(1000, buffer.getTraffic());
	}
}
