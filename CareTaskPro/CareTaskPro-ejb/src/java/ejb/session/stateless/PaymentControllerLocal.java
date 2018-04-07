/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.PaymentEntity;
import javax.ejb.Local;
import util.exception.PaymentNotFoundException;

/**
 *
 * @author Yap Jun Hao
 */
@Local
public interface PaymentControllerLocal {

    public PaymentEntity createNewPayment(PaymentEntity paymentEntity);

    public PaymentEntity retrievePaymentById(long paymentId) throws PaymentNotFoundException;

    public PaymentEntity updateReview(PaymentEntity paymentEntity);

    public void deleteReview(Long paymentId) throws PaymentNotFoundException;

    public void persist(Object object);
    
}
