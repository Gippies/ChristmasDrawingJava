package patterson.christmasdrawing.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import patterson.christmasdrawing.ChristmasDrawing;
import patterson.christmasdrawing.util.DynamicTable;
import patterson.christmasdrawing.util.PairingRandomizer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import java.awt.Color;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private JButton btnExport;

    private DynamicTable dynamicTable;

    public ChristmasExclusionsFrame(ChristmasDrawing christmasDrawing, JFrame exportCompleteFrame) {

        String[] tempCols = {"Names", "Excl 1"};
        String[][] tempData = {
                {"Billy", "Bob"},
                {"Joe", "Mark"}
        };
        dynamicTable = new DynamicTable(tempData, tempCols);
        mainTable.setModel(dynamicTable.getTableModel());

        btnAddRow.addActionListener(e -> {
            dynamicTable.addRow();
            mainTable.setModel(dynamicTable.getTableModel());
        });

        btnRemoveRow.addActionListener(e -> {
            dynamicTable.removeLastRow();
            mainTable.setModel(dynamicTable.getTableModel());
        });

        btnAddCol.addActionListener(e -> {
            dynamicTable.addColumn();
            mainTable.setModel(dynamicTable.getTableModel());
        });

        btnRemoveCol.addActionListener(e -> {
            dynamicTable.removeLastColumn();
            mainTable.setModel(dynamicTable.getTableModel());
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

                    int largestWidth = 0;
                    for (Row currentRow : datatypeSheet) {
                        List<String> temp = new ArrayList<>();
                        for (Cell currentCell : currentRow) {
                            temp.add(currentCell.getStringCellValue());
                        }
                        if (temp.size() > largestWidth) {
                            largestWidth = temp.size();
                        }
                        dataList.add(temp);
                    }
                    excelFile.close();

                    List<String> columnNames = new ArrayList<>();
                    columnNames.add("Name");
                    for (int i = 0; i < largestWidth - 1; i++) {
                        columnNames.add("Excl " + (i + 1));
                    }

                    dynamicTable = new DynamicTable(dataList, columnNames);
                    mainTable.setModel(dynamicTable.getTableModel());

                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });

        btnExport.addActionListener(e -> {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Exclusions");
            TableModel tableModel = mainTable.getModel();

            tableModel.getValueAt(0, 0);
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Row row = sheet.createRow(i);
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue((String) tableModel.getValueAt(i, j));
                }
            }

            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter xlsxFilter = new FileNameExtensionFilter("xlsx files (*.xlsx)", "xlsx");
            fileChooser.addChoosableFileFilter(xlsxFilter);
            fileChooser.setFileFilter(xlsxFilter);
            int state = fileChooser.showSaveDialog(mainPanel);

            if (state == JFileChooser.APPROVE_OPTION) {
                try (FileOutputStream outputStream = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath() + ".xlsx")) {
                    workbook.write(outputStream);
                    exportCompleteFrame.setLocationRelativeTo(mainPanel);
                    exportCompleteFrame.setVisible(true);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });

        btnCalculate.addActionListener(e -> {
            dynamicTable.setTableModel(mainTable.getModel());
            List<List<String>> dynamicTableData = dynamicTable.getData();
            Map<String, List<String>> dynamicTableMap = new HashMap<>();
            for (List<String> dynamicTableRow : dynamicTableData) {
                List<String> exclusionList = new ArrayList<>(dynamicTableRow.size() - 1);
                for (int j = 1; j < dynamicTableRow.size(); j++) {
                    if (dynamicTableRow.get(j) != null && !dynamicTableRow.get(j).equals(""))
                        exclusionList.add(dynamicTableRow.get(j));
                }
                dynamicTableMap.put(dynamicTableRow.get(0), exclusionList);
            }

            PairingRandomizer randomizer = christmasDrawing.getPairingRandomizer();
            randomizer.setExclusionMap(dynamicTableMap);
            Map<String, String> pairMap = randomizer.randomize();
            christmasDrawing.setPairingFramePairMap(pairMap);
            christmasDrawing.getPairingFrame().setVisible(true);
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
        btnClose = new JButton();
        btnClose.setText("Close");
        mainPanel.add(btnClose, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnExport = new JButton();
        btnExport.setText("Export Table");
        mainPanel.add(btnExport, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
