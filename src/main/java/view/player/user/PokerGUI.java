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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders;

import controller.player.user.UserPlayerController;
import model.player.user.UserPlayer;

/**
 * Class representing the graphical user interface for the poker game.
 */
public class PokerGUI {

    private final UserPlayerController controller;
    private MyButton checkButton;
    private MyButton callButton;
    private MyButton raiseButton;
    private MyButton foldButton;
    private MyButton allInButton;
    private JTextField textField;
    private JLabel label;
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
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setMinimumSize(frame.getSize());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,2));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2,1));
    
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.yellow);
        buttonsPanel.setLayout(new GridLayout(1,5));

        this.checkButton = new MyButton("Check");
        checkButton.setActionCommand("CHECK");
        checkButton.addActionListener(this.listener);
        buttonsPanel.add(this.checkButton);

        this.callButton = new MyButton("Call");
        callButton.setActionCommand("CALL");
        callButton.addActionListener(this.listener);
        buttonsPanel.add(this.callButton);

        this.raiseButton = new MyButton("Raise");
        raiseButton.setActionCommand("RAISE");
        raiseButton.addActionListener(this.listener);
        buttonsPanel.add(this.raiseButton);

        this.foldButton = new MyButton("Fold");
        foldButton.setActionCommand("FOLD");
        foldButton.addActionListener(this.listener);
        buttonsPanel.add(this.foldButton);

        this.allInButton = new MyButton("All-in");
        allInButton.setActionCommand("ALL_IN");
        allInButton.addActionListener(this.listener);
        buttonsPanel.add(this.allInButton);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        this.textField = new JTextField("Insert your bet here and then push Raise"); 
        this.textField.addFocusListener(new MyFocusListener());
        inputPanel.add(this.textField);

        this.label = new JLabel("chips: " + 2000); 
        //bisogna aggiungere il numero di chips iniziale del giocatore
        inputPanel.add(this.label);

        leftPanel.add(buttonsPanel);
        leftPanel.add(inputPanel);



        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(1,2));
        JLabel leftImage = new JLabel(new ImageIcon(""));
        leftImage.setBackground(Color.green); //colori che mi servono per capire la dispodizione
        leftImage.setOpaque(true);
        rightPanel.add(leftImage, BorderLayout.CENTER);
        JLabel rightImage = new JLabel(new ImageIcon(""));
        rightImage.setBackground(Color.red); //colori che mi servono per capire la dispodizione
        rightImage.setOpaque(true);
        rightPanel.add(rightImage, BorderLayout.CENTER);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        frame.add(mainPanel, BorderLayout.SOUTH);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    /**
     * Updates the states of the buttons based on the current game state.
     */
    public void updateButtonStates(final int chips) {
        checkButton.setEnabled(controller.canCheck());
        callButton.setEnabled(controller.canCall());
        raiseButton.setEnabled(controller.canRaise());
        foldButton.setEnabled(controller.canFold());
        allInButton.setEnabled(controller.canAllIn());
        label.setText("Your current balance is: " + chips);
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
                case "CHECK", "CALL", "FOLD", "ALL_IN" -> controller.receiveUserAction(((MyButton) e.getSource()).getActionCommand());
                default -> {
                }
            }
        }
    }

    /**
     * Inner class to handle focus events of the text field.
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
    
    private class MyButton extends  JButton {
        public MyButton(String text) { //classe di prova
            super(text);
            setBackground(new Color(236,205,153));
            setForeground(Color.BLACK);
            setFont(new Font("Kumar One", Font.BOLD, 15));
            setBorder(new BasicBorders.ButtonBorder(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
            setOpaque(true);
            setContentAreaFilled(true);
        }

    }

    public static void main(String[] args) {
        UserPlayer userPlayer = new UserPlayer(2000);
    }
}