/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.helper.managedbean;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Amber
 */
@Named(value = "helperHomepageLogoutManagedBean")
@RequestScoped
public class HelperHomepageLogoutManagedBean {

    /**
     * Creates a new instance of HelperHomepageLogoutManagedBean
     */
    public HelperHomepageLogoutManagedBean() {
    }
    
    public void logout(ActionEvent event) throws IOException {
        
        System.err.println("*****logout");

        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();

        FacesContext.getCurrentInstance().getExternalContext().redirect("../publicMainPage.xhtml");

    }
}
