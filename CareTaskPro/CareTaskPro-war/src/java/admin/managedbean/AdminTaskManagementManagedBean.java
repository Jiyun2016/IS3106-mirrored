/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.managedbean;

import ejb.session.stateless.TaskControllerLocal;
import entity.TaskEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import util.exception.TaskEntityNotFoundException;

/**
 *
 * @author panjiyun
 */
@Named(value = "adminTaskManagementManagedBean")
@Dependent
public class AdminTaskManagementManagedBean {

    @EJB(name = "TaskControllerLocal")
    private TaskControllerLocal taskControllerLocal;

    private List<TaskEntity> taskEntities;
    private List<TaskEntity> filteredTaskEntities;

    private TaskEntity taskEntityToView;
    private TaskEntity taskEntityToUpdate;

    private Long taskIdToView;
    private Long taskIdToUpdate;

    public AdminTaskManagementManagedBean() {

        filteredTaskEntities = new ArrayList<TaskEntity>();
    }

    @PostConstruct
    public void postConstruct() {
        setTaskEntities(taskControllerLocal.retrieveAllTask());
        if (getTaskEntities().isEmpty() || getTaskEntities() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No Task found. ", null));
            setTaskEntities(new ArrayList<TaskEntity>());
        }
    }

    public void updateTask(ActionEvent event) throws IOException {
        try {
            taskControllerLocal.updateTaskEntity(getTaskEntityToUpdate());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Task updated successfully", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }

    /**
     * @return the taskEntities
     */
    public List<TaskEntity> getTaskEntities() {
        return taskEntities;
    }

    /**
     * @param taskEntities the taskEntities to set
     */
    public void setTaskEntities(List<TaskEntity> taskEntities) {
        this.taskEntities = taskEntities;
    }

    /**
     * @return the filteredTaskEntities
     */
    public List<TaskEntity> getFilteredTaskEntities() {
        return filteredTaskEntities;
    }

    /**
     * @param filteredTaskEntities the filteredTaskEntities to set
     */
    public void setFilteredTaskEntities(List<TaskEntity> filteredTaskEntities) {
        this.filteredTaskEntities = filteredTaskEntities;
    }

    /**
     * @return the taskEntityToView
     */
    public TaskEntity getTaskEntityToView() {
        return taskEntityToView;
    }

    /**
     * @param taskEntityToView the taskEntityToView to set
     */
    public void setTaskEntityToView(TaskEntity taskEntityToView) {
        this.taskEntityToView = taskEntityToView;
    }

    /**
     * @return the taskEntityToUpdate
     */
    public TaskEntity getTaskEntityToUpdate() {
        return taskEntityToUpdate;
    }

    /**
     * @param taskEntityToUpdate the taskEntityToUpdate to set
     */
    public void setTaskEntityToUpdate(TaskEntity taskEntityToUpdate) {
        this.taskEntityToUpdate = taskEntityToUpdate;
    }

    /**
     * @return the taskIdToView
     */
    public Long getTaskIdToView() {
        return taskIdToView;
    }

    /**
     * @param taskIdToView the taskIdToView to set
     */
    public void setTaskIdToView(Long taskIdToView) {
        this.taskIdToView = taskIdToView;
    }

    /**
     * @return the taskIdToUpdate
     */
    public Long getTaskIdToUpdate() {
        return taskIdToUpdate;
    }

    /**
     * @param taskIdToUpdate the taskIdToUpdate to set
     */
    public void setTaskIdToUpdate(Long taskIdToUpdate) {
        this.taskIdToUpdate = taskIdToUpdate;
    }

}
