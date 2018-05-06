
import java.time.*;

public class Task extends SchedulingObject {
	
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
		// TODO Auto-generated method stub
		return 0;
	}

}
