package menu;

import auth.AuthenticationSystem;

import controller.ProjectController;
import controller.OfficerController;
import java.time.LocalDateTime;
import java.util.*;

import Interface.IOfficerServices;
import model.Project;
import model.Enquiry;
import model.Officer;

public class OfficerMenu {
    private Scanner scanner;
    private Officer officer;
    private IOfficerServices controller;

    public OfficerMenu(Officer officer, IOfficerServices controller) {
        this.scanner = new Scanner(System.in);
        this.officer = officer;
        this.controller = controller;
    }

    public void display() {
        while (true) {
            System.out.println("\n=== HDB Officer Menu ===");
            System.out.println("Welcome, " + officer.getName());
            System.out.println("=== Project Management ===");
            System.out.println("1. Register for Project");
            System.out.println("2. View Registration Status");
            System.out.println("3. View Project Details");
            System.out.println("4. Process Flat Selection");
            System.out.println("5. Generate Booking Receipt");
            System.out.println("6. View ");
            
            System.out.println("\n=== Enquiries Management ===");
            System.out.println("6. View Project Enquiries");
            System.out.println("7. Reply to Enquiries");
            
            System.out.println("\n=== Applicant Features ===");
            System.out.println("8. Browse Projects");
            System.out.println("9. Submit Application");
            System.out.println("10. View Application Status");
            System.out.println("11. Submit New Enquiry");
            System.out.println("12. View My Enquiries");
            System.out.println("13. Edit Enquiry");
            System.out.println("14. Delete Enquiry");
            System.out.println("15. Request Withdrawal");
            
            System.out.println("\n=== System ===");
            System.out.println("16. Change Password");
            System.out.println("17. Logout");
            
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:        
                    	controller.registerForProject(officer);
                        break;
                    case 2:
                    	controller.viewRegistrationStatus(officer);
                        break;
                    case 3:
                    	controller.viewProjectDetails(officer);
                        break;
                    case 4:
                    	controller.processFlatSelection(officer);
                        break;
                    case 5:
                        controller.generateReceipt(officer);
                        break;
                    case 6:
                    	controller.viewProjectEnquiries(officer);
                        break;
                    case 7:
                    	controller.replyToEnquiries(officer);
                        break;
                    case 8:
                        controller.browseProjects(officer);
                        break;
                    case 9:
                        controller.submitApplication(officer);
                        break;
                    case 10:
                        controller.viewApplicationStatus(officer);
                        break;
                    case 11:
                        controller.submitEnquiry(officer);
                        break;
                    case 12:
                        controller.viewEnquiries(officer);
                        break;
                    case 13:
                        controller.editEnquiry(officer);
                        break;
                    case 14:
                        controller.deleteEnquiry(officer);
                        break;
                    case 15:
                        controller.requestWithdrawal(officer);
                        break;
                    case 16:
                        controller.changePassword(officer);
                        break;
                    case 17:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}

    


