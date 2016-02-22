package aggiethings.tools;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeManagerTest {

	@Test
	public void testAggregatorTime() {
		int timeDiff = 30;
		TimeManager.setAggregatorDiff(timeDiff);
		assertEquals(timeDiff, TimeManager.getAggregatorDiff());
	}

	@Test
	public void testCloudTime() {
		int timeDiff = 30;
		TimeManager.setCloudDiff(timeDiff);
		assertEquals(timeDiff, TimeManager.getCloudDiff());
	}
}
