package controller;

import java.util.HashMap;
import java.util.Map;

import Database.ApplicationRepository;
import Database.RegistrationRepository;
import model.Application;
import model.Officer;
import model.Registration;

public class RegistrationController {
	private static Map<Integer, Registration> allRegistrations = RegistrationRepository.getRegistrationRepository();
	
	public static Map<Integer, Registration> OfficerGetOwnRegistration(Officer officer){
    	Map<Integer, Registration> myOwnRegistrations = new HashMap<>();
    	for (Map.Entry<Integer, Registration> entry : allRegistrations.entrySet()) {
    		if (entry.getValue().getOfficer() == officer) {
    			myOwnRegistrations.put(entry.getKey(), entry.getValue());
    		}
    	}
    	return myOwnRegistrations;
    }
	
	public static void addRegistration(Registration r) {
    	int id = RegistrationRepository.getidToAssign();
    	r.setID(id);
    	allRegistrations.put(id, r);
    }
	
}
