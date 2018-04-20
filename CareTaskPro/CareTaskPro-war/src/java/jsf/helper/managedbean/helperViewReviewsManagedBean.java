/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.helper.managedbean;

import ejb.session.stateless.HelperControllerLocal;
import ejb.session.stateless.ReviewControllerLocal;
import entity.HelperEntity;
import entity.ReviewEntity;
import entity.TaskEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import util.exception.ReviewNotFoundException;

/**
 *
 * @author Amber
 */
@Named(value = "helperViewReviewsManagedBean")
@RequestScoped
public class helperViewReviewsManagedBean implements Serializable{

    @EJB
    private ReviewControllerLocal reviewController;

   

    /**
     * Creates a new instance of helperViewReviewsManagedBean
     */
    private HelperEntity helper;
    private List<ReviewEntity> reviews;
    
    
    
    public helperViewReviewsManagedBean() {
        
    }
    
    @PostConstruct
    public void init(){
        try{
            setHelper((HelperEntity)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentHelperEntity"));
            setReviews(reviewController.retrieveReviewByHelperId(getHelper().getHelperId()));
            
            
        }catch(ReviewNotFoundException ex){
            setReviews(new ArrayList<ReviewEntity>());
        }
        
        
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
     * @return the reviews
     */
    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    /**
     * @param reviews the reviews to set
     */
    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }
    
}
