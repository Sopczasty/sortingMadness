package pl.put.poznan.sorting.app;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Dialog displaying results of sorting.
 */
public class ResultDialog extends JDialog implements ActionListener {
    // Labels
    final JLabel resultLabel = new JLabel("Posortowane dane:");
    final JLabel measuresLabel = new JLabel("Pomiary czasów:");
    // Panel for execution time measurement display
    final JPanel measurementsPanel = new JPanel();
    // Layout for panel above
    final GridLayout gl = new GridLayout();
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
        resultLabel.setBounds(10 + (width / 4), 10, width - 60, 20);
        resultArea.setBounds(20, 20 + (height / 10), width - 60, 60);
        okButton.setBounds(width / 3, height - 80, width / 3, 25);

        // Adding panel with measurements
        measurementsPanel.setBounds(20, 90 + (height / 10), width - 60, height - 240);
        measuresLabel.setBounds(20, 0, width - 60, 20);
        // Set to box layout so the panel will adjust the size of labels by itself
        measurementsPanel.setLayout(gl);
        add(measurementsPanel);

        // Prevent user from editing output data
        resultArea.setEditable(false);

        // Adding GUI elements to panel
        add(resultLabel);
        add(okButton);
        add(resultArea);

        // Adding action handler to closing button
        okButton.addActionListener(this);
    }

    /**
     * Converts integer array to string separated by comma
     * @param input integer array
     * @return string of merged input array
     */
    private String toString(int[] input) {
        if (input.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length - 1; i++) {
            sb.append(input[i]);
            sb.append(",");
        }
        sb.append(input[input.length - 1]);
        return sb.toString();
    }

    /**
     * Updates the contents of the dialog
     * @param result integer array of results
     * @param measurements measurements of algorithms
     */
    public void setResult(int[] result, Map<String, Long> measurements) {
        // Display the result
        String text = toString(result);
        resultArea.setText(text);

        // Remove previous labels
        measurementsPanel.removeAll();
        measurementsPanel.add(measuresLabel);

        // Resize measurements panel to fit new labels
        gl.setColumns(1);
        gl.setRows(measurements.size() + 1);

        // Add new labels
        for (Map.Entry<String, Long> entry : measurements.entrySet()) {
            measurementsPanel.add(new JLabel(entry.getKey() + " : " + entry.getValue() + "ms"));
        }
        // Update view
        measurementsPanel.validate();
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
