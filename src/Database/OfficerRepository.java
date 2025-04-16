package Database;

import java.util.HashMap;
import java.util.Map;

import model.Officer;

public class OfficerRepository {
private static HashMap<String, Officer> collOfOfficers = new HashMap<>(Map.ofEntries(
		Map.entry("T2109876H", new Officer("Daniel", "T2109876H", 36, "Single", "password")),
		Map.entry("S6543210I", new Officer("Emily", "S6543210I", 28, "Single", "password")),
		Map.entry("T1234567J", new Officer("David", "T1234567J", 29, "Married", "password"))
		));
	
	public static void put(Officer officer) {
		collOfOfficers.put(officer.getNric(), officer);
	}
	
	public static HashMap<String, Officer> getOfficerRepository() {
		return collOfOfficers;
	}
}
