/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.requester.managedbean;

import ejb.session.stateless.RequesterControllerLocal;
import entity.RequesterEntity;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.enumeration.Gender;
import util.stringConstant.GenderString;

/**
 *
 * @author panjiyun
 */
@Named(value = "requesterRegisterManagedBean")
@RequestScoped
public class RequesterRegisterManagedBean {

    @EJB(name = "RequesterControllerLocal")
    private RequesterControllerLocal requesterControllerLocal;

    private RequesterEntity requesterEntity;
    
    private String[] genders;

    /**
     * Creates a new instance of RequesterRegisterManagedBean
     */
    public RequesterRegisterManagedBean() {

        requesterEntity = new RequesterEntity();
    }
    
    @PostConstruct
    public void postConstruct() {
        genders = new String[]{GenderString.FEMALE,GenderString.MALE};
    }

    public void createNewRequester() {
        try {
            RequesterEntity re = requesterControllerLocal.createNewRequester(getRequesterEntity());
            setRequesterEntity(new RequesterEntity());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New requester registered successfully (Requester ID: " + re.getRequesterId() + ")", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while registering the new requester: " + ex.getMessage(), null));
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
     * @return the genders
     */
    public String[] getGenders() {
        return genders;
    }

    /**
     * @param genders the genders to set
     */
    public void setGenders(String[] genders) {
        this.genders = genders;
    }
    
}
