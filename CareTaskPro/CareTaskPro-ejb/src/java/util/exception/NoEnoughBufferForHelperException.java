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
public class NoEnoughBufferForHelperException extends Exception {

    /**
     * Creates a new instance of <code>NoEnoughBufferForHelperException</code>
     * without detail message.
     */
    public NoEnoughBufferForHelperException() {
    }

    /**
     * Constructs an instance of <code>NoEnoughBufferForHelperException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoEnoughBufferForHelperException(String msg) {
        super(msg);
    }
}
