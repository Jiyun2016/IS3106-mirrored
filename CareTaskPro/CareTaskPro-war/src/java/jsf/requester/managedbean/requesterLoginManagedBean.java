/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.requester.managedbean;

import ejb.session.stateless.RequesterControllerLocal;
import entity.RequesterEntity;
import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import util.exception.RequesterNotFoundException;
import util.exception.WrongCredentialException;


/**
 *
 * @author Amber
 */
@Named(value = "requesterLoginManagedBean")
@RequestScoped
public class requesterLoginManagedBean {

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

    @EJB
    private RequesterControllerLocal requesterController;
    
    private String phone;
    private String password;

    /**
     * Creates a new instance of requesterLoginManagedBean
     */
    public requesterLoginManagedBean() {
    }
    
   
    
    public void login(ActionEvent event) throws IOException
    {
        try
        {
            RequesterEntity currentRequesterEntity = requesterController.loginRequester(getPhone(), getPassword());
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLogin", true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentRequesterEntity", currentRequesterEntity);
            FacesContext.getCurrentInstance().getExternalContext().redirect("requesterHome.xhtml");            
        }
        catch(WrongCredentialException|RequesterNotFoundException|IOException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login credential: " + ex.getMessage(), null));
        }
    }
    
    
}
