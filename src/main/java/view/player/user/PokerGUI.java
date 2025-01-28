package view.player.user;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.player.user.UserPlayerController;

/**
 * Class representing the graphical user interface for the poker game.
 */
public class PokerGUI {
    private final UserPlayerController controller;
    private JButton checkButton;
    private JButton callButton;
    private JButton raiseButton;
    private JButton foldButton;
    private JButton allInButton;
    private JTextField textField;
    private final ActionListener listener = new MyActionListener();
    
    /**
     * Constructs a PokerGUI with the specified user player controller.
     * Initializes the GUI components and sets up the event listeners.
     * @param controller the user player controller associated with this GUI.
     */
    public PokerGUI(final UserPlayerController controller) {
        this.controller = controller;
        createAndShowGUI();
    }

    /**
     * Creates and displays the GUI components.
     * Sets up the buttons and their action listeners.
     */
    private void createAndShowGUI() {
        JFrame frame = new JFrame("Poker Game"); //queste tre righe sono da modificare poi successivamente
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        this.checkButton = new JButton("Check");
        checkButton.setActionCommand("CHECK");
        checkButton.addActionListener(this.listener);
        panel.add(this.checkButton);

        this.callButton = new JButton("Call");
        callButton.setActionCommand("CALL");
        callButton.addActionListener(this.listener);
        panel.add(this.callButton);

        this.raiseButton = new JButton("Raise");
        raiseButton.setActionCommand("RAISE");
        raiseButton.addActionListener(this.listener);
        panel.add(this.raiseButton);

        this.foldButton = new JButton("Fold");
        foldButton.setActionCommand("FOLD");
        foldButton.addActionListener(this.listener);
        panel.add(this.foldButton);

        this.allInButton = new JButton("All-in");
        allInButton.setActionCommand("ALL_IN");
        allInButton.addActionListener(this.listener);
        panel.add(this.allInButton);

        this.textField = new JTextField("Insert your bet here and then push Raise", 25); 
        this.textField.addFocusListener(new MyFocusListener());
        panel.add(this.textField);

        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Updates the states of the buttons based on the current bet.
     * @param currentBet the current bet in the game.
     */
    public void updateButtonStates(final int currentBet) {
        checkButton.setEnabled(controller.canCheck(currentBet));
        callButton.setEnabled(controller.canCall());
        raiseButton.setEnabled(controller.canRaise(currentBet));
        foldButton.setEnabled(controller.canFold());
        allInButton.setEnabled(controller.canAllIn());
    }

    /**
     * Disables all the buttons in the GUI.
     */
    public void disableAllButtons() {
        checkButton.setEnabled(false);
        callButton.setEnabled(false);
        raiseButton.setEnabled(false);
        foldButton.setEnabled(false);
        allInButton.setEnabled(false);
    }

    /**
     * Inner class to handle button click events.
     */
    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "RAISE" -> {
                    if (controller.isAmountOK(textField.getText())) {
                        int raiseAmount = Integer.parseInt(textField.getText());
                        controller.setRaiseAmount(raiseAmount);
                        controller.receiveUserAction(raiseButton.getActionCommand());
                    }
                }
                case "CHECK", "CALL", "FOLD", "ALL_IN" -> controller.receiveUserAction(((JButton) e.getSource()).getActionCommand());
                default -> {
                }
            }
        }
    }

    /**
     * Inner class to handle focus events for the text field.
     */
    private class MyFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            if (textField.getText().equals("Insert your bet here and then push Raise")) {
                textField.setText("");
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (textField.getText().isEmpty()) {
                textField.setText("Insert your bet here and then push Raise");
            }
        }
    }
}