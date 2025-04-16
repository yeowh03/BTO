package Database;

import java.util.HashMap;
import java.util.Map;

import model.Registration;

public class RegistrationRepository {
	private static Map<Integer, Registration> collOfRegistrations = new HashMap<>();
	private static int idToAssign = 0;
		
	public static Map<Integer, Registration> getRegistrationRepository() {
		return collOfRegistrations;
	}
		
	public static int getidToAssign() {
		int id = idToAssign;
		idToAssign += 1;
		return id;
	}
}
