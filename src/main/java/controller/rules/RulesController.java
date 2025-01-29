package controller.rules;

/**
 *  Interface for the rules controller.
 */
public interface RulesController {

    /**
     * Goes back to the main menu scene.
     */
    void goToMainMenuScene();

    /**
     * Returns the HTML content of the rules.
     * @return the HTML content of the rules
     */
    String getRulesHtml();
}
