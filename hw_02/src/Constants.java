public class Constants {
    private Constants() {
    }
    public static final String JSON_FILE_NAME = "students.json";
    public static final String HELP = """
            Commands:
             /r – selects random student, asks if present
             /l – list of students who received a grade
             /h – help, lists commands and how to use them
             /e – exit""";
    public static final String FINISH = "Program finished\n";
    public static final String UNKNOWN_COMMAND = "Unknown command, try again";
}
