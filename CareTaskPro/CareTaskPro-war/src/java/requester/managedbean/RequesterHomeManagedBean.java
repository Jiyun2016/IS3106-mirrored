/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requester.managedbean;

import ejb.session.stateless.PaymentControllerLocal;
import ejb.session.stateless.RequesterControllerLocal;
import ejb.session.stateless.TaskControllerLocal;
import entity.RequesterEntity;
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
import util.enumeration.TaskStatus;
import util.exception.InvalidLoginCredentialException;
import util.exception.RequesterNotFoundException;
import util.exception.TaskEntityNotFoundException;
import util.exception.WrongCredentialException;

/**
 *
 * @author panjiyun
 */
@Named(value = "requesterHomeManagedBean")
@RequestScoped
public class RequesterHomeManagedBean {

    @EJB(name = "PaymentControllerLocal")
    private PaymentControllerLocal paymentControllerLocal;

    @EJB(name = "RequesterControllerLocal")
    private RequesterControllerLocal requesterControllerLocal;

    @EJB(name = "TaskControllerLocal")
    private TaskControllerLocal taskControllerLocal;

    

    private String phone;
    private String password;
    private String username;

    public RequesterHomeManagedBean() {
    }

    public void login(ActionEvent event) throws IOException {
        try {
            RequesterEntity currentRequesterEntity = requesterControllerLocal.loginRequester(phone, password);
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLogin", true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentRequesterEntity", currentRequesterEntity);
            FacesContext.getCurrentInstance().getExternalContext().redirect("requesterHome.xhtml");

        } catch (Exception ex ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login credential: " + ex.getMessage(), null));
        }
    }

    public void logout(ActionEvent event) throws IOException {

        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();

        FacesContext.getCurrentInstance().getExternalContext().redirect("requesterHome.xhtml");

    }

 

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TaskEntity> getTaskAssigned() throws IOException {
        try {
            RequesterEntity re = (RequesterEntity)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentRequesterEntity");
            Long requesterId = re.getRequesterId();
            return taskControllerLocal.retrieveTaskByStatusByRequesterId(requesterId, TaskStatus.ASSIGNED);
        } catch (TaskEntityNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No task found: " + ex.getMessage(), null));
            return new ArrayList<TaskEntity>();
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
