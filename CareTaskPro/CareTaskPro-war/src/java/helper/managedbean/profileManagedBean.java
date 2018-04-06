/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.managedbean;

import ejb.session.stateless.HelperControllerLocal;
import entity.HelperEntity;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Amber
 */
@Named(value = "profileManagedBean")
@ViewScoped
public class profileManagedBean implements Serializable{

    @EJB
    private HelperControllerLocal helperController;
    
    private HelperEntity helperToView;
    

    
    public profileManagedBean() {
    }
    
    @PostConstruct
    public void init()
    {
        helperToView = (HelperEntity)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentHelperEntity");
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
    
    
    
    
}
