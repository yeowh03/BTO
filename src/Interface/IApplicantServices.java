package Interface;

import java.util.Map;

import model.Applicant;
import model.Project;

public interface IApplicantServices {
	Map<String, Project> browseProjects(Applicant applicant);
	void submitApplication(Applicant applicant);
	void viewApplicationStatus(Applicant applicant);
	void submitEnquiry(Applicant applicant);
	void viewEnquiries(Applicant applicant);
	void editEnquiry(Applicant applicant);
	void deleteEnquiry(Applicant applicant);
	void requestWithdrawal(Applicant applicant);
	void changePassword(Applicant applicant);
}
