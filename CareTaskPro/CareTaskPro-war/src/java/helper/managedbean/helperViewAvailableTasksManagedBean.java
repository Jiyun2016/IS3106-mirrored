/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.managedbean;

import ejb.session.stateless.TaskControllerLocal;
import entity.HelperEntity;
import entity.TaskEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.TaskEntityNotFoundException;

/**
 *
 * @author Amber
 */
@Named(value = "helperViewAvailableTasksManagedBean")
@ViewScoped
public class helperViewAvailableTasksManagedBean implements Serializable {

   
    @EJB
    private TaskControllerLocal taskControllerLocal;
    
    private TaskEntity task;
    private TaskEntity selectedTaskToView;
    private List<TaskEntity> tasksNotAssigned;
    private List<TaskEntity> filteredTasks;
    private List<TaskEntity> tasksChoosenAsPreferredHelper;
    private HelperEntity helper;
    private TaskEntity preferredTask;

    public helperViewAvailableTasksManagedBean(TaskEntity task, List<TaskEntity> tasksNotAssigned, List<TaskEntity> filteredTasks, List<TaskEntity> tasksChoosenAsPreferredHelper, HelperEntity helper, TaskEntity preferredTask) {
        this.task = task;
        this.tasksNotAssigned = tasksNotAssigned;
        this.filteredTasks = filteredTasks;
        this.tasksChoosenAsPreferredHelper = tasksChoosenAsPreferredHelper;
        this.helper = helper;
        this.preferredTask = preferredTask;
    }

   
    
    
    @PostConstruct
    public void postConstruct()
    {
        try{
        tasksNotAssigned = taskControllerLocal.retrieveTaskNotAssigned();
        helper = (HelperEntity)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentHelperEntity");
        tasksChoosenAsPreferredHelper = taskControllerLocal.retrieveTaskCompletedByHelperId(helper.getHelperId());
            System.err.println("!!!!!!!!!!!this is the first task"+tasksNotAssigned.get(0).getTaskId());
        }catch(TaskEntityNotFoundException ex){
        
        }
        
    }
    
    public void helperTakeTask(ActionEvent event)
    {
        TaskEntity taskToTake = (TaskEntity)event.getComponent().getAttributes().get("taskToTake");
   //     taskControllerLocal.assignHelperToTask(helper.getHelperId(), taskToTake.getTaskId());
        
        if(tasksNotAssigned.contains(taskToTake)){
            tasksNotAssigned.remove(taskToTake);
        }
        else{
            tasksChoosenAsPreferredHelper.remove(taskToTake);
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Task " + taskToTake.getTaskId() + " taken successfully", "Task " + taskToTake.getTaskId() + " taken successfully"));
        
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
     * @return the tasksNotAssigned
     */
    public List<TaskEntity> getTasksNotAssigned() {
        return tasksNotAssigned;
    }

    /**
     * @param tasksNotAssigned the tasksNotAssigned to set
     */
    public void setTasksNotAssigned(List<TaskEntity> tasksNotAssigned) {
        this.tasksNotAssigned = tasksNotAssigned;
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

    /**
     * @return the tasksChoosenAsPreferredHelper
     */
    public List<TaskEntity> getTasksChoosenAsPreferredHelper() {
        return tasksChoosenAsPreferredHelper;
    }

    /**
     * @param tasksChoosenAsPreferredHelper the tasksChoosenAsPreferredHelper to set
     */
    public void setTasksChoosenAsPreferredHelper(List<TaskEntity> tasksChoosenAsPreferredHelper) {
        this.tasksChoosenAsPreferredHelper = tasksChoosenAsPreferredHelper;
    }
    
}
