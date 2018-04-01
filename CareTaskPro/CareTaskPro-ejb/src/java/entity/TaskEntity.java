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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import util.enumeration.Category;
import util.enumeration.TaskStatus;
import util.enumeration.TimeSlot;

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
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date taskDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TimeSlot taskTimeSlot;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    @Column(length = 32, nullable = false)
    private Integer ratings;
    @Column(length = 32, nullable = false)
    private String reviews;
    @Column(nullable = false)
    private Boolean complained;
    
    //Many tasks belong to 1 requester
    @ManyToOne
    private RequesterEntity requester;
    
    //Many tasks belong to 1 helper
    @ManyToOne
    private HelperEntity chosenHelper;
    
    /*
    @ManyToMany
    private List<HelperEntity> preferredHelpers;
    
    @ManyToOne
    private HelperEntity assignedHelper;
*/
    public TaskEntity(){
        //this.assigned = false;
        //this.completed = false;
        this.complained = false;
        
    }
    
    /*
    public TaskEntity(Category category, String description, Date startDateTime, Date endDateTime, RequesterEntity requester, List<HelperEntity> preferredHelpers, HelperEntity assignedHelper) {
        this();
        this.category = category;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.requester = requester;
        this.preferredHelpers = preferredHelpers;
        this.assignedHelper = assignedHelper;
    }
*/

    public TaskEntity(Category category, String description, Date taskDate, TimeSlot taskTimeSlot, TaskStatus taskStatus, Integer ratings, String reviews, Boolean complained, RequesterEntity requester, HelperEntity chosenHelper) {
        this.category = category;
        this.description = description;
        this.taskDate = taskDate;
        this.taskTimeSlot = taskTimeSlot;
        this.taskStatus = taskStatus;
        this.ratings = ratings;
        this.reviews = reviews;
        this.complained = complained;
        this.requester = requester;
        this.chosenHelper = chosenHelper;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
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

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<HelperEntity> getPreferredHelpers() {
        return preferredHelpers;
    }

    public void setPreferredHelpers(List<HelperEntity> preferredHelpers) {
        this.preferredHelpers = preferredHelpers;
    }

    public HelperEntity getAssignedHelper() {
        return assignedHelper;
    }

    public void setAssignedHelper(HelperEntity assignedHelper) {
        this.assignedHelper = assignedHelper;
    }
*/

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public TimeSlot getTaskTimeSlot() {
        return taskTimeSlot;
    }

    public void setTaskTimeSlot(TimeSlot taskTimeSlot) {
        this.taskTimeSlot = taskTimeSlot;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public Boolean getComplained() {
        return complained;
    }

    public void setComplained(Boolean complained) {
        this.complained = complained;
    }

    public RequesterEntity getRequester() {
        return requester;
    }

    public void setRequester(RequesterEntity requester) {
        this.requester = requester;
    }

    public HelperEntity getChosenHelper() {
        return chosenHelper;
    }

    public void setChosenHelper(HelperEntity chosenHelper) {
        this.chosenHelper = chosenHelper;
    }
    
}
