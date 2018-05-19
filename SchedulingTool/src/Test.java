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
		System.out.println(testProject2.toString());


	}

}
