/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.requester.managedbean;

import ejb.session.stateless.ReviewControllerLocal;
import ejb.session.stateless.TaskControllerLocal;
import entity.ReviewEntity;
import entity.TaskEntity;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.TaskEntityNotFoundException;

/**
 *
 * @author panjiyun
 */
@Named(value = "requesterReviewManagedBean")
@ViewScoped
public class RequesterReviewManagedBean implements Serializable{

    @EJB(name = "TaskControllerLocal")
    private TaskControllerLocal taskControllerLocal;

    @EJB(name = "ReviewControllerLocal")
    private ReviewControllerLocal reviewControllerLocal;

    private ReviewEntity reviewEntity;

    private TaskEntity taskEntityToAddReview;
    private Long taskIdToAddReview;

    public RequesterReviewManagedBean() {
        
    }

    @PostConstruct
    public void postConstruct() {
        
        
        setTaskIdToAddReview((Long) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("taskIdToView"));
       // System.err.println("......postConstruct: taskIdToReview: "+ taskIdToAddReview);

        try {
            setTaskEntityToAddReview(taskControllerLocal.retrieveTaskById(getTaskIdToAddReview()));
            reviewEntity = new ReviewEntity(taskEntityToAddReview);
            
        } catch (TaskEntityNotFoundException ex) {
            setTaskEntityToAddReview(new TaskEntity());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while retrieving the task details: " + ex.getMessage(), null));
        } catch (Exception ex) {
            setTaskEntityToAddReview(new TaskEntity());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }

    public void createReview() {
        System.err.println(".....createReview triggered");
        reviewEntity = reviewControllerLocal.createNewReview(getReviewEntity());
        taskEntityToAddReview = taskControllerLocal.addReviewToTask(taskIdToAddReview, reviewEntity);
        reviewEntity = taskEntityToAddReview.getReviewEntity();
       
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New entity created successfully (Review ID: " + reviewEntity.getReviewId() + ")", null));

    }

    /**
     * @return the reviewEntity
     */
    public ReviewEntity getReviewEntity() {
        return reviewEntity;
    }

    /**
     * @param reviewEntity the reviewEntity to set
     */
    public void setReviewEntity(ReviewEntity reviewEntity) {
        this.reviewEntity = reviewEntity;
    }

    /**
     * @return the taskEntityToAddReview
     */
    public TaskEntity getTaskEntityToAddReview() {
        return taskEntityToAddReview;
    }

    /**
     * @param taskEntityToAddReview the taskEntityToAddReview to set
     */
    public void setTaskEntityToAddReview(TaskEntity taskEntityToAddReview) {
        this.taskEntityToAddReview = taskEntityToAddReview;
    }

    /**
     * @return the taskIdToAddReview
     */
    public Long getTaskIdToAddReview() {
        return taskIdToAddReview;
    }

    /**
     * @param taskIdToAddReview the taskIdToAddReview to set
     */
    public void setTaskIdToAddReview(Long taskIdToAddReview) {
        this.taskIdToAddReview = taskIdToAddReview;
    }

}
