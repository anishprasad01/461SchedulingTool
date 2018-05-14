//test
import java.time.*;
import java.util.Random;

public class Task extends SchedulingObject {
	
	//data members
	private String taskOwner = null; // The owner of the task
	private int previousTaskID = 0;
	private int nextTaskID = 0;
	private int taskDuration = 0;
	private LocalDate startDate = null;
	private LocalDate endDate = null;
	

	public Task(String newName, String newOwner, LocalDate newStart, LocalDate newEnd, int newPrev, int newNext) {
		super(newName);
		taskOwner = newOwner;
	    //check dates valid
		//check projectIDs valid
		//set
	}
	
	public Task(String newName, String newOwner, LocalDate newStart, LocalDate newEnd, int newPrev) {
		super(newName);
		// TODO Auto-generated constructor stub
	}

	public Task(SchedulingObject object) {
		super(object);
		
		Task toCopy = null;
		
		if(object instanceof Task) {
			toCopy = (Task) object;
		}
		
		this.setTaskOwner(toCopy.getTaskOwner());
		this.setTaskDuration(toCopy.getTaskDuration());
		this.setPreviousTaskID(toCopy.getPreviousTaskID());
		this.setNextTaskID(toCopy.getNextTaskID());
		
	}

	public String getTaskOwner() {
		return taskOwner;
	}

	public void setTaskOwner(String taskOwner) {
		this.taskOwner = taskOwner;
	}

	public int getPreviousTaskID() {
		return previousTaskID;
	}

	public void setPreviousTaskID(int previousTaskID) {
		this.previousTaskID = previousTaskID;
	}

	public int getNextTaskID() {
		return nextTaskID;
	}

	public void setNextTaskID(int nextTaskID) {
		this.nextTaskID = nextTaskID;
	}

	public int getTaskDuration() {
		return taskDuration;
	}

	public void setTaskDuration(int taskDuration) {
		this.taskDuration = taskDuration;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	//DOES NOT CHECK FOR DUPES
	@Override
	protected int generateID() {
		//task IDs are 5 to 6 digits
		Random idGen = new Random();
		int id = idGen.nextInt(990000) + 10000;
		return id;
	}

}
