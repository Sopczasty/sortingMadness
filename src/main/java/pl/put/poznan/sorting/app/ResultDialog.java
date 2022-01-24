package pl.put.poznan.sorting.app;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

/**
 * Dialog displaying results of sorting.
 */
public class ResultDialog extends JDialog implements ActionListener {
    // Labels
    final JLabel resultLabel = new JLabel("Posortowane dane:");
    final JLabel measuresLabel = new JLabel("Pomiary czasów:");
    final JLabel statisticsLabel = new JLabel("Statystyki:");
    // Panel for execution time measurement display
    final JPanel measurementsPanel = new JPanel();
    // Layout for panel above
    final GridLayout gl = new GridLayout();
    // Panel for execution statistics
    final JPanel statisticsPanel = new JPanel();
    // Layout for panel above
    final GridLayout gls = new GridLayout();
    // Button for closing the dialog
    final JButton okButton = new JButton("OK");

    // Text area with result (for copying and pasting)
    private final JTextArea resultArea = new JTextArea();

    /**
     * Constructor for result dialog
     * @param owner root frame
     * @param width width of the dialog
     * @param height height of the dialog
     */
    public ResultDialog(JFrame owner, int width, int height) {
        super(owner, "Rezultat", true);
        setSize(width, height);
        setLayout(null);

        // TODO: Maybe there is a better way to scale objects? Maybe GridLayout?
        // Adding constant GUI elements
        resultLabel.setBounds((width / 2) - 60, 10, 120, 20);
        resultArea.setBounds(20, 30, width - 60, 50);
        okButton.setBounds(width/2 - 30, 520, 60, 25);

        // Adding panel with measurements
        measurementsPanel.setBounds(20, 90, 300, 210);
        measuresLabel.setBounds((width / 2) - 60, 10, 120, 20);
        // Set to box layout so the panel will adjust the size of labels by itself
        measurementsPanel.setLayout(gl);

        // Adding panel with statistics
        statisticsPanel.setBounds(20, 320, width - 60, 180);
        statisticsLabel.setBounds((width / 2) - 60, 300, width - 60, 20);
        // Set to box layout so the panel will adjust the size of labels by itself
        statisticsPanel.setLayout(gls);


        // Prevent user from editing output data
        resultArea.setEditable(false);

        // Adding GUI elements to panel
        add(resultLabel);
        add(resultArea);
        add(measuresLabel);
        add(measurementsPanel);
        add(statisticsLabel);
        add(statisticsPanel);
        add(okButton);

        // Adding action handler to closing button
        okButton.addActionListener(this);
    }


    /**
     * Updates the contents of the dialog
     * @param result integer array of results
     * @param measurements measurements of algorithms
     */
    public void setResult(String result, Map<String, Long> measurements, Map<String, Object> statistics) {
        // Display the result
        resultArea.setText(result);

        // Remove previous labels
        measurementsPanel.removeAll();
        measurementsPanel.add(measuresLabel);
        statisticsPanel.removeAll();
        statisticsPanel.add(statisticsLabel);

        // Resize measurements panel to fit new labels
        gl.setColumns(1);
        gl.setRows(measurements.size() + 1);
        gls.setColumns(1);
        gls.setRows(statistics.size() + 1);

        // Add new labels
        for (Map.Entry<String, Long> entry : measurements.entrySet()) {
            measurementsPanel.add(new JLabel(entry.getKey() + " : " + entry.getValue() + "ms"));
        }
        for (Map.Entry<String, Object> entry: statistics.entrySet()){
           statisticsPanel.add(new JLabel(entry.getKey() + " : " + entry.getValue()));
        }
        // Update view
        measurementsPanel.validate();
        statisticsPanel.validate();
    }

    /**
     * Action listener for buttons
     * @param e event (i.e. clicked button)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }
}
