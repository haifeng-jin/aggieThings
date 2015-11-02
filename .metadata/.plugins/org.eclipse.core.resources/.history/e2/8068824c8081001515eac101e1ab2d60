import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SensorConfigTest {

	SensorConfig config;

	@Before
	public void init() {
	}

	@Test
	public void test1() {
		config = new SensorConfig(1, 2, 3);
		assertEquals(config.byteNum, 1);
		assertEquals(config.intermissionLength, 2);
		assertEquals(config.itemNum, 3);
	}

	@Test
	public void test2() {
		config = new SensorConfig(1, 2, (1<< 31));
		assertEquals(config.byteNum, 1);
		assertEquals(config.intermissionLength, 2);
		assertEquals(config.itemNum, (1<<31));
	}
}
