/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.managedbean;

import ejb.session.stateless.AdminControllerLocal;
import entity.AdminEntity;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author panjiyun
 */
@Named(value = "adminProfileManagedBean")
@Dependent
public class AdminProfileManagedBean {

     @EJB(name = "AdminControllerLocal")
    private AdminControllerLocal adminControllerLocal;

    private AdminEntity adminEntity;
    private Long adminId;

    /**
     * Creates a new instance of RequesterProfileManagedBean
     */
    public  AdminProfileManagedBean(){
    }

    @PostConstruct
    public void postConstruct() {
        setAdminEntity((AdminEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentAdminEntity"));
        setAdminId(getAdminEntity().getAdminId());

    }

    public void cancel(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("adminProfile.xhtml");
    }

    public void updateProfile() {
        try {
            adminControllerLocal.updateEmployee(getAdminEntity());
            setAdminEntity(adminControllerLocal.retrieveAdminById(getAdminId()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile updated successfully", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
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
     * @return the adminId
     */
    public Long getAdminId() {
        return adminId;
    }

    /**
     * @param adminId the adminId to set
     */
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
    
    
}
