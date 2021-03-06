package pl.put.poznan.sorting.app;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

/**
 * Main application class with GUI.
 */
public class App extends JFrame implements ActionListener {
    // GUI will split input based on this delimiter
    final String delimiter = ",";
    final JLabel inputLabel = new JLabel("Dane");

    // List of algorithms to sort with
    private final ArrayList<String> algorithms = new ArrayList<>();
    private SortingMadness madness;

    // GUI elements
    private final JButton sortButton = new JButton("Sortuj");
    private final JCheckBox bubbleBox = new JCheckBox("Bubble Sort");
    private final JCheckBox insertionBox = new JCheckBox("Insertion Sort");
    private final JCheckBox selectionBox = new JCheckBox("Selection Sort");
    private final JCheckBox quickBox = new JCheckBox("Quick Sort");
    private final JCheckBox heapBox = new JCheckBox("Heap Sort");
    private final JCheckBox mergeBox = new JCheckBox("Merge Sort");
    private final JCheckBox autoBox = new JCheckBox("Automatic Sort");
    private final JTextField inputArea = new JTextField(5);
    private final ResultDialog resultDialog = new ResultDialog(this, 400, 600);


    /**
     * Constructor for GUI application.
     */
    public App() {
        setTitle("SortingMadness");
        setSize(460, 405);
        setLayout(null);

        // Add buttons to Panel
        add(sortButton);
        add(bubbleBox);
        add(insertionBox);
        add(selectionBox);
        add(quickBox);
        add(heapBox);
        add(mergeBox);
        add(autoBox);
        add(inputArea);
        add(inputLabel);

        // Set sizes of GUI elements
        sortButton.setBounds(175, 265, 100, 20);
        inputLabel.setBounds(210, 20, 100, 25);
        inputArea.setBounds(20, 55, 405, 20);
        bubbleBox.setBounds(175, 75, 100, 25);
        insertionBox.setBounds(170, 100, 110, 25);
        selectionBox.setBounds(170, 130, 110, 20);
        quickBox.setBounds(180, 155, 100, 25);
        heapBox.setBounds(180, 180, 100, 25);
        mergeBox.setBounds(180, 205, 100, 25);
        autoBox.setBounds(170, 230, 120, 25);

        // Add action listeners to GUI elements
        sortButton.addActionListener(this);
        inputArea.addActionListener(this);
        bubbleBox.addActionListener(this);
        insertionBox.addActionListener(this);
        selectionBox.addActionListener(this);
        quickBox.addActionListener(this);
        heapBox.addActionListener(this);
        mergeBox.addActionListener(this);
        autoBox.addActionListener(this);
    }

    /**
     * Convert array of objects into string
     * @param data input array of objects
     * @return string containing concatenated values separated by comma
     */
    private String toString(Object [] data) {
        String result = "";
        for (int i = 0; i < data.length - 1; i++) {
            result += data[i].toString();
            result += ", ";
        }
        result += data[data.length - 1].toString();
        return result;
    }

    /**
     * Split input data into array of strings, sort them, fetch
     * execution times and display a modal with result.
     * @param data input data as string
     */
    private void invokeSort(String data) {
        madness = new SortingMadness.PrimitiveBuilder(algorithms.toArray(new String[0]))
                .convertData(data)
                .direction("asc")
                .build();

        Object[] output = madness.getResult();
        resultDialog.setResult(toString(output), madness.getMeasurements(), madness.getStatistics());
        resultDialog.setVisible(true);
    }

    /**
     * Action listener for all the buttons, calls their respective actions.
     * @param e event (i.e. pressed button)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == sortButton && !algorithms.isEmpty()) {
            if (inputArea.getText() != null && inputArea.getText().length() > 0)
                invokeSort(inputArea.getText());
        }
        else if (source == bubbleBox) {
            if (bubbleBox.isSelected()) algorithms.add("bubble");
            else algorithms.remove("bubble");
        }
        else if (source == insertionBox) {
            if (insertionBox.isSelected()) algorithms.add("insertion");
            else algorithms.remove("insertion");
        }
        else if (source == selectionBox) {
            if (selectionBox.isSelected()) algorithms.add("selection");
            else algorithms.remove("selection");
        }
        else if (source == quickBox) {
            if (quickBox.isSelected()) algorithms.add("quick");
            else algorithms.remove("quick");
        }
        else if (source == heapBox) {
            if (heapBox.isSelected()) algorithms.add("heap");
            else algorithms.remove("heap");
        }
        else if (source == mergeBox) {
            if (mergeBox.isSelected()) algorithms.add("merge");
            else algorithms.remove("merge");
        }
        else if (source == autoBox) {
            if (autoBox.isSelected()) algorithms.add("auto");
            else algorithms.remove("auto");
        }
    }

    /**
     * Main function creating the GUI.
     * @param args arguments passed by user (not used)
     */
    public static void main(String[] args) {
        App app = new App();
        // On close delete GUI instead of minimizing it
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
