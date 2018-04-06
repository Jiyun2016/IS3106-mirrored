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
    private Integer phone;
    @Column(length = 32, nullable = false)
    private String password;
    @Column(length = 32, nullable = false)
    private String address;
    @Column(length = 16, nullable = false, unique = true)
    private Integer creditCardNum;
    @Column(length = 32, nullable = false)
    private String creditCardName;
    @Column(length = 2, nullable = false)
    private Integer creditCardExpiryMonth;
    @Column(length = 4, nullable = false)
    private Integer creditCardExpiryYear;
    @Column(length = 3, nullable = false)
    private Integer creditCardCVC;
    
    @OneToMany(mappedBy = "requesterEntity")
    private List<TaskEntity> taskEntities;

    public RequesterEntity() {
        this.taskEntities = new ArrayList<>();
    }

    public RequesterEntity(String firstName, String lastName, Gender gender, String email, Integer phone, String password, String address, String creditCardName ,Integer creditCardNum, Integer creditCardExpiryMonth, Integer creditCardExpiryYear, Integer creditCardCVC) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.creditCardName = creditCardName;
        this.creditCardNum = creditCardNum;
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

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
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

    public Integer getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(Integer creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }
    
    public Integer getCreditCardExpiryMonth() {
        return creditCardExpiryMonth;
    }

    public void setCreditCardExpiryMonth(Integer creditCardExpiryMonth) {
        this.creditCardExpiryMonth = creditCardExpiryMonth;
    }

    public Integer getCreditCardExpiryYear() {
        return creditCardExpiryYear;
    }

    public void setCreditCardExpiryYear(Integer creditCardExpiryYear) {
        this.creditCardExpiryYear = creditCardExpiryYear;
    }

    public Integer getCreditCardCVC() {
        return creditCardCVC;
    }

    public void setCreditCardCVC(Integer creditCardCVC) {
        this.creditCardCVC = creditCardCVC;
    }

    public List<TaskEntity> getTaskEntities() {
        return taskEntities;
    }

    public void setTaskEntities(List<TaskEntity> taskEntities) {
        this.taskEntities = taskEntities;
    }

}
