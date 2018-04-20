/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.requester.managedbean;

import ejb.session.stateless.PaymentControllerLocal;
import ejb.session.stateless.RequesterControllerLocal;
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
@Named(value = "requesterPaymentManagedBean")
@RequestScoped
public class RequesterPaymentManagedBean {

    @EJB(name = "PaymentControllerLocal")
    private PaymentControllerLocal paymentControllerLocal;

    @EJB(name = "RequesterControllerLocal")
    private RequesterControllerLocal requesterControllerLocal;

    private List<PaymentEntity> paymentEntities;

    private RequesterEntity requesterEntity;
    private Long requesterId;
    
    private List<PaymentEntity> filteredPaymentEntities;

    public RequesterPaymentManagedBean() {
        filteredPaymentEntities = new ArrayList<PaymentEntity>();
    }

    @PostConstruct
    public void postConstruct() {
        setRequesterEntity((RequesterEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentRequesterEntity"));
        setRequesterId(getRequesterEntity().getRequesterId());
        try {
            setPaymentEntities(paymentControllerLocal.retrievePaymentByRequesterId(getRequesterId()));
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
     * @return the requesterEntity
     */
    public RequesterEntity getRequesterEntity() {
        return requesterEntity;
    }

    /**
     * @param requesterEntity the requesterEntity to set
     */
    public void setRequesterEntity(RequesterEntity requesterEntity) {
        this.requesterEntity = requesterEntity;
    }

    /**
     * @return the requesterId
     */
    public Long getRequesterId() {
        return requesterId;
    }

    /**
     * @param requesterId the requesterId to set
     */
    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
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
