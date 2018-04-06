package ws.restful.datamodel;

import entity.AdminEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "createAdminReq", propOrder = {
    "admin"
})
public class CreateAdminReq {
    
    private AdminEntity admin;

    public CreateAdminReq() {
    }

    public CreateAdminReq(AdminEntity admin) {
        this.admin = admin;
    }

    /**
     * @return the admin
     */
    public AdminEntity getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(AdminEntity admin) {
        this.admin = admin;
    }
    
}
