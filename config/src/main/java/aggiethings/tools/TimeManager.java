package aggiethings.tools;

import java.util.HashMap;

/**
 * 
 * @author Haifeng Jin
 *
 */
public class TimeManager {
	
	private HashMap<Integer, Integer> aggregatorDiff;
	private int cloudDiff;

	TimeManager() {
		aggregatorDiff = new HashMap<Integer, Integer>();
	}

	public void setAggregatorDiff(int id, int diff) {
		aggregatorDiff.put(id, diff);
		
	}

	public int getAggregatorDiff(int id) {
		return aggregatorDiff.get(id);
	}

	public void setCloudDiff(int diff) {
		cloudDiff = diff;
	}

	public int getCloudDiff() {
		return cloudDiff;
	}

}
