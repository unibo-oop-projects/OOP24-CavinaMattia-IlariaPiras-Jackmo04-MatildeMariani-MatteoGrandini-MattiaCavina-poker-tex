package view.player.user;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.player.user.UserPlayerController;
import view.gameScenePanels.PlayerPanelImpl;

/**
 * Class representing the graphical user interface for the poker game.
 */
public class UserPanel extends PlayerPanelImpl implements Serializable {

    private static final long serialVersionUID = 3L;

    private static final int FONT_SIZE = 15; 
    private static final int THICKNESS = 2;
    private static final int R_BORDER = 0;
    private static final int G_BORDER = 0;
    private static final int B_BORDER = 0;
    private static final int A_BORDER = 50;
    private static final int R_BACKGROUND = 220;
    private static final int G_BACKGROUND = 186;
    private static final int B_BACKGROUND = 133;
    private static final int R_INPUT_PANEL = 236;
    private static final int G_INPUT_PANEL = 230;
    private static final int B_INPUT_PANEL = 208;
    private static final int COLS = 5;
    private static final String MESSAGE = "Insert your bet here and then push Raise";

    private final UserPlayerController controller;
    private MyButton checkButton;
    private MyButton callButton;
    private MyButton raiseButton;
    private MyButton foldButton;
    private MyButton allInButton;
    private JTextField raiseAmount;
    private final ActionListener listener = new MyActionListener();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPanel.class);

    /**
     * Constructs a UserPanel with the specified user player controller.
     * Initializes the panel components and sets up the event listeners.
     * @param controller the user player controller associated with this panel.
     */
    public UserPanel(final UserPlayerController controller) {
        this.controller = controller;
        createUserPanel();
    }

    /**
     * Creates and displays the panel components.
     * Sets up the buttons and their action listeners.
     */
    public final void createUserPanel() {

        this.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        this.setLayout(new FlowLayout());

        final JPanel userPanel = new JPanel();
        userPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        userPanel.setLayout(new FlowLayout());

        final JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        inputPanel.setLayout(new GridLayout(2, 1));

        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        buttonsPanel.setLayout(new GridLayout(1, COLS));

        final JPanel chipsPanel = new JPanel();
        chipsPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        chipsPanel.setLayout(new FlowLayout());

        this.checkButton = new MyButton("Check");
        this.checkButton.initializeButton("CHECK", this.listener, buttonsPanel);
        this.callButton = new MyButton("Call");
        this.callButton.initializeButton("CALL", this.listener, buttonsPanel);
        this.raiseButton = new MyButton("Raise");
        this.raiseButton.initializeButton("RAISE", this.listener, buttonsPanel);
        this.foldButton = new MyButton("Fold");
        this.foldButton.initializeButton("FOLD", this.listener, buttonsPanel);
        this.allInButton = new MyButton("All-in");
        this.allInButton.initializeButton("ALL_IN", this.listener, buttonsPanel);

        this.raiseAmount = new JTextField(MESSAGE); 
        this.raiseAmount.setFont(new Font("Roboto", Font.PLAIN, FONT_SIZE));
        this.raiseAmount.addFocusListener(new MyFocusListener());
        this.raiseAmount.setBackground(new Color(R_INPUT_PANEL, G_INPUT_PANEL, B_INPUT_PANEL));
        this.raiseAmount.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
            BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
        chipsPanel.add(this.raiseAmount);

        this.getPlayerChips().setFont(new Font("Roboto", Font.PLAIN, FONT_SIZE));
        this.getPlayerChips().setBackground(new Color(R_INPUT_PANEL, G_INPUT_PANEL, B_INPUT_PANEL));
        this.getPlayerChips().setOpaque(true);
        this.getPlayerChips().setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
            BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
        chipsPanel.add(this.getPlayerChips());

        this.getPlayerRole().setFont(new Font("Roboto", Font.PLAIN, FONT_SIZE));
        this.getPlayerRole().setBackground(new Color(R_INPUT_PANEL, G_INPUT_PANEL, B_INPUT_PANEL));
        this.getPlayerRole().setOpaque(true);
        chipsPanel.add(this.getPlayerRole());

        inputPanel.add(buttonsPanel);
        inputPanel.add(chipsPanel);

        this.getCardsPanel().setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        this.getCardsPanel().setLayout(new FlowLayout());

        userPanel.add(inputPanel);
        userPanel.add(this.getCardsPanel());

        this.add(userPanel);
    }

    /**
     * Updates the states of the buttons based on the current game state.
     */
    public void updateButtonStates() {
        checkButton.setEnabled(controller.canCheck());
        callButton.setEnabled(controller.canCall());
        raiseButton.setEnabled(controller.canRaise());
        foldButton.setEnabled(controller.canFold());
        allInButton.setEnabled(controller.canAllIn());
        raiseAmount.setEnabled(controller.canRaise());
    }

    /**
     * Disables all the buttons and the text field in the GUI.
     */
    public void disableAllButtons() {
        raiseAmount.setText(MESSAGE);
        raiseAmount.setEnabled(false);
        checkButton.setEnabled(false);
        callButton.setEnabled(false);
        raiseButton.setEnabled(false);
        foldButton.setEnabled(false);
        allInButton.setEnabled(false);
    }

    /**
     * Sets the player action in the player panel.
     * @param action the action to set.
     */
    public void setPlayerAction(final String action) {
        this.setAction(action);
    }

    /**
     * Inner class to handle button click events.
     * This class implements the ActionListener interface and handles the button click events
     * for the buttons in the user interface. It processes the action commands and interacts
     * with the UserPlayerController to perform the appropriate actions based on the user's input.
     */
    private final class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            switch (e.getActionCommand()) {
                case "RAISE" -> {
                    if (controller.isAmountOK(raiseAmount.getText())) {
                        final int raiseAmount = Integer.parseInt(UserPanel.this.raiseAmount.getText());
                        controller.setRaiseAmount(raiseAmount);
                        controller.receiveUserAction(raiseButton.getActionCommand());
                    }
                }
                case "CHECK", "CALL", "FOLD", "ALL_IN" -> {
                    controller.receiveUserAction(((MyButton) e.getSource()).getActionCommand());
                }
                default -> {
                    LOGGER.error("Unexpected action command: " + e.getActionCommand());
                }
            }
            setPlayerAction(((MyButton) e.getSource()).getActionCommand());
        }
    }

    /**
     * Inner class to handle focus events of the text field.
     * This class implements the FocusListener interface and handles the focus events
     * for the text field in the user interface. It processes the focus gained and focus lost
     * events to manage the text in the text field.
     */
    private final class MyFocusListener implements FocusListener {

        @Override
        public void focusGained(final FocusEvent e) {
            if (MESSAGE.equals(raiseAmount.getText())) {
                raiseAmount.setText("");
            }
        }

        @Override
        public void focusLost(final FocusEvent e) {
            if (raiseAmount.getText().isEmpty()) {
                raiseAmount.setText(MESSAGE);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRole(final String role) {
        if (role.isBlank()) {
            this.getPlayerRole().setBorder(null);
        } else {
            this.getPlayerRole().setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
                BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
        }
       super.setRole(role);
    }
}
