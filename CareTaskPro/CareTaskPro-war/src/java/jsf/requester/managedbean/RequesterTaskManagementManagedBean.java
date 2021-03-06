/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.requester.managedbean;

import ejb.session.stateless.HelperControllerLocal;
import ejb.session.stateless.RequesterControllerLocal;
import ejb.session.stateless.TaskControllerLocal;
import entity.HelperEntity;
import entity.RequesterEntity;
import entity.TaskEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.enumeration.Category;
import util.enumeration.TaskStatus;
import util.exception.CancelTaskException;
import util.exception.TaskEntityNotFoundException;
import util.stringConstant.CategoryString;
import util.stringConstant.TaskStatusString;

/**
 *
 * @author panjiyun
 */
@Named(value = "requesterTaskManagementManagedBean")
@ViewScoped
public class RequesterTaskManagementManagedBean implements Serializable {

    @EJB(name = "HelperControllerLocal")
    private HelperControllerLocal helperControllerLocal;

    @EJB(name = "RequesterControllerLocal")
    private RequesterControllerLocal requesterControllerLocal;

    @EJB(name = "TaskControllerLocal")
    private TaskControllerLocal taskControllerLocal;

    private String[] categories;

    private RequesterEntity requesterEntity;
    private Long requesterId;

    private TaskEntity taskEntityToView;
    private TaskEntity taskEntityToUpdate;
    private TaskEntity taskEntityToComplain;
    private TaskEntity taskEntityToCancel;
    private TaskEntity taskEntityToReview;

    private Long taskIdToView;
    private Long taskIdToUpdate;
    private Long taskIdToComplain;
    private Long taskIdToCancel;
    private Long taskIdToReview;

    private List<TaskEntity> taskEntities;
    private List<TaskEntity> taskEntitiesPending;
    private List<TaskEntity> taskEntitiesAssigned;
    private List<TaskEntity> taskEntitiesCompleted;
    private List<TaskEntity> taskEntitiesComplained;
    private List<TaskEntity> taskEntitiesCancelled;

    private List<TaskEntity> filteredTaskEntities;
    private List<TaskEntity> filteredTaskEntitiesPending;
    private List<TaskEntity> filteredTaskEntitiesAssigned;
    private List<TaskEntity> filteredTaskEntitiesCompleted;
    private List<TaskEntity> filteredTaskEntitiesComplained;
    private List<TaskEntity> filteredTaskEntitiesCancelled;

    private List<SelectItem> selectItemsHelperEntities;

    public RequesterTaskManagementManagedBean() {

        filteredTaskEntities = new ArrayList<TaskEntity>();
        selectItemsHelperEntities = new ArrayList<SelectItem>();
        //     taskEntityToUpdate = new TaskEntity();

    }

