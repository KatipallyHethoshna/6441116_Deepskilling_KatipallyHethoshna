public class MVCTest {
    public static void main(String[] args) {
        // Create model
        Student student = new Student("Hethoshna", "S1234", "A");

        // Create view
        StudentView view = new StudentView();

        // Create controller
        StudentController controller = new StudentController(student, view);

        // Display initial details
        controller.updateView();

        // Modify data through controller
        controller.setStudentName("Reddy");
        controller.setStudentGrade("A+");

        // Display updated details
        controller.updateView();
    }
}
