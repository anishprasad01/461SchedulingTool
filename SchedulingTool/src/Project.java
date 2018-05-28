import java.time.*;
import java.util.*;

public class Project extends SchedulingObject {

	private ArrayList<Task> taskList = new ArrayList<Task>();
	private String managerName = "";

	public Project(String taskName, String name)
	{
		super(taskName);
		managerName = name;
	}

	public Project(String taskName, String name, int id)
	{
		super(taskName, id);
		managerName = name;
	}
	public boolean addTask(Task input) {
		if (input.getClass() == Task.class && input.getName() != "") {

		for(int i = 0; i < taskList.size();i++){
			if(taskList.get(i).getName() == input.getName()){

				System.out.println("Task is already added, ignoring command");
				return  false;
			}


		}
		this.taskList.add(input);

		return true;

		} else {
			return false;
		}
	}

	public ArrayList<Task> getTaskList() {
		return taskList;
	}
	public String getProjectName() {
		if (super.getName() != "") {
			return super.getName();
		} else {
			return "Project Unnamed";
		}
	}

	public String getManagerName() {
		if (this.managerName != "") {
			return this.managerName;
		} else {
			return "No PM Associated with Project";
		}
	}

	public void setManagerName(String input){

	this.managerName = input;

	}





	public void removeTask(Task input) {
		this.taskList.remove(input);
	}

	public void removeTaskByName(String input) {
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getName() == input) {
				taskList.remove(i);
			}
		}
	}

	public void printAllTaskNames() {

		for (int i = 0; i < this.taskList.size(); i++) {
		
		    System.out.println("Task " + i + ": " + taskList.get(i).getName() + " ID: " + taskList.get(i).getID());
		}
	}

	@Override
	public String toFile() {
		String retVal = "#" + this.getID() + "," + this.getName() + "," + this.getManagerName() + "##";
		return retVal;
	}

	public String toString() {
		return "ID: " + this.getID() + " Name: " + this.getName() + " PM: " + this.getManagerName();
	}

	@Override
	protected int generateID() {
		//User IDs are 4 digits
		Random idGen = new Random();
		int id = idGen.nextInt(900) + 100;
		return id;
	}



	//This method loops through all the tasks and assigns their early start, late start, early finish, and late finish

	public void setupCP(long earlyStartOfFirstActivity, long lateStartOfFirstActivity) {

		for (int i = 0; i < taskList.size(); i++) {

			// if it is the first element
			if (i == 0) {

				//set early start and late start similar to first argument
				//for early finish and late finish, they will simply be the early start and late start + duration of task

				taskList.get(i).setEarlyStart(earlyStartOfFirstActivity);
				taskList.get(i).setLateStart(lateStartOfFirstActivity);
				taskList.get(i).setEarlyFinish(taskList.get(i).getEarlyStart() -1 + taskList.get(i).getTaskDuration());
				taskList.get(i).setLateFinish(taskList.get(i).getLateStart() -1 + taskList.get(i).getTaskDuration());
			} else {

				//set early start and late start early finish and late finish of the previous task
				//for early finish and late finish, they will simply be the early start and late start + duration of task

				taskList.get(i).setEarlyStart(taskList.get(i - 1).getEarlyFinish());
				taskList.get(i).setLateStart(taskList.get(i - 1).getLateFinish());
				taskList.get(i).setEarlyFinish(taskList.get(i).getEarlyStart() -1 + taskList.get(i).getTaskDuration());
				taskList.get(i).setLateFinish(taskList.get(i).getLateStart() -1 + taskList.get(i).getTaskDuration());


			}

		}

	}

	//A method that returns the index of a task whose name is specified as input
	//so that it can be obtained from the task list using that index.
	//it is a helper for findTaskByName() and it returns -1 if fails.
	private int getTaskIndexByName(String input) {
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getName() == input) {

				return i;
			}

		}
		return -1;
	}

	//Gets free float of two activities whose names are entered as input string
	//First argument is current activity, and second argument is next activity
	//it returns -1 if either of the activities has the incorrect name passed in
	public long getFreeFloat(String currentActivity, String NextActivity) {

		if (getTaskIndexByName(currentActivity) == -1 || getTaskIndexByName(NextActivity) == -1) {
			//if either activity is not found


			return -1;
		} else {

			//return earlystart of current activity - early finish of next activity

			return (taskList.get(getTaskIndexByName(NextActivity)).getEarlyStart() - taskList.get(getTaskIndexByName(currentActivity)).getEarlyFinish());

		}


	}

	//Gets total float of current activity whose name is entered as input string
	//it returns -1 if either of the activities has the incorrect name passed in

	public long getTotalFloat(String currentActivity){

		if(getTaskIndexByName(currentActivity) == -1){

			//if activity is not found by name

			return -1;
		}
		else{
			//return late finish - early finish of this activity

			return (taskList.get(getTaskIndexByName(currentActivity)).getLateFinish() - taskList.get(getTaskIndexByName(currentActivity)).getEarlyFinish());

		}
	}

	//A method that returns a task whose name is specified as input
	public Task findTaskByName(String input){

		//return task using the getTaskIndexByName method.
		if(getTaskIndexByName(input) != -1){
			return taskList.get(getTaskIndexByName(input));
		}
		else{
			//return null;
			return null;
		}

	}

}