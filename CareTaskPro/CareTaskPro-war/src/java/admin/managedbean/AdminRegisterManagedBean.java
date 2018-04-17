/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.managedbean;

import ejb.session.stateless.AdminControllerLocal;
import entity.AdminEntity;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author panjiyun
 */
@Named(value = "adminRegisterManagedBean")
@RequestScoped
public class AdminRegisterManagedBean {

    @EJB(name = "AdminControllerLocal")
    private AdminControllerLocal adminControllerLocal;
    
    private AdminEntity adminEntity;
    

    
    public AdminRegisterManagedBean() {
        adminEntity = new AdminEntity();
        
    }
    
    
    public void createNewAdmin() {
        try {
            System.err.println(adminEntity.getFirstName()+","+adminEntity.getLastName()+","+adminEntity.getUsername()+","+adminEntity.getPassword());
            AdminEntity ad = adminControllerLocal.createNewAdmin(getAdminEntity());
            setAdminEntity(new AdminEntity());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New admin registered successfully (Admin ID: " + ad.getAdminId() + ")", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while registering the new admin: " + ex.getMessage(), null));
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
}
