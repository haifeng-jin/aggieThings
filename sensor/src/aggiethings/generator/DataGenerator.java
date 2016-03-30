package aggiethings.generator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import aggiethings.sensor.Sensor;
import aggiethings.sensor.SensorConfig;
import common.PortInfo;
import common.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * It creates and controls several sensors to generate and send data using
 * socket.
 * 
 * @author Haifeng Jin
 */
public class DataGenerator {
	ArrayList<Sensor> sensor;
	int sensorNum;
	HashMap<String, SensorConfig> configHash;

	public DataGenerator() {
		sensor = new ArrayList<Sensor>();
	}

	/*
	 * protected Sensor createSensor(SensorConfig config) { return new
	 * Sensor(config, PortInfo.getAggregatorAddress()); }
	 */
	/**
	 * Start each sensor as a separate thread.
	 */
	public void start() {
		for (Sensor ins : sensor) {
			ins.start();
		}
	}

	public void stop() {
		for (Sensor ins : sensor) {
			ins.stop();
		}
	}

	public static void main(String[] args) throws IOException {
		DataGenerator dataGenerator = createDataGenerator(JsonReader.readJsonFromUrl(args[0]));
		dataGenerator.start();
		System.in.read();
		dataGenerator.stop();
	}

	static DataGenerator createDataGenerator(JSONObject json) {
		DataGenerator generator = new DataGenerator();
		generator.config(json);
		return generator;
	}

	void config(JSONObject json) {
		try {
			addSensorConfigs(json.getJSONArray("sensorType"));
			addAggregators(json.getJSONArray("aggregator"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void addSensorConfigs(JSONArray jsonConfigArray) throws JSONException {
		configHash = new HashMap<String, SensorConfig>();
		for (int i = 0; i < jsonConfigArray.length(); i++) {
			configHash.put(jsonConfigArray.getJSONObject(i).getString("name"),
					new SensorConfig(jsonConfigArray.getJSONObject(i)));
		}
	}

	private void addAggregators(JSONArray jsonAggregatorArray) throws JSONException {
		for (int i = 0; i < jsonAggregatorArray.length(); i++) {
			addAggregator(jsonAggregatorArray.getJSONObject(i));
		}

	}

	private void addAggregator(JSONObject jsonAggregatorObject) throws JSONException {
		int id = jsonAggregatorObject.getInt("id");
		String aggregatorAddress = getAggregatorAddress(id);
		JSONArray jsonSensorArray = jsonAggregatorObject.getJSONArray("sensor");

		for (int i = 0; i < jsonSensorArray.length(); i++) {
			addSensors(jsonSensorArray.getJSONObject(i), aggregatorAddress);
		}

	}

	String getAggregatorAddress(int id) {
		return PortInfo.getAggregatorAddress(id);
	}

	private void addSensors(JSONObject jsonSensorObject, String aggregatorAddress) throws JSONException {
		SensorConfig config = configHash.get(jsonSensorObject.get("type"));

		for (int k = 0; k < jsonSensorObject.getInt("quantity"); k++) {
			sensor.add(new Sensor(config, aggregatorAddress));
		}

	}
}
