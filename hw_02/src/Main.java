// Homework #2

// Create an application to chose random student to answer a question.
// Once a student is chosen teacher has an option to flag student as present.
// After that teacher can grade the answer from 1 to 10.
// Student can be randomly chosen once per lesson.

// Commands:
// /r – selects random student, asks if present
// /l – list of students who received a grade
// /h – help, lists commands and how to use them
// /e – exit

// Bonus points:
// Store students and grades somewhere somehow
// Create some kind of frontend

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        loadStudents(students);

        new Frontend();

        while (true) {
            System.out.print("Put your command: ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command) {
                case "/r":
                    var st = returnRandomStudent(students);
                    System.out.println("Student: " + st.getName()
                            + " received a grade: " + st.getGrade());
                    saveStudents(students);
                    break;
                case "/l":
                    for (Student student : students) {
                        if (student.getGrade() != 0) {
                            System.out.println(student);
                        }
                    }
                    break;
                case "/h":
                    System.out.println(Constants.HELP);
                    break;
                case "/e":
                    System.out.println(Constants.FINISH);
                    return;
                default:
                    System.out.println(Constants.UNKNOWN_COMMAND);
            }
        }
    }

    private static boolean isStudentInList(int id, ArrayList<Student> students) {
        for (var st : students) {
            if (st.getStudentId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reads students from JSON file and adds them to the list
     * @param students list of students
     */
    private static void loadStudents(ArrayList<Student> students) {
        var file = new File(Constants.JSON_FILE_NAME);
        var fileContent = new StringBuilder();
        if (file.exists()) {
            try (var stream = new FileInputStream(file)) {
                int i;
                while((i = stream.read()) != -1) {
                    fileContent.append((char) i);
                }
            } catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            var parser = new JSONObject(fileContent.toString());
            var getStudents = (JSONArray) parser.get("students");
            for (var i = 0; i < getStudents.length(); i++) {
                var student = getStudents.getJSONObject(i);
                var id = student.getInt("id");
                if (isStudentInList(id, students)) {
                    continue;
                }
                var firstName = student.getString("firstName");
                var lastName = student.getString("lastName");
                var grade = student.getInt("grade");
                var present = student.getBoolean("present");

                var st = new Student(firstName, lastName);
                st.setGrade(grade);
                st.setPresent(present);
                students.add(st);
            }
        }
    }

    /**
     * Serializes students list and saves it to JSON file
     * @param students list of students
     */
    private static void saveStudents(ArrayList<Student> students) {
        var json = new JSONObject();
        var array = new JSONArray();
        for (var student : students) {
            var studentJson = new JSONObject();
            studentJson.put("id", student.getStudentId());
            studentJson.put("firstName", student.getFirstName());
            studentJson.put("lastName", student.getLastName());
            studentJson.put("grade", student.getGrade());
            studentJson.put("present", student.isPresent());
            array.put(studentJson);
        }
        json.put("students", array);
        try (var stream = new FileOutputStream(Constants.JSON_FILE_NAME)) {
            stream.write(json.toString().getBytes());
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Returns a random student from the list,
     * if the student is not present,
     * marks him or her present and sets a random grade,
     * else chooses the nearest not present student
     * (if there is no not present student, marks will be rewritten)
     * @param students list of students
     * @return random student
     */
    private static Student returnRandomStudent(ArrayList<Student> students) {
        var random = new Random();
        var student = students.get(random.nextInt(students.size()));
        if (student.isPresent()) {
            for (var st : students) {
                if (!st.isPresent()) {
                    student = st;
                    break;
                }
            }
        }
        student.setPresent(true);
        student.setGrade(random.nextInt(9) + 1);
        return student;
    }
}

// new: connecting library + JsonSerialization + FileStream + JFrame