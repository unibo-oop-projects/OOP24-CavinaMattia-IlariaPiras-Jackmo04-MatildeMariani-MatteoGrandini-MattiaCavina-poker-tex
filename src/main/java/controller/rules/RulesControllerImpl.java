package controller.rules;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import controller.menu.MainMenuControllerImpl;
import view.View;
import view.scenes.MainMenuScene;

/**
 * Implementation of the rules controller.
 * Manages the return to the main menu scene.
 */
public class RulesControllerImpl implements RulesController {

    private static final String RULES_HTML_PATH = "rules/rules.html";

    private View mainView;

    /**
     * Creates a new rules controller.
     * @param mainView the main view of the application
     */
    public RulesControllerImpl(final View mainView) {
        this.mainView = mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToMainMenuScene() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuControllerImpl(this.mainView)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRulesHtml() {
        InputStream is = ClassLoader.getSystemResourceAsStream(RULES_HTML_PATH);
        if (is == null) {
            throw new IllegalArgumentException("File not found: " + RULES_HTML_PATH);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "<html><body><p>Errore nel caricamento del file HTML</p></body></html>";
        }
    }

}
