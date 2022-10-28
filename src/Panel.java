import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel {

    private JButton[] numbers = numbers = new JButton[10];
    private Font font = new Font("san", Font.CENTER_BASELINE, 25);
    private Font font2 = new Font("san", Font.CENTER_BASELINE, 15);
    private Font font3 = new Font("san", Font.CENTER_BASELINE, 10);
    private JButton backspace = new JButton("" + '\u2794'),
    c = new JButton("c"),
    ce = new JButton("ce"),
    add = new JButton("+"),
    sub = new JButton("-"),
    mul = new JButton(""+'\u00D7'),
    div = new JButton(""+'\u00F7'),
    minus = new JButton(""+'\u00B1'),
    equal = new JButton("="),
    cubed = new JButton("" +'\u00B3'),
    squared = new JButton("" +'\u00B2'),
    root = new JButton("" +'\u221A'),
    dot = new JButton(".");

    private JTextField gWin;
    private JTextField win;

    public Panel() {
        setLayout(null);
        setFocusable(true);
        grabFocus();

        placing();
        action();

    }

    private void action() {
        ActionListener num = (ActionEvent e) -> {
            JButton b = (JButton) e.getSource();
            win.setText(win.getText() + b.getText());
        };

        for (JButton b : numbers) {
            b.addActionListener(num);
        }
        dot.addActionListener(num);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char sym = e.getKeyChar();
                if (!Character.isDigit(sym))
                    return;
                win.setText(win.getText() + sym);
            }
        });

        ActionListener act = (ActionEvent e) -> {
            JButton b = (JButton) e.getSource();
            gWin.setText(gWin.getText()+win.getText()+b.getText());
            if (Logic.check()) {
                Logic.getNums().push(Double.parseDouble(win.getText()));
            }
            Logic.action(b.getText());
            win.setText("");
        };
        add.addActionListener(act);
        sub.addActionListener(act);
        mul.addActionListener(act);
        div.addActionListener(act);
        squared.addActionListener(act);
        cubed.addActionListener(act);
        root.addActionListener((ActionEvent e) -> {
            JButton b = (JButton) e.getSource();
            gWin.setText(gWin.getText()+b.getText()+win.getText());
            if (Logic.check()) {
                Logic.getNums().push(Double.parseDouble(win.getText()));
            }
            Logic.action(b.getText());
            win.setText("");
        });

        equal.addActionListener((ActionEvent e) -> {
            JButton b = (JButton) e.getSource();

            if (Logic.check()) {
                Logic.getNums().push(Double.parseDouble(win.getText()));
            }
            Logic.action(b.getText());

            gWin.setText(gWin.getText()+win.getText()+b.getText()+Logic.getNums().peek());

            win.setText(""+Logic.getNums().pop());
        });

        backspace.addActionListener((ActionEvent e) -> {
            int i = win.getText().length() - 2;
            win.setText(win.getText().substring(0,win.getText().length() - 1));
        });

        ce.addActionListener((ActionEvent e) ->{
            win.setText("");
        });

        c.addActionListener((ActionEvent e) ->{
            win.setText("");
            gWin.setText("");
            while (!Logic.getNums().empty()) {
                Logic.getNums().pop();
            }
            while (!Logic.getOperations().empty()) {
                Logic.getOperations().pop();
            }
        });

        minus.addActionListener((ActionEvent e) -> {
          win.setText("-" + win.getText());
        });

    }

    private void placing () {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                numbers[i*3+j+1] = new JButton((i*3+j+1) +"");
                numbers[i*3+j+1].setFont(font);
                numbers[i*3+j+1].setBounds(10+58*j,334-58*i,48,48);
                add(numbers[i*3+j+1]);
            }
        }
        numbers[0] = new JButton("0");
        numbers[0].setFont(font);
        numbers[0].setBounds(10,392,106,48);
        add(numbers[0]);

        c.setFont(font);
        c.setBounds(126,160,48,48);
        add(c);

        ce.setFont(font3);
        ce.setBounds(68,160,48,48);
        add(ce);

        backspace.setFont(font3);
        backspace.setBounds(10,160,48,48);
        add(backspace);

        minus.setFont(font);
        minus.setBounds(184,160,48,48);
        add(minus);

        root.setFont(font2);
        root.setBounds(242,160,48,48);
        add(root);

        div.setFont(font);
        div.setBounds(184,218,48,48);
        add(div);

        mul.setFont(font);
        mul.setBounds(184,276,48,48);
        add(mul);

        sub.setFont(font);
        sub.setBounds(184,334,48,48);
        add(sub);

        add.setFont(font);
        add.setBounds(184,392,48,48);
        add(add);

        squared.setFont(font2);
        squared.setBounds(242,218,48,48);
        add(squared);

        cubed.setFont(font2);
        cubed.setBounds(242,276,48,48);
        add(cubed);

        equal.setFont(font);
        equal.setBounds(242,334,48,106);
        add(equal);

        dot.setFont(font);
        dot.setBounds(126,392,48,48);
        add(dot);


        win = new JTextField();
        win.setBounds(10,70,280,80);
        win.setEditable(false);
        win.setFont(font);
        add(win);

        gWin = new JTextField();
        gWin.setBounds(10,10,280,50);
        gWin.setEditable(false);
        gWin.setFont(font2);
        add(gWin);

    }



}
