package ws.restful.datamodel;

import entity.AdminEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "updateAdminReq", propOrder = {
    "admin"
})
public class UpdateAdminReq {
    
    private AdminEntity admin;

    public UpdateAdminReq() {
    }

    public UpdateAdminReq(AdminEntity admin) {
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
