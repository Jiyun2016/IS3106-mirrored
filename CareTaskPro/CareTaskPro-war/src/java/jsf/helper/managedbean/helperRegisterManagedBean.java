/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.helper.managedbean;

import ejb.session.stateless.HelperControllerLocal;
import entity.HelperEntity;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Amber
 */
@Named(value = "helperRegisterManagedBean")
@RequestScoped
public class helperRegisterManagedBean{

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
    
    
    
    public void saveNewHelper()
    {
        try{
            if(newHelper.getHelperRole().equalsIgnoreCase("professional")){
                newHelper.setChargeRate(new BigDecimal(0.5));
                newHelper.setIsCertified(true);
            }
            else{
                newHelper.setChargeRate(new BigDecimal(0.4));
                newHelper.setIsCertified(false);
            }
            
        HelperEntity he = helperController.createNewHelper(getNewHelper());
       
        setNewHelper(he);
       
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New helper " + getNewHelper().getFirstName() +" "+ getNewHelper().getLastName() + " created successfully", null));
    }catch(Exception ex){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while registering the new helper: " + ex.getMessage(), null));
    }
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
