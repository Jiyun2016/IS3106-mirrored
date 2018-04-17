/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.managedbean;

import ejb.session.stateless.HelperControllerLocal;
import entity.HelperEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

/**
 *
 * @author panjiyun
 */
@Named(value = "adminHelperManagementManagedBean")
@ViewScoped
public class AdminHelperManagementManagedBean implements Serializable {

    @EJB(name = "HelperControllerLocal")
    private HelperControllerLocal helperControllerLocal;

    private HelperEntity helperEntity;
    private List<HelperEntity> helperEntities;
    private List<HelperEntity> filteredHelperEntities;
    
    private Long helperIdToViewReview;

    public AdminHelperManagementManagedBean() {
        
        filteredHelperEntities = new ArrayList<HelperEntity>();
    }
    
      @PostConstruct
    public void postConstruct() {
        setFilteredHelperEntities(helperEntities);
        setHelperEntities(helperControllerLocal.retrieveAllHelpers());
        if (getHelperEntities().isEmpty() || getHelperEntities() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No Helper found. ", null));
            setHelperEntities(new ArrayList<HelperEntity>());
        }
    }
    
     public void onChange() {
    }
    
   
    public void viewReview(ActionEvent event) throws IOException{
        setHelperIdToViewReview((Long) event.getComponent().getAttributes().get("helperIdToViewReview"));
        System.err.println(".....MB viewReview:helperIdToViewReview: "+getHelperIdToViewReview());
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("helperIdToViewReview", helperIdToViewReview);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewReview.xhtml");

    }
    /**
     * @return the helperEntities
     */
    public List<HelperEntity> getHelperEntities() {
        return helperEntities;
    }

    /**
     * @param helperEntities the helperEntities to set
     */
    public void setHelperEntities(List<HelperEntity> helperEntities) {
        this.helperEntities = helperEntities;
    }

    /**
     * @return the filteredHelperEntities
     */
    public List<HelperEntity> getFilteredHelperEntities() {
        return filteredHelperEntities;
    }

    /**
     * @param filteredHelperEntities the filteredHelperEntities to set
     */
    public void setFilteredHelperEntities(List<HelperEntity> filteredHelperEntities) {
        this.filteredHelperEntities = filteredHelperEntities;
    }

    /**
     * @return the helperEntity
     */
    public HelperEntity getHelperEntity() {
        return helperEntity;
    }

    /**
     * @param helperEntity the helperEntity to set
     */
    public void setHelperEntity(HelperEntity helperEntity) {
        this.helperEntity = helperEntity;
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
    
}
