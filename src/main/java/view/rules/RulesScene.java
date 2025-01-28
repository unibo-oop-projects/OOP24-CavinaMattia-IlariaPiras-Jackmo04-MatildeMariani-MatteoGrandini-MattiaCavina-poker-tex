package view.rules;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.rules.RulesController;
import view.commons.Scene;

public class RulesScene extends JPanel implements Scene {

    private static final String NAME = "rules";

    private final RulesController controller;

    public RulesScene(RulesController controller) {
        this.controller = controller;

        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Regole", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.add(title, BorderLayout.NORTH);

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> this.controller.goToMainMenuScene());
        this.add(backButton, BorderLayout.SOUTH);
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public String getSceneName() {
        return NAME;
    }

}
