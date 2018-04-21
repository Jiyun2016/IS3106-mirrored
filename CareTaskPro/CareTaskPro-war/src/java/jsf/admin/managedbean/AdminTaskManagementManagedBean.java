package jsf.admin.managedbean;

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
import util.stringConstant.CategoryString;
import util.stringConstant.TaskStatusString;

/**
 *
 * @author panjiyun
 */
@Named(value = "adminTaskManagementManagedBean")
@ViewScoped
public class AdminTaskManagementManagedBean implements Serializable {

    @EJB(name = "HelperControllerLocal")
    private HelperControllerLocal helperControllerLocal;

    @EJB(name = "RequesterControllerLocal")
    private RequesterControllerLocal requesterControllerLocal;

    @EJB(name = "TaskControllerLocal")
    private TaskControllerLocal taskControllerLocal;

    private String[] categories;
    private String[] taskStatuses;

    private RequesterEntity requesterEntity;
    private Long requesterId;

    private TaskEntity taskEntityToView;
    private TaskEntity taskEntityToUpdate;

    private Long taskIdToView;
    private Long taskIdToUpdate;

    private List<TaskEntity> taskEntities;
    private List<TaskEntity> taskEntitiesPending;
    private List<TaskEntity> taskEntitiesAssigned;
    private List<TaskEntity> taskEntitiesCompleted;
    private List<TaskEntity> taskEntitiesComplained;
    private List<TaskEntity> taskEntitiesCancelled;

    private List<TaskEntity> filteredTaskEntities;

    private List<SelectItem> selectItemsHelperEntities;

    public AdminTaskManagementManagedBean() {

        filteredTaskEntities = new ArrayList<TaskEntity>();
        selectItemsHelperEntities = new ArrayList<SelectItem>();
        //     taskEntityToUpdate = new TaskEntity();

    }

    @PostConstruct
    public void postConstruct() {
        setFilteredTaskEntities(taskEntities);

        setTaskEntitiesPending(taskControllerLocal.retrieveTasksByStatus((TaskStatusString.PENDING)));
        setTaskEntitiesComplained(taskControllerLocal.retrieveTasksByStatus((TaskStatusString.COMPLAINED)));
        taskEntitiesAssigned = taskControllerLocal.retrieveTasksByStatus(TaskStatusString.ASSIGNED);
        taskEntitiesCompleted = taskControllerLocal.retrieveTasksByStatus(TaskStatusString.COMPLETED);
        setTaskEntitiesCancelled(taskControllerLocal.retrieveTasksByStatus(TaskStatusString.CANCELLED));
        
        categories = new String[]{CategoryString.COMPANIONSHIP, CategoryString.HEALTHCARE, CategoryString.HOUSEWORK};
        setTaskStatuses(new String[]{TaskStatusString.ASSIGNED, TaskStatusString.PENDING, TaskStatusString.COMPLETED, TaskStatusString.CANCELLED, TaskStatusString.COMPLAINED});

        List<HelperEntity> helperEntities = helperControllerLocal.retrieveAllHelpers();
        for (HelperEntity helperEntity : helperEntities) {
            getSelectItemsHelperEntities().add(new SelectItem(helperEntity, helperEntity.getFirstName(), helperEntity.getLastName()));
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("HelperEntityConverter.helperEntities", helperEntities);

    }

    @PreDestroy
    public void preDestroy() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("HelperEntityConverter.helperEntities", null);
    }

    public void onChange() {
    }

    public void updateTask(ActionEvent event) throws IOException {
        try {
            System.err.println(".....task to update:" + taskEntityToUpdate.getTaskId()+" "+ taskEntityToUpdate.getTaskStatus().toString());
            taskIdToUpdate = taskEntityToUpdate.getTaskId();
            taskControllerLocal.updateTaskEntity(getTaskEntityToUpdate());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Task updated successfully", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
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
     * @return the taskStatuses
     */
    public String[] getTaskStatuses() {
        return taskStatuses;
    }

    /**
     * @param taskStatuses the taskStatuses to set
     */
    public void setTaskStatuses(String[] taskStatuses) {
        this.taskStatuses = taskStatuses;
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

}
