/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.ReviewEntity;
import entity.TaskEntity;
import java.util.List;
import javax.ejb.Local;
import util.enumeration.Category;
import util.enumeration.TaskStatus;
import util.exception.CancelTaskException;
import util.exception.NoEnoughBufferForHelperException;
import util.exception.TaskEntityNotFoundException;
import util.exception.TaskTimeClashException;

/**
 *
 * @author panjiyun
 */
@Local
public interface TaskControllerLocal {

    public TaskEntity retrieveTaskById(Long taskId) throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskInProcessByAssignedHelperId(Long helperId) throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskCompletedByHelperId(Long helperId) throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskByPreferredHelperId(Long helperId) throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskByCategory(Category category) throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskAssigned() throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskNotAssigned() throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskComplained() throws TaskEntityNotFoundException;

    public TaskEntity updateTaskEntity(TaskEntity taskEntity);

    public TaskEntity createNewTask(TaskEntity taskEntity);
    
    public TaskEntity setTaskAsComplained(Long taskId);

    public List<TaskEntity> retrieveTaskByStatusByRequesterId(Long requesterId, String status) throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskByStatusByHelperId(Long helperId, String status) throws TaskEntityNotFoundException;

    public TaskEntity setTaskAsCancelled(Long taskId) throws CancelTaskException;

    public TaskEntity addReviewToTask(Long taskId, ReviewEntity reviewEntity);

    public List<TaskEntity> retrieveAllTask();

    public TaskEntity updateTaskEntityByRequester(TaskEntity ta,Long taId);

<<<<<<< HEAD
    public List<TaskEntity> retrieveTasksByStatus(TaskStatus status);
    
    public TaskEntity assignHelperToTask(Long HelperId, Long taskId)throws NoEnoughBufferForHelperException , TaskTimeClashException;

=======
    public List<TaskEntity> retrieveTasksByStatus(String status);
>>>>>>> 4fcb0c200f7b5c8a5c2055ee61313d046395718f

}
