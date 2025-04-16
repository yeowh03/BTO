package Database;

import java.util.HashMap;
import java.util.Map;

import model.Manager;

public class ManagerRepository {
	private static HashMap<String, Manager> collOfManagers = new HashMap<>(Map.ofEntries(
		Map.entry("T8765432F", new Manager("Michael", "T8765432F", 36, "Single", "password")),
		Map.entry("S5678901G", new Manager("Jessica", "T8765432F", 26, "Married", "password"))
		));
	
	public static void put(Manager manager) {
		collOfManagers.put(manager.getNric(), manager);
	}
	
	public static HashMap<String, Manager> getManagerRepository() {
		return collOfManagers;
	}
}