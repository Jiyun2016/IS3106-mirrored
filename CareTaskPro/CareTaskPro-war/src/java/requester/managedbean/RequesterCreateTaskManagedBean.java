package requester.managedbean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ejb.session.stateless.HelperControllerLocal;
import ejb.session.stateless.RequesterControllerLocal;
import ejb.session.stateless.TaskControllerLocal;
import entity.HelperEntity;
import entity.RequesterEntity;
import entity.TaskEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.enumeration.Category;

/**
 *
 * @author panjiyun
 */
@Named(value = "requesterCreateTaskManagedBean")
@ViewScoped
public class RequesterCreateTaskManagedBean implements Serializable{

    @EJB(name = "HelperControllerLocal")
    private HelperControllerLocal helperControllerLocal;

    @EJB(name = "RequesterControllerLocal")
    private RequesterControllerLocal requesterControllerLocal;

    @EJB(name = "TaskControllerLocal")
    private TaskControllerLocal taskControllerLocal;
    
    private Category[] categories;

    private RequesterEntity requesterEntity;
    private Long requesterId;

    private TaskEntity taskEntity;

    private List<SelectItem> selectItemsHelperEntities;
    private List<HelperEntity> helperEntities;

    /**
     * Creates a new instance of RequesterTaskManagedBean
     */
    public RequesterCreateTaskManagedBean() {
        selectItemsHelperEntities = new ArrayList<SelectItem>();

    }

    @PostConstruct
    public void postConstruct() {
        setRequesterEntity((RequesterEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentRequesterEntity"));
        setRequesterId(getRequesterEntity().getRequesterId());
        setTaskEntity(new TaskEntity());
        taskEntity.setRequesterEntity(requesterEntity);
        setCategories(Category.values());

        List<HelperEntity> helperEntities = helperControllerLocal.retrieveAllHelpers();
        for (HelperEntity helperEntity : helperEntities) {
            getSelectItemsHelperEntities().add(new SelectItem(helperEntity,helperEntity.getHelperId().toString(), helperEntity.getFirstName()));
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("HelperEntityConverter.helperEntities", helperEntities);

    }

    @PreDestroy
    public void preDestroy() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("HelperEntityConverter.helperEntities", null);
    }
    
     
    public void onChange()
    {
    }

    public void createNewTask() {

        TaskEntity te = taskControllerLocal.createNewTask(getTaskEntity());
        setTaskEntity(new TaskEntity());

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New task posted successfully (Task ID: " + te.getTaskId() + ")", null));

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
     * @return the taskEntity
     */
    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    /**
     * @param taskEntity the taskEntity to set
     */
    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
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
    public Category[] getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

 
  

}
