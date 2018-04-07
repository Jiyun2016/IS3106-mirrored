/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author Yap Jun Hao
 */
public class AdminPasswordChangeException extends Exception {

    /**
     * Creates a new instance of <code>AdminPasswordChangeException</code>
     * without detail message.
     */
    public AdminPasswordChangeException() {
    }

    /**
     * Constructs an instance of <code>AdminPasswordChangeException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public AdminPasswordChangeException(String msg) {
        super(msg);
    }
}
