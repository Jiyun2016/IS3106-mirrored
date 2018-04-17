/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requester.managedbean;

import ejb.session.stateless.HelperControllerLocal;
import entity.HelperEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author panjiyun
 */
@Named(value = "requesterHelperManagedBean")
@RequestScoped
public class RequesterHelperManagedBean {

    /**
     * Creates a new instance of RequesterHelperManagedBean
     */
    @EJB(name = "HelperControllerLocal")
    private HelperControllerLocal helperControllerLocal;

    private List<HelperEntity> helperEntities;
    private List<HelperEntity> filteredHelperEntities;
    private HelperEntity helperEntityToView;
    
    private Long helperIdToViewReview;

    public RequesterHelperManagedBean() {

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
    
      public void viewReview(ActionEvent event) throws IOException{
        setHelperIdToViewReview((Long) event.getComponent().getAttributes().get("helperIdToViewReview"));
        System.err.println(".....MB viewReview:helperIdToViewReview: "+getHelperIdToViewReview());
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("helperIdToViewReview", getHelperIdToViewReview());
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
     * @return the helperEntityToView
     */
    public HelperEntity getHelperEntityToView() {
        return helperEntityToView;
    }

    /**
     * @param helperEntityToView the helperEntityToView to set
     */
    public void setHelperEntityToView(HelperEntity helperEntityToView) {
        this.helperEntityToView = helperEntityToView;
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
