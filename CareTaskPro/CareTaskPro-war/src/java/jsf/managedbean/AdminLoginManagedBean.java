/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.AdminControllerLocal;
import entity.AdminEntity;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author panjiyun
 */
@Named(value = "adminLoginManagedBean")
@RequestScoped
public class AdminLoginManagedBean {

    @EJB
    private AdminControllerLocal adminControllerLocal;
    
    private List<AdminEntity> adminEntities;
    
    private String username;
    private String password;



    /**
     * Creates a new instance of AdminLogin
     */
    public AdminLoginManagedBean() {
    }
    
    
     public void login(ActionEvent event) throws IOException
    {
        try
        {
            AdminEntity currentAdminEntity = adminControllerLocal.adminLogin(username, password);
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLogin", true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentAdminEntity", currentAdminEntity);
            FacesContext.getCurrentInstance().getExternalContext().redirect("adminLogin.xhtml");            
        }
        catch(InvalidLoginCredentialException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login credential: " + ex.getMessage(), null));
        }
    }
    
    
    
    public void logout(ActionEvent event) throws IOException
    {
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("adminLogin.xhtml");
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
    
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
