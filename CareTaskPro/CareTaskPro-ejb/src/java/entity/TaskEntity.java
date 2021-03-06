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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import util.enumeration.Category;
import util.enumeration.TaskStatus;
import util.stringConstant.TaskStatusString;

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

    private String category;
    

    @Column(length = 32, nullable = false)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date endDateTime;
    @Column(nullable = false)
    private String taskStatus;
    
    @OneToOne(mappedBy = "taskEntity",optional = true)
    @JoinColumn(nullable = true)
    private PaymentEntity paymentEntity;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private RequesterEntity requesterEntity;
    @ManyToMany
    private List<HelperEntity> preferredHelpers;
    @ManyToOne(optional = true)
    @JoinColumn(nullable = true)
    private HelperEntity helperEntity; 
    @OneToOne(mappedBy = "taskEntity",optional = true)
    @JoinColumn(nullable = true)
    private ReviewEntity reviewEntity;

    public TaskEntity() {
        this.taskStatus = TaskStatusString.PENDING;
//        this.paymentEntity = new PaymentEntity();
//        this.reviewEntity = new ReviewEntity();
//        this.helperEntity = new HelperEntity();
    }
    
     public TaskEntity(String category, String description, Date startDateTime, Date endDateTime, RequesterEntity requesterEntity, List<HelperEntity> preferredHelpers) {
        this.taskStatus = TaskStatusString.PENDING;
        this.category = category;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.requesterEntity = requesterEntity;
        this.preferredHelpers = preferredHelpers;
    }


    public TaskEntity(String category, String description, Date startDateTime, Date endDateTime, String taskStatus,  RequesterEntity requesterEntity, HelperEntity helperEntity) {

        this.category = category;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.taskStatus = taskStatus;
        this.requesterEntity = requesterEntity;
        this.helperEntity = helperEntity;
    }
    
     public TaskEntity(String category, String description, Date startDateTime, Date endDateTime, String taskStatus,  RequesterEntity requesterEntity) {
        this.category = category;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.taskStatus = taskStatus;
        this.requesterEntity = requesterEntity;
        
    }
    
   

    

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public RequesterEntity getRequesterEntity() {
        return requesterEntity;
    }

    public void setRequesterEntity(RequesterEntity requesterEntity) {
        this.requesterEntity = requesterEntity;
    }

    public List<HelperEntity> getPreferredHelpers() {
        return preferredHelpers;
    }

    public void setPreferredHelpers(List<HelperEntity> preferredHelpers) {
        this.preferredHelpers = preferredHelpers;
    }

    public HelperEntity getHelperEntity() {
        return helperEntity;
    }

    public void setHelperEntity(HelperEntity helperEntity) {
        this.helperEntity = helperEntity;
    }

    public ReviewEntity getReviewEntity() {
        return reviewEntity;
    }

    public void setReviewEntity(ReviewEntity reviewEntity) {
        this.reviewEntity = reviewEntity;
    }

    public PaymentEntity getPaymentEntity() {
        return paymentEntity;
    }

    public void setPaymentEntity(PaymentEntity paymentEntity) {
        this.paymentEntity = paymentEntity;
    }

  
}
