package util.exception;

/**
 *
 * @author Bowen
 */
public class WrongCredentialException extends Exception {

    /**
     * Creates a new instance of <code>WrongCredentialException</code> without
     * detail message.
     */
    public WrongCredentialException() {
    }

    /**
     * Constructs an instance of <code>WrongCredentialException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public WrongCredentialException(String msg) {
        super(msg);
    }
}
