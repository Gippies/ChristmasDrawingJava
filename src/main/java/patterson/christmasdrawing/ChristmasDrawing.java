package patterson.christmasdrawing;

import patterson.christmasdrawing.gui.ChristmasExclusionsFrame;
import patterson.christmasdrawing.gui.ChristmasPairingsFrame;
import patterson.christmasdrawing.gui.ExportCompleteFrame;
import patterson.christmasdrawing.util.PairingRandomizer;

import javax.swing.JFrame;
import java.util.Map;

public class ChristmasDrawing {

    private final JFrame pairingFrame;
    private final PairingRandomizer pairingRandomizer;
    private final ChristmasPairingsFrame christmasPairingsFrame;

    public ChristmasDrawing() {
        JFrame exportCompleteFrame = new JFrame("Export Complete!");
        exportCompleteFrame.setContentPane(new ExportCompleteFrame().getMainPanel());
        exportCompleteFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        exportCompleteFrame.pack();

        pairingFrame = new JFrame("Pairings");
        christmasPairingsFrame = new ChristmasPairingsFrame(exportCompleteFrame);
        pairingFrame.setContentPane(christmasPairingsFrame.getMainPanel());
        pairingFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pairingFrame.pack();

        pairingRandomizer = new PairingRandomizer();

        JFrame frame = new JFrame("Christmas Drawing");
        frame.setContentPane(new ChristmasExclusionsFrame(this, exportCompleteFrame).getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setPairingFramePairMap(Map<String, String> pairMap) {
        christmasPairingsFrame.setTableContents(pairMap);
    }

    public JFrame getPairingFrame() {
        return pairingFrame;
    }

    public PairingRandomizer getPairingRandomizer() {
        return pairingRandomizer;
    }

    public static void main(String[] args) {
        new ChristmasDrawing();
    }
}
