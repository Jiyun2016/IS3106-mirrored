package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import util.enumeration.HelperRole;

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
    private String email;
    @Column(length = 32, nullable = false, unique = true)
    private String phone;
    @Column(length = 32, nullable = false)
    private String password;
    @Column(length = 32, nullable = false)
    private String address;
    @Column(nullable = false)
    private Boolean isCertified;
    @Column(length = 32)
    private String certName;
    @Column(length = 32)
    private String certNum;
    @Column(length = 32, nullable = false)
    private String bankAccountNumber;
   
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HelperRole helperRole;
    
    @OneToMany(mappedBy = "helperEntity")
    private List<TaskEntity> taskEntities;
    
    @ManyToMany
    private List<TaskEntity> recommendedTaskEntities;
            
    public HelperEntity() {

    }

    public HelperEntity(String firstName, String lastName, String email, String phone, String password, String address, Boolean isCertified, String certName, String certNum, String bankAccountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.isCertified = isCertified;
        this.certName = certName;
        this.certNum = certNum;
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
        hash += (getHelperId() != null ? getHelperId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HelperEntity)) {
            return false;
        }
        HelperEntity other = (HelperEntity) object;
        if ((this.getHelperId() == null && other.getHelperId() != null) || (this.getHelperId() != null && !this.helperId.equals(other.helperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HelperEntity[ helperId=" + getHelperId() + " ]";
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(Boolean isCertified) {
        this.isCertified = isCertified;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    /**
     * @return the helperRole
     */
    public HelperRole getHelperRole() {
        return helperRole;
    }

    /**
     * @param helperRole the helperRole to set
     */
    public void setHelperRole(HelperRole helperRole) {
        this.helperRole = helperRole;
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

    /**
     * @return the recommendedTaskEntities
     */
    public List<TaskEntity> getRecommendedTaskEntities() {
        return recommendedTaskEntities;
    }

    /**
     * @param recommendedTaskEntities the recommendedTaskEntities to set
     */
    public void setRecommendedTaskEntities(List<TaskEntity> recommendedTaskEntities) {
        this.recommendedTaskEntities = recommendedTaskEntities;
    }

}
