import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class Application {
	
	private String currentUser;
	private HashMap<String, User> users = new HashMap<String, User>(); //stores user objects, access by string
	private HashMap<Project, Integer> data = new HashMap<Project, Integer>(); // stores projects by ID
	

	public Application(String username) {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void saveToFile() {
		
	}
	
	private void createFile() {
		
	}
	
	public void restoreFromFile() {
		
	}
	
	private boolean saveStateToFile() {
		return false;
	}
	
	private boolean rebuildStateFromFile() {
		return false;
	}
	
	public boolean createProject() {
		return false;
	}
	
	public boolean createTask() {
		return false;
	}
	
	public boolean createUser() {
		return false;
	}
	
	
	

}
