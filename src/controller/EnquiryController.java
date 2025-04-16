package controller;

import Database.EnquiryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Enquiry;
import model.Project;
import model.Applicant;
import model.Application;
import model.Enquiry;

public class EnquiryController {
    private static Map<Integer, Enquiry> allEnquiries = EnquiryRepository.getRepository();
    
    public static void submitEnquiry(Enquiry enquiry) {
    	int id = EnquiryRepository.getidToAssign();
    	enquiry.setID(id);
    	allEnquiries.put(id, enquiry);
    	System.out.println("Enquiry added!");
    }
    
    public static Map<Integer, Enquiry> applicantGetEnquiries(Applicant applicant) {
    	Map<Integer, Enquiry> applicantOwnEnquiries = new HashMap<>();
    	for (Map.Entry<Integer, Enquiry> entry : allEnquiries.entrySet()) {
    		if (entry.getValue().getApplicant() == applicant) {
    			applicantOwnEnquiries.put(entry.getKey(), entry.getValue());
    		}
    	}
    	return applicantOwnEnquiries;
    }
    
    public static void deleteEnquiry(Enquiry enquiry) {
    	allEnquiries.remove(enquiry.getID());
    }
    
    public static Map<Integer, Enquiry> getProjectsEnquiries(Map<String, Project> projects){
    	Map<Integer, Enquiry> enquiries = new HashMap<Integer, Enquiry>();
    	for (Map.Entry<String, Project> entry : projects.entrySet()) {
    		for (Map.Entry<Integer, Enquiry> entry2: allEnquiries.entrySet()) { // look through all enquiries to find matching project
    			if (entry2.getValue().getProject() == entry.getValue()) {
    				enquiries.put(entry2.getKey(), entry2.getValue());
    			}
    		}
    	}
    	return enquiries;
    }
}