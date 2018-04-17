/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.managedbean;

import ejb.session.stateless.HelperControllerLocal;
import ejb.session.stateless.TaskControllerLocal;
import entity.HelperEntity;
import entity.TaskEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import util.exception.TaskEntityNotFoundException;

/**
 *
 * @author Amber
 */
@Named(value = "helperViewUpcomingTasksManagedBean")
@ViewScoped
public class helperViewTasksManagedBean implements Serializable{

    @EJB
    private TaskControllerLocal taskController;

    @EJB
    private HelperControllerLocal helperController;
    
    private List<TaskEntity> tasks;
    private TaskEntity task;
    private HelperEntity helper;
    private TaskEntity taskToView;
    
    private ScheduleModel eventModel;
 
    private ScheduleEvent event = new DefaultScheduleEvent();

    public helperViewTasksManagedBean() {
        this.taskToView = new TaskEntity();
    }
    
    
    public helperViewTasksManagedBean(List<TaskEntity> tasks, HelperEntity helper,TaskEntity taskToView) {
        this.helper = helper;
        this.tasks = tasks;
        this.taskToView = taskToView;
        
    }
    
    
    @PostConstruct
    public void init(){
        try{
        eventModel = new DefaultScheduleModel();
        helper = (HelperEntity)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentHelperEntity");
        tasks = taskController.retrieveTaskInProcessByAssignedHelperId(helper.getHelperId());
        for(TaskEntity t: tasks){
            eventModel.addEvent(new DefaultScheduleEvent(t.getCategory(),t.getStartDateTime(),t.getEndDateTime(),t.getRequesterEntity()));
        }
        }catch(TaskEntityNotFoundException ex){
            
        }
    }

     public void onEventSelect(SelectEvent selectEvent) {
        setEvent((ScheduleEvent) selectEvent.getObject());
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
     * @return the helper
     */
    public HelperEntity getHelper() {
        return helper;
    }

    /**
     * @param helper the helper to set
     */
    public void setHelper(HelperEntity helper) {
        this.helper = helper;
    }

    /**
     * @return the taskToView
     */
    public TaskEntity getTaskToView() {
        return taskToView;
    }

    /**
     * @param taskToView the taskToView to set
     */
    public void setTaskToView(TaskEntity taskToView) {
        this.taskToView = taskToView;
    }

    /**
     * @return the eventModel
     */
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    /**
     * @param eventModel the eventModel to set
     */
    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    /**
     * @return the event
     */
    public ScheduleEvent getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
}
