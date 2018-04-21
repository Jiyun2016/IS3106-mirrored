/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.helper.managedbean;

import jsf.requester.managedbean.*;
import ejb.session.stateless.PaymentControllerLocal;
import ejb.session.stateless.RequesterControllerLocal;
import entity.HelperEntity;
import entity.PaymentEntity;
import entity.RequesterEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.PaymentEntityNotFoundException;

/**
 *
 * @author panjiyun
 */
@Named(value = "helperPaymentManagedBean")
@RequestScoped
public class HelperPaymentManagedBean {

    @EJB(name = "PaymentControllerLocal")
    private PaymentControllerLocal paymentControllerLocal;

    @EJB(name = "RequesterControllerLocal")
    private RequesterControllerLocal requesterControllerLocal;

    private List<PaymentEntity> paymentEntities;

    private HelperEntity helperEntity;
    private Long HelperId;
    
    private List<PaymentEntity> filteredPaymentEntities;

    public HelperPaymentManagedBean() {
        filteredPaymentEntities = new ArrayList<PaymentEntity>();
    }

    @PostConstruct
    public void postConstruct() {
        setHelperEntity((HelperEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentHelperEntity"));
        setHelperId(getHelperEntity().getHelperId());
        try {
            setPaymentEntities(paymentControllerLocal.retrievePaymentByRequesterId(getHelperId()));
        } catch (PaymentEntityNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No payment record found: " + ex.getMessage(), null));
            setPaymentEntities(new ArrayList<PaymentEntity>());
        }

    }

    /**
     * @return the paymentEntities
     */
    public List<PaymentEntity> getPaymentEntities() {
        return paymentEntities;
    }

    /**
     * @param paymentEntities the paymentEntities to set
     */
    public void setPaymentEntities(List<PaymentEntity> paymentEntities) {
        this.paymentEntities = paymentEntities;
    }

    /**
     * @return the helperEntity
     */
    public HelperEntity getHelperEntity() {
        return helperEntity;
    }

    /**
     * @param helperEntity the helperEntity to set
     */
    public void setHelperEntity(HelperEntity helperEntity) {
        this.helperEntity = helperEntity;
    }

    /**
     * @return the HelperId
     */
    public Long getHelperId() {
        return HelperId;
    }

    /**
     * @param HelperId the HelperId to set
     */
    public void setHelperId(Long HelperId) {
        this.HelperId = HelperId;
    }

    /**
     * @return the filteredPaymentEntities
     */
    public List<PaymentEntity> getFilteredPaymentEntities() {
        return filteredPaymentEntities;
    }

    /**
     * @param filteredPaymentEntities the filteredPaymentEntities to set
     */
    public void setFilteredPaymentEntities(List<PaymentEntity> filteredPaymentEntities) {
        this.filteredPaymentEntities = filteredPaymentEntities;
    }

}
