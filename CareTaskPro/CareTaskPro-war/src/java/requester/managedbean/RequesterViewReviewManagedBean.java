/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requester.managedbean;

import ejb.session.stateless.ReviewControllerLocal;
import entity.ReviewEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.ReviewNotFoundException;

/**
 *
 * @author panjiyun
 */
@Named(value = "requesterViewReviewManagedBean")
@ViewScoped
public class RequesterViewReviewManagedBean implements Serializable{

    @EJB(name = "ReviewControllerLocal")
    private ReviewControllerLocal reviewControllerLocal;
    

    
    private Long helperIdToViewReview;
    
    private List<ReviewEntity> reviewEntities;
            
    public RequesterViewReviewManagedBean() {
        
    }
    
    @PostConstruct
    public void postConstruct(){
         setHelperIdToViewReview((Long)FacesContext.getCurrentInstance().getExternalContext().getFlash().get("helperIdToViewReview"));
         System.err.println(".....ReviewMB: helperIdToReview: "+getHelperIdToViewReview());
         try{
             setReviewEntities(reviewControllerLocal.retrieveReviewByHelperId(getHelperIdToViewReview()));
         }catch(ReviewNotFoundException ex){
             setReviewEntities(new ArrayList<ReviewEntity>());
         }
           
    }

    /**
     * @return the helperIdToViewReview
     */
    public Long getHelperIdToViewReview() {
        return helperIdToViewReview;
    }

    /**
     * @param helperIdToViewReview the helperIdToViewReview to set
     */
    public void setHelperIdToViewReview(Long helperIdToViewReview) {
        this.helperIdToViewReview = helperIdToViewReview;
    }

    /**
     * @return the reviewEntities
     */
    public List<ReviewEntity> getReviewEntities() {
        return reviewEntities;
    }

    /**
     * @param reviewEntities the reviewEntities to set
     */
    public void setReviewEntities(List<ReviewEntity> reviewEntities) {
        this.reviewEntities = reviewEntities;
    }
    
    
    
}
