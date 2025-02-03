package view.scenes;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.difficulty.DifficultySelectionController;
import view.scenes.api.Scene;

public class DifficultySelectionScene extends JPanel implements Scene {

    private static final String SCENE_NAME = "difficulty selection";

    private final DifficultySelectionController controller;

    public DifficultySelectionScene(final DifficultySelectionController controller) { 
        this.controller = controller;
        this.setLayout(new BorderLayout());

        JPanel center = new JPanel();

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3,1));

        JRadioButton facile = new JRadioButton("Facile");
        JRadioButton medio = new JRadioButton("Medio");
        JRadioButton difficile = new JRadioButton("Difficile");

        ActionListener difficultyListener = e -> {
            JRadioButton source = (JRadioButton) e.getSource();
            switch (source.getText()) {
                case "Facile" -> this.controller.setDifficulty(Difficulty.EASY);
                case "Medio" -> this.controller.setDifficulty(Difficulty.MEDIUM);
                case "Difficile" -> this.controller.setDifficulty(Difficulty.HARD);
            }
        };

        facile.addActionListener(difficultyListener);
        medio.addActionListener(difficultyListener);
        difficile.addActionListener(difficultyListener);

        ButtonGroup group = new ButtonGroup();
        group.add(facile);
        group.add(medio);
        group.add(difficile);

        buttonsPanel.add(facile);
        buttonsPanel.add(medio);
        buttonsPanel.add(difficile);
        
        JButton conferma = new JButton("Conferma");
        conferma.addActionListener(e -> {
            this.controller.goToGameScene();
        });

        JLabel initialChipsLabel = new JLabel("Con quante chip vuoi iniziare? ");
        JTextField input = new JTextField("", 15);
        input.addActionListener(e -> {
        this.controller.setInitialChips(Integer.parseInt(input.getText()));
        });

        center.add(buttonsPanel);
        center.add(conferma);
        center.add(initialChipsLabel);
        center.add(input);
        this.add(center, BorderLayout.CENTER);

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
        return SCENE_NAME;
    }

    public enum Difficulty { //da togliere una volta realizzato GameScene
        EASY, MEDIUM, HARD;
    }
}