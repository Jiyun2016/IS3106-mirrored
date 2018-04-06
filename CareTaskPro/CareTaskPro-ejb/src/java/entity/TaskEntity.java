package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import util.enumeration.Category;
import util.enumeration.TaskStatus;

/**
 *
 * @author panjiyun
 */
@Entity
public class TaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    
    @Column(length = 32, nullable = false)
    private Category category;
    
    @Column(length = 32, nullable = false)
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date endTime;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    
    
    @OneToOne
    private PaymentEntity paymentEntity;
   
    @ManyToOne
    private RequesterEntity requesterEntity;
    
    @ManyToMany
    private List<HelperEntity> preferredHelpers;
    
    @ManyToOne
    private HelperEntity helperEntity; 
    
    @OneToOne
    private ReviewEntity reviewEntity;

  
    
   
    
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskId != null ? taskId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the taskId fields are not set
        if (!(object instanceof TaskEntity)) {
            return false;
        }
        TaskEntity other = (TaskEntity) object;
        if ((this.taskId == null && other.taskId != null) || (this.taskId != null && !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TaskEntity[ id=" + taskId + " ]";
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the taskStatus
     */
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    /**
     * @param taskStatus the taskStatus to set
     */
    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
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
     * @return the preferredHelpers
     */
    public List<HelperEntity> getPreferredHelpers() {
        return preferredHelpers;
    }

    /**
     * @param preferredHelpers the preferredHelpers to set
     */
    public void setPreferredHelpers(List<HelperEntity> preferredHelpers) {
        this.preferredHelpers = preferredHelpers;
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
     * @return the reviewEntity
     */
    public ReviewEntity getReviewEntity() {
        return reviewEntity;
    }

    /**
     * @param reviewEntity the reviewEntity to set
     */
    public void setReviewEntity(ReviewEntity reviewEntity) {
        this.reviewEntity = reviewEntity;
    }

    /**
     * @return the paymentEntity
     */
    public PaymentEntity getPaymentEntity() {
        return paymentEntity;
    }

    /**
     * @param paymentEntity the paymentEntity to set
     */
    public void setPaymentEntity(PaymentEntity paymentEntity) {
        this.paymentEntity = paymentEntity;
    }

  
}
