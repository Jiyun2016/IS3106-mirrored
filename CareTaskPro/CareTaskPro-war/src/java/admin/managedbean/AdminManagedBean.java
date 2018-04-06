/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.managedbean;

import ejb.session.stateless.AdminControllerLocal;
import entity.AdminEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author panjiyun
 */
@Named(value = "adminManagedBean")
@SessionScoped

//viewmodel
public class AdminManagedBean implements Serializable {

    @EJB
    private AdminControllerLocal adminControllerLocal;
    
    //model
    private List<AdminEntity> adminEntities;

    /**
     * Creates a new instance of AdminManagedBean
     */
    public AdminManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct(){
        adminEntities = adminControllerLocal.retrieveAllAdmin();
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
    
}
