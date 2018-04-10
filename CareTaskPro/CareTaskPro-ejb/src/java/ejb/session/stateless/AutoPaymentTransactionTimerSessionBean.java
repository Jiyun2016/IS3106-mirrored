/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.PaymentEntity;
import entity.TaskEntity;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.PaymentStatus;
import util.enumeration.TaskStatus;

/**
 *
 * @author panjiyun
 */
@Stateless

public class AutoPaymentTransactionTimerSessionBean implements AutoPaymentTransactionTimerSessionBeanLocal {

    @Resource
    private SessionContext sessionContext;

    @PersistenceContext(unitName = "CareTaskPro-ejbPU")
    private EntityManager em;

    public AutoPaymentTransactionTimerSessionBean() {
    }

    @Override
    public void createPaymentTransactionTimer(Long taskId, Long duration) {

        TimerService timerService = sessionContext.getTimerService();

        timerService.createSingleActionTimer(duration, new TimerConfig(taskId, true));
    }

    @Timeout
    public void handleTimeout(Timer timer) {
        System.out.println("********** PaymentTransactionTimer.handleTimeout(): the task try to complete payment transaction is " + timer.getInfo().toString());

        Long taskId = Long.parseLong(timer.getInfo().toString());
        TaskEntity taskEntity = em.find(TaskEntity.class, taskId);

        PaymentEntity paymentEntity = taskEntity.getPaymentEntity();
        Long paymentId = paymentEntity.getPaymentId();

        if (taskEntity.getTaskStatus().equals(TaskStatus.COMPLAINED)) {
            paymentEntity.setPaymentStatus(PaymentStatus.SUSPENDED);
            em.merge(paymentEntity);

        } else {
            paymentEntity.setPaymentStatus(PaymentStatus.COMPLETED);
            em.merge(paymentEntity);
            taskEntity.setTaskStatus(TaskStatus.COMPLETED);
            em.merge(taskEntity);
        }

        System.out.println("********** PaymentTransactionTimer.handleTimeout(): payment with id " + paymentId + " is " + paymentEntity.getPaymentStatus().toString());
        System.out.println("********** PaymentTransactionTimer.handleTimeout(): task with id " + taskId + " is " + taskEntity.getTaskStatus().toString());

    }

}
