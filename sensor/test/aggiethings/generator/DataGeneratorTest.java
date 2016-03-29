package aggiethings.generator;

import static org.junit.Assert.*;

import org.junit.Test;

import common.JsonReader;

public class DataGeneratorTest {
	@Test
	public void testConfigFile() {
		DataGenerator generator = new DataGenerator() {
			@Override
			String getAggregatorAddress(int id) {
				return "";
			}
		};
		generator.config(JsonReader.readJsonFromFile("resources/config.json"));
		assertEquals(90, generator.sensor.size());
		assertEquals(3, generator.configHash.size());
	}
}
