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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import util.enumeration.Gender;
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
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(length = 32, nullable = false, unique = true)
    private String email;
    @Column(length = 8, nullable = false, unique = true)
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
        taskEntities = new ArrayList<>();
        recommendedTaskEntities = new ArrayList<>();
    }

    public HelperEntity(String firstName, String lastName, Gender gender, String email, String phone, String password, String address, Boolean isCertified, String certName, String certNum, String bankAccountNumber) {
        this();
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.isCertified = isCertified;
        this.certName = certName;
        this.certNum = certNum;
        this.bankAccountNumber = bankAccountNumber;

        if(isCertified) {
            this.helperRole = HelperRole.PROFESSIONAL;
        }
        else {
            this.helperRole = HelperRole.NONPROFESSIONAL;
        }
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

    public HelperRole getHelperRole() {
        return helperRole;
    }

    public void setHelperRole(HelperRole helperRole) {
        this.helperRole = helperRole;
    }

    public List<TaskEntity> getTaskEntities() {
        return taskEntities;
    }

    public void setTaskEntities(List<TaskEntity> taskEntities) {
        this.taskEntities = taskEntities;
    }

    public List<TaskEntity> getRecommendedTaskEntities() {
        return recommendedTaskEntities;
    }

    public void setRecommendedTaskEntities(List<TaskEntity> recommendedTaskEntities) {
        this.recommendedTaskEntities = recommendedTaskEntities;
    }

}
