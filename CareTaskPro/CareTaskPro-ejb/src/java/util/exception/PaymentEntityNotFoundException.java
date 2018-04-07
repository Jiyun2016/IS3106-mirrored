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
public class PaymentEntityNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>PaymentEntityNotFoundException</code>
     * without detail message.
     */
    public PaymentEntityNotFoundException() {
    }

    /**
     * Constructs an instance of <code>PaymentEntityNotFoundException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public PaymentEntityNotFoundException(String msg) {
        super(msg);
    }
}
