//name: Saad Rafique
//reg no: SP25-BCS-121

public class Student{
	String name;
	String email;
	float CGPA;

	Student	(String name, String email, float CGPA){
	this.name = name;
	this.email = email;
	this.CGPA = CGPA;

}	

}

void display() {
        System.out.println("Name: " +name);
        System.out.println("Email: " +email);
        System.out.println("CGPA: " +CGPA);
    }
}

public static void  main(String agrs[]){
	Student s1 = new Student("Ahmed", "ahmed@cuilahore.com", "3.8")

	s1.display();

}