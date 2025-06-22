// EmployeeManager.java
import java.util.Scanner;

public class EmployeeManager {
    private Employee[] employees;
    private int count;

    public EmployeeManager(int capacity) {
        employees = new Employee[capacity];
        count = 0;
    }

    public void addEmployee(Employee emp) {
        if (count < employees.length) {
            employees[count++] = emp;
            System.out.println("Employee added.");
        } else {
            System.out.println("No space to add more employees.");
        }
    }

    public void viewEmployees() {
        if (count == 0) {
            System.out.println("No employees to show.");
            return;
        }
        for (int i = 0; i < count; i++) {
            employees[i].showDetails();
        }
    }

    public void searchById(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                System.out.println("Employee Found:");
                employees[i].showDetails();
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public void deleteById(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--count] = null;
                System.out.println("Employee deleted.");
                return;
            }
        }
        System.out.println("Employee not found to delete.");
    }

    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager(10);
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Employee\n2. View Employees\n3. Search Employee\n4. Delete Employee\n5. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // clear buffer
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Position: ");
                    String role = sc.nextLine();
                    System.out.print("Salary: ");
                    double salary = sc.nextDouble();
                    manager.addEmployee(new Employee(id, name, role, salary));
                    break;
                case 2:
                    manager.viewEmployees();
                    break;
                case 3:
                    System.out.print("Enter ID to search: ");
                    manager.searchById(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Enter ID to delete: ");
                    manager.deleteById(sc.nextInt());
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        sc.close();
    }
}
