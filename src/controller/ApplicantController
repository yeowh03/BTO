package controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import Interface.IApplicantServices;
import model.Applicant;
import model.Application;
import model.ApplicationStatus;
import model.Enquiry;
import model.Project;

public class ApplicantController implements IApplicantServices{
	public ApplicantController() {
		
	}
	
	public Map<String, Project> browseProjects(Applicant applicant) {
		Map<String, Project> eligibleProjects = ProjectController.getEligibleProjects(applicant);
		
		// filter out those without visibility
		Map<String, Project> visible = new HashMap<>();
		for (Map.Entry<String, Project> entry : eligibleProjects.entrySet()) {
			if (entry.getValue().isVisible()) {
				visible.put(entry.getKey(), entry.getValue());
			}
		}
		
		// adding those that have been applied but no longer with visibility
		Map<Integer, Application> applied = ApplicationController.ApplicantGetOwnApplication(applicant);
		for (Map.Entry<Integer, Application> entry : applied.entrySet()) {
			if (!visible.containsKey(entry.getValue().getProject().getProjectName())) {
				visible.put(entry.getValue().getProject().getProjectName(), entry.getValue().getProject());
			}
		}
		
		if (applicant.getWantSort() == 1) {
			visible = new TreeMap<>(visible);
		}
		
		for (Map.Entry<String, Project> entry : visible.entrySet()) {
			if (applicant.getNeighbourhoodFilter().isEmpty() || entry.getValue().getNeighborhood().equalsIgnoreCase(applicant.getNeighbourhoodFilter())) {
		        System.out.printf("Project Name: %s\n", entry.getValue().getProjectName());
		        System.out.printf("Neighbourhood: %s\n", entry.getValue().getNeighborhood());
		            
		        if (applicant.getTypeFilter().isEmpty()) {
		        	System.out.printf("Total Number of 2-Room Flats: %d\n", entry.getValue().getUnitTypes().get("2-Room").getTotalUnits());
			        System.out.printf("Available Number of 2-Room Flats: %d\n", entry.getValue().getUnitTypes().get("2-Room").getAvailableUnits());
			        System.out.printf("Total Number of 3-Room Flats: %d\n", entry.getValue().getUnitTypes().get("3-Room").getTotalUnits());
			        System.out.printf("Available Number of 3-Room Flats: %d\n", entry.getValue().getUnitTypes().get("3-Room").getAvailableUnits());
		         } else if (applicant.getTypeFilter().equalsIgnoreCase("2-Room")) {
		        	 System.out.printf("Total Number of 2-Room Flats: %d\n", entry.getValue().getUnitTypes().get("2-Room").getTotalUnits());
			         System.out.printf("Available Number of 2-Room Flats: %d\n", entry.getValue().getUnitTypes().get("2-Room").getAvailableUnits());
		         } else if (applicant.getTypeFilter().equalsIgnoreCase("3-Room")) {
		            System.out.printf("Total Number of 3-Room Flats: %d\n", entry.getValue().getUnitTypes().get("3-Room").getTotalUnits());
			        System.out.printf("Available Number of 3-Room Flats: %d\n", entry.getValue().getUnitTypes().get("3-Room").getAvailableUnits());
		         }
		            
		        System.out.printf("Opening Date: %s\n", entry.getValue().getOpeningDate());
		        System.out.printf("Closing Date: %s\n", entry.getValue().getClosingDate());
		        System.out.printf("HDB Manager: %s\n", entry.getValue().getAssignedManager().getName());
			}
	    }
		return visible;
	}

