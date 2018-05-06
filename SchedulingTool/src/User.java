import java.util.Vector;

public class User {
    private String firstName;
    private String lastName;
    private int userID = 0;
    private Vector<int> projects = new Vector<int>();

    public User(String firstName, String lastName, int userID)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
    }

    public void addProject(int projectID)
    {
        projects.add(projectID);
    }

    public void removeProject(int projectID)
    {
        projects.remove((Integer) projectID);
    }
}