// I added a comment for coding

public abstract class SchedulingObject {
	
	private int ID = 0;
	private String name = "";

	public SchedulingObject(String newName){
		name = newName;
		ID = generateID();
	}
	
	public SchedulingObject(SchedulingObject toCopy) {
		this.ID = toCopy.ID;
		this.name = toCopy.name;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	protected abstract int generateID();
	
	public abstract String toFile();
	
	public String toString() {
		String retVal = "ID: " + this.getID() + " Name: " + this.getName() + " ";
		return retVal;
	}
}
