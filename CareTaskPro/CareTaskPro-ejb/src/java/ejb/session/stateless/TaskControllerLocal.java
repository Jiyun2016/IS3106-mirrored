/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.TaskEntity;
import java.util.List;
import javax.ejb.Local;
import util.enumeration.Category;
import util.exception.TaskEntityNotFoundException;

/**
 *
 * @author panjiyun
 */
@Local
public interface TaskControllerLocal {

    public TaskEntity retrieveTaskById(long taskId) throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskInProcessByAssignedHelperId(Long helperId) throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskCompletedByHelperId(long helperId) throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskByPreferredHelperId(Long helperId) throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskByCategory(Category category) throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskAssigned() throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskNotAssigned() throws TaskEntityNotFoundException;

    public List<TaskEntity> retrieveTaskComplained() throws TaskEntityNotFoundException;

    public TaskEntity updateTaskEntity(TaskEntity taskEntity);

    public TaskEntity createNewTask(TaskEntity taskEntity);

}
