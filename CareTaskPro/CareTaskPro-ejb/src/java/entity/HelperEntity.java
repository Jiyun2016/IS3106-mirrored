package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import util.enumeration.Gender;

/**
 *
 * @author Bowen
 */
@Entity
public class HelperEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 32, nullable = false)
    private String firstName;
    @Column(length = 32, nullable = false)
    private String lastName;
    @Column(length = 32, nullable = false, unique = true)
    private Gender gender;
    @Column(length = 32, nullable = false)
    private Date dateOfBirth;
    @Column(length = 32, nullable = false, unique = true)
    private String email;
    @Column(length = 32, nullable = false, unique = true)
    private String phone;
    @Column(length = 32, nullable = false, unique = true)
    private String password;
    @Column(length = 32, nullable = false)
    private String address;
    @Column(nullable = false)
    private boolean isCertified;
    @Column(length = 32, unique = true)
    private String certNum;
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
        if (!(object instanceof HelperEntity)) {
            return false;
        }
        HelperEntity other = (HelperEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HelperEntity[ id=" + id + " ]";
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the isCertified
     */
    public boolean isCertified() {
        return isCertified;
    }

    /**
     * @param isCertified the isCertified to set
     */
    public void setIsCertified(boolean isCertified) {
        this.isCertified = isCertified;
    }

    /**
     * @return the certNum
     */
    public String getCertNum() {
        return certNum;
    }

    /**
     * @param certNum the certNum to set
     */
    public void setCertNum(String certNum) {
        this.certNum = certNum;
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
