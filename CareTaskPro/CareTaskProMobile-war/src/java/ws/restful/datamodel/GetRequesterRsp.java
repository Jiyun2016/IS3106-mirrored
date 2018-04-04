package ws.restful.datamodel;

import java.util.Date;
import javax.xml.bind.annotation.XmlType;
import util.enumeration.Gender;

/**
 *
 * @author Bowen
 */
@XmlType(name = "getRequesterRsp", propOrder = {
    "firstName",
    "lastName",
    "gender",
    "dateOfBirth",
    "email",
    "phone",
    "password",
    "address",
    "creditCardNum",
    "creditCardName",
    "creditCardCVC",
    "isLoggedIn"
})
public class GetRequesterRsp {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date dateOfBirth;
    private String email;
    private String phone;
    private String password;
    private String address;
    private String creditCardNum;
    private String creditCardName;
    private String creditCardCVC;
    private Boolean isLoggedIn;

    public GetRequesterRsp() {
    }

    public GetRequesterRsp(String firstName, String lastName, Gender gender, Date dateOfBirth, String email, String phone, String password, String address, String creditCardNum, String creditCardName, String creditCardCVC, Boolean isLoggedIn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.creditCardNum = creditCardNum;
        this.creditCardName = creditCardName;
        this.creditCardCVC = creditCardCVC;
        this.isLoggedIn = isLoggedIn;
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
     * @return the isLoggedIn
     */
    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    /**
     * @param isLoggedIn the isLoggedIn to set
     */
    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
    
}
