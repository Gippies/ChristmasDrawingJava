package patterson.christmasdrawing.util;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicTable {

    private List<String> columnNames;
    private List<List<String>> data;

    public DynamicTable(String[][] data, String[] columnNames) {
        this.data = new ArrayList<>();
        this.columnNames = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            this.data.add(new ArrayList<>());
            this.data.get(i).addAll(Arrays.asList(data[i]));
        }
        this.columnNames.addAll(Arrays.asList(columnNames));
    }

    public DynamicTable(List<List<String>> data, List<String> columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    public void addRow() {
        data.add(new ArrayList<>());
    }

    public void removeLastRow() {
        if (data.size() > 1)
            data.remove(data.size() - 1);
    }

    public void addColumn() {
        columnNames.add("Excl " + columnNames.size());
        for (List<String> datum : data) {
            datum.add("");
        }
    }

    public void removeLastColumn() {
        if (columnNames.size() > 1) {
            columnNames.remove(columnNames.size() - 1);
            for (List<String> datum : data) {
                datum.remove(datum.size() - 1);
            }
        }
    }

    public List<List<String>> getData() {
        return data;
    }

    public TableModel getTableModel() {
        String[] tableCols = new String[columnNames.size()];
        String[][] tableData = new String[data.size()][columnNames.size()];
        for (int i = 0; i < columnNames.size(); i++) {
            tableCols[i] = columnNames.get(i);
        }

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                tableData[i][j] = data.get(i).get(j);
            }
        }

        return new DefaultTableModel(tableData, tableCols);
    }

    public void setTableModel(TableModel model) {
        data = new ArrayList<>();
        columnNames = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            data.add(new ArrayList<>());
            for (int j = 0; j < model.getColumnCount(); j++) {
                data.get(i).add((String) model.getValueAt(i, j));
                if (i == 0) {
                    columnNames.add(model.getColumnName(j));
                }
            }
        }
    }

}
