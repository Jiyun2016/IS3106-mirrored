/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.helper.managedbean;

import ejb.session.stateless.HelperControllerLocal;
import entity.HelperEntity;
import java.io.Serializable;
import javax.annotation.PostConstruct;
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
@Named(value = "helperProfileManagedBean")
@ViewScoped
public class helperProfileManagedBean implements Serializable{

    @EJB
    private HelperControllerLocal helperController;
    
    private HelperEntity helperToView;
    
    private Boolean renderProfessionalNurseControls;
    
    

    
    public helperProfileManagedBean() {
    }
    
    @PostConstruct
    public void init()
    {
        helperToView = (HelperEntity)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentHelperEntity");
        setRenderProfessionalNurseControls((Boolean) helperToView.getHelperRole().equalsIgnoreCase("professional"));
    }

    public void saveHelper(ActionEvent event)
    {
       helperController.updateHelper(helperToView);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Helper " + helperToView.getHelperId() + " updated successfully", null));
    }
    /**
     * @return the helperToView
     */
    public HelperEntity getHelperToView() {
        return helperToView;
    }

    /**
     * @param helperToView the helperToView to set
     */
    public void setHelperToView(HelperEntity helperToView) {
        this.helperToView = helperToView;
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
