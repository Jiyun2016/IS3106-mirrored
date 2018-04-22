/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.helper.managedbean;

import ejb.session.stateless.ReviewControllerLocal;
import entity.HelperEntity;
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
@Named(value = "helperViewReviewManagedBean")
@ViewScoped
public class HelperViewReviewManagedBean implements Serializable{

    @EJB(name = "ReviewControllerLocal")
    private ReviewControllerLocal reviewControllerLocal;
    
   private HelperEntity helper;
    
    private Long helperIdToViewReview;
    
    private List<ReviewEntity> reviewEntities;
            
    public HelperViewReviewManagedBean() {
        
    }
    
    @PostConstruct
    public void postConstruct(){
        helper = (HelperEntity)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentHelperEntity");
        System.err.println("...helper:"+helper.getHelperId());
         setHelperIdToViewReview(helper.getHelperId());
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
