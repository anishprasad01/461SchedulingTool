
public abstract class SchedulingObject {
	
	private int ID;
	private String name;

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
	
	public String toString() {
		String retVal = "ID: " + this.getID() + " Name: " + this.getName() + " ";
		return retVal;
	}
}