	public void submitApplication(Applicant applicant) {
		// check if have pending application
		Map<Integer, Application> myApplications = ApplicationController.ApplicantGetOwnApplication(applicant);
		for (Map.Entry<Integer, Application> entry : myApplications.entrySet()) {
			if (entry.getValue().getStatus() == ApplicationStatus.PENDING) {
				System.out.println("Cannot apply multiple projects!");
				return;
			};
		}
		
		// get projects for my target group
		Map<String, Project> eligibleProjects = ProjectController.getEligibleProjects(applicant);
		
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
			System.out.println("Enter '2' for 2-Room, '3' for 3-Room.");
			try {
				int flatType = sc.nextInt();
				sc.nextLine();
				if (flatType == 2) {
					Application app = new Application(applicant, eligibleProjects.get(pName), "2-Room");
					ApplicationController.addApplication(app);
				} else if (flatType == 3) {
					Application app = new Application(applicant, eligibleProjects.get(pName), "3-Room");
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

	public void viewApplicationStatus(Applicant applicant) {
		Map<Integer, Application> myApplications = ApplicationController.ApplicantGetOwnApplication(applicant);
		for (Map.Entry<Integer, Application> entry : myApplications.entrySet()) {
			System.out.printf("Application ID: %d\n", entry.getValue().getApplicationId());
			System.out.printf("Project Name: %d\n", entry.getValue().getProject().getProjectName());
			System.out.printf("Flat Type: %s\n", entry.getValue().getUnitType());
			System.out.printf("Status: %s", entry.getValue().getStatus());
		}
	}
 
	public void submitEnquiry(Applicant applicant) {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter question: ");
        String question = scanner.nextLine().trim();
        
        // only can enquire about projects applicant can see
        System.out.print("Enter Project Name: ");
        String projName = scanner.nextLine().trim();
        
        Map<String, Project> visibleProj = this.browseProjects(applicant);
        for (Map.Entry<String, Project> entry : visibleProj.entrySet()) {
        	if (entry.getKey().equalsIgnoreCase(projName)) {
        		Enquiry enquiry = new Enquiry(question, entry.getValue(), applicant);
        		EnquiryController.submitEnquiry(enquiry);
        		return;
        	}
        }
        
        System.out.println("Invalid Projct Name.");
	}
	
	public void viewEnquiries(Applicant applicant) {
		Map<Integer, Enquiry> myEnquiries = EnquiryController.applicantGetEnquiries(applicant);
		for (Map.Entry<Integer, Enquiry> entry : myEnquiries.entrySet()) {
			System.out.printf("Enquiry ID: %d\n", entry.getValue().getID());
			System.out.printf("Project Name: %d\n", entry.getValue().getProject().getProjectName());
			System.out.printf("Question: %s\n", entry.getValue().getQuestion());
			System.out.printf("Reply: %s", entry.getValue().getReply());
		}
	}
	
	public void editEnquiry(Applicant applicant) {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the enquiry to edit: ");
        
        try {
            int idToEdit = Integer.parseInt(scanner.nextLine().trim());
            Map<Integer, Enquiry> myEnquiries = EnquiryController.applicantGetEnquiries(applicant);
            for (Map.Entry<Integer, Enquiry> entry : myEnquiries.entrySet()) {
            	if (entry.getValue().getApplicant() == applicant && entry.getValue().getID() == idToEdit) {
            		System.out.print("Enter new detail: ");
                    String newQuestion = scanner.nextLine().trim();
                    entry.getValue().setQuestion(newQuestion);
                    System.out.println("Edit successfully!");
                    return;
            	}
            }
            System.out.println("Enquiry ID not found.");
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
	}
	
	public void deleteEnquiry(Applicant applicant) {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the enquiry to delete: ");
        
        try {
            int idToDelete = Integer.parseInt(scanner.nextLine().trim());
            Map<Integer, Enquiry> myEnquiries = EnquiryController.applicantGetEnquiries(applicant);
            for (Map.Entry<Integer, Enquiry> entry : myEnquiries.entrySet()) {
            	if (entry.getValue().getApplicant() == applicant && entry.getValue().getID() == idToDelete) {
            		EnquiryController.deleteEnquiry(entry.getValue());
                    System.out.println("Deleted successfully!");
                    return;
            	}
            }
            System.out.println("Enquiry ID not found.");
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
	}

	public void requestWithdrawal(Applicant applicant) {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the enquiry to delete: ");
        
        try {
        	int idToWithdraw = Integer.parseInt(scanner.nextLine().trim());
        	Map<Integer, Application> myApplications = ApplicationController.ApplicantGetOwnApplication(applicant);
    		for (Map.Entry<Integer, Application> entry : myApplications.entrySet()) {
    			if (entry.getValue().getApplicationId() == idToWithdraw) {
    				entry.getValue().setStatus(ApplicationStatus.PENDING_CANCEL_BOOKING);
    				System.out.println("Request sent!");
    				return;
    			}
    		}
    		
    		System.out.println("Application ID not found.");
        }
        
        catch (NumberFormatException e) {
        	System.out.println("Invalid input. Please enter a valid number.");
        }
	}

	public void changePassword(Applicant applicant) {
		System.out.println("Enter new password: ");
		Scanner scanner = new Scanner(System.in);
		String newPassword = scanner.nextLine().trim();
		applicant.setPassword(newPassword);
	}
	
}
