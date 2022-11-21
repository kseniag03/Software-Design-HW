import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Frontend implements ActionListener {
    private final JFrame frame = new JFrame();
    private final JButton rButton = new JButton();
    private final JButton lButton = new JButton();
    private final JButton hButton = new JButton();
    private final JButton eButton = new JButton();
    private final JTextArea textField = new JTextArea();
    private final JPanel textPanel = new JPanel();

    public Frontend() {
        setupFront();
        setupInputField();
        setupButtons();
    }

    private void setupFront() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Student Attendance Sheet");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setupButtons() {
        var buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));
        rButton.setText("Get random student");
        lButton.setText("Get list of graded students");
        hButton.setText("Help");
        eButton.setText("Exit");
        for (var button : new JButton[]{rButton, lButton, hButton, eButton}) {
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupInputField() {
        textField.setBackground(Color.GRAY);
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textField.setText("output");
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textField, BorderLayout.CENTER);
        frame.add(textPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rButton) {
            textField.setText("rButton");
        } else if (e.getSource() == lButton) {
            textField.setText("lButton");
        } else if (e.getSource() == hButton) {
            textField.setText(Constants.HELP);
        } else if (e.getSource() == eButton) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
