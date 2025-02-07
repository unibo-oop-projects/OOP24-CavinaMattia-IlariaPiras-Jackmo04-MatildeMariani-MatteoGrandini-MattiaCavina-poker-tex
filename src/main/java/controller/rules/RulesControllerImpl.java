package controller.rules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import controller.scene.SceneControllerImpl;
import view.View;

/**
 * Implementation of the rules controller.
 * Manages the return to the main menu scene.
 */
public class RulesControllerImpl extends SceneControllerImpl implements RulesController {

    private static final String RULES_HTML_PATH = "rules/rules.html";

    /**
     * Creates a new rules controller.
     * @param mainView the main view of the application
     */
    public RulesControllerImpl(final View mainView) {
        super(mainView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRulesHtml() {
        final InputStream is = ClassLoader.getSystemResourceAsStream(RULES_HTML_PATH);
        if (is == null) {
            throw new IllegalArgumentException("File not found: " + RULES_HTML_PATH);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            final var html = reader.lines().collect(Collectors.joining("\n"));
            return correctImagesURLs(html);
        } catch (IOException e) {
            return "<html><body><p>Errore nel caricamento del file HTML</p></body></html>";
        }
    }

    private String correctImagesURLs(final String html) {
        return html.replaceAll("images/", ClassLoader.getSystemResource("rules/images/").toString());
    }

}
