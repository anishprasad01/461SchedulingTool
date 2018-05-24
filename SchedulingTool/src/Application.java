import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class Application {
	
	private String username;
	private User currentUser;
	private static HashMap<String, User> users = new HashMap<String, User>(); //stores user objects, access by string
	private static HashMap<String, Project> projects = new HashMap<String, Project>(); // stores project, access by string
	//private static HashMap<Integer, Name>

	public Application(String inputUsername) {
		this.username = inputUsername;
	}

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to The Nameless Scheduler!");
		System.out.println("Here are your options:");
		
		boolean quit = false;
		
		while(!quit) {
			int functionChoice = getUserFunctionChoice(input);
			
			switch(functionChoice) {
				
			case 1://new user
				System.out.println("Enter a Name");
				String name = input.nextLine();
				//if the user in map already
				if(users.containsKey(name)) {
					System.err.println("Error: User already exists.\nOverwrite User?");
					System.out.println("Y or N?");
					char overwrite = input.next().charAt(0);
					if(overwrite == 'y' || overwrite == 'n') {
						User toAdd = new User(name);
						users.put(name, toAdd);
						System.out.println("User Overwritten");
					}
					else {
						System.out.println("User not modified");
					}
					
				}
				else {
					User newUser = new User(name);
					users.put(name, newUser);
					System.out.println("User Created");
				}
				break;
				
			case 2:
				System.out.println("Enter a project name");
				String projectName = input.nextLine();
				if(projects.containsKey(projectName)) {
					System.err.println("Error: Project already exists");
				}
				else {
					System.out.println("Enter a manager name");
					String managerName = input.nextLine();
					Project toAdd = new Project(projectName, managerName);
					projects.put(projectName, toAdd);
				}
				break;
				
			case 3:
				System.out.println("Enter the name of the project this task will belong too");
				String taskProject = input.nextLine();
			}
		}
		
	}
	
	private static int getUserFunctionChoice(Scanner input) {
		int choice = 0;
		int count = 5;
		while (true) {
			System.out.println();
			System.out.println("1 To Create a new User. 2 To Create a new Project. 3 To Create a new Task.");
			System.out.println("4 To View all Projects. 5 To View Tasks by Project. 6 To Perform Calculations.");
			System.out.println("7 To Delete ALL Data. 8 To Exit Program");
			System.out.println("Please enter a number to choose a function.");
			choice = input.nextInt();
			if(choice < 9 && choice > 0) {
				return choice;
			}
			--count;
			if(count == 0) {
				System.out.println("5 Tries Excceded, Exiting Program");
				System.exit(0);
			}
			else {
				System.out.println("Incorrect Input\n" + count + " tries remaining");
			}
		}
	}
	
	private boolean getUserFromMap() {
		currentUser = this.users.get(username);
		if(currentUser != null) {
			return true;
		}
		else {
			return false;
		}
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
