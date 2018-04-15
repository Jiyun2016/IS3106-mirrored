/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.managedbean;

import ejb.session.stateless.HelperControllerLocal;
import entity.HelperEntity;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Amber
 */
@Named(value = "helperAccountManagedBean")
@RequestScoped
public class helperAccountManagedBean {

    @EJB
    private HelperControllerLocal helperController;

    private HelperEntity helperToView;
    /**
     * Creates a new instance of myAccountManagedBean
     */
    public helperAccountManagedBean() {
    }
    
    
    @PostConstruct
    public void init()
    {
        setHelperToView((HelperEntity)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentHelperEntity"));
    }

    public void saveHelper(ActionEvent event)
    {
       helperController.updateHelper(getHelperToView());
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Book " + getHelperToView().getHelperId() + " updated successfully", null));
    }

    
    /**
     * @return the helperToView
     */
    public HelperEntity getHelperToView() {
        return helperToView;
    }

    /**
     * @param helperToView the helperToView to set
     */
    public void setHelperToView(HelperEntity helperToView) {
        this.helperToView = helperToView;
    }
    
}
