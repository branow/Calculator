import javax.swing.*;

public class Start {

    private JFrame win;

    public Start () {
        win = new JFrame("Calculator");
        win.setResizable(false);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
        win.setSize(315,490);
        win.setLocationRelativeTo(null);

        win.add(new Panel());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Start();
            }
        });
    }


}
