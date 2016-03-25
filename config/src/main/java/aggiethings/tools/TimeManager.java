package aggiethings.tools;

public class TimeManager {
	
	private static int aggregatorDiff = 0;
	private static int cloudDiff = 0;


	public static void setAggregatorDiff(int diff) {
		aggregatorDiff = diff;
		
	}

	public static int getAggregatorDiff() {
		return aggregatorDiff;
	}

	public static void setCloudDiff(int diff) {
		cloudDiff = diff;
	}

	public static int getCloudDiff() {
		return cloudDiff;
	}

}
