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
public class TaskPricingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskPricingId;
    @Column(precision = 18, scale = 2,nullable = false)
    private BigDecimal amount;
    @Column(length = 32, unique = true)
    private String description;

    public TaskPricingEntity() {
    }

    public TaskPricingEntity(BigDecimal amount, String description) {
        this.amount = amount;
        this.description = description;
    }    
    
    public Long getTaskPricingId() {
        return taskPricingId;
    }

    public void setId(Long taskPricingId) {
        this.taskPricingId = taskPricingId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskPricingId != null ? taskPricingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskPricingEntity)) {
            return false;
        }
        TaskPricingEntity other = (TaskPricingEntity) object;
        if ((this.taskPricingId == null && other.taskPricingId != null) || (this.taskPricingId != null && !this.taskPricingId.equals(other.taskPricingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TaskPricingEntity[ taskPricingId=" + taskPricingId + " ]";
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
