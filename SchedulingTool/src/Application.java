import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class Application {
	private static File file;
	//private static String username;
	private static User currentUser;
	private static HashMap<String, User> users = new HashMap<String, User>(); //stores user objects, access by string
	private static HashMap<String, Project> projects = new HashMap<String, Project>(); // stores project, access by string

	public Application(/*String inputUsername*/) {
		//this.username = inputUsername;
	}

	public static void main(String[] args) {
        file = new File("projectData.csv");
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to The Nameless Scheduler!");
		
		login();
		
		System.out.println("Here are your options:");
		
		boolean quit = false;
		
		while(!quit) {
			int functionChoice = getUserFunctionChoice(input);
			
			switch(functionChoice) {
				
			case 1://new user
				createUser();
				break;
				
			case 2:
				createProject();
				break;
				
			case 3:
				//createTask();
				break;
			
			case 4:
				listAllProjects();
				break;
				
			case 5:
				//listTasksByProject();
				break;
				
			case 6:
				//performCalculations();
				break;
				
			case 7:
				//purgeData();
				break;
				
			case 8:
				saveStateToFile();
				quit = true;
				break;
			}
		}
		System.exit(0);
	}
	
	
	private static void login() {
		Scanner input = new Scanner(System.in);
		int count = 5;
		while(true) {
			System.out.println("Enter your username.");
			String username = input.nextLine();
			if(users.isEmpty()) {
				createUser();
			}
			User temp = users.get(username);
			if(temp.equals(null) && count != 0) {
				System.out.println("That user does not exist. Try again");
				System.out.println("You have " + count + " tries remaining");
				count--;
			}
			else if(temp.equals(null) && count == 0) {
				System.out.println("Tries exceeded, exiting program.");
				System.exit(0);
			}
			else {
				currentUser = temp;
				break;
			}
		}
	}
	
	private static int getUserFunctionChoice(Scanner input) {
		int choice = 0;
		int count = 5;
		while (true) {
			System.out.println();
			System.out.println("1 To Create a new User. 2 To Create a new Project. 3 To Create a new Task.");
			System.out.println("4 To list all Projects. 5 To list Tasks by Project. 6 To Perform Calculations.");
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
	
//	private boolean getUserFromMap() {
//		currentUser = this.users.get(username);
//		if(currentUser != null) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
	public static void saveToFile() throws IOException
    {
		if (!file.exists())
        {
            createFile();
        }

        FileWriter writer = new FileWriter(file);
        // Iterate over the Tasks
        for (User user : users.values())
        {
            writer.write(user.toFile() + "\n");
        }

        // Iterate over the Projects
        for (Project project : projects.values())
        {
            writer.write(project.toFile() + "\n");
            // Iterate over the Tasks in the list for that Project
            for (Task task : project.getTaskList())
            {
                writer.write(task.toFile() + "\n");
            }
        }
	}
	
	private static void createFile() throws IOException {
		if (file.createNewFile()) {
			System.out.println("File is created!");
		} else {
			System.out.println("File already exists.");
		}
	}

	public static void restoreFromFile() throws FileNotFoundException
	{
	    Scanner scanner = new Scanner(file);
	    while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            line = line.replaceAll("#", "");
            String array[] = line.split("\\s");
            // The id comes first and the name is second
            if (array.length == 2)
            {
                String name = array[1];
            	User user = new User(array[1], Integer.parseInt(array[0]));
                users.put(name, user);
            }
            else if (array.length == 3)
            {
                // Same issue with ID for Project
                // Project name is index 1
                String name = array[1];
                // Project name, manager name, id
                Project project = new Project(name, array[2], Integer.parseInt(array[0]));
                projects.put(name, project);
            }
            else if (array.length == 9)
            {

            }
            else
            {

            }
        }

	}
	
	private static boolean saveStateToFile() {
		return false;
	}
	
	private static boolean rebuildStateFromFile() {
		return false;
	}
	
	public static void createProject() {
		Scanner input = new Scanner(System.in);
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
	}
	
	public static void createTask() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the name of the project this task will belong to");
		String taskProject = input.nextLine();
	}
	
	public static void createUser() {
		Scanner input = new Scanner(System.in);
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
	}
	
	public static void listAllProjects() {
		System.out.println(projects.toString());
	}
}
