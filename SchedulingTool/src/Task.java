
import java.time.*;
import java.time.temporal.ChronoUnit;
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

	private long earlyStart = 0;
    private long earlyFinish = 0;
    private long lateStart = 0;
    private long lateFinish = 0;

    
    public Task(String newName, String newOwner, LocalDate newStart, 
    		LocalDate newEnd, int parentID, long earlyStart, long lateStart)
    {
        super(newName);

        taskOwner = newOwner;
        //check dates valid
        if (newEnd.isAfter(newStart))
        {
            startDate = newStart;
            endDate = newEnd;
        }

        if (earlyStart <= lateStart)
        {
            this.earlyStart = earlyStart;
            this.lateStart = lateStart;
        }
        //set
        
        this.calculateDuration();
        this.setParentProjectID(parentID);
    }
    
 // This constructor also takes an id
    public Task(String newName, String newOwner, LocalDate newStart, 
    		LocalDate newEnd, int parentID,
                int newPrev, int newNext, long earlyStart, long lateStart)
    {
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
            this.earlyStart = earlyStart;
            this.lateStart = lateStart;
            setEarlyStart(earlyStart);
            setLateStart(lateStart);

        //set
        this.calculateDuration();
        this.setParentProjectID(parentID);
    }
    
    // This constructor also takes an id
    public Task(int id, String newName, String newOwner, LocalDate newStart, 
    		LocalDate newEnd, int parentID,
                int newPrev, int newNext, long earlyStart, long lateStart)
    {
        super(newName, id);

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


            this.earlyStart = earlyStart;
            this.lateStart = lateStart;
        //set
        this.calculateDuration();
        this.setParentProjectID(parentID);
    }
    
    public Task(int id, String newName, String newOwner, LocalDate newStart, 
    		LocalDate newEnd, int parentID, long earlyStart, long lateStart)
    {
        super(newName, id);

        taskOwner = newOwner;
        //check dates valid
        if (newEnd.isAfter(newStart))
        {
            startDate = newStart;
            endDate = newEnd;
        }
        

            this.earlyStart = earlyStart;
            this.lateStart = lateStart;


        //set
        this.calculateDuration();
        this.setParentProjectID(parentID);
    }
    
    public Task(String newName, String newOwner, LocalDate newStart, 
    		LocalDate newEnd, int parentID)
    {
        super(newName);

        taskOwner = newOwner;
        //check dates valid
        if (newEnd.isAfter(newStart))
        {
            startDate = newStart;
            endDate = newEnd;
        }

        if (earlyStart <= lateStart)
        {
            this.earlyStart = earlyStart;
            this.lateStart = lateStart;
        }
        //set
        this.calculateDuration();
        this.setParentProjectID(parentID);
    }
    
 // This constructor also takes an id
    public Task(String newName, String newOwner, LocalDate newStart, 
    		LocalDate newEnd, int parentID,
                int newPrev, int newNext)
    {
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

        if (earlyStart <= lateStart)
        {
            this.earlyStart = earlyStart;
            this.lateStart = lateStart;
        }
        //set
        this.calculateDuration();
        this.setParentProjectID(parentID);
    }
    
    // This constructor also takes an id
    public Task(int id, String newName, String newOwner, LocalDate newStart, 
    		LocalDate newEnd, int parentID,
                int newPrev, int newNext)
    {
        super(newName, id);

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

        if (earlyStart <= lateStart)
        {
            this.earlyStart = earlyStart;
            this.lateStart = lateStart;
        }
        //set
        this.calculateDuration();
        this.setParentProjectID(parentID);
    }
    
    public Task(int id, String newName, String newOwner, LocalDate newStart, 
    		LocalDate newEnd, int parentID)
    {
        super(newName, id);

        taskOwner = newOwner;
        //check dates valid
        if (newEnd.isAfter(newStart))
        {
            startDate = newStart;
            endDate = newEnd;
        }
        
        if (earlyStart <= lateStart)
        {
            this.earlyStart = earlyStart;
            this.lateStart = lateStart;
        }
        //set
        this.calculateDuration();
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
		if(endDate.isAfter(startDate)) {
			taskDuration = ChronoUnit.DAYS.between(startDate, endDate);
		}
		else {
			return;
		}
	}

	public long getTaskDuration() {
		return taskDuration;
	}
	
	public void setTaskDuration(long newTaskDuration) {
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
		String retVal;
		// The previous task id for the first task will be 0
		if (this.getPreviousTaskID() == 0)
        {
            retVal = "#" + this.getID() + "," + this.getName() + "," + this.getTaskOwner() + "," +
                    this.getTaskDuration() + "," + this.getParentProjectID() + "," + this.getPreviousTaskID()
                    + "," + this.getNextTaskID() + "," + this.getStartDate().toString()
                    + "," + this.getEndDate().toString() + "," + this.getEarlyStart() + "," + this.getLateStart() + "##";
        }
        else
        {
            retVal = "#" + this.getID() + "," + this.getName() + "," + this.getTaskOwner() + "," +
                    this.getTaskDuration() + "," + this.getParentProjectID() + "," + this.getPreviousTaskID()
                    + "," + this.getNextTaskID() + "," + this.getStartDate().toString()
                    + "," + this.getEndDate().toString() + "##";
        }
		return retVal;
	}

	@Override
	public String toString() 
	{
		return "ID: " + this.getID() + " Task: " + this.getName() +" Owner: " + taskOwner + ", Start date: " + startDate.toString() + ", End date: "
	         + endDate.toString() + ", Duration: " + this.getTaskDuration() + "\nPrevious Task: " + this.getPreviousTaskID()
		+ " Next Task: " + this.getNextTaskID();
		 
	}
}
