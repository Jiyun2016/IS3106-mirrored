/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.managedbean;

import entity.TaskEntity;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Amber
 */
@Named(value = "helperViewTasksHistoryManagedBean")
@ViewScoped
public class helperViewTasksHistoryManagedBean implements Serializable{

    private List<TaskEntity> tasksHistory;
    private TaskEntity task;
    /**
     * Creates a new instance of helperViewTasksHistoryManagedBean
     */
    public helperViewTasksHistoryManagedBean() {
    }

    /**
     * @return the tasksHistory
     */
    public List<TaskEntity> getTasksHistory() {
        return tasksHistory;
    }

    /**
     * @param tasksHistory the tasksHistory to set
     */
    public void setTasksHistory(List<TaskEntity> tasksHistory) {
        this.tasksHistory = tasksHistory;
    }

    /**
     * @return the task
     */
    public TaskEntity getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(TaskEntity task) {
        this.task = task;
    }
    
    
}
