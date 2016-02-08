import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sensor.SensorConfig;


public class SensorConfigTest {

	SensorConfig config;

	@Before
	public void init() {
	}

	@Test
	public void test1() {
		config = new SensorConfig("a", 1, 2);
		assertEquals(config.name, "a");
		assertEquals(config.byteNum, 1);
		assertEquals(config.intermissionLength, 2);
	}

	@Test
	public void test2() {
		config = new SensorConfig("b", 1, 2);
		assertEquals(config.name, "b");
		assertEquals(config.byteNum, 1);
		assertEquals(config.intermissionLength, 2);
	}
}
