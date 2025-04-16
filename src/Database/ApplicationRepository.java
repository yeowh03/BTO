package Database;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Application;

public class ApplicationRepository {
	
	private static Map<Integer, Application> collOfApplications = new HashMap<>();
	private static int idToAssign = 0;
		
	public static Map<Integer, Application> getApplicationRepository() {
		return collOfApplications;
	}
		
	public static int getidToAssign() {
		int id = idToAssign;
		idToAssign += 1;
		return id;
	}
}