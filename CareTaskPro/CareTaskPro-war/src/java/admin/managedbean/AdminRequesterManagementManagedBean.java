/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.managedbean;

import ejb.session.stateless.RequesterControllerLocal;
import entity.RequesterEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author panjiyun
 */
@Named(value = "adminRequesterManagementManagedBean")
@RequestScoped
public class AdminRequesterManagementManagedBean {

    @EJB(name = "RequesterControllerLocal")
    private RequesterControllerLocal requesterControllerLocal;

   
    private List<RequesterEntity> requesterEntities;
    private List<RequesterEntity> filteredRequesterEntities;

    public AdminRequesterManagementManagedBean() {
        filteredRequesterEntities = new ArrayList<RequesterEntity>();

    }
    
     @PostConstruct
    public void postConstruct() {
        setRequesterEntities(requesterControllerLocal.retrieveAllRequesters());
        if (getRequesterEntities().isEmpty() || getRequesterEntities() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No Requester found. ", null));
            setRequesterEntities(new ArrayList<RequesterEntity>());
        }
    }

    /**
     * @return the requesterEntities
     */
    public List<RequesterEntity> getRequesterEntities() {
        return requesterEntities;
    }

    /**
     * @param requesterEntities the requesterEntities to set
     */
    public void setRequesterEntities(List<RequesterEntity> requesterEntities) {
        this.requesterEntities = requesterEntities;
    }

    /**
     * @return the filteredRequesterEntities
     */
    public List<RequesterEntity> getFilteredRequesterEntities() {
        return filteredRequesterEntities;
    }

    /**
     * @param filteredRequesterEntities the filteredRequesterEntities to set
     */
    public void setFilteredRequesterEntities(List<RequesterEntity> filteredRequesterEntities) {
        this.filteredRequesterEntities = filteredRequesterEntities;
    }
    
    

}
