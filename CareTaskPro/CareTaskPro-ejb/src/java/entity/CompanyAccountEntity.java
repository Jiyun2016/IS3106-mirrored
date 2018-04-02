package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Yap Jun Hao
 */
@Entity
public class CompanyAccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyAccountId;
    @Column(precision = 18, scale = 2,nullable = false)
    private BigDecimal balance;
    
    //1 company account can be mapped to 1 admin
    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private AdminEntity admin;
    
    //1 company account can have many task payments
    //@OneToMany(mappedBy = "comapanyAccountEntity")
    //private List<TaskPaymentEntity> taskPayments;
    
    //1 company account can have many payroll payments
    //@OneToMany(mappedBy = "companyAccountEntity")
    //private List<PayrollEntity> payrollPayments;
            
    public CompanyAccountEntity() {
        balance = BigDecimal.ZERO;
        //taskPayments = new ArrayList<>();
        //payrollPayments = new ArrayList<>();
    }

    public Long getCompanyAccountId() {
        return companyAccountId;
    }

    public void setCompanyAccountId(Long companyAccountId) {
        this.companyAccountId = companyAccountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyAccountId != null ? companyAccountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyAccountEntity)) {
            return false;
        }
        CompanyAccountEntity other = (CompanyAccountEntity) object;
        if ((this.companyAccountId == null && other.companyAccountId != null) || (this.companyAccountId != null && !this.companyAccountId.equals(other.companyAccountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CompanyAccountEntity[ companyAccountId=" + companyAccountId + " ]";
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AdminEntity getAdmin() {
        return admin;
    }

    public void setAdmin(AdminEntity admin) {
        this.admin = admin;
    }
/*
    public List<TaskPaymentEntity> getTaskPayments() {
        return taskPayments;
    }

    public void setTaskPayments(List<TaskPaymentEntity> taskPayments) {
        this.taskPayments = taskPayments;
    }

    public List<PayrollEntity> getPayrollPayments() {
        return payrollPayments;
    }

    public void setPayrollPayments(List<PayrollEntity> payrollPayments) {
        this.payrollPayments = payrollPayments;
    }
  */  
}
