package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();
    private final Controller controller = new SimpleController();

    SimpleGUI() {
        // Panels
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        // Elements
        final JTextField textField = new JTextField();
        final JTextArea textArea = new JTextArea();
        final JButton printButton = new JButton("Print");
        final JButton historyButton = new JButton("Show History");
        // Elements Layout
        mainPanel.add(textField, BorderLayout.NORTH);
        mainPanel.add(textArea, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(printButton, BorderLayout.EAST);
        buttonPanel.add(historyButton, BorderLayout.WEST);
        // Frame Settings
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Handlers
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setNextString(textField.getText());
                controller.printCurrentString();
            }
        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                for (final String printedString : controller.getHistory()) {
                    textArea.append(printedString + "\n");
                }
            }
        });
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
    }


    /**
     * 
     * @param args ignored
     */
    public static void main(final String[] args) {
        new SimpleGUI().display();
    }
}
