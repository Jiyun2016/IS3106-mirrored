/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import util.enumeration.Category;

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
    private Date startDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;
    @Column(nullable = false)
    private boolean assigned;
    @Column(nullable = false)
    private boolean completed;
    @Column(nullable = false)
    private boolean complained;
    
    @ManyToOne
    private RequesterEntity requester;
    
    @ManyToMany
    private List<HelperEntity> preferredHelpers;
    
    @ManyToOne
    private HelperEntity assignedHelper;

    public TaskEntity(){
        this.assigned = false;
        this.completed = false;
        this.complained = false;
        
    }
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
     * @return the startDateTime
     */
    public Date getStartDateTime() {
        return startDateTime;
    }

    /**
     * @param startDateTime the startDateTime to set
     */
    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * @return the endDateTime
     */
    public Date getEndDateTime() {
        return endDateTime;
    }

    /**
     * @param endDateTime the endDateTime to set
     */
    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * @return the assigned
     */
    public boolean isAssigned() {
        return assigned;
    }

    /**
     * @param assigned the assigned to set
     */
    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    /**
     * @return the completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * @return the complained
     */
    public boolean isComplained() {
        return complained;
    }

    /**
     * @param complained the complained to set
     */
    public void setComplained(boolean complained) {
        this.complained = complained;
    }

    /**
     * @return the requester
     */
    public RequesterEntity getRequester() {
        return requester;
    }

    /**
     * @param requester the requester to set
     */
    public void setRequester(RequesterEntity requester) {
        this.requester = requester;
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
     * @return the assignedHelper
     */
    public HelperEntity getAssignedHelper() {
        return assignedHelper;
    }

    /**
     * @param assignedHelper the assignedHelper to set
     */
    public void setAssignedHelper(HelperEntity assignedHelper) {
        this.assignedHelper = assignedHelper;
    }

}
