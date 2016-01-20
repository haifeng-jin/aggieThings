
import generator.DataGenerator;


import org.junit.Assert;
import org.junit.Test;

import sensor.Sensor;
import sensor.SensorConfig;
import static org.mockito.Mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class DataGeneratorTest {
	/*
	 * Test the DataGenerator. Handle each sensor using a separate thread of
	 * ClientHandler.
	 */
	@Test
	public void test() {
		SensorConfig config = new SensorConfig(10, 500, 5);
		final int sensorNum = 10;
		final Sensor[] sensorArray = new Sensor[sensorNum];
		boolean[] started = new boolean[sensorNum];

		DataGenerator generator = new DataGenerator(config, sensorNum) {
			int cnt = 0;

			@Override
			protected Sensor createSensor(SensorConfig config) {
				Assert.assertTrue(cnt < sensorNum);
				return sensorArray[cnt++];
			}
		};

		for (int i = 0; i < sensorNum; i++) {
			sensorArray[i] = mock(Sensor.class);
			doAnswer(new MyAnswer(started, i)).when(sensorArray[i]).start();
		}
		
		generator.start();
		
		for (int i = 0; i < sensorNum; i++) {
			sensorArray[i].start();
			Assert.assertEquals(true, started[i]);
		}

	}

	@SuppressWarnings("rawtypes")
	private class MyAnswer implements Answer {
		
		boolean[] started;
		int index;

		public MyAnswer(boolean[] started, int index) {
			this.started = started;
			this.index = index;
		}

		public Object answer(InvocationOnMock invocation) throws Throwable {
			started[index] = true;
			return null;
		}
		
	}
}
