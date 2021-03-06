/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.helper.managedbean;

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
import util.exception.NoEnoughBufferForHelperException;
import util.exception.TaskEntityNotFoundException;
import util.exception.TaskTimeClashException;

/**
 *
 * @author Amber
 */
@Named(value = "viewAvailableTasksManagedBean")
@ViewScoped
public class ViewAvailableTasksManagedBean implements Serializable {

   
    @EJB
    private TaskControllerLocal taskControllerLocal;
    
    private TaskEntity task;
    private TaskEntity selectedTaskToView;
    private List<TaskEntity> tasksNotAssigned;
    private List<TaskEntity> filteredTasks1;
    private List<TaskEntity> filteredTasks2;
    private List<TaskEntity> tasksChoosenAsPreferredHelper;
    private HelperEntity helper;
    private TaskEntity preferredTask;

    
    
    public ViewAvailableTasksManagedBean() {
        System.err.println("********** ViewAvailableTasksManagedBean");
        
    }
    
    

    public ViewAvailableTasksManagedBean(TaskEntity task, List<TaskEntity> tasksNotAssigned, List<TaskEntity> filteredTasks, List<TaskEntity> tasksChoosenAsPreferredHelper, HelperEntity helper, TaskEntity preferredTask) {
        this.task = task;
        this.tasksNotAssigned = tasksNotAssigned;
        this.filteredTasks1 = filteredTasks;
        this.tasksChoosenAsPreferredHelper = tasksChoosenAsPreferredHelper;
        this.helper = helper;
        this.preferredTask = preferredTask;
    }
    
   
    
    
    @PostConstruct
    public void postConstruct()
    {
        try{
            System.err.println("********* Post Construct");
        tasksNotAssigned = taskControllerLocal.retrieveTaskNotAssigned();
        
       
        helper = (HelperEntity)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentHelperEntity");
        tasksChoosenAsPreferredHelper = taskControllerLocal.retrieveTaskByPreferredHelperId(helper.getHelperId());

   //         System.err.println("!!!!!!!!!!!this is the first task"+tasksNotAssigned.get(0).getTaskId());
        
          filteredTasks1 = new ArrayList<>();
        
        for(TaskEntity te:tasksNotAssigned)
        {
            filteredTasks1.add(te);
        }
        
          filteredTasks2 = new ArrayList<>();
        
        for(TaskEntity te:tasksChoosenAsPreferredHelper)
        {
            filteredTasks2.add(te);
        }
        
        

        }catch(TaskEntityNotFoundException ex){
        
        }
        
    }
    
    public void helperTakeTask(ActionEvent event)
    {
        try{
            System.err.println("********task is taken");
        TaskEntity taskToTake = (TaskEntity)event.getComponent().getAttributes().get("task");
          System.err.println(taskToTake.getTaskId() + "; " + helper.getHelperId());
        taskControllerLocal.assignHelperToTask(helper.getHelperId(), taskToTake.getTaskId());
        
        if(tasksNotAssigned.contains(taskToTake)){
            tasksNotAssigned.remove(taskToTake);
        }
        else{
            tasksChoosenAsPreferredHelper.remove(taskToTake);
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Task " + taskToTake.getTaskId() + " taken successfully", "Task " + taskToTake.getTaskId() + " taken successfully"));
        }
        catch(NoEnoughBufferForHelperException|TaskTimeClashException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(),null));
            System.err.println("*********** ERROR: " + ex.getMessage());
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
    public List<TaskEntity> getFilteredTasks1() {
        return filteredTasks1;
    }

    /**
     * @param filteredTasks the filteredTasks to set
     */
    public void setFilteredTasks1(List<TaskEntity> filteredTasks) {
        this.filteredTasks1 = filteredTasks;
    }
    
     /**
     * @return the filteredTasks
     */
    public List<TaskEntity> getFilteredTasks2() {
        return filteredTasks2;
    }

    /**
     * @param filteredTasks the filteredTasks to set
     */
    public void setFilteredTasks2(List<TaskEntity> filteredTasks) {
        this.filteredTasks2 = filteredTasks;
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
