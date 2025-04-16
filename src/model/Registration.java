package model;

public class Registration {
	private int id;
	private Project project;
	private Officer officer;
	private RegistrationStatus status;
	
	public Registration(Project project, Officer officer) {
		this.project = project;
		this.officer = officer;
		this.status = RegistrationStatus.PENDING;
	}
	
	public Project getProject() {
		return this.project;
	}
	
	public Officer getOfficer() {
		return this.officer;
	}
	
	public RegistrationStatus getStatus() {
		return this.status;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public void setOfficer(Officer officer) {
		this.officer = officer;
	}
	
	public void setStatus(RegistrationStatus status) {
		this.status = status;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}

