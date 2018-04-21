/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.requester.managedbean;

import ejb.session.stateless.RequesterControllerLocal;
import entity.RequesterEntity;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.RequesterNotFoundException;

/**
 *
 * @author panjiyun
 */
@Named(value = "requesterProfileManagedBean")
@ViewScoped
public class RequesterProfileManagedBean implements Serializable {

    @EJB(name = "RequesterControllerLocal")
    private RequesterControllerLocal requesterControllerLocal;

    private RequesterEntity requesterEntity;
    private Long requesterId;

    /**
     * Creates a new instance of RequesterProfileManagedBean
     */
    public RequesterProfileManagedBean() {
    }

    @PostConstruct
    public void postConstruct()  {
        requesterEntity = (RequesterEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentRequesterEntity");
        requesterId = requesterEntity.getRequesterId();
        try{requesterEntity = requesterControllerLocal.retrieveRequesterById(requesterId);
        }catch(RequesterNotFoundException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not login", null));
        
        }

    }

    public void cancel(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("requesterProfile.xhtml");
    }

    public void updateProfile() {
        try {
            requesterControllerLocal.updateRequesterProfile(requesterEntity);
            requesterEntity = requesterControllerLocal.retrieveRequesterById(getRequesterId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile updated successfully", null));
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

}
