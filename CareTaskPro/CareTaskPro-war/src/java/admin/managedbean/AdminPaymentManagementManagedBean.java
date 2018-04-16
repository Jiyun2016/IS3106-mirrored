/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.managedbean;

import ejb.session.stateless.PaymentControllerLocal;
import entity.PaymentEntity;
import entity.RequesterEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author panjiyun
 */
@Named(value = "adminPaymentManagementManagedBean")
@RequestScoped
public class AdminPaymentManagementManagedBean {

    @EJB(name = "PaymentControllerLocal")
    private PaymentControllerLocal paymentControllerLocal;
    private List<PaymentEntity> paymentEntities;
    private List<PaymentEntity> filteredPaymentEntities;

    public AdminPaymentManagementManagedBean() {
        filteredPaymentEntities = new ArrayList<PaymentEntity>();

    }
    
     @PostConstruct
    public void postConstruct() {
        setPaymentEntities(paymentControllerLocal.retrieveAllPayment());
        if (getPaymentEntities().isEmpty() || getPaymentEntities() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No Payment found. ", null));
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
