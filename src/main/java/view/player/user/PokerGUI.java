package view.player.user;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
        checkButton.addActionListener(e -> {
            controller.receiveUserAction(checkButton.getActionCommand());
        });
        panel.add(this.checkButton);

        this.callButton = new JButton("Call");
        callButton.setActionCommand("CALL");
        callButton.addActionListener(e -> { 
            controller.receiveUserAction(callButton.getActionCommand());
        });
        panel.add(this.callButton);

        this.raiseButton = new JButton("Raise");
        raiseButton.setActionCommand("RAISE");
        raiseButton.addActionListener(e -> {
            controller.receiveUserAction(raiseButton.getActionCommand());
        });
        panel.add(this.raiseButton);

        this.foldButton = new JButton("Fold");
        foldButton.setActionCommand("FOLD");
        foldButton.addActionListener(e -> { 
            controller.receiveUserAction(foldButton.getActionCommand());
        });
        panel.add(this.foldButton);

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
    }

    /**
     * Disables all the buttons in the GUI.
     */
    public void disableAllButtons() {
        checkButton.setEnabled(false);
        callButton.setEnabled(false);
        raiseButton.setEnabled(false);
        foldButton.setEnabled(false);
    }
}