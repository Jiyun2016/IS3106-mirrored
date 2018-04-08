package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class RequesterEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requesterId;
    @Column(length = 32, nullable = false)
    private String firstName;
    @Column(length = 32, nullable = false)
    private String lastName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(length = 32, nullable = false, unique = true)
    private String email;
    @Column(length = 18, nullable = false, unique = true)
    private String phone;
    @Column(length = 32, nullable = false)
    private String password;
    @Column(length = 32, nullable = false)
    private String address;
    @Column(length = 16, nullable = false, unique = true)
    private String creditCardNum;
    @Column(length = 32, nullable = false)
    private String creditCardName;
    @Column(length = 2, nullable = false)
    private String creditCardExpiryMonth;
    @Column(length = 4, nullable = false)
    private String creditCardExpiryYear;
    @Column(length = 3, nullable = false)
    private String creditCardCVC;
    
    @OneToMany(mappedBy = "requesterEntity")
    private List<TaskEntity> taskEntities;

    public RequesterEntity() {
        this.taskEntities = new ArrayList<>();
    }

    public RequesterEntity(String firstName, String lastName, Gender gender, String email, String phone, String password, String address, String creditCardNum, String creditCardName, String creditCardExpiryMonth, String creditCardExpiryYear, String creditCardCVC) {
        this();
 
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.creditCardNum = creditCardNum;
        this.creditCardName = creditCardName;
        this.creditCardExpiryMonth = creditCardExpiryMonth;
        this.creditCardExpiryYear = creditCardExpiryYear;
        this.creditCardCVC = creditCardCVC;
    }

    public Long getrequesterId() {
        return getRequesterId();
    }

    public void setId(Long requesterId) {
        this.setRequesterId(requesterId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getRequesterId() != null ? getRequesterId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequesterEntity)) {
            return false;
        }
        RequesterEntity other = (RequesterEntity) object;
        if ((this.getRequesterId() == null && other.getRequesterId() != null) || (this.getRequesterId() != null && !this.requesterId.equals(other.requesterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RequesterEntity[ requesterId=" + getRequesterId() + " ]";
    }

    /**
     * @return the requesterId
     */
    public Long getRequesterId() {
        return requesterId;
    }

    /**
     * @param requesterId the requesterId to set
     */
    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
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
     * @return the creditCardNum
     */
    public String getCreditCardNum() {
        return creditCardNum;
    }

    /**
     * @param creditCardNum the creditCardNum to set
     */
    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    /**
     * @return the creditCardName
     */
    public String getCreditCardName() {
        return creditCardName;
    }

    /**
     * @param creditCardName the creditCardName to set
     */
    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    /**
     * @return the creditCardExpiryMonth
     */
    public String getCreditCardExpiryMonth() {
        return creditCardExpiryMonth;
    }

    /**
     * @param creditCardExpiryMonth the creditCardExpiryMonth to set
     */
    public void setCreditCardExpiryMonth(String creditCardExpiryMonth) {
        this.creditCardExpiryMonth = creditCardExpiryMonth;
    }

    /**
     * @return the creditCardExpiryYear
     */
    public String getCreditCardExpiryYear() {
        return creditCardExpiryYear;
    }

    /**
     * @param creditCardExpiryYear the creditCardExpiryYear to set
     */
    public void setCreditCardExpiryYear(String creditCardExpiryYear) {
        this.creditCardExpiryYear = creditCardExpiryYear;
    }

    /**
     * @return the creditCardCVC
     */
    public String getCreditCardCVC() {
        return creditCardCVC;
    }

    /**
     * @param creditCardCVC the creditCardCVC to set
     */
    public void setCreditCardCVC(String creditCardCVC) {
        this.creditCardCVC = creditCardCVC;
    }

    /**
     * @return the taskEntities
     */
    public List<TaskEntity> getTaskEntities() {
        return taskEntities;
    }

    /**
     * @param taskEntities the taskEntities to set
     */
    public void setTaskEntities(List<TaskEntity> taskEntities) {
        this.taskEntities = taskEntities;
    }

}
