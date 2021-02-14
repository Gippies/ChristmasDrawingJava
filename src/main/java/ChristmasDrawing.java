import javax.swing.JFrame;

public class ChristmasDrawing {

    public ChristmasDrawing() {
        JFrame frame = new JFrame("Christmas Drawing");
        frame.setContentPane(new ChristmasExclusionsFrame().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ChristmasDrawing();
    }
}
