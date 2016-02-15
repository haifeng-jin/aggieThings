package generator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import common.PortInfo;
import common.JsonReader;
import sensor.Sensor;
import sensor.SensorConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * It creates and controls several sensors to generate and send data using socket.
 */
public class DataGenerator {
	ArrayList<Sensor> sensor;
	int sensorNum;
	HashMap<String, SensorConfig> configHash;

	/*
	 * Creating all the sensors as required.
	 */

	public DataGenerator(String path, String address) {
		try {
			JSONObject json = JsonReader.readJsonFromFile(path);

			// Reading sensor types
			JSONArray jsonConfigArray = json.getJSONArray("sensorType");
			configHash = new HashMap<String, SensorConfig>();
			for (int i = 0; i < jsonConfigArray.length(); i++) {
				configHash.put(jsonConfigArray.getJSONObject(i).getString("name"),
						new SensorConfig(jsonConfigArray.getJSONObject(i)));
			}

			// Reading sensors
			JSONArray jsonSensorArray = json.getJSONArray("sensor");
			sensor = new ArrayList<Sensor>();
			for (int i = 0; i < jsonSensorArray.length(); i++) {
				JSONObject jsonSensorObject = jsonSensorArray.getJSONObject(i);
				SensorConfig config = configHash.get(jsonSensorObject.get("type"));
				for (int j = 0; j < jsonSensorObject.getInt("quantity"); j++) {
					sensor.add(new Sensor(config, address));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	protected Sensor createSensor(SensorConfig config) {
		return new Sensor(config, PortInfo.getAggregatorAddress());
	}

	/*
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
		DataGenerator dataGenerator = new DataGenerator(args[0], PortInfo.getAggregatorAddress());
		dataGenerator.start();
		System.in.read();
		dataGenerator.stop();
	}
}
