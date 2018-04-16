/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.PaymentEntity;
import entity.TaskEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.constant.CompanyConstant;
import util.constant.TimeConstant;
import util.enumeration.PaymentStatus;
import util.exception.PaymentEntityNotFoundException;
import util.stringConstant.PaymentStatusString;

/**
 *
 * @author panjiyun
 */
@Stateless
public class PaymentController implements PaymentControllerLocal {

    @PersistenceContext(unitName = "CareTaskPro-ejbPU")
    private EntityManager em;

    @EJB
    private AutoPaymentTransactionTimerSessionBeanLocal autoPaymentTransactionTimerSessionBean;

    @Override
    public PaymentEntity createPaymentEntity(TaskEntity taskEntity) {
        BigDecimal chargeRate = taskEntity.getHelperEntity().getChargeRate();
        Long numOfHour = (taskEntity.getEndDateTime().getTime() - taskEntity.getStartDateTime().getTime()) / TimeConstant.MILLISEC_PER_HOUR;
        BigDecimal paymentAmount = chargeRate.multiply((new BigDecimal(numOfHour)));
        BigDecimal companyRevenue = paymentAmount.multiply(CompanyConstant.COMPANY_CHARGE_PERCENTAGE);
        BigDecimal helperSalary = paymentAmount.subtract(companyRevenue);

        PaymentEntity paymentEntity = new PaymentEntity(taskEntity, PaymentStatusString.PENDING, paymentAmount, helperSalary, new Date(System.currentTimeMillis()), companyRevenue);

        em.persist(paymentEntity);
        em.flush();
        em.refresh(paymentEntity);

        taskEntity.setPaymentEntity(paymentEntity);
        em.merge(taskEntity);

        Long durationForPayment = TimeConstant.TIME_LAG_FOR_PAYMENT + taskEntity.getEndDateTime().getTime() - System.currentTimeMillis();
        autoPaymentTransactionTimerSessionBean.createPaymentTransactionTimer(taskEntity.getTaskId(), durationForPayment);
        
      
        return paymentEntity;
    }

    @Override
    public List<PaymentEntity> retrievePaymentByHelperId(Long helperId) throws PaymentEntityNotFoundException {
        List<PaymentEntity> payments;

        payments = em.createQuery("SELECT p FROM PaymentEntity p WHERE p.taskEntity.helperEntity.helperId = :inHelperId")
                .setParameter("inHelperId", helperId)
                .getResultList();
        if (payments != null && !payments.isEmpty()) {
            for (PaymentEntity p : payments) {
                p.getPaymentId();
            }
            return payments;
        } else {
            throw new PaymentEntityNotFoundException("No payment is found.");
        }
    }

    @Override
    public List<PaymentEntity> retrievePaymentByRequesterId(Long requesterId) throws PaymentEntityNotFoundException {
        List<PaymentEntity> payments;

        payments = em.createQuery("SELECT p FROM PaymentEntity p WHERE p.taskEntity.requesterEntity.requesterId = :inRequesterId")
                .setParameter("inRequesterId", requesterId)
                .getResultList();
        if (payments != null && !payments.isEmpty()) {
            for (PaymentEntity p : payments) {
                p.getPaymentId();
            }
            return payments;
        } else {
            throw new PaymentEntityNotFoundException("No payment is found.");
        }
    }

    public void suspendPayment(Long paymentId) {
        PaymentEntity paymentEntity = em.find(PaymentEntity.class, paymentId);
        paymentEntity.setPaymentStatus(PaymentStatusString.SUSPENDED);
        em.merge(paymentEntity);
    }
    
    @Override
    public List<PaymentEntity> retrieveAllPayment() {
        Query query = em.createQuery("SELECT p FROM PaymentEntity p");
        return query.getResultList();

    }

}
