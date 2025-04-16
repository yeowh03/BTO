package Interface;

import model.Officer;

public interface IOfficerServices extends IApplicantServices{
	void submitApplication(Officer officer);
	void processFlatSelection(Officer officer);
	void registerForProject(Officer officer);
	void viewRegistrationStatus(Officer officer);
	void viewProjectDetails(Officer officer);
	void viewProjectEnquiries(Officer officer);
	void replyToEnquiries(Officer officer);
	void generateReceipt(Officer officer);
}
