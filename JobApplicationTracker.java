import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class JobApplicationTracker extends JFrame {
    private int totalApplications = 0;
    private int rejections = 0;
    private int interviews = 0;
    private int ghosted = 0;

    private JLabel totalCountLabel;
    private JLabel rejectionCountLabel;
    private JLabel interviewCountLabel;
    private JLabel ghostedCountLabel;
    private static final String DATA_FILE = "job_tracker_data.txt";

    public JobApplicationTracker() {
        loadData();
        createUI();
    }

    private void createUI() {
        setTitle("Job Application Tracker");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 10, 10));

        // Initialize Labels
        totalCountLabel = new JLabel("Total Applications: " + totalApplications);
        rejectionCountLabel = new JLabel("Rejections: " + rejections);
        interviewCountLabel = new JLabel("Interviews: " + interviews);
        ghostedCountLabel = new JLabel("Ghosted: " + ghosted);

        // Create Buttons
        JButton addButton = new JButton("Add Application");
        JButton subtractButton = new JButton("Subtract Application");
        JButton rejectButton = new JButton("Add Rejection");
        JButton unRejectButton = new JButton("Subtract Rejection");
        JButton interviewButton = new JButton("Add Interview");
        JButton unInterviewButton = new JButton("Subtract Interview");
        JButton ghostButton = new JButton("Add Ghosted");
        JButton unGhostButton = new JButton("Subtract Ghosted");

        // Add action listeners for buttons
        addButton.addActionListener(e -> {
            totalApplications++;
            updateLabels();
        });

        subtractButton.addActionListener(e -> {
            if (totalApplications > 0) totalApplications--;
            updateLabels();
        });

        rejectButton.addActionListener(e -> {
            rejections++;
            updateLabels();
        });

        unRejectButton.addActionListener(e -> {
            if (rejections > 0) rejections--;
            updateLabels();
        });

        interviewButton.addActionListener(e -> {
            interviews++;
            updateLabels();
        });

        unInterviewButton.addActionListener(e -> {
            if (interviews > 0) interviews--;
            updateLabels();
        });

        ghostButton.addActionListener(e -> {
            ghosted++;
            updateLabels();
        });

        unGhostButton.addActionListener(e -> {
            if (ghosted > 0) ghosted--;
            updateLabels();
        });

        // Add components to the frame
        add(totalCountLabel);
        add(addButton);
        add(subtractButton);
        add(rejectionCountLabel);
        add(rejectButton);
        add(unRejectButton);
        add(interviewCountLabel);
        add(interviewButton);
        add(unInterviewButton);
        add(ghostedCountLabel);
        add(ghostButton);
        add(unGhostButton);

        // Save data on window closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveData();
            }
        });
    }

    private void updateLabels() {
        totalCountLabel.setText("Total Applications: " + totalApplications);
        rejectionCountLabel.setText("Rejections: " + rejections);
        interviewCountLabel.setText("Interviews: " + interviews);
        ghostedCountLabel.setText("Ghosted: " + ghosted);
    }

    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            totalApplications = Integer.parseInt(reader.readLine());
            rejections = Integer.parseInt(reader.readLine());
            interviews = Integer.parseInt(reader.readLine());
            ghosted = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            // Defaults are already set to 0
        }
    }

    private void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            writer.println(totalApplications);
            writer.println(rejections);
            writer.println(interviews);
            writer.println(ghosted);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to save data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JobApplicationTracker().setVisible(true);
        });
    }
}
