package pokertexas.controller.rules;

import pokertexas.controller.scene.SceneController;

/**
 * Interface for the rules controller.
 * Also provides SceneController methods.
 */
public interface RulesController extends SceneController {

    /**
     * Returns the HTML string representing the rules.
     * @return the HTML string representing the rules
     */
    String getRulesHtml();

}
