package controller;

import Database.ApplicationRepository;
import model.Project;
import model.User;
import model.Applicant;
import model.Application;
import model.ApplicationStatus;
import model.Enquiry;
import model.Officer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ApplicationController {
    private static Map<Integer, Application> allApplications = ApplicationRepository.getApplicationRepository();
    
    public static void addApplication(Application app) {
    	int id = ApplicationRepository.getidToAssign();
    	app.setID(id);
    	allApplications.put(id, app);
    }
    
    public static void removeApplication(int id) {
    	allApplications.remove(id);
    }
    
    public static Map<Integer, Application> officerGetSuccessfulApplication(Officer officer){
    	Map<Integer, Application> myProjApplications = new HashMap<>();
    	for (Map.Entry<Integer, Application> entry : allApplications.entrySet()) {
    		if (entry.getValue().getProject().getAssignedOfficers().containsKey(officer.getNric()) && entry.getValue().getStatus() == ApplicationStatus.SUCCESSFUL) {
    			myProjApplications.put(entry.getKey(), entry.getValue());
    		}
    	}
    	return myProjApplications;
    }
    
    public static Map<Integer, Application> ApplicantGetOwnApplication(Applicant applicant){
    	Map<Integer, Application> myOwnApplications = new HashMap<>();
    	for (Map.Entry<Integer, Application> entry : allApplications.entrySet()) {
    		if (entry.getValue().getApplicant() == applicant) {
    			myOwnApplications.put(entry.getKey(), entry.getValue());
    		}
    	}
    	return myOwnApplications;
    }
    
    public static Map<Integer, Application> getProjectsBookedApplications(Map<String, Project> projects){
    	Map<Integer, Application> applications = new HashMap<Integer, Application>();
    	for (Map.Entry<String, Project> entry : projects.entrySet()) {
    		for (Map.Entry<Integer, Application> entry2: allApplications.entrySet()) { // look through all applications to find matching project
    			if (entry2.getValue().getProject() == entry.getValue() && entry2.getValue().getStatus() == ApplicationStatus.BOOKED) {
    				applications.put(entry2.getKey(), entry2.getValue());
    			}
    		}
    	}
    	return applications;
    }
    
}