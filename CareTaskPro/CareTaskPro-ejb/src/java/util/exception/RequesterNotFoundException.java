package util.exception;

/**
 *
 * @author Bowen
 */
public class RequesterNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>RequesterNotFoundException</code> without
     * detail message.
     */
    public RequesterNotFoundException() {
    }

    /**
     * Constructs an instance of <code>RequesterNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public RequesterNotFoundException(String msg) {
        super(msg);
    }
}
