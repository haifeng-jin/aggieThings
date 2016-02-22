package aggiethings.cloud;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import common.DataItem;

public class CloudServerTest {
	private byte[][] byteArray;
	private byte[] average;
	private CloudServer cloud;
	
	@Before
	public void setUp() {
		cloud = new CloudServer();
	}

	@Test
	public void testQuery() {
		int size = 5;
		initialize(size);

		for (int i = 0; i < size; i++) {
			DataItem item = new DataItem(byteArray[i]);
			item.setTimestamp(new Timestamp(i), 0);
			cloud.insert(item);
		}
		DataItem result = cloud.query();
		DataItem expected = new DataItem(average);
		expected.setTimestamp(cloud.database.get(cloud.database.size() - 1).getTimestamp());
		assertEquals(expected, result);
	}

	private void initialize(int size) {
		byteArray = new byte[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				byteArray[i][j] = (byte) (i * size + j);
		}
		int temp = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				temp += byteArray[j][i];
		}
		average = new byte[1];
		average[0] = (byte) (temp / (size * size));
	}

}
