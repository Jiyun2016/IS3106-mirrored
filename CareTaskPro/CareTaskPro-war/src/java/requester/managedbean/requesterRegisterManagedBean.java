/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requester.managedbean;

import ejb.session.stateless.RequesterControllerLocal;
import entity.RequesterEntity;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Amber
 */
@Named(value = "requesterRegisterManagedBean")
@ViewScoped
public class requesterRegisterManagedBean implements Serializable{

    @EJB
    private RequesterControllerLocal requesterController;
    
    private RequesterEntity newRequester;

    /**
     * Creates a new instance of requesterRegisterManagedBean
     */
    public requesterRegisterManagedBean() {
    }

    public requesterRegisterManagedBean(RequesterEntity newRequester) {
        this.newRequester = newRequester;
    }
    
    public void saveNewRequester(ActionEvent event)
    {
        setNewRequester(requesterController.createNewRequester(getNewRequester()));
        getNewRequester().setRequesterId(getNewRequester().getRequesterId());
       
        setNewRequester(new RequesterEntity());
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New requester " + getNewRequester().getFirstName() + getNewRequester().getLastName() + " created successfully", null));
    }

    /**
     * @return the newRequester
     */
    public RequesterEntity getNewRequester() {
        return newRequester;
    }

    /**
     * @param newRequester the newRequester to set
     */
    public void setNewRequester(RequesterEntity newRequester) {
        this.newRequester = newRequester;
    }

}
