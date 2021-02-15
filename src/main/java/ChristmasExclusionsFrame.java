import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChristmasExclusionsFrame {
    private JPanel mainPanel;
    private JButton btnCalculate;
    private JButton btnClose;
    private JTable mainTable;
    private JScrollPane mainScrollPane;
    private JButton btnImport;
    private JButton btnAddCol;
    private JButton btnRemoveCol;
    private JButton btnAddRow;
    private JButton btnRemoveRow;

    private JoshTable joshTable;

    public ChristmasExclusionsFrame() {

        String[] cols = {"Names", "Excl 1", "Excl 2"};
        String[][] data = {
                {"Billy", "Bob", ""},
                {"Joe", "Mark", ""}
        };
        joshTable = new JoshTable(data, cols);
        mainTable.setModel(joshTable.getTableModel());

        btnAddRow.addActionListener(e -> {
            joshTable.addRow();
            mainTable.setModel(joshTable.getTableModel());
        });

        btnRemoveRow.addActionListener(e -> {
            joshTable.removeLastRow();
            mainTable.setModel(joshTable.getTableModel());
        });

        btnAddCol.addActionListener(e -> {
            joshTable.addColumn();
            mainTable.setModel(joshTable.getTableModel());
        });

        btnRemoveCol.addActionListener(e -> {
            joshTable.removeLastColumn();
            mainTable.setModel(joshTable.getTableModel());
        });

        btnImport.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(mainPanel);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                try {
                    FileInputStream excelFile = new FileInputStream(fileChooser.getSelectedFile());
                    Workbook workbook = new XSSFWorkbook(excelFile);
                    Sheet datatypeSheet = workbook.getSheetAt(0);

                    List<List<String>> dataList = new ArrayList<>();

                    for (Row currentRow : datatypeSheet) {
                        List<String> temp = new ArrayList<>();
                        for (Cell currentCell : currentRow) {
                            temp.add(currentCell.getStringCellValue());
                        }
                        dataList.add(temp);
                    }

                    List<String> columnNames = new ArrayList<>();
                    columnNames.add("Name");
                    for (int i = 0; i < dataList.get(0).size() - 1; i++) {
                        columnNames.add("Excl " + (i + 1));
                    }

                    joshTable = new JoshTable(dataList, columnNames);
                    mainTable.setModel(joshTable.getTableModel());

                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
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
        mainPanel.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        btnClose = new JButton();
        btnClose.setText("Close");
        mainPanel.add(btnClose, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mainScrollPane = new JScrollPane();
        mainPanel.add(mainScrollPane, new GridConstraints(0, 0, 2, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        mainTable = new JTable();
        mainTable.setAutoResizeMode(0);
        mainTable.setSelectionBackground(new Color(-11833681));
        mainScrollPane.setViewportView(mainTable);
        btnImport = new JButton();
        btnImport.setText("Import File");
        mainPanel.add(btnImport, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnCalculate = new JButton();
        btnCalculate.setText("Calculate");
        mainPanel.add(btnCalculate, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnAddCol = new JButton();
        btnAddCol.setText("Add Column");
        mainPanel.add(btnAddCol, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnRemoveCol = new JButton();
        btnRemoveCol.setText("Remove Column");
        mainPanel.add(btnRemoveCol, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnAddRow = new JButton();
        btnAddRow.setText("Add Row");
        mainPanel.add(btnAddRow, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnRemoveRow = new JButton();
        btnRemoveRow.setText("Remove Row");
        mainPanel.add(btnRemoveRow, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
