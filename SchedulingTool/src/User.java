import java.util.ArrayList;
import java.util.Random;

public class User extends SchedulingObject{
	
    private ArrayList<Integer> projects = new ArrayList<Integer>();

    public User(String username)
    {
    	//ID generated in superclass constructor
        super(username);
    }
    
    public boolean userInProject(Object projectID) {
    	if(projectID instanceof Integer) {
    		Integer project = (Integer) projectID;
        	if(this.projects.contains(project)) {
        		return true;
        	}
        	else {
        		return false;
        	}
    	}
    	else {
    		System.err.println("Not a valid ProjectID");
    		return false;
    	}
    }
    
    public void printProjects()
	{
    	
	}

    public boolean addProject(int projectID)
    {
    	if(projectID > 0) {
    		this.projects.add(projectID);
    		return true;
    	}
    	return false;
    }

    public boolean removeProject(Object projectID)
    {
        if(projectID instanceof Integer) {
        	return this.projects.remove((Integer) projectID);
        }
        else {
        	return false;
        }
    }
    
    private int getProjectIndex(int target) {
    	for(int i = 0; i < projects.size(); i++) {
    		if(projects.get(i) == target) {
    			return i;
    		}
    	}
    	return -1;
    }

    //DOES NOT CHECK FOR DUPES
	@Override
	protected int generateID() {
		//User IDs are 4 digits
		Random idGen = new Random();
		int id = idGen.nextInt(9000) + 1000;
		return id;
	}
	
	@Override
	public String toFile() {
		String retVal = "#" + this.getID() + "," + this.getName() + "##"; 
		return retVal;
	}
	
	@Override
	public String toString() {
		String retVal = "ID: " + this.getID() + " Name: " + this.getName(); 
		return retVal;
	}
}

