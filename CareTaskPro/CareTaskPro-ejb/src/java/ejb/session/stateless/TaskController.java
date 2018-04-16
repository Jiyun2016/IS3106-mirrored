/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.HelperEntity;
import entity.PaymentEntity;
import entity.ReviewEntity;
import entity.TaskEntity;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.constant.TimeConstant;
import util.enumeration.Category;
import util.enumeration.TaskStatus;
import util.exception.CancelTaskException;
import util.exception.TaskEntityNotFoundException;

/**
 *
 * @author panjiyun
 */
@Stateless
public class TaskController implements TaskControllerLocal {

    @EJB(name = "PaymentControllerLocal")
    private PaymentControllerLocal paymentControllerLocal;

    @EJB
    private NoResponderAutoCloseTimerSessionBeanLocal noResponderAutoCloseTimerSessionBean;

    @PersistenceContext(unitName = "CareTaskPro-ejbPU")
    private EntityManager em;

    @Resource
    private EJBContext eJBContext;

    @Override
    public TaskEntity createNewTask(TaskEntity taskEntity) {
        em.persist(taskEntity);
        em.flush();
        em.refresh(taskEntity);

        Long durationBeforeCancel = taskEntity.getStartDateTime().getTime() - TimeConstant.TIME_BUFFER_FOR_CANCEL - System.currentTimeMillis();
        noResponderAutoCloseTimerSessionBean.createNoResponderAutoCloseTimer(taskEntity.getTaskId(), durationBeforeCancel);

        return taskEntity;
    }

    @Override
    public List<TaskEntity> retrieveAllTask() {
        Query query = em.createQuery("SELECT t FROM TaskEntity t");
        return query.getResultList();

    }

    @Override
    public TaskEntity retrieveTaskById(Long taskId) throws TaskEntityNotFoundException {
        TaskEntity taskEntity = em.find(TaskEntity.class, taskId);
        if (taskEntity != null) {
            return taskEntity;
        } else {
            throw new TaskEntityNotFoundException("Task with id " + taskId + " does not exist!");
        }
    }

    @Override
    public List<TaskEntity> retrieveTaskByStatusByRequesterId(Long requesterId, TaskStatus status) throws TaskEntityNotFoundException {
        List<TaskEntity> tasks;
        tasks = em.createQuery("SELECT task FROM TaskEntity task WHERE task.taskStatus = :status AND task.requesterEntity.requesterId = :requesterId")
                .setParameter("status", status)
                .setParameter("requesterId", requesterId)
                .getResultList();
        if (tasks != null && !tasks.isEmpty()) {
            for (TaskEntity t : tasks) {
                t.getTaskId();
            }
            return tasks;
        } else {
            throw new TaskEntityNotFoundException("The requester with id " + requesterId + " has no " + status.toString() + " task.");
        }

    }

    @Override
    public List<TaskEntity> retrieveTaskByStatusByHelperId(Long helperId, TaskStatus status) throws TaskEntityNotFoundException {
        List<TaskEntity> tasks;
        tasks = em.createQuery("SELECT task FROM TaskEntity task WHERE task.taskStatus = :status AND task.requesterEntity.requesterId = :helperId")
                .setParameter("status", status)
                .setParameter("helperId", helperId)
                .getResultList();
        if (tasks != null && !tasks.isEmpty()) {
            for (TaskEntity t : tasks) {
                t.getTaskId();
            }
            return tasks;
        } else {
            throw new TaskEntityNotFoundException("The helper with id " + helperId + " has no " + status.toString() + " task.");
        }

    }

    @Override
    public List<TaskEntity> retrieveTaskInProcessByAssignedHelperId(Long helperId) throws TaskEntityNotFoundException {
        List<TaskEntity> tasks;
        tasks = em.createQuery("SELECT task FROM TaskEntity task WHERE task.assignedHelper.id = :helperId AND task.taskStatus = :status")
                .setParameter("helperId", helperId.toString())
                .setParameter("status", TaskStatus.ASSIGNED)
                .getResultList();

        if (tasks != null && !tasks.isEmpty()) {
            for (TaskEntity t : tasks) {
                t.getTaskId();
            }
            return tasks;
        } else {
            throw new TaskEntityNotFoundException("No task is found assigned to the helper with id " + helperId);
        }

    }

    @Override
    public List<TaskEntity> retrieveTaskCompletedByHelperId(Long helperId) throws TaskEntityNotFoundException {
        List<TaskEntity> tasks;

        tasks = em.createQuery("SELECT DISTINCT t FROM TaskEntity t WHERE t.assignedHelper.id = :helperId AND task.taskStatus = :status")
                .setParameter("helperId", helperId.toString())
                .setParameter("status", TaskStatus.COMPLETED)
                .getResultList();

        if (tasks != null && !tasks.isEmpty()) {
            for (TaskEntity t : tasks) {
                t.getTaskId();
            }
            return tasks;
        } else {
            throw new TaskEntityNotFoundException("No task is completed by helper with id " + helperId);
        }

    }

    @Override
    public List<TaskEntity> retrieveTaskByPreferredHelperId(Long helperId) throws TaskEntityNotFoundException {
        List<TaskEntity> tasks;

        tasks = em.createQuery("SELECT DISTINCT t FROM TaskEntity t, IN (t.preferredHelpers) p WHERE p.id = :helperId")
                .setParameter("helperId", helperId)
                .getResultList();

        if (tasks != null && !tasks.isEmpty()) {
            for (TaskEntity t : tasks) {
                t.getTaskId();
            }
            return tasks;
        } else {
            throw new TaskEntityNotFoundException("No task is recommended to the helper with id " + helperId);
        }

    }

