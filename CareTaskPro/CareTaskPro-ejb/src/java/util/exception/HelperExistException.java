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
public class HelperExistException extends Exception {

    /**
     * Creates a new instance of <code>HelperExistException</code> without
     * detail message.
     */
    public HelperExistException() {
    }

    /**
     * Constructs an instance of <code>HelperExistException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public HelperExistException(String msg) {
        super(msg);
    }
}
