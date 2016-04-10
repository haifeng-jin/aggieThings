package common;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

public class DataItemTest {

	@Test
	public void testToString() {
		DataItem item = new DataItem(new byte[100]);
		DataItem item2 = new DataItem(item.toString());
		System.out.println(item);
		System.out.println(item2);
		assertEquals(item, item2);
	}
	
	@Test
	public void testEqual() {
		DataItem item = new DataItem(new byte[100]);
		DataItem item2 = new DataItem(new byte[50]);
		assertNotEquals(item, item2);
	}

	@Test
	public void testEqual2() {
		DataItem item = new DataItem(new byte[3]);
		DataItem item2 = new DataItem(new byte[3]);
		for (int i = 0; i < 3; i++)
		{
			item.getData()[i] = (byte) (i + 97);
			item2.getData()[i] = (byte) (i + 98);
		}
		assertNotEquals(item, item2);
	}
	
	@Test
	public void testStampArray() {
		DataItem item = new DataItem(new byte[3]);
		item.addTimestamp();
		item.setTimestamp(new Timestamp(0), 0);
		item.addTimestamp(new Timestamp(1));
		item.addTimestamp(new Timestamp(2));
		assertEquals(new Timestamp(1), item.getTimestamp(1));
		assertEquals(new Timestamp(2), item.getTimestamp(2));
	}
}
