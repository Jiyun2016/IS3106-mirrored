/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.admin.managedbean;

import ejb.session.stateless.AdminControllerLocal;
import entity.AdminEntity;
import entity.TaskEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author panjiyun
 */
@Named(value = "adminManagementManagedBean")
@RequestScoped

//viewmodel
public class AdminManagementManagedBean implements Serializable {

    @EJB
    private AdminControllerLocal adminControllerLocal;

    //model
    private List<AdminEntity> adminEntities;

    private AdminEntity adminEntity;

    private List<AdminEntity> filteredAdminEntities;

    /**
     * Creates a new instance of AdminManagedBean
     */
    public AdminManagementManagedBean() {
        filteredAdminEntities = new ArrayList<AdminEntity>();
    }

    @PostConstruct
    public void postConstruct() {
        adminEntities = adminControllerLocal.retrieveAllAdmin();
        if (adminEntities.isEmpty() || adminEntities == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No Admin found. ", null));
            adminEntities = new ArrayList<AdminEntity>();
        }
    }

    public void createAdmin() {
        setAdminEntity(adminControllerLocal.createNewAdmin(getAdminEntity()));
    }

    public List<AdminEntity> viewAllAdmin() {
        adminEntities = adminControllerLocal.retrieveAllAdmin();
        if (adminEntities.isEmpty() || adminEntities == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No Admin found. ", null));
            return new ArrayList<AdminEntity>();
        }
        return adminEntities;
    }

    /**
     * @return the adminEntities
     */
    public List<AdminEntity> getAdminEntities() {
        return adminEntities;
    }

    /**
     * @param adminEntities the adminEntities to set
     */
    public void setAdminEntities(List<AdminEntity> adminEntities) {
        this.adminEntities = adminEntities;
    }

    /**
     * @return the adminEntity
     */
    public AdminEntity getAdminEntity() {
        return adminEntity;
    }

    /**
     * @param adminEntity the adminEntity to set
     */
    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }

    /**
     * @return the filteredAdminEntities
     */
    public List<AdminEntity> getFilteredAdminEntities() {
        return filteredAdminEntities;
    }

    /**
     * @param filteredAdminEntities the filteredAdminEntities to set
     */
    public void setFilteredAdminEntities(List<AdminEntity> filteredAdminEntities) {
        this.filteredAdminEntities = filteredAdminEntities;
    }

}
