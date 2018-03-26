package util.exception;

/**
 *
 * @author Bowen
 */
public class HelperNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>HelperNotFoundException</code> without
     * detail message.
     */
    public HelperNotFoundException() {
    }

    /**
     * Constructs an instance of <code>HelperNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public HelperNotFoundException(String msg) {
        super(msg);
    }
}
