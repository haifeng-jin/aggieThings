package aggiethings.tools;

import static org.junit.Assert.*;

import org.junit.Test;

public class AggregatorIdManagerTest {

	@Test
	public void test() {
		AggregatorIdManager manager = new AggregatorIdManager();
		for (int i = 0; i < 10; i++) {
			assertEquals(i, manager.getNewId());
		}
	}

}
