/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.TaskEntity;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.TaskStatus;
import util.stringConstant.TaskStatusString;

/**
 *
 * @author panjiyun
 */
@Stateless
public class NoResponderAutoCloseTimerSessionBean implements NoResponderAutoCloseTimerSessionBeanLocal {

    @Resource
    private SessionContext sessionContext;

    @PersistenceContext(unitName = "CareTaskPro-ejbPU")
    private EntityManager em;

    public NoResponderAutoCloseTimerSessionBean() {
    }

    @Override
    public void createNoResponderAutoCloseTimer(Long taskId, Long duration) {

        TimerService timerService = sessionContext.getTimerService();

        timerService.createSingleActionTimer(duration, new TimerConfig(taskId, true));
    }

    @Timeout
    public void handleTimeout(Timer timer) {
        Long taskId = Long.parseLong(timer.getInfo().toString());
        TaskEntity taskEntity = em.find(TaskEntity.class, taskId);

        if (taskEntity.getTaskStatus().equals(TaskStatusString.PENDING)) {

            System.out.println("********** NoResponderAutoCloseTimer.handleTimeout(): the task to be closed is " + timer.getInfo().toString());

            taskEntity.setTaskStatus(TaskStatusString.CANCELLED);
            em.merge(taskEntity);

        }
            System.out.println("********** NoResponderAutoCloseTimer.handleTimeout(): the task with id " + taskEntity.getTaskId() + " is " +  taskEntity.getTaskStatus().toString());

        
    }

}
