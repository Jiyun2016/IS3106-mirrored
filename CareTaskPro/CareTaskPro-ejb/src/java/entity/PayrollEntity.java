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
public class PayrollEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payrollId;
    @Column(precision = 18, scale = 2,nullable = false)
    private BigDecimal totalAmount;
    @Column(nullable = false)
    private Integer quantity;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date paymentDateTime;
    
    //Many payroll transactions can be made to 1 company account
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private CompanyAccountEntity companyAccount;
    
    //Many payroll transactions is related to 1 payroll rate
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private PayrollRateEntity payrollRate;
    
    //Many payroll transactions can be made to 1 helper
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private HelperEntity helper;

    //1 allowance payment to 1 task
    @OneToOne
    private TaskEntity task;

    public PayrollEntity() {
    }
    
    public PayrollEntity(BigDecimal totalAmount, Integer quantity, Date paymentDateTime, CompanyAccountEntity companyAccount, PayrollRateEntity payrollRate, HelperEntity helper, TaskEntity task) {
        this.totalAmount = totalAmount;
        this.quantity = quantity;
        this.paymentDateTime = paymentDateTime;
        this.companyAccount = companyAccount;
        this.payrollRate = payrollRate;
        this.helper = helper;
        this.task = task;
    }

    public Long getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(Long payrollId) {
        this.payrollId = payrollId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payrollId != null ? payrollId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PayrollEntity)) {
            return false;
        }
        PayrollEntity other = (PayrollEntity) object;
        if ((this.payrollId == null && other.payrollId != null) || (this.payrollId != null && !this.payrollId.equals(other.payrollId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PayrollEntity[ payrollId=" + payrollId + " ]";
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(Date paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    public CompanyAccountEntity getCompanyAccount() {
        return companyAccount;
    }

    public void setCompanyAccount(CompanyAccountEntity companyAccount) {
        this.companyAccount = companyAccount;
    }

    public PayrollRateEntity getPayrollRate() {
        return payrollRate;
    }

    public void setPayrollRate(PayrollRateEntity payrollRate) {
        this.payrollRate = payrollRate;
    }

    public HelperEntity getHelper() {
        return helper;
    }

    public void setHelper(HelperEntity helper) {
        this.helper = helper;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }
    
    
    
}
