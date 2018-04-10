package ws.restful.datamodel;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "createAdminRsp", propOrder = {
    "adminId"
})
public class CreateAdminRsp {
    
    private Long adminId;

    public CreateAdminRsp() {
    }

    public CreateAdminRsp(Long adminId) {
        this.adminId = adminId;
    }

    /**
     * @return the adminId
     */
    public Long getAdminId() {
        return adminId;
    }

    /**
     * @param adminId the adminId to set
     */
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
    
}
