import javax.swing.JFrame;

public class ChristmasDrawing {

    private final JFrame pairingFrame;
    private final PairingRandomizer pairingRandomizer;

    public ChristmasDrawing() {
        pairingFrame = new JFrame("Pairings");
        pairingFrame.setContentPane(new ChristmasPairingsFrame().getMainPanel());
        pairingFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pairingFrame.pack();

        pairingRandomizer = new PairingRandomizer();

        JFrame frame = new JFrame("Christmas Drawing");
        frame.setContentPane(new ChristmasExclusionsFrame(this).getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
