/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

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
    public void createPaymentTransactionTimer(long taskId, Date expiration) {

        TimerService timerService = sessionContext.getTimerService();

        timerService.createSingleActionTimer(expiration, new TimerConfig(taskId, true));
    }

    @Timeout
    public void handleTimeout(Timer timer) {
        System.out.println("********** PaymentTransactionTimer.handleTimeout(): the task to complete payment transaction is " + timer.getInfo().toString());
        long auctionListId = Long.parseLong(timer.getInfo().toString());
        
        //transction
        //...
        
        //notify requester,helper
        //...

    }

    

}
