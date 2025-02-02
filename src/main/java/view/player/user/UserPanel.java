package view.player.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.player.user.UserPlayerController;

/**
 * Class representing the graphical user interface for the poker game.
 */
public class UserPanel {

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

    private final UserPlayerController controller;
    private MyButton checkButton;
    private MyButton callButton;
    private MyButton raiseButton;
    private MyButton foldButton;
    private MyButton allInButton;
    private JTextField textField;
    private JLabel label;
    private MyLabel image1;
    private MyLabel image2;
    private final ActionListener listener = new MyActionListener();
    
    /**
     * Constructs a UserPanel with the specified user player controller.
     * Initializes the GUI components and sets up the event listeners.
     * @param controller the user player controller associated with this GUI.
     * @param initialChips the initial number of chips the user player has.
     */
    public UserPanel(final UserPlayerController controller, final int initialChips) {
        this.controller = controller;
        createUserPanel(initialChips);
    }

    /**
     * Creates and displays the GUI components.
     * Sets up the buttons and their action listeners.
     * @param initialChips the initial number of chips the user player has.
     */
    private void createUserPanel(final int initialChips) {
        JFrame frame = new JFrame("Poker Game"); //queste tre righe sono da modificare poi successivamente
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setMinimumSize(frame.getSize());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        mainPanel.setLayout(new FlowLayout());

        JPanel userPanel = new JPanel();
        userPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        userPanel.setLayout(new FlowLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        inputPanel.setLayout(new GridLayout(2,1));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        buttonsPanel.setLayout(new GridLayout(1,5));

        JPanel chipsPanel = new JPanel();
        chipsPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        chipsPanel.setLayout(new FlowLayout());

        JPanel cardsPanel = new JPanel();
        cardsPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        cardsPanel.setLayout(new FlowLayout());

        this.checkButton = new MyButton("Check", "CHECK", this.listener, buttonsPanel);
        this.callButton = new MyButton("Call", "CALL", this.listener, buttonsPanel);
        this.raiseButton = new MyButton("Raise", "RAISE", this.listener, buttonsPanel);
        this.foldButton = new MyButton("Fold", "FOLD", this.listener, buttonsPanel);
        this.allInButton = new MyButton("All-in", "ALL_IN", this.listener, buttonsPanel);

        this.textField = new JTextField("Insert your bet here and then push Raise"); 
        this.textField.setFont(new Font("Roboto", Font.PLAIN, FONT_SIZE));
        this.textField.addFocusListener(new MyFocusListener());
        this.textField.setBackground(new Color(R_INPUT_PANEL, G_INPUT_PANEL, B_INPUT_PANEL));
        this.textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
                        BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
        chipsPanel.add(this.textField);

        this.label = new JLabel("Chips: " + initialChips); 
        this.label.setFont(new Font("Roboto", Font.PLAIN, FONT_SIZE));
        this.label.setBackground(new Color(R_INPUT_PANEL, G_INPUT_PANEL, B_INPUT_PANEL));
        this.label.setOpaque(true);
        this.label.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
                        BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
        chipsPanel.add(this.label);

        inputPanel.add(buttonsPanel);
        inputPanel.add(chipsPanel);

        this.image1 = new MyLabel("src/main/java/view/player/user/asso.jpg"); //immagine di prova
        this.image2 = new MyLabel("src/main/java/view/player/user/asso.jpg"); //immagine di prova
        
        cardsPanel.add(this.image1);
        cardsPanel.add(this.image2);

        userPanel.add(inputPanel);
        userPanel.add(cardsPanel);

        mainPanel.add(userPanel);

        frame.add(mainPanel, BorderLayout.SOUTH);
        frame.setResizable(true);
        frame.setVisible(true);

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
        textField.setEnabled(controller.canRaise());
    }

    /**
     * Disables all the buttons and the text field in the GUI.
     */
    public void disableAllButtons() {
        textField.setText("Insert your bet here and then push Raise");
        textField.setEnabled(false);
        checkButton.setEnabled(false);
        callButton.setEnabled(false);
        raiseButton.setEnabled(false);
        foldButton.setEnabled(false);
        allInButton.setEnabled(false);
    }

    /**
     * Updates the label in the user interface.
     * This method sets the text of the label to display the current number of chips the user has.
     * @param chips the current number of chips the user has.
     */
    public void updateLabelUserChips(final int chips) {
        label.setText("Chips: " + chips);
    }

    /**
     * Inner class to handle button click events.
     * This class implements the ActionListener interface and handles the button click events
     * for the buttons in the user interface. It processes the action commands and interacts
     * with the UserPlayerController to perform the appropriate actions based on the user's input.
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
                case "CHECK", "CALL", "FOLD", "ALL_IN" -> controller.receiveUserAction(((MyButton) e.getSource()).getActionCommand());
                default -> {
                }
            }
        }
    }

    /**
     * Inner class to handle focus events of the text field.
     * This class implements the FocusListener interface and handles the focus events
     * for the text field in the user interface. It processes the focus gained and focus lost
     * events to manage the text in the text field.
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