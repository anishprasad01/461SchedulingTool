import java.time.*;
import java.util.*;

public class Project extends SchedulingObject {

	private ArrayList<Task> taskList = new ArrayList<Task>();

	public Project(String newName) {
		super(newName);
	}

	public boolean addTask(Task input) {
		if(input.getClass() == Task.class) {
			this.taskList.add(input);
			return true;
		}
		else {
			return false;
		}
	}

	public String getProjectName() {
		return super.getName();
	}

	public void setProjectName(String input) {
		if (input instanceof String) {
			super.setName(input);
		}
	}

	/*public void addTaskByName(String input) {
		Task newTask = new Task(input);
		this.taskList.add(newTask);
	}*/

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
		for (int i = 0; i < taskList.size(); i++) {
			System.console().writer()
					.print("Task " + i + ": " + taskList.get(i).getName() + " ID: " + taskList.get(i).getID());
		}
	}

	@Override
	protected int generateID() {
		//User IDs are 4 digits
		Random idGen = new Random();
		int id = idGen.nextInt(900) + 100;
		return id;
	}

}
