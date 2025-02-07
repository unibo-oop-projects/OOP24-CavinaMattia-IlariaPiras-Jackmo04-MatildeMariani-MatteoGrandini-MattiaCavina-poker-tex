package controller.rules;

import controller.scene.SceneController;

/**
 *  Interface for the rules controller.
 */
public interface RulesController extends SceneController {

    /**
     * Returns the HTML content of the rules.
     * @return the HTML content of the rules
     */
    String getRulesHtml();

}
