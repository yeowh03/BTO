package auth;

import java.util.HashMap;
import java.util.List;

import model.Applicant;
import model.Officer;
import model.Manager;
import model.User;
import model.UserType;
import Database.ApplicantRepository;
import Database.OfficerRepository;
import Database.ManagerRepository;

public class AuthenticationSystem {
    private static HashMap<String, Applicant> applicantRepository = ApplicantRepository.getApplicantRepository();
    private static HashMap<String, Officer> officerRepository = OfficerRepository.getOfficerRepository();
    private static HashMap<String, Manager> managerRepository = ManagerRepository.getManagerRepository();

    public User login(String nric, String password, UserType type) {
        if (!isValidNRIC(nric)) {
            return null;
        }

        switch (type) {
            case APPLICANT:
                if (applicantRepository.containsKey(nric) && applicantRepository.get(nric).getPassword().equals(password)) {
                	return applicantRepository.get(nric);
                }
                break;
            case OFFICER:
            	if (officerRepository.containsKey(nric) && officerRepository.get(nric).getPassword().equals(password)) {
                	return officerRepository.get(nric);
                }
                break;
            case MANAGER:
            	if (managerRepository.containsKey(nric) && managerRepository.get(nric).getPassword().equals(password)) {
                	return managerRepository.get(nric);
                }
                break;
            default:
                return null;
        }
        return null;
    }

    private boolean isValidNRIC(String nric) {
        if (nric == null || nric.length() != 9) {
            return false;
        }

        // First character must be 'S' or 'T'
        char firstChar = nric.charAt(0);
        if (firstChar != 'S' && firstChar != 'T') {
            return false;
        }

        // Middle 7 characters must be digits
        String digits = nric.substring(1, 8);
        if (!digits.matches("\\d{7}")) {
            return false;
        }

        // Last character must be a letter
        return Character.isLetter(nric.charAt(8));
    }

}


