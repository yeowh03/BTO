package menu;

import java.util.Scanner;

import auth.AuthenticationSystem;
import model.User;
import model.UserType;
import model.Manager;
import model.Applicant;
import model.Officer;
import controller.OfficerController;
import controller.ApplicantController;
import controller.ManagerController;

public class MainMenu {
    private Scanner scanner;
    private AuthenticationSystem authSystem;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
        this.authSystem = new AuthenticationSystem();
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("\n=== BTO Management System ===");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        showLoginMenu();
                        break;
                    case 2:
                        System.out.println("Thank you for using BTO Management System");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void showLoginMenu() {
        while (true) {
            System.out.println("\n=== Login Menu ===");
            System.out.println("1. Applicant Login");
            System.out.println("2. Officer Login");
            System.out.println("3. Manager Login");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        handleApplicantLogin();
                        break;
                    case 2:
                        handleOfficerLogin();
                        break;
                    case 3:
                        handleManagerLogin();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void handleApplicantLogin() {
        System.out.print("Enter NRIC: ");
        String nric = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        Applicant applicant = (Applicant) authSystem.login(nric, password, UserType.APPLICANT);
        if (applicant != null) {
            new ApplicantMenu(applicant, new ApplicantController()).display();
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    private void handleOfficerLogin() {
        System.out.print("Enter NRIC: ");
        String nric = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        Officer officer = (Officer) authSystem.login(nric, password, UserType.OFFICER);
        if (officer != null) {
            new OfficerMenu(officer, new OfficerController()).display();
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    private void handleManagerLogin() {
        System.out.print("Enter NRIC: ");
        String nric = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        Manager manager = (Manager) authSystem.login(nric, password, UserType.MANAGER);
        if (manager != null) {
            new ManagerMenu(manager, new ManagerController()).display();
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

}


