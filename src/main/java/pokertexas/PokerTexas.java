package pokertexas;
import pokertexas.view.ViewImpl;

/**
 * Main class of the application.
 */
public final class PokerTexas {

    private PokerTexas() {
    }

    /**
     * Main method of the application.
     * @param args unused
     */
    public static void main(final String[] args) {
        new ViewImpl();
    }
}
