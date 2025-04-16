package Database;

import java.util.HashMap;
import java.util.Map;

import model.Project;

// projectName: project object
public class ProjectRepository {
	private static Map<String, Project> collOfProjects = new HashMap<>() ;
			
	public static Map<String, Project> getRepository() {
		return collOfProjects;
	}
}


