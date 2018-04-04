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
public class AdminEntityNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>AdminEntityNotFoundException</code>
     * without detail message.
     */
    public AdminEntityNotFoundException() {
    }

    /**
     * Constructs an instance of <code>AdminEntityNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public AdminEntityNotFoundException(String msg) {
        super(msg);
    }
}
