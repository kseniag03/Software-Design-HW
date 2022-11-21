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

import javax.swing.*;
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
        students.add(new Student("John", "Doe"));
        students.add(new Student("Jane", "Doe"));

        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        for (Student student : students) {
            JSONObject studentJson = new JSONObject();
            studentJson.put("firstName", student.getFirstName());
            studentJson.put("lastName", student.getLastName());
            studentJson.put("grade", student.getGrade());
            studentJson.put("present", student.isPresent());
            array.put(studentJson);
        }

        json.put("students", array);
        //System.out.println(json.toString());
        saveStudents(json);

        loadStudents(students);

        for (Student student : students) {
            System.out.println(student);
        }

        //new Frontend();


        while(true) {
            System.out.print("Put your command: ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command) {
                case "/r":
                    System.out.println(returnRandomStudent(students));
                    break;
                case "/l":
                    for (Student student : students) {
                        if (student.getGrade() != 0) {
                            System.out.println(student);
                        }
                    }
                    break;
                case "/h":
                    System.out.println("Commands:");
                    System.out.println("/r – selects random student, asks if present");
                    System.out.println("/l – list of students who received a grade");
                    System.out.println("/h – help, lists commands and how to use them");
                    System.out.println("/e – exit");
                    break;
                case "/e":
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }
    private static void loadStudents(ArrayList<Student> students) {
        File file = new File("students.json");

        String fileContent = "";

        if (file.exists()) {
            // load students

            try (FileInputStream stream = new FileInputStream(file)) {
                int i = -1;
                while((i = stream.read()) != -1) {
                    //System.out.print((char)i);
                    fileContent += (char)i;
                    //fileContent.append((char)i);
                }
            } catch(IOException ex){
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println(fileContent);

            JSONObject parser = new JSONObject(fileContent);
            var getStudents = (JSONArray)parser.get("students");

            for (int i = 0; i < getStudents.length(); i++) {
                System.out.println(getStudents.get(i));
            }

            for (int i = 0; i < getStudents.length(); i++) {
                var student = getStudents.getJSONObject(i); //getStudents.get(i);
                System.out.println(student);

                String firstName = student.getString("firstName");
                String lastName = student.getString("lastName");
                int grade = student.getInt("grade");
                boolean present = student.getBoolean("present");

                Student st = new Student(firstName, lastName);
                st.setGrade(grade);
                st.setPresent(present);
                students.add(st);
            }
        }
    }
    private static void saveStudents(JSONObject json) {
        // save students
        try (FileOutputStream stream = new FileOutputStream("students.json")) {
            stream.write(json.toString().getBytes());
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    private static Student returnRandomStudent(ArrayList<Student> students) {
        Random random = new Random();
        var student = students.get(random.nextInt(students.size()));
        student.setPresent(true);
        student.setGrade(random.nextInt(9) + 1);
        return student;
    }
}

// connecting library + json serialization + filestream