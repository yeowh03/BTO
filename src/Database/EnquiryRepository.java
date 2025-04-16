package Database;

import java.util.HashMap;
import java.util.Map;
import model.Enquiry;


public class EnquiryRepository {
	private static Map<Integer, Enquiry> collOfEnquiries = new HashMap<>();
	private static int idToAssign = 0;
		
	public static Map<Integer, Enquiry> getRepository() {
		return collOfEnquiries;
	}
	
	public static int getidToAssign() {
		int id = idToAssign;
		idToAssign += 1;
		return id;
	}
}