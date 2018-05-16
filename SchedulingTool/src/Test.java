
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
		System.out.println(testProject2.toString());
		

	}

}
