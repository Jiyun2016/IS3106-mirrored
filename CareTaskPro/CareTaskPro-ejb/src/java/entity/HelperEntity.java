package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import util.enumeration.Gender;
import util.enumeration.TimeSlot;

/**
 *
 * @author Bowen
 */
@Entity
public class HelperEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long helperId;
    @Column(length = 32, nullable = false)
    private String firstName;
    @Column(length = 32, nullable = false)
    private String lastName;
    @Column(length = 32, nullable = false, unique = true)
    private Gender gender;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(length = 32, nullable = false, unique = true)
    private String email;
    @Column(length = 32, nullable = false, unique = true)
    private String phone;
    @Column(length = 32, nullable = false, unique = true)
    private String password;
    @Column(length = 32, nullable = false)
    private String address;
    @Column(length = 32, nullable = false)
    private String occupation;
    @Column(nullable = false)
    private Boolean isCertified;
    @Column(length = 32, nullable = false)
    private String certName;
    @Column(length = 32, unique = true)
    private String certNum;
    @Column(nullable = false)
    private Boolean isLoggedIn;
    @Temporal(TemporalType.DATE)
    private Date availableDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TimeSlot availableTimeSlot;
    @Column(length = 32, nullable = false)
    private String bankName;
    @Column(length = 32, nullable = false)
    private String bankAccountNumber;
    
    //1 helper has many tasks
    @OneToMany(mappedBy = "requesterEntity")
    private List<TaskEntity> tasks;
    
    //1 helper has many allowances
    @OneToMany(mappedBy = "requesterEntity")
    private List<PayrollEntity> allowances;
    
    /*
    @OneToMany(mappedBy = "helper")
    private List<TaskEntity> pendingTasks;
    @OneToMany(mappedBy = "helper")
    private List<TaskEntity> completedTasks;
    @OneToMany(mappedBy = "helper")
    private List<PaymentEntity> payments;
    */

    public HelperEntity() {
        tasks = new ArrayList<>();
        allowances = new ArrayList<>();
        isLoggedIn = false;
    }

    public HelperEntity(String firstName, String lastName, Gender gender, Date dateOfBirth, String email, String phone, String password, String address, String occupation, boolean isCertified, String certName, String certNum, boolean isLoggedIn, Date availableDate, TimeSlot availableTimeSlot, String bankName, String bankAccountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.occupation = occupation;
        this.isCertified = isCertified;
        this.certName = certName;
        this.certNum = certNum;
        this.availableDate = availableDate;
        this.availableTimeSlot = availableTimeSlot;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
    }

    public Long getHelperId() {
        return helperId;
    }

    public void setHelperId(Long helperId) {
        this.helperId = helperId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (helperId != null ? helperId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HelperEntity)) {
            return false;
        }
        HelperEntity other = (HelperEntity) object;
        if ((this.helperId == null && other.helperId != null) || (this.helperId != null && !this.helperId.equals(other.helperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HelperEntity[ helperId=" + helperId + " ]";
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

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public Date getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

    public TimeSlot getAvailableTimeSlot() {
        return availableTimeSlot;
    }

    public void setAvailableTimeSlot(TimeSlot availableTimeSlot) {
        this.availableTimeSlot = availableTimeSlot;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Boolean getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(Boolean isCertified) {
        this.isCertified = isCertified;
    }

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public List<PayrollEntity> getAllowances() {
        return allowances;
    }

    public void setAllowances(List<PayrollEntity> allowances) {
        this.allowances = allowances;
    }

}
