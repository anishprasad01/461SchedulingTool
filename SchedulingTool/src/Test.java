import java.time.*;
public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Project testProject1 = new Project("TestProject1", "AP");
		System.out.println(testProject1.toString());
		
	    Project testProject2 = new Project(null, null);
		System.out.println(testProject2.toString());
		
		testProject2.setName("TestProject2");
		testProject2.addTask(new Task("Code the program", "Rahil", LocalDate.of(2018, 5, 19),
                LocalDate.of(2018, 5, 30), 0, 2));

		testProject2.addTask(new Task("Test The Program", "Hossam", LocalDate.of(2018, 5, 27),
				LocalDate.of(2018, 5, 30), 0, 2));


		System.out.println(testProject2.toString());

		//Test that setup early start, early finish, etc


        //remove the 2 to search for task correctly, add the 2 to see what the code does when task is not found
        Task tempTask = testProject2.FindTaskByName("Code the program");
        if(tempTask == null){
        	System.out.println("Could not find specified task");
		}
		else{
        	tempTask.calculateDuration();
			testProject2.setupCP(1,3);
			System.out.println("Found task and it will be on: " + tempTask.getStartDate());
        	System.out.println("It has early Start: " + tempTask.getEarlyStart());
        	System.out.println("It has late Start: " + tempTask.getLateStart());
        	System.out.println("Its Duration: " + tempTask.getTaskDuration());
        	System.out.println("It has early finish: "+ tempTask.getEarlyFinish());
        	System.out.println("It has late finish: " + tempTask.getLateFinish());
        	System.out.println("Total float: " + testProject2.getTotalFloat(tempTask.getName()) );
            System.out.println("Task Owner: " + tempTask.getTaskOwner());
            System.out.println("Parent Project Owner ID: " + tempTask.getParentProjectID());
            System.out.println("Previous Task ID: " + tempTask.getPreviousTaskID());
            System.out.println("Next Task ID: "+ tempTask.getNextTaskID());
            System.out.println("Start Date: "+ tempTask.getStartDate());
            System.out.println("End Date: "+ tempTask.getEndDate());


		}

		//testProject2.printAllTaskNames();

		System.out.println(testProject2.getManagerName());
		System.out.println(testProject2.getProjectName());
		System.out.println(testProject2.getID());


	}




}
