package model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
    private String name;
    private String nric;
    private int age;
    private String maritalStatus;
    private String password;
    
    private String neighbourhoodFilter = "";
	private String typeFilter = "";
	private int wantSort = 1;
	
    public User(String name, String nric, int age, String maritalStatus, String password) {
        this.name = name;
        this.nric = nric;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.password = password;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getNric() {
        return nric;
    }

    public int getAge() {
        return age;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public String getNeighbourhoodFilter() {
    	return neighbourhoodFilter;
    }
    public String getTypeFilter() {
    	return typeFilter;
    }
    public int getWantSort() {
    	return wantSort;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    
    public void setPassword(String pwd) {
        this.password = pwd;
    }
    
    public void updateFilter() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter neighbourhood: ");
        neighbourhoodFilter = sc.nextLine().trim();
        System.out.println("Enter type: ");
        typeFilter = sc.nextLine().trim();
        do {
        	try {
        		System.out.println("Enter 1 for sorting, 0 for no sorting by Project Name: ");
	        	wantSort = sc.nextInt();
	        	if (wantSort != 0 && wantSort != 1) {
	        		 System.out.println("Please enter 0 or 1.");
	        	}
        	} catch (InputMismatchException e){
        		 System.out.println("Invalid input.");
        		 sc.next();
        	}
        }while (wantSort != 0 && wantSort != 1);
	}
}
