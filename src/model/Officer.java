package model;

import java.util.Map;
import java.time.LocalDate;

import Database.ProjectRepository;

public class Officer extends Applicant{
	
	private Map<String, Project> handledProjects;
	
	public Officer(String name, String nric, int age, String maritalStatus, String password){
		super(name, nric, age, maritalStatus, password);
	}
	
	public void addHandledProject(String name, Project p) {
		handledProjects.put(name, p);
	}
	
	public Map<String, Project> geHandledProject(String name, Project p) {
		return handledProjects;
	}
}

