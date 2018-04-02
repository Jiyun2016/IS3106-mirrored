package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Yap Jun Hao
 */
@Entity
public class TaskPaymentEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskPaymentId;
    @Column(precision = 18, scale = 2,nullable = false)
    private BigDecimal totalAmount;
    @Column(nullable = false)
    private Integer Quantity;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date paidDateTime;
    
    //Many task payment transaction can be made to 1 company account 
    //@ManyToOne(optional = false)
    //@JoinColumn(nullable = false)
    //private CompanyAccountEntity companyAccount;
    
    //Many task payment transactions is related to 1 task pricing
    //@ManyToOne(optional = false)
    //@JoinColumn(nullable = false)
    //private RequesterEntity requester;
    
    //1 task payment to 1 task
    //@OneToOne
    //private TaskEntity task;

    public TaskPaymentEntity() {
    }

    public TaskPaymentEntity(BigDecimal totalAmount, Integer Quantity, Date paidDateTime) {
        this.totalAmount = totalAmount;
        this.Quantity = Quantity;
        this.paidDateTime = paidDateTime;
        //this.companyAccount = companyAccount;
        //this.requester = requester;
        //this.task = task;
    }
    
    public Long getTaskPaymentId() {
        return taskPaymentId;
    }

    public void setTaskPaymentId(Long taskPaymentId) {
        this.taskPaymentId = this.taskPaymentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskPaymentId != null ? taskPaymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskPaymentEntity)) {
            return false;
        }
        TaskPaymentEntity other = (TaskPaymentEntity) object;
        if ((this.taskPaymentId == null && other.taskPaymentId != null) || (this.taskPaymentId != null && !this.taskPaymentId.equals(other.taskPaymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TaskPaymentEntity[ taskPaymentId=" + taskPaymentId + " ]";
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

    public Date getPaidDateTime() {
        return paidDateTime;
    }

    public void setPaidDateTime(Date paidDateTime) {
        this.paidDateTime = paidDateTime;
    }
/*
    public CompanyAccountEntity getCompanyAccount() {
        return companyAccount;
    }

    public void setCompanyAccount(CompanyAccountEntity companyAccount) {
        this.companyAccount = companyAccount;
    }

    public RequesterEntity getRequester() {
        return requester;
    }

    public void setRequester(RequesterEntity requester) {
        this.requester = requester;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }
  */  
}
