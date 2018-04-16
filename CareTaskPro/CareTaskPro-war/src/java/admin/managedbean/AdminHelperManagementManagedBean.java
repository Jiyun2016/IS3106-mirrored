/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.managedbean;

import ejb.session.stateless.HelperControllerLocal;
import entity.HelperEntity;
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
@Named(value = "adminHelperManagementManagedBean")
@RequestScoped
public class AdminHelperManagementManagedBean {

    @EJB(name = "HelperControllerLocal")
    private HelperControllerLocal helperControllerLocal;

    
    private List<HelperEntity> helperEntities;
    private List<HelperEntity> filteredHelperEntities;

    public AdminHelperManagementManagedBean() {
        
        filteredHelperEntities = new ArrayList<HelperEntity>();
    }
    
      @PostConstruct
    public void postConstruct() {
        setHelperEntities(helperControllerLocal.retrieveAllHelpers());
        if (getHelperEntities().isEmpty() || getHelperEntities() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No Helper found. ", null));
            setHelperEntities(new ArrayList<HelperEntity>());
        }
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
    
}
