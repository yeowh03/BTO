package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

import Interface.IOfficerServices;
import model.Applicant;
import model.Application;
import model.ApplicationStatus;
import model.Enquiry;
import model.Officer;
import model.Project;
import model.Registration;

public class OfficerController extends ApplicantController implements IOfficerServices{
	
	public OfficerController() {
		
	}
	
	public void submitApplication(Officer officer) {
		
		// check if have pending application
		Map<Integer, Application> myApplications = ApplicationController.ApplicantGetOwnApplication(officer);
		for (Map.Entry<Integer, Application> entry : myApplications.entrySet()) {
			if (entry.getValue().getStatus() == ApplicationStatus.PENDING) {
				System.out.println("Cannot apply multiple projects!");
				return;
			};
		}
		
		// get projects for my target group
		Map<String, Project> eligibleProjects = ProjectController.getEligibleProjects(officer);
		
		// filter out those without visibility
		for (Map.Entry<String, Project> entry : eligibleProjects.entrySet()) {
			if (!entry.getValue().isVisible()) {
				eligibleProjects.remove(entry.getKey());
			}
		}
		
		// filter out those not within application period
		LocalDate today = LocalDate.now();
		for (Map.Entry<String, Project> entry : eligibleProjects.entrySet()) {
			if (today.isBefore(entry.getValue().getOpeningDate()) || today.isAfter(entry.getValue().getClosingDate())) {
				eligibleProjects.remove(entry.getKey());
			}
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the project name you want to apply for: ");
		String pName = sc.nextLine().trim();
		if (eligibleProjects.containsKey(pName)) {
			// officer checks if he already registered for this project before
			Map<Integer, Registration> registrations = RegistrationController.OfficerGetOwnRegistration(officer);
			for (Map.Entry<Integer, Registration> entry : registrations.entrySet()) {
				if (entry.getValue().getProject().getProjectName().equals(pName)) {
					System.out.println("ALREADY REGISTERED FOR THIS PROJECT BEFORE");
					return;
				}
			}	
			
			System.out.println("Enter '2' for 2-Room, '3' for 3-Room.");
			try {
				int flatType = sc.nextInt();
				sc.nextLine();
				if (flatType == 2) {
					Application app = new Application(officer, eligibleProjects.get(pName), "2-Room");
					ApplicationController.addApplication(app);
				} else if (flatType == 3) {
					Application app = new Application(officer, eligibleProjects.get(pName), "3-Room");
					ApplicationController.addApplication(app);
				} else {
					System.out.println("Invalid room type.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		} else {
			System.out.println("Invalid Project Name.");
		}
	}
	
	public void processFlatSelection(Officer officer) {
		// retrieve application with nric
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter nric: ");
		String nric = sc.nextLine().trim();
		
		// find application
		Map<Integer, Application> applications = ApplicationController.officerGetSuccessfulApplication(officer);
		for(Map.Entry<Integer, Application> entry : applications.entrySet()) {
			if (entry.getValue().getApplicant().getNric().equalsIgnoreCase(nric)) {
				// update remaining flatType number 
				entry.getValue().getProject().getUnitTypes().get(entry.getValue().getUnitType()).assignUnit();
				// change status to successful
				entry.getValue().setStatus(ApplicationStatus.BOOKED);
				// update applicant's profile
				entry.getValue().getApplicant().setflatTypeBooked(entry.getValue().getUnitType());
				System.out.println("Booked successfully.");
				return;
			}
		}	
		System.out.println("NRIC not found");
    }

    public void registerForProject(Officer officer) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the project name to register: ");
        String projectName = sc.nextLine().trim();
        
        // retrieve the project
        Project targetProject = ProjectController.getProject(projectName);
        if (targetProject == null) {
        	System.out.println("Project not found.");
        	return;
        }
        
        // check for overlapping dates
        Map<String, Project> assignedProjects = ProjectController.getAssignedProjects(officer);
        for(Map.Entry<String, Project> entry : assignedProjects.entrySet()) {
        	if (!entry.getValue().getOpeningDate().isAfter(targetProject.getClosingDate()) && !targetProject.getOpeningDate().isAfter(entry.getValue().getClosingDate())) { // overlap
        		System.out.println("Cannot handle multiple projects.");
        		return;
        	}
        }
        
        // now check if he is an applicant
        Map<Integer, Application> myApplications = ApplicationController.ApplicantGetOwnApplication(officer);
        for(Map.Entry<Integer, Application> entry : myApplications.entrySet()) {
        	if (entry.getValue().getApplicant() == officer) {
        		System.out.println("Cannot register for applied projects!");
        		return;
        	}
        }
        
        // check if registered before
        Map<Integer, Registration> myRegistrations = RegistrationController.OfficerGetOwnRegistration(officer);
        for(Map.Entry<Integer, Registration> entry : myRegistrations.entrySet()) {
        	if (entry.getValue().getProject() == targetProject) {
        		System.out.println("Registered before.");
        		return;
        	}
        }
        
        // register
        Registration newRegistration = new Registration(targetProject, officer);
        RegistrationController.addRegistration(newRegistration);
    }

    public void viewRegistrationStatus(Officer officer) {
    	Map<Integer, Registration> myRegistrations = RegistrationController.OfficerGetOwnRegistration(officer);
    	for(Map.Entry<Integer, Registration> entry : myRegistrations.entrySet()) {
    		System.out.printf("Registration ID: %d\n", entry.getKey());
    		System.out.printf("Project Name: %s\n", entry.getValue().getProject().getProjectName());
    		System.out.printf("Status: %s\n", entry.getValue().getStatus());
    		System.out.println("------");
    	}
    }

    public void viewProjectDetails(Officer officer) {
    	Map<String, Project> assignedProjects = ProjectController.getAssignedProjects(officer);
    	if (officer.getWantSort() == 1) {
			assignedProjects = new TreeMap<>(assignedProjects);
    	}
			
		for(Map.Entry<String, Project> entry : assignedProjects.entrySet()) {
			if (!officer.getNeighbourhoodFilter().isEmpty() && !entry.getValue().getNeighborhood().equalsIgnoreCase(officer.getNeighbourhoodFilter())) {
				continue;
			}
			System.out.printf("Project Name: %s\n", entry.getValue().getProjectName());
            System.out.printf("Neighbourhood: %s\n", entry.getValue().getNeighborhood());
            if (officer.getTypeFilter().isEmpty()) {
            	System.out.printf("Total Number of 2-Room Flats: %d\n", entry.getValue().getUnitTypes().get("2-Room").getTotalUnits());
	            System.out.printf("Available Number of 2-Room Flats: %d\n", entry.getValue().getUnitTypes().get("2-Room").getAvailableUnits());
	            System.out.printf("Total Number of 3-Room Flats: %d\n", entry.getValue().getUnitTypes().get("3-Room").getTotalUnits());
	            System.out.printf("Available Number of 3-Room Flats: %d\n", entry.getValue().getUnitTypes().get("3-Room").getAvailableUnits());
            } else if (officer.getTypeFilter().equalsIgnoreCase("2-Room")) {
            	System.out.printf("Total Number of 2-Room Flats: %d\n", entry.getValue().getUnitTypes().get("2-Room").getTotalUnits());
	            System.out.printf("Available Number of 2-Room Flats: %d\n", entry.getValue().getUnitTypes().get("2-Room").getAvailableUnits());
            } else if (officer.getTypeFilter().equalsIgnoreCase("3-Room")) {
            	System.out.printf("Total Number of 3-Room Flats: %d\n", entry.getValue().getUnitTypes().get("3-Room").getTotalUnits());
	            System.out.printf("Available Number of 3-Room Flats: %d\n", entry.getValue().getUnitTypes().get("3-Room").getAvailableUnits());
            }        
            System.out.printf("Opening Date: %s\n", entry.getValue().getOpeningDate());
            System.out.printf("Closing Date: %s\n", entry.getValue().getClosingDate());
            System.out.printf("HDB Manager: %s\n", entry.getValue().getAssignedManager().getName());
            
            System.out.println("\nOfficer Information:");
            System.out.println("Total Officer Slots: " + entry.getValue().getOfficerSlots());
            System.out.println("Current Officers: " + entry.getValue().getAssignedOfficers().size());
		}
    }

    public void viewProjectEnquiries(Officer officer) {
    	Map<String, Project> assignedProjects = ProjectController.getAssignedProjects(officer);
    	
    	Map<Integer, Enquiry> enquiries = EnquiryController.getProjectsEnquiries(assignedProjects);
    	
    	for(Map.Entry<Integer, Enquiry> entry : enquiries.entrySet()) {
    		System.out.printf("Enquiry ID: %d\n", entry.getKey());
    		System.out.printf("Project Name: %s\n", entry.getValue().getProject().getProjectName());
    		System.out.printf("Question: %s\n", entry.getValue().getQuestion());
    		System.out.printf("Reply: %s\n", entry.getValue().getReply());
    		System.out.println("------");
    	}
    }

    public void replyToEnquiries(Officer officer) {
    	Map<String, Project> assignedProjects = ProjectController.getAssignedProjects(officer);
    	
    	Map<Integer, Enquiry> enquiries = EnquiryController.getProjectsEnquiries(assignedProjects);
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter the enquiry ID you wish to reply: ");
    	try {
    		int id = sc.nextInt();
    		sc.nextLine();
    		if (enquiries.containsKey(id)) {
    			System.out.println("Enter response: ");
    			String reply = sc.nextLine().trim();
    			enquiries.get(id).setReply(reply);
    			System.out.println("Enquiry replied.");
    		} else {
    			System.out.println("Enquiry ID not found.");
    		}
    	}catch (InputMismatchException e) {
    		System.out.println("Invalid input!");
    	}
    }
    
    public void generateReceipt(Officer officer) {
    	Map<String, Project> assignedProjects = ProjectController.getAssignedProjects(officer);
    	
    	Map<Integer, Application> applications = ApplicationController.getProjectsBookedApplications(assignedProjects);
    	
    	for(Map.Entry<Integer, Application> entry : applications.entrySet()) {
    		System.out.printf("Applicant Name: %s\n", entry.getValue().getApplicant().getName());
    		System.out.printf("Applicant NRIC: %s\n", entry.getValue().getApplicant().getNric());
    		System.out.printf("Applicant age: %d\n", entry.getValue().getApplicant().getAge());
    		System.out.printf("Applicant Marital Status: %s\n", entry.getValue().getApplicant().getMaritalStatus());
    		System.out.printf("Flat Type Booked: %s\n", entry.getValue().getUnitType());
    		
    		System.out.println("Project Details");
    		System.out.printf("Project Name: %s\n", entry.getValue().getProject().getProjectName());
            System.out.printf("Neighbourhood: %s\n", entry.getValue().getProject().getNeighborhood());
     
            System.out.printf("Total Number of 2-Room Flats: %d\n", entry.getValue().getProject().getUnitTypes().get("2-Room").getTotalUnits());
	        System.out.printf("Available Number of 2-Room Flats: %d\n", entry.getValue().getProject().getUnitTypes().get("2-Room").getAvailableUnits());
	        System.out.printf("Total Number of 3-Room Flats: %d\n", entry.getValue().getProject().getUnitTypes().get("3-Room").getTotalUnits());
	        System.out.printf("Available Number of 3-Room Flats: %d\n", entry.getValue().getProject().getUnitTypes().get("3-Room").getAvailableUnits());
      
            System.out.printf("Opening Date: %s\n", entry.getValue().getProject().getOpeningDate());
            System.out.printf("Closing Date: %s\n", entry.getValue().getProject().getClosingDate());
            System.out.printf("HDB Manager: %s\n", entry.getValue().getProject().getAssignedManager().getName());
            System.out.println("------");
    	}
    }
}












