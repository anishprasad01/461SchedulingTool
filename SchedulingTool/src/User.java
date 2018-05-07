import java.util.ArrayList;

public class User extends SchedulingObject{
	
    private ArrayList<Integer> projects = new ArrayList<Integer>();

    public User(String username)
    {
    	//ID generated in superclass constructor
        super(username);
    }
    
    public String getUserName() {
    	return super.getName();
    }
    
    public int getUserID() {
    	return super.getID();
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
    
    //public void printProjects

    public boolean addProject(int projectID)
    {
    	if(projectID > 0) {
    		this.projects.add(projectID);
    		return true;
    	}
    	return false;
    }

    public boolean removeProject(int projectID)
    {
        int projectIndex = getProjectIndex(projectID);
        
        if(projectIndex == -1) {
        	return false;
        }
        else {
        	this.projects.remove(projectIndex);
        	return true;
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

	@Override
	protected int generateID() {
		// TODO Auto-generated method stub
		return 0;
	}
}

