package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private static final int PROPORTION = 5;
    private static final String TITLE = "My first java GUI";
    private final JFrame frame = new JFrame(TITLE);
    private final Controller c = new Controller();

    /**
     * GUI constructor.
     */
    public SimpleGUIWithFileChooser() {
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        // Button
        final JButton saveButton = new JButton("Save");
        mainPanel.add(saveButton, BorderLayout.SOUTH);
        // Text area
        final JTextArea textArea = new JTextArea();
        mainPanel.add(textArea, BorderLayout.CENTER);
        // Browsing panel
        final JPanel browser = new JPanel();
        browser.setLayout(new BorderLayout());
        mainPanel.add(browser, BorderLayout.NORTH);
        // Text field for browsing
        final JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setText(c.getCurrentFilePath());
        browser.add(textField, BorderLayout.CENTER);
        // Button for browsing
        final JButton browserButton = new JButton("Browse...");
        browser.add(browserButton, BorderLayout.LINE_END);

        // Frame base settings
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Handlers
        */
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    c.writeFile(textArea.getText());
                } catch (IOException exception) {
                    exception.printStackTrace();  // NOPMD: allowed as we need to check the ST
                }
            }
        });
        browserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                switch (chooser.showSaveDialog(browserButton)) {
                    case JFileChooser.APPROVE_OPTION:
                        c.setCurrentFile(chooser.getSelectedFile());
                        textField.setText(chooser.getSelectedFile().getAbsolutePath());
                        break;

                    case JFileChooser.CANCEL_OPTION:
                        break;

                    default:
                        JOptionPane.showMessageDialog(browserButton, "an error has occurred");
                        break;
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
     * @param args ignored.
     */
    public static void main(final String[] args) {

        new SimpleGUIWithFileChooser().display();
    }
}
