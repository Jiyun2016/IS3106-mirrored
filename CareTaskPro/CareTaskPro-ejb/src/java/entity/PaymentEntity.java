package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import util.enumeration.PaymentStatus;
import util.enumeration.TaskStatus;

/**
 *
 * @author Yap Jun Hao
 */
@Entity
public class PaymentEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @Column(nullable = false)
    private String paymentStatus;
    @Column(precision = 18, scale = 2,nullable = false)
    private BigDecimal paymentAmount;
    @Column(precision = 18, scale = 2,nullable = false)
    private BigDecimal helperSalary;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date paymentTime;
    @Column(precision = 3, scale = 2,nullable = false)
    private BigDecimal companyRevenue;
 
    @OneToOne(optional = false)
    private TaskEntity taskEntity;

    public PaymentEntity() {
    }

    public PaymentEntity(TaskEntity taskEntity, String paymentStatus, BigDecimal paymentAmount, BigDecimal helperSalary, Date paymentTime, BigDecimal companyRevenue) {
        this.taskEntity = taskEntity;
        this.paymentStatus = paymentStatus;
        this.paymentAmount = paymentAmount;
        this.helperSalary = helperSalary;
        this.paymentTime = paymentTime;
        this.companyRevenue = companyRevenue;
    }
    
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentId != null ? paymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentEntity)) {
            return false;
        }
        PaymentEntity other = (PaymentEntity) object;
        if ((this.paymentId == null && other.paymentId != null) || (this.paymentId != null && !this.paymentId.equals(other.paymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PaymentEntity[ paymentId=" + paymentId + " ]";
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getHelperSalary() {
        return helperSalary;
    }

    public void setHelperSalary(BigDecimal helperSalary) {
        this.helperSalary = helperSalary;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public BigDecimal getCompanyRevenue() {
        return companyRevenue;
    }

    public void setCompanyRevenue(BigDecimal companyRevenue) {
        this.companyRevenue = companyRevenue;
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

}
