public class Student {
    private static int counter = 0;
    private final int studentId;
    private final String firstName;
    private final String lastName;
    private int grade;
    private boolean present;

    public Student(String firstName, String lastName) {
        studentId = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = 0;
        this.present = false;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return studentId + " " + getName() + ", grade: " + grade + ", present: " + present;
    }
}
