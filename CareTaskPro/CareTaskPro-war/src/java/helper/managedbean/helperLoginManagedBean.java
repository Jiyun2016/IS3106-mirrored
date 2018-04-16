/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.managedbean;

import ejb.session.stateless.HelperControllerLocal;
import static entity.AdminEntity_.username;
import entity.HelperEntity;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import util.exception.HelperNotFoundException;
import util.exception.WrongCredentialException;

/**
 *
 * @author Amber
 */
@Named(value = "helperLoginManagedBean")
@RequestScoped
public class helperLoginManagedBean {

    

    @EJB
    private HelperControllerLocal helperControllerLocal;
    
    private String phone;
    private String password;

    
    public helperLoginManagedBean() {
    }
    
    public void login(ActionEvent event) throws IOException
    {
        try
        {
            HelperEntity currentHelperEntity = helperControllerLocal.loginHelper(getPhone(), getPassword());
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLogin", true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentHelperEntity", currentHelperEntity);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../HelperWeb/HelperHomepage.xhtml");            
        }
        catch(WrongCredentialException|HelperNotFoundException|IOException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login credential: " + ex.getMessage(), null));
        }
    }
    
    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
