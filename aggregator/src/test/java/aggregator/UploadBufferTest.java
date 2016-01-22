package aggregator;

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

}
