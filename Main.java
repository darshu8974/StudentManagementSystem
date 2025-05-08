import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Department: ");
                    String dept = sc.nextLine();
                    dao.addStudent(new Student(name, age, dept));
                    break;
                case 2:
                    List<Student> list = dao.getAllStudents();
                    for (Student s : list) {
                        System.out.println(s);
                    }
                    break;
                case 3:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    Student s = dao.getStudentById(id);
                    if (s != null) System.out.println(s);
                    else System.out.println("Student not found.");
                    break;
                case 4:
                    System.out.print("Enter ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String n = sc.nextLine();
                    System.out.print("New Age: ");
                    int a = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Department: ");
                    String d = sc.nextLine();
                    dao.updateStudent(new Student(uid, n, a, d));
                    break;
                case 5:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    dao.deleteStudent(did);
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
