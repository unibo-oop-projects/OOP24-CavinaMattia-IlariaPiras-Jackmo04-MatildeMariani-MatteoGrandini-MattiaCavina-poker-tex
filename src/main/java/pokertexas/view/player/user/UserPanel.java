package pokertexas.view.player.user;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import pokertexas.controller.player.user.UserPlayerController;
import pokertexas.view.gamepanels.PlayerPanelImpl;

/**
 * Class representing the graphical user interface for the poker game.
 */
public class UserPanel extends PlayerPanelImpl {

    private static final int FONT_SIZE = 15;
    private static final int THICKNESS = 2;
    private static final int R_BORDER = 0;
    private static final int G_BORDER = 0;
    private static final int B_BORDER = 0;
    private static final int A_BORDER = 50;
    private static final int COLOR_BACKGROUND = 0xDCBA85;
    private static final int COLOR_INPUT_PANEL = 0xECE6D0;
    private static final int NUM_BUTTONS = 5;
    private static final String MESSAGE = "Insert your bet here, press enter and then push Raise";
    private static final String FONT = "Roboto";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPanel.class);

    private final UserPlayerController controller;
    private GenericButton checkButton;
    private GenericButton callButton;
    private GenericButton raiseButton;
    private GenericButton foldButton;
    private GenericButton allInButton;
    private JTextField raiseAmount;
    private JLabel errorLabel;
    private final ActionListener listener = new InputActionListener();
    private final JPanel userPlayerPanel = new JPanel();

    /**
     * Constructs a UserPanel with the specified user player controller.
     * Initializes the panel components and sets up the event listeners.
     * @param controller the user player controller associated with this panel.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Intentional storage of a object UserPlayerController")
    public UserPanel(final UserPlayerController controller) {
        this.controller = controller;
        createUserPanel();
        disableAllButtons();
    }

    /**
     * Creates and displays the panel components.
     * Sets up the buttons and their action listeners.
     */
    private void createUserPanel() {
        userPlayerPanel.setBackground(new Color(COLOR_BACKGROUND));
        userPlayerPanel.setLayout(new FlowLayout());

        final JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(COLOR_BACKGROUND));
        inputPanel.setLayout(new GridLayout(3, 1));

        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(COLOR_BACKGROUND));
        buttonsPanel.setLayout(new GridLayout(1, NUM_BUTTONS));

        final JPanel chipsPanel = new JPanel();
        chipsPanel.setBackground(new Color(COLOR_BACKGROUND));
        chipsPanel.setLayout(new FlowLayout());

        this.checkButton = new GenericButton("Check");
        this.checkButton.initializeButton("CHECK", this.listener, buttonsPanel);
        this.callButton = new GenericButton("Call");
        this.callButton.initializeButton("CALL", this.listener, buttonsPanel);
        this.raiseButton = new GenericButton("Raise");
        this.raiseButton.initializeButton("RAISE", this.listener, buttonsPanel);
        this.foldButton = new GenericButton("Fold");
        this.foldButton.initializeButton("FOLD", this.listener, buttonsPanel);
        this.allInButton = new GenericButton("All-in");
        this.allInButton.initializeButton("ALL_IN", this.listener, buttonsPanel);

        this.raiseAmount = new JTextField(MESSAGE); 
        this.raiseAmount.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
        this.raiseAmount.addFocusListener(new InputFocusListener());
        this.raiseAmount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        this.raiseAmount.setBackground(new Color(COLOR_INPUT_PANEL));
        this.raiseAmount.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
            BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
        chipsPanel.add(this.raiseAmount);

        this.errorLabel = new JLabel();
        this.errorLabel.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
        this.errorLabel.setOpaque(true);
        this.errorLabel.setBackground(new Color(COLOR_INPUT_PANEL));
        this.errorLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
            BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));

        this.getPlayerChips().setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
        this.getPlayerChips().setBackground(new Color(COLOR_INPUT_PANEL));
        this.getPlayerChips().setOpaque(true);
        this.getPlayerChips().setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
            BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
        chipsPanel.add(this.getPlayerChips());

        this.getPlayerRole().setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
        this.getPlayerRole().setBackground(new Color(COLOR_INPUT_PANEL));
        this.getPlayerRole().setOpaque(true);
        chipsPanel.add(this.getPlayerRole());

        inputPanel.add(buttonsPanel);
        inputPanel.add(chipsPanel);
        inputPanel.add(this.errorLabel);

        this.getCardsPanel().getPanel().setBackground(new Color(COLOR_BACKGROUND));
        this.getCardsPanel().getPanel().setLayout(new FlowLayout());

        userPlayerPanel.add(inputPanel);
        userPlayerPanel.add(this.getCardsPanel().getPanel());
    }

    /**
     * Disables all the buttons and the text field in the GUI.
     */
    private void disableAllButtons() {
        this.errorLabel.setText("");
        this.raiseAmount.setText(MESSAGE);
        this.raiseAmount.setEnabled(false);
        this.checkButton.setEnabled(false);
        this.callButton.setEnabled(false);
        this.raiseButton.setEnabled(false);
        this.foldButton.setEnabled(false);
        this.allInButton.setEnabled(false);
    }

    /**
     * Updates the states of the buttons based on the current game state.
     */
    private void updateButtonStates() {
        if (this.getPlayerRole().getText().isBlank() || !controller.isPreFlop()) {
            this.checkButton.setEnabled(controller.canCheck());
            this.callButton.setEnabled(controller.canCall());
            this.raiseButton.setEnabled(controller.canRaise());
            this.foldButton.setEnabled(controller.canFold());
            this.allInButton.setEnabled(controller.canAllIn());
            this.raiseAmount.setEnabled(controller.canRaise());
        }
    }

    /**
     * Inner class to handle button click events.
     * This class implements the ActionListener interface and handles the button click events
     * for the buttons in the user interface. It processes the action commands and interacts
     * with the UserPlayerController to perform the appropriate actions based on the user's input.
     */
    private final class InputActionListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            switch (e.getActionCommand()) {
                case "RAISE" -> {
                    if (controller.isAmountOK(raiseAmount.getText())) {
                        final int raiseAmount = Integer.parseInt(UserPanel.this.raiseAmount.getText());
                        controller.setRaiseAmount(raiseAmount);
                        controller.receiveUserAction(raiseButton.getActionCommand());
                        disableAllButtons();
                    } else {
                        final String numericText = getPlayerChips().getText().replaceAll("[^0-9]", "");
                        errorLabel.setText("Invalid amount! Please try again. Insert a number between " 
                        + (controller.getState().getCurrentBet() - controller.getUserPlayer().getTotalPhaseBet() + 1) 
                        + " and " + (Long.parseLong(numericText) - 1));
                    }
                }
                case "CHECK", "CALL", "FOLD", "ALL_IN" -> {
                    controller.receiveUserAction(((GenericButton) e.getSource()).getActionCommand());
                    disableAllButtons();
                }
                default -> {
                    LOGGER.error("Unexpected action command: " + e.getActionCommand());
                }
            }
            setAction(((GenericButton) e.getSource()).getActionCommand());
            raiseAmount.setText(MESSAGE);
        }
    }

    /**
     * Inner class to handle focus events of the text field.
     * This class implements the FocusListener interface and handles the focus events
     * for the text field in the user interface. It processes the focus gained and focus lost
     * events to manage the text in the text field.
     */
    private final class InputFocusListener implements FocusListener {

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final boolean isTurn) {
        if (isTurn) {
            this.updateButtonStates();
        } else {
            this.disableAllButtons();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The userPlayerPanel is intended to be exposed")
    @Override
    public JPanel getPanel() {
        return this.userPlayerPanel;
    }

}
