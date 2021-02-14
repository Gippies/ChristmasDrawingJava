import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoshTable {

    private final List<String> columnNames;
    private final List<List<String>> data;

    public JoshTable(String[][] data, String[] columnNames) {
        this.data = new ArrayList<>();
        this.columnNames = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            this.data.add(new ArrayList<>());
            this.data.get(i).addAll(Arrays.asList(data[i]));
        }
        this.columnNames.addAll(Arrays.asList(columnNames));
    }

    public void addColumn(String columnHeader) {
        columnNames.add(columnHeader);
        for (List<String> datum : data) {
            datum.add("");
        }
    }

    public void removeLastColumn() {
        columnNames.remove(columnNames.size() - 1);
        for (List<String> datum : data) {
            datum.remove(datum.size() - 1);
        }
    }

    public TableModel getTableModel() {
        String[] tableCols = new String[columnNames.size()];
        String[][] tableData = new String[data.size()][data.get(0).size()];
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

}
