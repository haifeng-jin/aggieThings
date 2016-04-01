package aggiethings.tools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TimeManagerTest {
	
	TimeManager manager;
	
	@Before
	public void setUp() {
		manager = new TimeManager();
	}

	@Test
	public void testAggregatorTime() {
		for (int i = 0; i < 10; i++) {
			manager.setAggregatorDiff(i, i * 10);
			assertEquals(i * 10, manager.getAggregatorDiff(i));
		}
	}

	@Test
	public void testCloudTime() {
		int timeDiff = 30;
		manager.setCloudDiff(timeDiff);
		assertEquals(timeDiff, manager.getCloudDiff());
	}
}
