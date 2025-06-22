import java.util.Scanner;

public class TaskManager {
    private Task head;

    // Add task at end
    public void insertTask(int id, String name, String stat) {
        Task newTask = new Task(id, name, stat);
        if (head == null) {
            head = newTask;
        } else {
            Task current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTask;
        }
        System.out.println("Task added.");
    }

    // Display all tasks
    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks found.");
            return;
        }
        Task temp = head;
        while (temp != null) {
            temp.showTask();
            temp = temp.next;
        }
    }

    // Search by task ID
    public void findTask(int id) {
        Task temp = head;
        while (temp != null) {
            if (temp.taskId == id) {
                System.out.println("Task found:");
                temp.showTask();
                return;
            }
            temp = temp.next;
        }
        System.out.println("Task ID not found.");
    }

    // Delete by task ID
    public void removeTask(int id) {
        if (head == null) {
            System.out.println("No tasks to delete.");
            return;
        }

        if (head.taskId == id) {
            head = head.next;
            System.out.println("Task removed.");
            return;
        }

        Task prev = head;
        Task curr = head.next;

        while (curr != null) {
            if (curr.taskId == id) {
                prev.next = curr.next;
                System.out.println("Task removed.");
                return;
            }
            prev = curr;
            curr = curr.next;
        }
        System.out.println("Task not found.");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TaskManager list = new TaskManager();
        int option;

        do {
            System.out.println("\n1. Add Task\n2. View All Tasks\n3. Search Task\n4. Delete Task\n5. Exit");
            System.out.print("Choose: ");
            option = in.nextInt();
            in.nextLine(); // clear buffer

            switch (option) {
                case 1:
                    System.out.print("ID: ");
                    int id = in.nextInt();
                    in.nextLine();
                    System.out.print("Name: ");
                    String name = in.nextLine();
                    System.out.print("Status: ");
                    String status = in.nextLine();
                    list.insertTask(id, name, status);
                    break;
                case 2:
                    list.displayTasks();
                    break;
                case 3:
                    System.out.print("Search by ID: ");
                    list.findTask(in.nextInt());
                    break;
                case 4:
                    System.out.print("Delete by ID: ");
                    list.removeTask(in.nextInt());
                    break;
                case 5:
                    System.out.println("Closing system.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 5);

        in.close();
    }
}
