package model;

public class Enquiry {
    private int id; 
    private String question = "";
    private String reply = "";
    private Applicant applicant; // can be officer or applicant
    private Project project;

    public Enquiry(String question, Project project, Applicant applicant) {
        this.question = question;
        this.project = project;
        this.applicant = applicant;
    }
    
    public int getID() {
    	return this.id;
    }
    
    public String getQuestion() {
    	return this.question;
    }
    
    public String getReply() {
    	return this.reply;
    }
    
    public Applicant getApplicant() {
    	return this.applicant;
    }
    
    public Project getProject() {
    	return this.project;
    }
    
    public void setReply(String r) {
    	this.reply = r;
    }
    
    public void setQuestion(String q) {
    	this.question = q;
    }
    
    public void setID(int id) {
    	this.id = id;
    }
    
    @Override
    public String toString() {
        return "ID: " + applicant.getName() + "\nProject: " + project.getProjectName() + "\nQuestion: " + question + "\nReply: " + reply + "\n";
    }
}
