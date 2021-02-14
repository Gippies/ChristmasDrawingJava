import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.Color;
import java.awt.Insets;
import java.io.File;

public class ChristmasDrawingFrame {
    private JPanel mainPanel;
    private JButton btnCalculate;
    private JButton btnClose;
    private JTable mainTable;
    private JScrollPane mainScrollPane;
    private JButton btnExport;

    public ChristmasDrawingFrame() {

        btnCalculate.setEnabled(false);

        String[] cols = {"Col 1", "Col2", "Col3"};
        String[][] data = {
                {"One", "Two", "TwoHalf"},
                {"Three", "Four", "Five"}
        };
        TableModel model = new DefaultTableModel(data, cols);

        mainTable.setModel(model);

        btnExport.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(mainPanel);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                System.out.println(file.getName());
                btnCalculate.setEnabled(true);
            }
        });

        btnCalculate.addActionListener(e -> {
            String[] cols2 = {"Person", "Exclusion"};
            String[][] data2 = {
                    {"Ron", "Josh"},
                    {"Mary Lou", "Aaron"}
            };
            mainTable.setModel(new DefaultTableModel(data2, cols2));
        });

        btnClose.addActionListener(e -> {
            System.exit(0);
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        btnClose = new JButton();
        btnClose.setText("Close");
        mainPanel.add(btnClose, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mainScrollPane = new JScrollPane();
        mainPanel.add(mainScrollPane, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        mainTable = new JTable();
        mainTable.setSelectionBackground(new Color(-11833681));
        mainScrollPane.setViewportView(mainTable);
        btnExport = new JButton();
        btnExport.setText("Export File");
        mainPanel.add(btnExport, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnCalculate = new JButton();
        btnCalculate.setText("Calculate");
        mainPanel.add(btnCalculate, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}