package ws.restful.datamodel;

import entity.HelperEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "createHelperReq", propOrder = {
    "helper"
})
public class CreateHelperReq {

    private HelperEntity helper;
    
    public CreateHelperReq() {
    }

    public CreateHelperReq(HelperEntity helper) {
        this.helper = helper;
    }

    /**
     * @return the helper
     */
    public HelperEntity getHelper() {
        return helper;
    }

    /**
     * @param helper the helper to set
     */
    public void setHelper(HelperEntity helper) {
        this.helper = helper;
    }
    
}