    @PostConstruct
    public void postConstruct() {
        setRequesterEntity((RequesterEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentRequesterEntity"));
        setRequesterId(getRequesterEntity().getRequesterId());
        //       setFilteredTaskEntities(taskEntities);
        categories = new String[]{CategoryString.COMPANIONSHIP, CategoryString.HEALTHCARE, CategoryString.HOUSEWORK};

        viewPendingTask();
        viewAssignedTask();
        viewComplainedTask();
        viewCompletedTask();
        viewCancelledTask();

        setFilteredTaskEntitiesPending(new ArrayList<>());
        for (TaskEntity te : taskEntitiesPending) {
            getFilteredTaskEntitiesPending().add(te);
        }

        setFilteredTaskEntitiesComplained(new ArrayList<>());
        for (TaskEntity te : taskEntitiesComplained) {
            getFilteredTaskEntitiesComplained().add(te);
        }

        setFilteredTaskEntitiesCompleted(new ArrayList<>());
        for (TaskEntity te : taskEntitiesCompleted) {
            getFilteredTaskEntitiesCompleted().add(te);
        }

        setFilteredTaskEntitiesAssigned(new ArrayList<>());
        for (TaskEntity te : taskEntitiesAssigned) {
            getFilteredTaskEntitiesAssigned().add(te);
        }
        
         setFilteredTaskEntitiesCancelled(new ArrayList<>());
        for (TaskEntity te : taskEntitiesCancelled) {
            getFilteredTaskEntitiesCancelled().add(te);
        }

        List<HelperEntity> helperEntities = helperControllerLocal.retrieveAllHelpers();
        for (HelperEntity helperEntity : helperEntities) {
            getSelectItemsHelperEntities().add(new SelectItem(helperEntity, helperEntity.getHelperId().toString(), helperEntity.getFirstName()));
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("HelperEntityConverter.helperEntities", helperEntities);

    }

    @PreDestroy
    public void preDestroy() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("HelperEntityConverter.helperEntities", null);
    }

    public void onChange() {
    }

    public void viewPendingTask() {
        try {
            setTaskEntitiesPending(taskControllerLocal.retrieveTaskByStatusByRequesterId(getRequesterId(), TaskStatusString.PENDING));

        } catch (TaskEntityNotFoundException ex) {
            //          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No task found: " + ex.getMessage(), null));
            taskEntitiesPending = new ArrayList<TaskEntity>();
        }
    }

    public void viewAssignedTask() {
        try {
            setTaskEntitiesAssigned(taskControllerLocal.retrieveTaskByStatusByRequesterId(getRequesterId(), TaskStatusString.ASSIGNED));

        } catch (TaskEntityNotFoundException ex) {
            //          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No task found: " + ex.getMessage(), null));
            taskEntitiesAssigned = new ArrayList<TaskEntity>();
        }
    }

    public void viewCompletedTask() {
        try {
            setTaskEntitiesCompleted(taskControllerLocal.retrieveTaskByStatusByRequesterId(getRequesterId(), TaskStatusString.COMPLETED));

        } catch (TaskEntityNotFoundException ex) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No task found: " + ex.getMessage(), null));
            taskEntitiesCompleted = new ArrayList<TaskEntity>();
        }
    }

    public void viewComplainedTask() {
        try {
            setTaskEntitiesComplained(taskControllerLocal.retrieveTaskByStatusByRequesterId(getRequesterId(), TaskStatusString.COMPLAINED));

        } catch (TaskEntityNotFoundException ex) {
            //         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No task found: " + ex.getMessage(), null));
            taskEntitiesComplained = new ArrayList<TaskEntity>();
        }
    }
    
    public void viewCancelledTask() {
        try {
            setTaskEntitiesCancelled(taskControllerLocal.retrieveTaskByStatusByRequesterId(getRequesterId(), TaskStatusString.CANCELLED));

        } catch (TaskEntityNotFoundException ex) {
            //         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No task found: " + ex.getMessage(), null));
            taskEntitiesCancelled = new ArrayList<TaskEntity>();
        }
    }

//    public void viewTaskDetail(ActionEvent event) throws IOException {
//        try {
//            setTaskIdToView((Long) event.getComponent().getAttributes().get("taskIdToView"));
//            setTaskEntityToView(taskControllerLocal.retrieveTaskById(getTaskIdToView()));
//        } catch (TaskEntityNotFoundException ex) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
//        }
//
//    }
    public void updateTask(ActionEvent event) throws IOException {
        try {
            System.err.println(".....task to update:" + taskEntityToUpdate.getTaskId());
            taskIdToUpdate = taskEntityToUpdate.getTaskId();
            taskControllerLocal.updateTaskEntityByRequester(getTaskEntityToUpdate(), taskIdToUpdate);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Task updated successfully", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }

    public void complainTask(ActionEvent event) throws IOException {
        setTaskIdToComplain((Long) event.getComponent().getAttributes().get("taskIdToComplain"));
        taskControllerLocal.setTaskAsComplained(getTaskIdToComplain());
        viewPendingTask();
        viewAssignedTask();
        viewComplainedTask();
        viewCompletedTask();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Task is set as complained", null));
    }

    public void cancelTask(ActionEvent event) throws IOException {
        setTaskIdToCancel((Long) event.getComponent().getAttributes().get("taskIdToCancel"));
        //   System.err.println(".....task "+ taskIdToCancel+ " is to be canceled");
        try {
            TaskEntity task = taskControllerLocal.setTaskAsCancelled(getTaskIdToCancel());
            System.err.println("task status now: " + task.getTaskStatus());
            viewPendingTask();
            viewAssignedTask();
            viewComplainedTask();
            viewCompletedTask();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Task is cancelled", null));

        } catch (CancelTaskException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Task is already assigned and cannot be cancelled", null));

        }

    }

    public void addReviewToTask(ActionEvent event) throws IOException {
        setTaskIdToReview((Long) event.getComponent().getAttributes().get("taskIdToReview"));
        System.err.println(".....taskIdToReview: " + taskIdToReview);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("taskIdToView", getTaskIdToReview());
        FacesContext.getCurrentInstance().getExternalContext().redirect("createReview.xhtml");

    }

    /**
     * @return the requesterEntity
     */
    public RequesterEntity getRequesterEntity() {
        return requesterEntity;
    }

    /**
     * @param requesterEntity the requesterEntity to set
     */
    public void setRequesterEntity(RequesterEntity requesterEntity) {
        this.requesterEntity = requesterEntity;
    }

    /**
     * @return the requesterId
     */
    public Long getRequesterId() {
        return requesterId;
    }

    /**
     * @param requesterId the requesterId to set
     */
    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
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
     * @return the taskEntityToComplain
     */
    public TaskEntity getTaskEntityToComplain() {
        return taskEntityToComplain;
    }

    /**
     * @param taskEntityToComplain the taskEntityToComplain to set
     */
    public void setTaskEntityToComplain(TaskEntity taskEntityToComplain) {
        this.taskEntityToComplain = taskEntityToComplain;
    }

    /**
     * @return the taskEntityToCancel
     */
    public TaskEntity getTaskEntityToCancel() {
        return taskEntityToCancel;
    }

    /**
     * @param taskEntityToCancel the taskEntityToCancel to set
     */
    public void setTaskEntityToCancel(TaskEntity taskEntityToCancel) {
        this.taskEntityToCancel = taskEntityToCancel;
    }

    /**
     * @return the taskEntityToReview
     */
    public TaskEntity getTaskEntityToReview() {
        return taskEntityToReview;
    }

    /**
     * @param taskEntityToReview the taskEntityToReview to set
     */
    public void setTaskEntityToReview(TaskEntity taskEntityToReview) {
        this.taskEntityToReview = taskEntityToReview;
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

    /**
     * @return the taskIdToComplain
     */
    public Long getTaskIdToComplain() {
        return taskIdToComplain;
    }

    /**
     * @param taskIdToComplain the taskIdToComplain to set
     */
    public void setTaskIdToComplain(Long taskIdToComplain) {
        this.taskIdToComplain = taskIdToComplain;
    }

    /**
     * @return the taskIdToCancel
     */
    public Long getTaskIdToCancel() {
        return taskIdToCancel;
    }

    /**
     * @param taskIdToCancel the taskIdToCancel to set
     */
    public void setTaskIdToCancel(Long taskIdToCancel) {
        this.taskIdToCancel = taskIdToCancel;
    }

    /**
     * @return the taskIdToReview
     */
    public Long getTaskIdToReview() {
        return taskIdToReview;
    }

    /**
     * @param taskIdToReview the taskIdToReview to set
     */
    public void setTaskIdToReview(Long taskIdToReview) {
        this.taskIdToReview = taskIdToReview;
    }

    /**
     * @return the selectItemsHelperEntities
     */
    public List<SelectItem> getSelectItemsHelperEntities() {
        return selectItemsHelperEntities;
    }

    /**
     * @param selectItemsHelperEntities the selectItemsHelperEntities to set
     */
    public void setSelectItemsHelperEntities(List<SelectItem> selectItemsHelperEntities) {
        this.selectItemsHelperEntities = selectItemsHelperEntities;
    }

    /**
     * @return the categories
     */
    public String[] getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    /**
     * @return the taskEntitiesPending
     */
    public List<TaskEntity> getTaskEntitiesPending() {
        return taskEntitiesPending;
    }

    /**
     * @param taskEntitiesPending the taskEntitiesPending to set
     */
    public void setTaskEntitiesPending(List<TaskEntity> taskEntitiesPending) {
        this.taskEntitiesPending = taskEntitiesPending;
    }

    /**
     * @return the taskEntitiesAssigned
     */
    public List<TaskEntity> getTaskEntitiesAssigned() {
        return taskEntitiesAssigned;
    }

    /**
     * @param taskEntitiesAssigned the taskEntitiesAssigned to set
     */
    public void setTaskEntitiesAssigned(List<TaskEntity> taskEntitiesAssigned) {
        this.taskEntitiesAssigned = taskEntitiesAssigned;
    }

    /**
     * @return the taskEntitiesCompleted
     */
    public List<TaskEntity> getTaskEntitiesCompleted() {
        return taskEntitiesCompleted;
    }

    /**
     * @param taskEntitiesCompleted the taskEntitiesCompleted to set
     */
    public void setTaskEntitiesCompleted(List<TaskEntity> taskEntitiesCompleted) {
        this.taskEntitiesCompleted = taskEntitiesCompleted;
    }

    /**
     * @return the taskEntitiesComplained
     */
    public List<TaskEntity> getTaskEntitiesComplained() {
        return taskEntitiesComplained;
    }

    /**
     * @param taskEntitiesComplained the taskEntitiesComplained to set
     */
    public void setTaskEntitiesComplained(List<TaskEntity> taskEntitiesComplained) {
        this.taskEntitiesComplained = taskEntitiesComplained;
    }

    /**
     * @return the filteredTaskEntitiesPending
     */
    public List<TaskEntity> getFilteredTaskEntitiesPending() {
        return filteredTaskEntitiesPending;
    }

    /**
     * @param filteredTaskEntitiesPending the filteredTaskEntitiesPending to set
     */
    public void setFilteredTaskEntitiesPending(List<TaskEntity> filteredTaskEntitiesPending) {
        this.filteredTaskEntitiesPending = filteredTaskEntitiesPending;
    }

    /**
     * @return the filteredTaskEntitiesAssigned
     */
    public List<TaskEntity> getFilteredTaskEntitiesAssigned() {
        return filteredTaskEntitiesAssigned;
    }

    /**
     * @param filteredTaskEntitiesAssigned the filteredTaskEntitiesAssigned to
     * set
     */
    public void setFilteredTaskEntitiesAssigned(List<TaskEntity> filteredTaskEntitiesAssigned) {
        this.filteredTaskEntitiesAssigned = filteredTaskEntitiesAssigned;
    }

    /**
     * @return the filteredTaskEntitiesCompleted
     */
    public List<TaskEntity> getFilteredTaskEntitiesCompleted() {
        return filteredTaskEntitiesCompleted;
    }

    /**
     * @param filteredTaskEntitiesCompleted the filteredTaskEntitiesCompleted to
     * set
     */
    public void setFilteredTaskEntitiesCompleted(List<TaskEntity> filteredTaskEntitiesCompleted) {
        this.filteredTaskEntitiesCompleted = filteredTaskEntitiesCompleted;
    }

    /**
     * @return the filteredTaskEntitiesComplained
     */
    public List<TaskEntity> getFilteredTaskEntitiesComplained() {
        return filteredTaskEntitiesComplained;
    }

    /**
     * @param filteredTaskEntitiesComplained the filteredTaskEntitiesComplained
     * to set
     */
    public void setFilteredTaskEntitiesComplained(List<TaskEntity> filteredTaskEntitiesComplained) {
        this.filteredTaskEntitiesComplained = filteredTaskEntitiesComplained;
    }

    /**
     * @return the taskEntitiesCancelled
     */
    public List<TaskEntity> getTaskEntitiesCancelled() {
        return taskEntitiesCancelled;
    }

    /**
     * @param taskEntitiesCancelled the taskEntitiesCancelled to set
     */
    public void setTaskEntitiesCancelled(List<TaskEntity> taskEntitiesCancelled) {
        this.taskEntitiesCancelled = taskEntitiesCancelled;
    }

    /**
     * @return the filteredTaskEntitiesCancelled
     */
    public List<TaskEntity> getFilteredTaskEntitiesCancelled() {
        return filteredTaskEntitiesCancelled;
    }

    /**
     * @param filteredTaskEntitiesCancelled the filteredTaskEntitiesCancelled to set
     */
    public void setFilteredTaskEntitiesCancelled(List<TaskEntity> filteredTaskEntitiesCancelled) {
        this.filteredTaskEntitiesCancelled = filteredTaskEntitiesCancelled;
    }

}