    @Override
    public List<TaskEntity> retrieveTaskByCategory(Category category) throws TaskEntityNotFoundException {
        List<TaskEntity> tasks;

        tasks = em.createQuery("SELECT DISTINCT t FROM TaskEntity t WHERE t.category = :category")
                .setParameter("category", category)
                .getResultList();

        if (tasks != null && !tasks.isEmpty()) {
            for (TaskEntity t : tasks) {
                t.getTaskId();
            }
            return tasks;
        } else {
            throw new TaskEntityNotFoundException("No task is under category " + category.toString());
        }

    }

    @Override
    public List<TaskEntity> retrieveTaskAssigned() throws TaskEntityNotFoundException {
        List<TaskEntity> tasks;

        tasks = em.createQuery("SELECT DISTINCT t FROM TaskEntity t WHERE t.taskStatus = :inStatus")
                .setParameter("inStatus", TaskStatus.ASSIGNED)
                .getResultList();

        if (tasks != null && !tasks.isEmpty()) {
            for (TaskEntity t : tasks) {
                t.getTaskId();
            }
            return tasks;
        } else {
            throw new TaskEntityNotFoundException("No task is assigned");
        }

    }

    @Override
    public List<TaskEntity> retrieveTaskNotAssigned() throws TaskEntityNotFoundException {
        List<TaskEntity> tasks;

        tasks = em.createQuery("SELECT DISTINCT t FROM TaskEntity t WHERE t.taskStatus = :inStatus")
                .setParameter("inStatus", TaskStatus.PENDING)
                .getResultList();

        if (tasks != null && !tasks.isEmpty()) {
            for (TaskEntity t : tasks) {
                t.getTaskId();
            }
            return tasks;
        } else {
            throw new TaskEntityNotFoundException("No task is assigned");
        }

    }

    @Override
    public List<TaskEntity> retrieveTaskComplained() throws TaskEntityNotFoundException {
        List<TaskEntity> tasks;

        tasks = em.createQuery("SELECT DISTINCT t FROM TaskEntity t WHERE t.taskStatus = :inStatus")
                .setParameter("inStatus", TaskStatus.COMPLAINED)
                .getResultList();

        if (tasks != null && !tasks.isEmpty()) {
            for (TaskEntity t : tasks) {
                t.getTaskId();
            }
            return tasks;
        } else {
            throw new TaskEntityNotFoundException("No task is assigned");
        }

    }

    @Override
    public TaskEntity updateTaskEntityByRequester(TaskEntity ta, Long taId) {
        
        TaskEntity t = em.find(TaskEntity.class, taId);
        t.setCategory(ta.getCategory());
        t.setDescription(ta.getDescription());
        t.setEndDateTime(ta.getEndDateTime());
        t.setPreferredHelpers(ta.getPreferredHelpers());
        t.setStartDateTime(ta.getStartDateTime());

        return t;
    }

    @Override
    public TaskEntity updateTaskEntity(TaskEntity ta) {
        em.merge(ta);
        ta = em.find(TaskEntity.class, ta.getTaskId());

        return ta;
    }

    @Override
    public TaskEntity assignHelperToTask(Long HelperId, Long taskId) {
        HelperEntity helper = em.find(HelperEntity.class, HelperId);
        TaskEntity task = em.find(TaskEntity.class, taskId);
        task.setHelperEntity(helper);
        task.setTaskStatus(TaskStatus.ASSIGNED);
        helper.getTaskEntities().add(task);
        em.merge(task);
        em.merge(helper);
        PaymentEntity payment = paymentControllerLocal.createPaymentEntity(task);
        task = em.find(TaskEntity.class, taskId);

        return task;
    }

    @Override
    public TaskEntity setTaskAsComplained(Long taskId) {

        TaskEntity task = em.find(TaskEntity.class, taskId);
        task.setTaskStatus(TaskStatus.COMPLAINED);

        em.merge(task);
        task = em.find(TaskEntity.class, taskId);

        return task;
    }

    @Override
    public TaskEntity setTaskAsCancelled(Long taskId) throws CancelTaskException {

        TaskEntity task = em.find(TaskEntity.class, taskId);

        if (task.getTaskStatus().equals(TaskStatus.PENDING)) {
            task.setTaskStatus(TaskStatus.CANCELLED);
            System.err.println("1 ^^^^^^^^taskcontroller: task status is " + task.getTaskStatus());
            em.merge(task);
            task = em.find(TaskEntity.class, taskId);
            System.err.println("2 ^^^^^^^^taskcontroller: task status is " + task.getTaskStatus());
            return task;
        } else {
            throw new CancelTaskException(" Task is already assigned.");
        }
    }

    @Override
    public TaskEntity addReviewToTask(Long taskId, ReviewEntity reviewEntity) {
        TaskEntity task = em.find(TaskEntity.class, taskId);

        task.setReviewEntity(reviewEntity);
        reviewEntity.setTaskEntity(task);

        em.merge(task);
        em.merge(reviewEntity);

        return task;

    }
    
    @Override
    public List<TaskEntity> retrieveTasksByStatus(TaskStatus status) {
        List<TaskEntity> tasks;
        tasks = em.createQuery("SELECT task FROM TaskEntity task WHERE task.taskStatus = :status")
                .setParameter("status", status)
                .getResultList();
        if (tasks != null && !tasks.isEmpty()) {
            for (TaskEntity t : tasks) {
                t.getTaskId();
            }
        }
        return tasks;
    }   

}
