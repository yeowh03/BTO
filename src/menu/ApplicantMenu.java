package menu;

import auth.AuthenticationSystem;

import model.Applicant;
import controller.EnquiryController;
import java.util.Scanner;

import Interface.IApplicantServices;

public class ApplicantMenu {
    private Scanner scanner;
    private AuthenticationSystem authSystem;
    private Applicant applicant;
    private IApplicantServices controller;

    public ApplicantMenu(Applicant applicant, IApplicantServices controller) {
        this.scanner = new Scanner(System.in);
        this.authSystem = new AuthenticationSystem();
        this.applicant = applicant;
        this.controller = controller;
    }

    public void display() {
        // Managers cannot access BTO application features

        while (true) {
            System.out.println("\n=== Applicant Menu ===");
            System.out.println("Welcome, " + applicant.getName());
            System.out.println("1. Browse Projects");
            System.out.println("2. Submit Application");
            System.out.println("3. View Application Status");
            System.out.println("4. Submit New Enquiry");
            System.out.println("5. View My Enquiries");
            System.out.println("6. Edit Enquiry");
            System.out.println("7. Delete Enquiry");
            System.out.println("8. Request Withdrawal");
            System.out.println("9. Change Password");
            System.out.println("10. Update Filters");
            System.out.println("11. Logout");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                    	this.controller.browseProjects();
                        break;
                    case 2:
                    	this.controller.submitApplication();
                        break;
                    case 3:
                    	this.controller.viewApplicationStatus();
                        break;
                    case 4:
                    	this.controller.submitEnquiry();
                        break;
                    case 5:
                    	this.controller.viewEnquiries();
                        break;
                    case 6:
                    	this.controller.editEnquiry();
                        break;
                    case 7:
                    	this.controller.deleteEnquiry();
                        break;
                    case 8:
                    	this.controller.requestWithdrawal();
                        break;
                    case 9:
                    	this.controller.changePassword();
                        break;
                    case 10:
                    	this.controller.updateFilter();
                        break;
                    case 11:
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
