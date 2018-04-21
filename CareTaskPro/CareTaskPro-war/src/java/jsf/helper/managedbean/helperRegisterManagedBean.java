/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.helper.managedbean;

import ejb.session.stateless.HelperControllerLocal;
import entity.HelperEntity;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Amber
 */
@Named(value = "helperRegisterManagedBean")
@ViewScoped
public class helperRegisterManagedBean implements Serializable {

    @EJB
    private HelperControllerLocal helperController;
    
    private HelperEntity newHelper;
    
    private Boolean renderProfessionalNurseControls;
    

    public helperRegisterManagedBean(HelperEntity newHelper) {
        this.newHelper = newHelper;
    }

    public helperRegisterManagedBean() {
        newHelper = new HelperEntity();
        renderProfessionalNurseControls = false;
    }
    
    
    
    public void updateProfessionalNurseControls()
    {
        if(this.newHelper.getHelperRole().equals("PROFESSIONAL"))
        {
            this.renderProfessionalNurseControls = true;
        }
        else
        {
            this.renderProfessionalNurseControls = false;
        }
    }
    
    
    
    public void saveNewHelper(ActionEvent event)
    {
        setNewHelper(helperController.createNewHelper(getNewHelper()));
        getNewHelper().setHelperId(getNewHelper().getHelperId());
       
        setNewHelper(new HelperEntity());
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New helper " + getNewHelper().getFirstName() + getNewHelper().getLastName() + " created successfully", null));
    }

    /**
     * @return the newHelper
     */
    public HelperEntity getNewHelper() {
        return newHelper;
    }

    /**
     * @param newHelper the newHelper to set
     */
    public void setNewHelper(HelperEntity newHelper) {
        this.newHelper = newHelper;
    }

    /**
     * @return the renderProfessionalNurseControls
     */
    public Boolean getRenderProfessionalNurseControls() {
        return renderProfessionalNurseControls;
    }

    /**
     * @param renderProfessionalNurseControls the renderProfessionalNurseControls to set
     */
    public void setRenderProfessionalNurseControls(Boolean renderProfessionalNurseControls) {
        this.renderProfessionalNurseControls = renderProfessionalNurseControls;
    }
}
