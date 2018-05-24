
import java.time.*;
import java.util.Random;

public class Task extends SchedulingObject {
	
	//data members
	private String taskOwner = null; // The owner of the task
	private int previousTaskID = 0; //the ID of the previous task
	private int nextTaskID = 0;
	private long taskDuration = 0;
	private int parentProjectID = 0;
	private LocalDate startDate = null;
	private LocalDate endDate = null;

	//Variables that are used for Critical path Calculations

	private long earlyStart;
    private long earlyFinish;
    private long lateStart;
    private long lateFinish;

	public Task(String newName, String newOwner, LocalDate newStart, LocalDate newEnd, int parentID, int newPrev, int newNext) {
		super(newName);
		taskOwner = newOwner;
	    //check dates valid
		if (newEnd.isAfter(newStart))
		{
			startDate = newStart;
			endDate = newEnd;
		}
		//check projectIDs valid
		if (newNext > newPrev)
		{
			previousTaskID = newPrev;
			nextTaskID = newNext;
		}
		//set
		
		this.setParentProjectID(parentID);
	}
	
	public Task(String newName, String newOwner, LocalDate newStart, LocalDate newEnd, int parentID, int newPrev) {
		super(newName);
		taskOwner = newOwner;
		//check dates valid
		if (newEnd.isAfter(newStart))
		{
			startDate = newStart;
			endDate = newEnd;
		}
		//check projectIDs valid
		if (nextTaskID > newPrev)
		{
			previousTaskID = newPrev;
		}
		
		this.setParentProjectID(parentID);
	}
	
	public Task(String newName, String newOwner, LocalDate newStart, LocalDate newEnd, int parentID) {
		super(newName);
		taskOwner = newOwner;
		//check dates valid
		if (newEnd.isAfter(newStart))
		{
			startDate = newStart;
			endDate = newEnd;
		}
		
		this.setParentProjectID(parentID);
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
		this.setStartDate(toCopy.getStartDate());
		this.setEndDate(toCopy.getEndDate());
		this.setParentProjectID(toCopy.getParentProjectID());
	}

	public String getTaskOwner() {
		return taskOwner;
	}

	public void setTaskOwner(String taskOwner) {
		this.taskOwner = taskOwner;
	}
	
	public int getParentProjectID() {
		return parentProjectID;
	}
	
	public void setParentProjectID(int newParentID) {
		parentProjectID = newParentID;
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
	
	public void calculateDuration() {
		taskDuration = Duration.between(endDate.atStartOfDay(), startDate.atStartOfDay()).toDays();
	}

	public long getTaskDuration() {
		return taskDuration;
	}
	
	private void setTaskDuration(long newTaskDuration) {
		this.taskDuration = newTaskDuration;
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


	//Accessors and Mutators for the critical path variables

	public long getEarlyStart(){
		return earlyStart;
	}

	public void setEarlyStart(long input){
		earlyStart = input;
	}

	public long getLateStart(){

		return lateStart;
	}

	public void setLateStart(long input){
		lateStart = input;
	}

	public long getEarlyFinish(){
		return earlyFinish;
	}

	public void setEarlyFinish(long input){
		earlyFinish = input;
	}

	public long getLateFinish(){
		return lateFinish;
	}

	public void setLateFinish(long input){
		lateFinish = input;
	}

	//DOES NOT CHECK FOR DUPES
	@Override
	protected int generateID() {
		//task IDs are 5 to 6 digits
		Random idGen = new Random();
		int id = idGen.nextInt(990000) + 10000;
		return id;
	}
	
	//#ID,NAME,OWNER,DURATION,PROJECTID,PREVID,NEXTID,STARTDATE,ENDDATE##
	
	@Override
	public String toFile() {
		this.calculateDuration();
		String retVal = "#" + this.getID() + "," + this.getName() + "," + this.getTaskOwner() + "," + 
				this.getTaskDuration() + "," + this.getParentProjectID() + "," + this.getPreviousTaskID()
				 + "," + this.getNextTaskID() + "," + this.getStartDate().toString()
				 + "," + this.getEndDate().toString() + "##";
		return retVal;
	}

	@Override
	public String toString() 
	{
		return "Task: " + this.getName() +" Owner: " + taskOwner + ", Start date: " + startDate.toString() + ", End date: "
	         + endDate.toString() + ", Duration: " + this.getTaskDuration();
		 
	}
}
