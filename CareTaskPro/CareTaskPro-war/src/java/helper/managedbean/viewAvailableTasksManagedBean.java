/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.managedbean;

import ejb.session.stateless.TaskControllerLocal;
import entity.TaskEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.enumeration.Category;
import util.exception.TaskEntityNotFoundException;

/**
 *
 * @author Amber
 */
@Named(value = "viewAvailabelTasksManagedBean")
@ViewScoped
public class viewAvailableTasksManagedBean  implements Serializable {

   
    @EJB
    private TaskControllerLocal taskControllerLocal;
    
    private TaskEntity task;
    private TaskEntity selectedTaskToView;
    private TaskEntity selectedTaskToTake;
    private List<TaskEntity> tasks;
    private List<TaskEntity> filteredTasks;
    
    public viewAvailableTasksManagedBean() {
        tasks = new ArrayList<>();
    }
    
    
    @PostConstruct
    public void postConstruct()
    {
        try{
        tasks = taskControllerLocal.retrieveTaskByCategory(Category.HOUSEWORK);//change category to helper's category
        
        }
        catch(TaskEntityNotFoundException ex){
            //dialog box showing no tasks available
        }
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

    /**
     * @return the selectedTaskToView
     */
    public TaskEntity getSelectedTaskToView() {
        return selectedTaskToView;
    }

    /**
     * @param selectedTaskToView the selectedTaskToView to set
     */
    public void setSelectedTaskToView(TaskEntity selectedTaskToView) {
        this.selectedTaskToView = selectedTaskToView;
    }

    /**
     * @return the selectedTaskToTake
     */
    public TaskEntity getSelectedTaskToTake() {
        return selectedTaskToTake;
    }

    /**
     * @param selectedTaskToTake the selectedTaskToTake to set
     */
    public void setSelectedTaskToTake(TaskEntity selectedTaskToTake) {
        this.selectedTaskToTake = selectedTaskToTake;
    }

    /**
     * @return the tasks
     */
    public List<TaskEntity> getTasks() {
        return tasks;
    }

    /**
     * @param tasks the tasks to set
     */
    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return the filteredTasks
     */
    public List<TaskEntity> getFilteredTasks() {
        return filteredTasks;
    }

    /**
     * @param filteredTasks the filteredTasks to set
     */
    public void setFilteredTasks(List<TaskEntity> filteredTasks) {
        this.filteredTasks = filteredTasks;
    }
    
}
