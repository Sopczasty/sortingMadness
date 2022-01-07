package pl.put.poznan.sorting.app;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultDialog extends JDialog implements ActionListener {
    final JLabel resultLabel = new JLabel("Posortowane dane:");
    final JLabel measuresLabel = new JLabel("Pomiary czasów:");

    private JTextArea resultArea = new JTextArea();
    private JButton okButton = new JButton("OK");

    public ResultDialog(JFrame owner, int width, int height) {
        super(owner, "Rezultat", true);
        setSize(width, height);
        setLayout(null);

        resultLabel.setBounds(10 + (width / 4), 10, width - 60, height / 10);
        resultArea.setBounds(20, 20 + (height / 10), width - 60, height / 3);
        measuresLabel.setBounds(20, 0, 2, 2);
        okButton.setBounds(width / 3, 30 + (height / 10) + (height / 3), width / 3, 25);

        resultArea.setEditable(false);

        add(resultLabel);
        add(okButton);
        add(resultArea);

        okButton.addActionListener(this);
    }

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

    public void setResult(int[] result) {
        String text = toString(result);
        resultArea.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }
}
