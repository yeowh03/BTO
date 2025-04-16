package Database;

import java.util.HashMap;
import java.util.Map;
import model.Applicant;

public class ApplicantRepository { // nric as key
	private static HashMap<String, Applicant> collOfApplicants = new HashMap<>(Map.ofEntries(
			Map.entry("S1234567A", new Applicant("John", "S1234567A", 35, "Single", "password")),
			Map.entry("T7654321B", new Applicant("Sarah", "T7654321B", 40, "Married", "password")),
			Map.entry("S9876543C", new Applicant("Grace", "S9876543C", 37, "Married", "password")),
			Map.entry("T2345678D", new Applicant("James", "T2345678D", 30, "Married", "password")),
			Map.entry("S3456789E", new Applicant("Rachel", "S3456789E", 35, "Single", "password"))
			));
	
	public static void put(Applicant applicant) {
		collOfApplicants.put(applicant.getNric(), applicant);
	}
	
	public static HashMap<String, Applicant> getApplicantRepository() {
		return collOfApplicants;
	}
}
