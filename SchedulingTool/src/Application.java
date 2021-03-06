
import java.awt.*;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import java.io.*;


/**
 * This is the Team Unknown Scheduling Tool's main class.
 * See README.md for more information.
 * @authors Anish Prasad, Rahil Mehta, Hossam Basiony
 */
public class Application {
	private static User currentUser; // stores the information of the user currently logged in
	private static HashMap<String, User> users = new HashMap<String, User>(); //stores user objects, access by string
	private static HashMap<String, Project> projects = new HashMap<String, Project>(); // stores project, access by string

	public Application() {

	}


	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to The Nameless Scheduler!");
		restoreFromFile();
		login();

		System.out.println("Here are your options:");

		// Application presents options to the user
		boolean quit = false;
		while (!quit) {

			int functionChoice = getUserFunctionChoice(input);

			switch (functionChoice) {

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


					performCalculations();
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
					try {
						saveToFile();
						System.out.println("Data Saved to File\nExiting Program");
						quit = true;
					} catch (IOException exception) {
						System.err.print("ERROR SAVING");
					}
					break;

				default:
					System.err.println("\nIncorrect Input\n");
					break;
			}

		}
		System.exit(0);
	}


	/**
	 * This method handles the user login. If no users exist in the system, it will give the user the ability to register.
	 * Otherwise, it will give the user five tries to log in with a user in the system.
	 */
	private static void login() {
		Scanner input = new Scanner(System.in);
		int count = 5;
		int iterator = 0;
		String username = "";
		if (users.isEmpty()) {
			System.out.println("No users found in system");
			System.out.println("What user name would you like to register with");
			username = input.nextLine();

			System.out.println("Creating user " + username);
			createUser(username);
		}
		while (true) {
			System.out.println("Enter your username for login.");
			username = input.nextLine();
			while (!checkStringIsValid(username)) {

				System.out.println("User name cannot be empty");
				System.out.println("Enter your username.");
				username = input.nextLine();
			}

			User temp = users.get(username);
			if (!users.containsKey(username) && count != 0) {
				System.out.println("That user does not exist. Try again");
				System.out.println("You have " + count + " tries remaining");
				count--;
			} else if (!users.containsKey(username) && count == 0) {
				System.out.println("Tries exceeded, exiting program.");
				System.exit(0);
			} else {
				currentUser = temp;
				System.out.println("Succesfully logged in as " + username + "\n");
				break;
			}
		}
	}

	/**
	 * This method processes the user's function choice and handles invalid input.
	 * The user is given a list of choices and five tries to make a valid choice.
	 * @param input The Scanner retrieving data from the user
	 * @return An integer representing the user's input
	 */
	private static int getUserFunctionChoice(Scanner input) {
		int choice = -1;
		int count = 5;
		boolean hasTriedBefore = false;
		while (choice < 0 || choice >= 10) {
			try {
				System.out.println();
				if (hasTriedBefore == true) {
					System.out.println("Incorrect Input");
				} else {
					hasTriedBefore = true;
				}
				System.out.println("1 To Create a new User. 2 To Create a new Project. 3 To Create a new Task.");
				System.out.println("4 To list all Projects. 5 To list Tasks by Project. 6 To Perform Calculations.");
				System.out.println("7 To list all users. 8 To Delete ALL Data.");
				System.out.println("9 To login as a new User. 0 To Exit Program");
				System.out.println("Please enter a number to choose a function.");
				choice = input.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid Value, Exiting Program");
				return 0;
			}
		}
		while (true) {
			if (choice < 10 && choice > -1) {
				return choice;
			}
			--count;
			if (count == 0) {
				System.out.println("5 Tries Excceded, Exiting Program");
				System.exit(0);
			} else {
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

	/**
	 * This method saves to file the current list of projects, their tasks and users
	 * @throws IOException if the saving process fails due to IO problems
	 */
	private static void saveToFile() throws IOException {
		File file = new File("projectData.csv");
		if (!file.exists()) {
			createFile(file);
		}

		FileWriter writer = new FileWriter(file);
		// Iterate over the Tasks
		for (User user : users.values()) {
			writer.write(user.toFile() + "\n");
		}

		// Iterate over the Projects
		for (Project project : projects.values()) {
			writer.write(project.toFile() + "\n");
			// Iterate over the Tasks in the list for that Project
			for (Task task : project.getTaskList()) {
				writer.write(task.toFile() + "\n");
			}
		}
		writer.close();
	}

	//check if file exists, if not it creates one
	private static void createFile(File file) throws IOException {
		if (file.createNewFile()) {
			System.out.println("File is created!");
		} else {
			System.out.println("File already exists.");
		}
	}

	//restores from file
	private static void restoreFromFile() throws FileNotFoundException {
		File file = new File("projectData.csv");
		// If the file does not exist, create it
		if (!file.exists()) {
			try {
				createFile(file);
			} catch (IOException io) {

			}
		}
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			line = line.replaceAll("#", "");
			String array[] = line.split(",");
			// The id comes first and the name is second
			if (array.length == 2) {
				String name = array[1];
				User user = new User(array[1], Integer.parseInt(array[0]));
				users.put(name, user);
			} else if (array.length == 3) {
				// Same issue with ID for Project
				// Project name is index 1
				String name = array[1];
				// Project name, manager name, id
				Project project = new Project(name, array[2], Integer.parseInt(array[0]));
				projects.put(name, project);
			} else if (array.length == 9 || array.length == 11) {
				Task task;
				if (array.length == 9) {
					task = new Task(Integer.parseInt(array[0]), array[1], array[2], LocalDate.parse(array[7]), LocalDate.parse(array[8]),
							Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]));
				} else {
					task = new Task(Integer.parseInt(array[0]), array[1], array[2], LocalDate.parse(array[7]), LocalDate.parse(array[8]),
							Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]), Long.parseLong(array[9]),
							Long.parseLong(array[10]));
				}

				int projectID = task.getParentProjectID();
				String projectName = "";
				// Find the right project to add this task to
				for (Project project : projects.values()) {
					// Found the parent project
					if (project.getID() == projectID) {
						projectName = project.getName();
					}
				}
				Project retrieveProject = projects.get(projectName);
				if (!(retrieveProject == null)) {
					retrieveProject.addTask(task);
				}
			} else {
				System.out.println("This line is not formatted correctly");
			}
		}
	}

	private static void createProject() {
		Scanner input = new Scanner(System.in);
		boolean projectNameSet = false;
		String projectName = "";
		while (projectNameSet == false) {
			System.out.println("Enter a project name");
			projectName = input.nextLine();

			if (!checkStringIsValid(projectName)) {
				System.out.println("Project name cannot be empty");
			} else {
				projectNameSet = true;
			}
		}
		if (projects.containsKey(projectName)) {
			System.err.println("Error: Project already exists");
		} else {

			boolean managerNameSet = false;
			String managerName = "";
			while (managerNameSet == false) {
				System.out.println("Enter a manager name");
				managerName = input.nextLine();

				if (!checkStringIsValid(managerName)) {
					System.out.println("Manager name cannot be empty");
				} else {
					managerNameSet = true;
				}

			}

			Project toAdd = new Project(projectName, managerName);
			projects.put(projectName, toAdd);
			System.out.println("Project created");
		}
	}

	private static void createTask() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the name of the project this task will belong to");
		String taskProject = input.nextLine();
		int counter = 0;
		long es = -1;
		long ls = -1;


		if (!projects.containsKey(taskProject)) {
			System.out.println("Project does not exist\nStarting project creation wizard\n");
			createProject();
			System.out.println();
		}

		System.out.println("Enter the name of the task");
		String taskName = "";
		String taskOwner = "";
		String sDate = "";

		while (!checkStringIsValid(taskName)) {
			if (counter != 0) {
				System.out.println("Something went wrong, name can not be empty");
				System.out.println("Enter the name of the task");

			}
			taskName = input.nextLine();
			counter++;
		}
		counter = 0;

		System.out.println("Enter the name of the task owner");

		while (!checkStringIsValid(taskOwner)) {
			if (counter != 0) {
				System.out.println("Something went wrong, name can not be empty");
				System.out.println("Enter the name of the task owner");

			}
			taskOwner = input.nextLine();
			counter++;
		}
		counter = 0;
		System.out.println("Please enter the start date in the format YYYY-MM-DD");

		boolean isSet = false;
		LocalDate startDate = LocalDate.MIN;
		LocalDate endDate = LocalDate.MIN;

		while (!checkStringIsValid(sDate) && isSet == false) {
			if (counter != 0) {
				System.out.println("Something went wrong, date can not be empty or incorrect");
				System.out.println("Enter the start date in the format YYYY-MM-DD");

			}
			sDate = input.nextLine();
			counter++;
			try {
				startDate = LocalDate.parse(sDate);

				isSet = true;
			} catch (Exception e) {
				System.out.println("Inserted incorrect date, check date again");
				isSet = false;
				sDate = "";
			}

		}
		counter = 0;


		isSet = false;
		System.out.println("Please enter the end date in the format YYYY-MM-DD");
		String eDate = "";

		while (!checkStringIsValid(eDate) && isSet == false) {
			if (counter != 0) {
				System.out.println("Something went wrong, date can not be empty or incorrect. Also End Date needs to be after Start Date.");
				System.out.println("Enter end date in the format YYYY-MM-DD");

			}
			eDate = input.nextLine();
			counter++;
			try {
				endDate = LocalDate.parse(eDate);
				if(endDate.isAfter(startDate)) {
					isSet = true;
				}else{
					eDate = "";
					isSet = false;
				}
			} catch (Exception e) {
				System.out.println("Inserted incorrect date, check date again");
				isSet = false;
				eDate = "";
			}

		}
       counter = 0;
		System.out.println("Please enter name of previous task, if there is no previous task, just type zero");
        String prevTemp="";
        Task prevTask = null;
		while (!checkStringIsValid(prevTemp)) {
			if (counter != 0) {
				System.out.println("Something went wrong, Previous task can not be empty");
				System.out.println("Please enter name of previous task, if there is no previous task, just type zero");

			}
			prevTemp = input.nextLine();
			counter++;
		}
        int prevID = 0;
        if(projects.get(taskProject).getTaskList().contains(prevTemp)) {

        	prevTask = projects.get(taskProject).getTaskByName(prevTemp);

        }

		if (projects.get(taskProject) != null && projects.get(taskProject).getTaskList().size() == 0) {

			System.out.println("Enter an early start value");
			es = input.nextLong();
			System.out.println("Enter a late start value");
			ls = input.nextLong();

			while (es < 0) {
				try {
					es = input.nextLong();
				} catch (Exception e) {
					System.out.println("Your early start input is invalid");
					System.out.println("Enter an early start value");

				}
			}
			System.out.println("Enter a late start value");
			while (ls < 0) {
				try {
					ls = input.nextLong();
				} catch (Exception e) {
					System.out.println("Your late start input is invalid");
					System.out.println("Enter an late start value");

				}
			}
             if(prevTask != null) {

				 prevID = prevTask.getID();

			 }
		}
		if (projects.get(taskProject).getTaskList().size() == 0) {


			if (projects.containsKey(taskProject)) {
				Project tempProject = projects.get(taskProject);
				int parentID = tempProject.getID();
				Task toAdd = new Task(taskName, taskOwner, startDate, endDate, parentID,
						prevID, 0, es, ls);
				toAdd.setEarlyStart(es);
				toAdd.setLateStart(ls);
				//prevTemp.setNextTaskID(tempProject.getID());
				//prevTemp.setNextTaskID(tempProject.getID());
				toAdd.calculateDuration();
				tempProject.getTaskList().add(toAdd);
			}
		} else {
			try {
				if (!projects.containsKey(taskProject)) {
					Project tempProject = projects.get(taskProject);
					int parentID = tempProject.getID();
					//     public Task(String newName, String newOwner, LocalDate newStart,
					//    		LocalDate newEnd, int parentID,
					//                int newPrev, int newNext, long earlyStart, long lateStart)
					Task toAdd = new Task(taskName, taskOwner, startDate, endDate, parentID, prevID, 0, es, ls);
					prevTask.setNextTaskID(tempProject.getID());
					toAdd.setEarlyStart(es);
					toAdd.setLateStart(ls);
					toAdd.calculateDuration();
					tempProject.getTaskList().add(toAdd);
				}
			} catch (Exception e) {
				System.out.println("Something went wrong, going to main menu");
			}
		}

		System.out.println("Task created");
	}




	private static void createUser() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a Name");
		String name = input.nextLine();
		while (!checkStringIsValid(name)){
			System.out.println("User name can not be empty");
			System.out.println("Enter a Name");
			name = input.nextLine();

		}
		//if the user in map already
		if(users.containsKey(name)) {
			System.err.println("Error: User already exists");
			System.err.println("Overwrite User?");
			System.out.println("Y or N?");
			
			
			char overwrite = input.next().trim().charAt(0);
			if(overwrite == 'y' || overwrite == 'Y') {
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
		String choice = input.next().trim();
		if(choice.charAt(0) == 'y' || choice.charAt(0) == 'Y') {
			projects.clear();
			users.clear();
			System.out.println("Removed all Data");
		}
		else {
			System.out.println("Removal operation cancelled");
		}
	}
	
	public static void performCalculations() {

	    //int Operation = 0;
	    float input1 = 0;
	    float input2 = 0;
	    Scanner input = new Scanner(System.in);

	    System.out.println("Do you want to calculate free float or total float? Enter f or t");
        String operation = input.nextLine();

        System.out.println("Enter the name of the project");
        String projectString = input.nextLine();
        Project project = projects.get(projectString);

        if (operation.equals("f")) {

            System.out.println("Enter the name of the current activity.");
			System.out.println("Here are your options: ");
			for(int i = 0; i < project.getTaskList().size();i++){
				System.out.println(project.getTaskList().get(i));
			}
            String current = input.nextLine();

            System.out.println("Enter the name of the next activity.");
			System.out.println("Here are your options: ");
			for(int i = 0; i < project.getTaskList().size();i++){
				System.out.println(project.getTaskList().get(i));
			}
            String next = input.nextLine();

            System.out.println("Free float = " + (project.getTaskByName(next).getEarlyStart() - project.getTaskByName(current).getEarlyFinish()));

        }

       else if (operation.equals("t")) {
			try {
				System.out.println("Enter the name of the current activity.");
				System.out.println("Here are your options: ");
				for(int i = 0; i < project.getTaskList().size();i++){
					System.out.println(project.getTaskList().get(i));
				}
				String current = input.nextLine();

				System.out.println("Total float = " + (project.getTaskByName(current).getLateStart() - project.getTaskByName(current).getEarlyStart()));
			}
			catch (Exception e){
				System.out.println("Something went wrong, most likely activity is not found.");
				System.out.println("Check spelling of activity (case sensitive)");

			}
		}

    }
  static boolean checkStringIsValid(String Input){
		return Input != null && !Input.isEmpty() && !Input.trim().isEmpty();
  }

  static void setEarlyStart(long Input, long value) throws Exception{

		try{
         			Input = value;
		}
		catch (Exception e){
			throw new Exception("Something went Wrong");
		}

  }


}
