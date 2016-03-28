package aggiethings.sensor;

import org.json.JSONException;
import org.json.JSONObject;

public class SensorConfig {
	// Name of the type of sensor
	public String name;
	// Number of bytes in each item.
	public int byteNum;
	// The length of the intermission between items transference in
	// milliseconds.
	public int intermissionLength;

	public SensorConfig(String name, int byteNum, int intervalLength) {
		this.byteNum = byteNum;
		this.intermissionLength = intervalLength;
		this.name = name;
	}

	public SensorConfig(JSONObject json) {
		try {
			this.name = json.getString("name");
			this.byteNum = json.getInt("byteNum");
			this.intermissionLength = json.getInt("intervalLength");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
