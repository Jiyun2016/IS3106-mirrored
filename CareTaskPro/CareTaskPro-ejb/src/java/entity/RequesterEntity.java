package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import util.enumeration.Gender;

/**
 *
 * @author Bowen
 */
@Entity
public class RequesterEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requesterId;
    @Column(length = 32, nullable = false)
    private String firstName;
    @Column(length = 32, nullable = false)
    private String lastName;
    @Column(length = 32, nullable = false, unique = true)
    private Gender gender;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    @Column(length = 32, nullable = false, unique = true)
    private String email;
    @Column(length = 32, nullable = false, unique = true)
    private String phone;
    @Column(length = 32, nullable = false)
    private String password;
    @Column(length = 32, nullable = false)
    private String address;
    @Column(nullable = false)
    private Boolean isLoggedIn;
    @Column(length = 32, nullable = false)
    private String creditCardNumber;
    @Column(length = 32, nullable = false)
    private String creditCardName;
    @Column(length = 32, nullable = false)
    private String creditCardCVC;
    @Column(length = 32, nullable = false)
    private String creditCardOTP;
    
    //1 requester request for many tasks
    @OneToMany(mappedBy = "requesterEntity")
    private List<TaskEntity> tasks;
    
    //1 requester makes many payments
    @OneToMany(mappedBy = "requesterEntity")
    private List<TaskPaymentEntity> payments;
    
    /*
    @OneToMany(mappedBy = "requester")
    private List<ReviewEntity> reviews;
    @OneToMany(mappedBy = "requester")
    private List<TaskEntity> pendingTasks;
    @OneToMany(mappedBy = "requester")
    private List<TaskEntity> completedTasks;
    @OneToMany(mappedBy = "requester")
    private List<PaymentEntity> payments;
            */

    public RequesterEntity() {
        tasks = new ArrayList<>();
        payments = new ArrayList<>();
        isLoggedIn = false;
    }

    public RequesterEntity(String firstName, String lastName, Gender gender, Date dateOfBirth, String email, String phone, String password, String address, String creditCardNumber, String creditCardName, String creditCardCVC, String creditCardOTP) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.creditCardName = creditCardName;
        this.creditCardCVC = creditCardCVC;
        this.creditCardOTP = creditCardOTP;
    }
    
    public Long getrequesterId() {
        return requesterId;
    }

    public void setId(Long requesterId) {
        this.requesterId = requesterId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requesterId != null ? requesterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequesterEntity)) {
            return false;
        }
        RequesterEntity other = (RequesterEntity) object;
        if ((this.requesterId == null && other.requesterId != null) || (this.requesterId != null && !this.requesterId.equals(other.requesterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RequesterEntity[ requesterId=" + requesterId + " ]";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    /*
    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public List<TaskEntity> getPendingTasks() {
        return pendingTasks;
    }

    public void setPendingTasks(List<TaskEntity> pendingTasks) {
        this.pendingTasks = pendingTasks;
    }

    public List<TaskEntity> getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(List<TaskEntity> completedTasks) {
        this.completedTasks = completedTasks;
    }

    public List<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentEntity> payments) {
        this.payments = payments;
    }
*/

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public String getCreditCardCVC() {
        return creditCardCVC;
    }

    public void setCreditCardCVC(String creditCardCVC) {
        this.creditCardCVC = creditCardCVC;
    }

    public String getCreditCardOTP() {
        return creditCardOTP;
    }

    public void setCreditCardOTP(String creditCardOTP) {
        this.creditCardOTP = creditCardOTP;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public List<TaskPaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(List<TaskPaymentEntity> payments) {
        this.payments = payments;
    }
    
    
    
}
