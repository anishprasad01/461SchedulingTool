
import java.time.*;
import java.util.Random;

public class Task extends SchedulingObject {
	
	//data members
	private int projectID = 0;
	private String taskOwner = null;
	private int previousTaskID = 0;
	private int nextTaskID = 0;
	private int taskDuration = 0;
	private LocalDate startDate = null;
	private LocalDate endDate = null;
	

	public Task(String newName) {
		super(newName);
		// TODO Auto-generated constructor stub
	}

	public Task(SchedulingObject toCopy) {
		super(toCopy);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int generateID() {
		//task IDs are 5 to 6 digits
		Random idGen = new Random();
		int id = idGen.nextInt(990000) + 10000;
		return id;
	}

}
