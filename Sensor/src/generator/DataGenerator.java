package generator;

import org.json.JSONException;
import org.json.JSONObject;

import common.JsonReader;
import common.PortInfo;
import sensor.Sensor;
import sensor.SensorConfig;

import java.util.ArrayList;

/*
 * It creates and controls several sensors to generate and send data using socket.
 */
public class DataGenerator {
	ArrayList<Sensor> sensorList;
	int sensorNum;

	/*
	 * Creating all the sensors as required.
	 */
	public DataGenerator(SensorConfig config, int sensorNum) {
		sensorList = new ArrayList<Sensor>();
		for (int i = 0; i < sensorNum; i++) {
			Sensor sensor = new Sensor(config, PortInfo.getAddress());
			sensorList.add(sensor);
		}
	}

	/*
	 * Start each sensor as a separate thread.
	 */
	public void start() {
		for (Sensor sensor : sensorList) {
			sensor.start();
		}
	}

	public static void main(String[] args) {
		JSONObject json = JsonReader.readJsonFromFile(args[0]);
		SensorConfig config;
		try {
			config = new SensorConfig(json.getInt("byteNum"),
					json.getInt("intervalLength"), json.getInt("itemNum"));
			DataGenerator dataGenerator = new DataGenerator(config,
					json.getInt("sensorNum"));
			dataGenerator.start();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}