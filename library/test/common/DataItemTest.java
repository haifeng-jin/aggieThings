package common;

import static org.junit.Assert.*;


import org.junit.Test;

public class DataItemTest {

	@Test
	public void testToString() {
		DataItem item = new DataItem(new byte[100]);
		DataItem item2 = new DataItem(item.toString());
		assertEquals(item, item2);
	}
	
	@Test
	public void testStringEqual() {
		DataItem item = new DataItem(new byte[100]);
		DataItem item2 = new DataItem(new byte[50]);
		assertNotEquals(item, item2);
	}

	@Test
	public void testStringEqual2() {
		DataItem item = new DataItem(new byte[3]);
		DataItem item2 = new DataItem(new byte[3]);
		for (int i = 0; i < 3; i++)
		{
			item.getData()[i] = (byte) (i + 97);
			item2.getData()[i] = (byte) (i + 98);
		}
		assertNotEquals(item, item2);
	}
}
