package aggiethings.tools;

public class AggregatorIdManager {
	
	int idCount;
	
	public AggregatorIdManager() {
		idCount = 0;
	}

	public int getNewId() {
		return idCount++;
	}

}
