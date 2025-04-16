package model;

import java.time.LocalDateTime;

public class Application {
    private int applicationId;
    private Applicant applicant;
    private Project project;
    private String unitType;
    private ApplicationStatus status;
    private LocalDateTime applicationDate;
    private LocalDateTime lastUpdated;
    private String remarks;

    public Application(Applicant appl, Project proj, String unitType) {
        this.applicant = appl;
        this.project = proj;
        this.unitType = unitType;
        this.status = ApplicationStatus.PENDING;
        this.applicationDate = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
    }

    // Getters
    public int getApplicationId() {
        return applicationId;
    }

    public Applicant getApplicant() {
        return this.applicant;
    }

    public Project getProject() {
        return project;
    }

    public String getUnitType() {
        return unitType;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public String getRemarks() {
        return remarks;
    }

    // Setters
    public void setStatus(ApplicationStatus status) {
        this.status = status;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
        this.lastUpdated = LocalDateTime.now();
    }
    
    public void setID(int id) {
    	this.applicationId = id;
    }

    @Override
    public String toString() {
        return String.format("Application %s: %s - %s (%s)",
                applicationId, project.getProjectName(), unitType, status);
    }
}

