package view.player.user;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.player.user.UserPlayerController;
import model.player.api.Action;
import model.player.api.Role;
import model.player.user.UserPlayer;

public class PokerGUI {
    private final UserPlayerController controller;

    public PokerGUI(UserPlayer player) {
        this.controller = new UserPlayerController(player);
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Poker Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton checkButton = new JButton("Check");
        checkButton.addActionListener(e -> controller.receiveUserAction(Action.CHECK));
        panel.add(checkButton);

        JButton callButton = new JButton("Call");
        callButton.addActionListener(e -> controller.receiveUserAction(Action.CALL));
        panel.add(callButton);

        JButton raiseButton = new JButton("Raise");
        raiseButton.addActionListener(e -> controller.receiveUserAction(Action.RAISE));
        panel.add(raiseButton);

        JButton foldButton = new JButton("Fold");
        foldButton.addActionListener(e -> controller.receiveUserAction(Action.FOLD));
        panel.add(foldButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        UserPlayer player = new UserPlayer(10000, Role.DEALER);
        PokerGUI pokerGUI = new PokerGUI(player);
    }
}