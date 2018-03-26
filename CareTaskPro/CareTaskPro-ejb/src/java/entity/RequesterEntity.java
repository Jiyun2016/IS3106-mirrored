package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Bowen
 */
@Entity
public class RequesterEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 32, nullable = false)
    private String name;
    @Column(length = 32, nullable = false)
    private String password;
    @Column(length = 32, nullable = false, unique = true)
    private String phone;
    @Column(length = 32, nullable = false)
    private String address;
    @Column(nullable = false)
    private boolean isLoggedIn;
    @OneToMany(mappedBy = "requester")
    private List<ReviewEntity> reviews;
    @OneToMany(mappedBy = "requester")
    private List<TaskEntity> pendingTasks;
    @OneToMany(mappedBy = "requester")
    private List<TaskEntity> completedTasks;
    @OneToMany(mappedBy = "requester")
    private List<PaymentEntity> payments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequesterEntity)) {
            return false;
        }
        RequesterEntity other = (RequesterEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RequesterEntity[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the isLoggedIn
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * @param isLoggedIn the isLoggedIn to set
     */
    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    /**
     * @return the reviews
     */
    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    /**
     * @param reviews the reviews to set
     */
    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    /**
     * @return the pendingTasks
     */
    public List<TaskEntity> getPendingTasks() {
        return pendingTasks;
    }

    /**
     * @param pendingTasks the pendingTasks to set
     */
    public void setPendingTasks(List<TaskEntity> pendingTasks) {
        this.pendingTasks = pendingTasks;
    }

    /**
     * @return the completedTasks
     */
    public List<TaskEntity> getCompletedTasks() {
        return completedTasks;
    }

    /**
     * @param completedTasks the completedTasks to set
     */
    public void setCompletedTasks(List<TaskEntity> completedTasks) {
        this.completedTasks = completedTasks;
    }

    /**
     * @return the payments
     */
    public List<PaymentEntity> getPayments() {
        return payments;
    }

    /**
     * @param payments the payments to set
     */
    public void setPayments(List<PaymentEntity> payments) {
        this.payments = payments;
    }

}
