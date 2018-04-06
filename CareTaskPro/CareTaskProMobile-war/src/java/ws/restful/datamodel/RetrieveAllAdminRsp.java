package ws.restful.datamodel;

import entity.AdminEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "retrieveAllAdminRsp", propOrder = {
    "admins"
})
public class RetrieveAllAdminRsp {
    
    private List<AdminEntity> admins;

    public RetrieveAllAdminRsp() {
    }

    public RetrieveAllAdminRsp(List<AdminEntity> admins) {
        this.admins = admins;
    }

    /**
     * @return the admins
     */
    public List<AdminEntity> getAdmins() {
        return admins;
    }

    /**
     * @param admins the admins to set
     */
    public void setAdmins(List<AdminEntity> admins) {
        this.admins = admins;
    }
    
}
