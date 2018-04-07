package ejb.session.stateless;

import entity.PaymentEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.PaymentNotFoundException;

/**
 *
 * @author Yap Jun Hao
 */
@Stateless
public class PaymentController implements PaymentControllerLocal {

    @PersistenceContext(unitName = "CareTaskPro-ejbPU")
    private EntityManager em;

    @Override
    public PaymentEntity createNewPayment(PaymentEntity paymentEntity) {
        em.persist(paymentEntity);
        em.flush();
        em.refresh(paymentEntity);
        return paymentEntity;
    }

    @Override
    public PaymentEntity retrievePaymentById(long paymentId) throws PaymentNotFoundException {
        PaymentEntity paymentEntity = em.find(PaymentEntity.class, paymentId);
        if (paymentEntity != null) {
            return paymentEntity;
        } else {
            throw new PaymentNotFoundException("Payment with id " + paymentId + " does not exist!");
        }
    }
    
    @Override
    public PaymentEntity updateReview(PaymentEntity paymentEntity) {
        em.merge(paymentEntity);
        em.refresh(paymentEntity);
        return paymentEntity;
    }

    @Override
    public void deleteReview(Long paymentId) throws PaymentNotFoundException {
        PaymentEntity paymentEntity = retrievePaymentById(paymentId);
        em.remove(paymentEntity);
    }

    @Override
    public void persist(Object object) {
        em.persist(object);
    }
    
}
