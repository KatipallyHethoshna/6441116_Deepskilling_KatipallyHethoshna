// Employee.java
public class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    public Employee(int id, String name, String position, double salary) {
        this.employeeId = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public void showDetails() {
        System.out.println("ID: " + employeeId + ", Name: " + name + ", Role: " + position + ", Salary: Rs" + salary);
    }
}
