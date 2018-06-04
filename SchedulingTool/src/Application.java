import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import java.io.*;

public class Application {
	//private static File file;
	//private static String username;
	private static User currentUser;
	private static HashMap<String, User> users = new HashMap<String, User>(); //stores user objects, access by string
	private static HashMap<String, Project> projects = new HashMap<String, Project>(); // stores project, access by string

	public Application(/*String inputUsername*/) {
		//this.username = inputUsername;
	}

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to The Nameless Scheduler!");
		restoreFromFile();
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
				createTask();
				break;
			
			case 4:
				listAllProjects();
				break;
				
			case 5:
				listTasksByProject();
				break;
				
			case 6:
				//performCalculations();
				break;
				
			case 7:
				listAllUsers();
				break;
				
			case 8:
				purgeData();
				break;
				
			case 9:
				login();
				break;

			case 0:
			    try
                {
                    saveToFile();
                    System.out.println("Data Saved to File\nExiting Program");
                    quit = true;
                }
                catch (IOException exception)
                {
                    System.err.print("ERROR SAVING");
                }
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
				System.out.println("No users found in system");
				System.out.println("Creating user " + username);
				createUser(username);
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
				System.out.println("Succesfully logged in as " + username + "\n");
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
			System.out.println("7 To list all users. 8 To Delete ALL Data.");
			System.out.println("9 To login as a new User. 0 To Exit Program");
			System.out.println("Please enter a number to choose a function.");
			choice = input.nextInt();
			if(choice < 10 && choice > -1) {
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
	private static void saveToFile() throws IOException
    {
		File file = new File("projectData.csv");
		if (!file.exists())
        {
            createFile(file);
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
        writer.close();
        //file.close();
	}
	
	private static void createFile(File file) throws IOException {
		if (file.createNewFile()) {
			System.out.println("File is created!");
		} else {
			System.out.println("File already exists.");
		}
	}

	private static void restoreFromFile() throws FileNotFoundException
    {
        System.out.println("Restoring data from file.\n");
        File file = new File("projectData.csv");
        // If the file does not exist, create it
        if (!file.exists())
        {
            try
            {
                createFile(file);
            }
            catch (IOException io)
            {

            }
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            line = line.replaceAll("#", "");
            String array[] = line.split(",");
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
            else if (array.length == 9 || array.length == 11)
            {
                Task task;
                if (array.length == 9)
                {
                    task = new Task(Integer.parseInt(array[0]), array[1], array[2], LocalDate.parse(array[7]), LocalDate.parse(array[8]),
                            Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]));
                }
                else
                {
                    task = new Task(Integer.parseInt(array[0]), array[1], array[2], LocalDate.parse(array[7]), LocalDate.parse(array[8]),
                            Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]), Long.parseLong(array[9]),
                            Long.parseLong(array[10]));
                }

                int projectID = task.getParentProjectID();
                String projectName = "";
                // Find the right project to add this task to
                for (Project project : projects.values())
                {
                    // Found the parent project
                    if (project.getID() == projectID)
                    {
                        projectName = project.getName();
                    }
                }
                Project retrieveProject = projects.get(projectName);
                if (retrieveProject)
                {
                    retrieveProject.addTask(task);
                }
            }
            else
            {
                System.out.println("This line is not formatted correctly");
            }
        }
    }

	private static void createProject() {
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
			System.out.println("Project created");
		}
	}
	
	private static void createTask() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the name of the project this task will belong to");
		String taskProject = input.nextLine();
		
		if(!projects.containsKey(taskProject)) {
			System.out.println("Project does not exist\nStarting project creation wizard\n");
			createProject();
			System.out.println();
		}
		
		System.out.println("Enter the name of the task");
		String taskName = input.nextLine();
		System.out.println("Enter the name of the task owner");
		String taskOwner = input.nextLine();
		System.out.println("Please enter the start date in the format YYYY-MM-DD");
		String sDate = input.nextLine();
		LocalDate startDate = LocalDate.parse(sDate);
		System.out.println("Please enter the end date in the format YYYY-MM-DD");
		String eDate = input.nextLine();
		LocalDate endDate = LocalDate.parse(eDate);
		System.out.println("Enter parent Task name if applicable, or enter 0 if not");
		String parent = input.nextLine();
		
		if(projects.get(taskProject).getTaskList().size() == 0) {
			System.out.println("Enter an early start value");
			long es = input.nextLong();
			System.out.println("Enter a late start value");
			long ls = input.nextLong();
			
			if(projects.containsKey(taskProject) ) {
				Project temp = projects.get(taskProject);
				int parentID;
				if(temp.getTaskList().contains(parent)) {
					parentID = temp.getTaskByName(parent).getID();
					Task toAdd = new Task(taskName, taskOwner, startDate, endDate, parentID,
							es, ls);
					toAdd.calculateDuration();
					temp.getTaskList().add(toAdd);
				}
				else {
					Task toAdd = new Task(taskName, taskOwner, startDate, endDate, 0, es, ls);
					toAdd.calculateDuration();
					temp.getTaskList().add(toAdd);
				}
			}
		}
		else {
			if(projects.containsKey(taskProject)) {
				Project temp = projects.get(taskProject);
				int parentID;
				if(temp.getTaskList().contains(parent)) {
					parentID = temp.getTaskByName(parent).getID();
					Task toAdd = new Task(taskName, taskOwner, startDate, endDate, parentID);
					toAdd.calculateDuration();
					temp.getTaskList().add(toAdd);
				}
				else {
					Task toAdd = new Task(taskName, taskOwner, startDate, endDate, 0);
					toAdd.calculateDuration();
					temp.getTaskList().add(toAdd);
				}
			}
		}
		System.out.println("Task created");
	}
	
	private static void createUser() {
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
	
	private static void createUser(String username) {
		User newUser = new User(username);
		users.put(username, newUser);
		System.out.println("User Created");
	}
	
	private static void listAllProjects() {
		System.out.println(projects.toString());
	}
	
	private static void listTasksByProject() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the name of the project");
		String project = input.nextLine();
		if(projects.containsKey(project)) {
			ArrayList<Task> taskList = projects.get(project).getTaskList();
			for(int i = 0; i < taskList.size(); i++) {
				System.out.println(taskList.get(i).toString());
			}
		}
		else {
			System.out.println("That project does not exist");
		}
	}
	
	private static void listAllUsers() {
		System.out.println(users.toString());
	}
	
	private static void purgeData() {
		Scanner input = new Scanner(System.in);
		System.out.println("Are you sure you want to DELETE ALL DATA?\ny or n");
		String choice = input.next();
		if(choice.charAt(0) == 'y' || choice.charAt(0) == 'Y') {
			projects.clear();
			users.clear();
			System.out.println("Removed all Data");
		}
		else {
			System.out.println("Removal operation cancelled");
		}
	}
}
