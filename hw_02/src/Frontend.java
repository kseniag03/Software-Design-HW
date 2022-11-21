import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Frontend implements ActionListener {
    private static final String FILE_NAME = "students.json";
    private static final String FILE_PATH = "src" + File.separator + FILE_NAME;
    private static final String HELP = "Commands: "
            + " /r – selects random student, asks if present"
            + " /l – list of students who received a grade"
            + " /h – help, lists commands and how to use them"
            + " /e – exit";
    private static final String[] OPTIONS = {"Yes", "No"};
    private static final String[] GRADES = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static final String[] COMMANDS = {"/r", "/l", "/h", "/e"};
    private static final String[] COMMANDS_DESCRIPTION = {"selects random student, asks if present",
            "list of students who received a grade", "help, lists commands and how to use them", "exit"};



    private final JFrame frame = new JFrame();
    private final JButton rButton = new JButton();
    private final JButton lButton = new JButton();
    private final JButton hButton = new JButton();
    private final JButton eButton = new JButton();
    private final JTextField inputField = new JTextField();

    public Frontend() {
        setupFront();
        setupInputField();
        setupButtons();
    }

    private void setupFront() {
        frame.setSize(400, 400);
        //frame.setLayout(null);

        frame.setTitle("Student Attendance Sheet");
        //frame.getContentPane().setLayout(null);
        frame.setBounds(200,200,400,400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        //frame.getContentPane().add(new JLabel("Hello World"), BorderLayout.CENTER);

        //frame.getContentPane().add(new JButton(Action));

        //frame.pack();
        frame.setVisible(true);
    }

    private void setupButtons() {
        var buttonStack = new JPanel();

        rButton.setText("Get random student");
        lButton.setText("Get list of graded students");
        hButton.setText("Help"); // why?..
        eButton.setText("Exit");

        buttonStack.add(rButton);
        buttonStack.add(lButton);
        buttonStack.add(hButton);
        buttonStack.add(eButton);

        int width = inputField.getSize().width;
        int height = inputField.getSize().height;
        int x = (int)(Math.random()*(width));
        int y = (int)(Math.random()*(height + 30));
        buttonStack.setLocation(x,y);

        buttonStack.setSize(200, 200);

        //rButton.setBounds(100, 100, 100, 100);

        frame.add(buttonStack);
        //frame.add(rButton);
    }

    private void setupInputField() {
        inputField.setBounds(100, 100, 300, 300);
        inputField.setBackground(Color.LIGHT_GRAY);
        inputField.setForeground(Color.BLUE);
        inputField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        inputField.setHorizontalAlignment(JTextField.LEFT);
        inputField.setText("...");

        frame.add(inputField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rButton) {
            inputField.setText("rButton");
        } else if (e.getSource() == lButton) {
            inputField.setText("lButton");
        } else if (e.getSource() == hButton) {
            inputField.setText("hButton");
        } else if (e.getSource() == eButton) {
            inputField.setText("eButton");
        }
    }

}
