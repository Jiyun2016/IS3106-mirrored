/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author panjiyun
 */
public class CancelTaskException extends Exception {

    /**
     * Creates a new instance of <code>CancelTaskException</code> without detail
     * message.
     */
    public CancelTaskException() {
    }

    /**
     * Constructs an instance of <code>CancelTaskException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CancelTaskException(String msg) {
        super(msg);
    }
}
