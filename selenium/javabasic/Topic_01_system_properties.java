package javabasic;

public class Topic_01_system_properties {
	

	public static void main(String[] args) {
		String projectPath = System.getProperty("user.dir");
		
		System.out.println(projectPath);

	}

}
