package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Yap Jun Hao
 */
@Entity
public class PayrollRateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payrollRateId;
    @Column(precision = 18, scale = 2,nullable = false)
    private BigDecimal amount;
    @Column(length = 32, unique = true)
    private String description;

    public PayrollRateEntity() {
    }

    public PayrollRateEntity(BigDecimal amount, String description) {
        this.amount = amount;
        this.description = description;
    }
    
    public Long getPayrollRateId() {
        return payrollRateId;
    }

    public void setPayrollRateId(Long payrollRateId) {
        this.payrollRateId = payrollRateId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payrollRateId != null ? payrollRateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PayrollRateEntity)) {
            return false;
        }
        PayrollRateEntity other = (PayrollRateEntity) object;
        if ((this.payrollRateId == null && other.payrollRateId != null) || (this.payrollRateId != null && !this.payrollRateId.equals(other.payrollRateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PayrollRateEntity[ payrollRateId=" + payrollRateId + " ]";
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
