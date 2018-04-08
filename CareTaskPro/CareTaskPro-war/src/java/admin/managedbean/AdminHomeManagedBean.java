/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.managedbean;

import ejb.session.stateless.AdminControllerLocal;
import ejb.session.stateless.TaskControllerLocal;
import entity.AdminEntity;
import entity.TaskEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import util.exception.InvalidLoginCredentialException;
import util.exception.TaskEntityNotFoundException;

/**
 *
 * @author panjiyun
 */
@Named(value = "adminHomeManagedBean")
@RequestScoped
public class AdminHomeManagedBean {

    @EJB(name = "TaskControllerLocal")
    private TaskControllerLocal taskControllerLocal;

    @EJB(name = "AdminControllerLoccal")
    private AdminControllerLocal adminControllerLoccal;

    private String username;
    private String password;

    public AdminHomeManagedBean() {
    }

    public void login(ActionEvent event) throws IOException {
        try {
            AdminEntity currentAdminEntity = adminControllerLoccal.adminLogin(username, password);
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLogin", true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentAdminEntity", currentAdminEntity);
            FacesContext.getCurrentInstance().getExternalContext().redirect("adminHome.xhtml");

        } catch (InvalidLoginCredentialException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login credential: " + ex.getMessage(), null));
        }
    }

    public void logout(ActionEvent event) throws IOException {

        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();

        FacesContext.getCurrentInstance().getExternalContext().redirect("adminHome.xhtml");

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

    public List<TaskEntity> getTaskAssigned() throws IOException {
        try {
            return taskControllerLocal.retrieveTaskAssigned();
        } catch (TaskEntityNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No task found: " + ex.getMessage(), null));
            return new ArrayList<TaskEntity>();
        }

    }

}
