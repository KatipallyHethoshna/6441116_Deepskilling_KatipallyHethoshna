public class Task {
    int taskId;
    String taskName;
    String status;
    Task next;

    public Task(int id, String name, String stat) {
        this.taskId = id;
        this.taskName = name;
        this.status = stat;
        this.next = null;
    }

    public void showTask() {
        System.out.println("[" + taskId + "] " + taskName + " - " + status);
    }
}
