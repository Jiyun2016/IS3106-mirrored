package ws.restful.datamodel;

import entity.HelperEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "retrieveHelperRsp", propOrder = {
    "helper"
})
public class RetrieveHelperRsp {
    
    private HelperEntity helper;

    public RetrieveHelperRsp() {
    }

    public RetrieveHelperRsp(HelperEntity helper) {
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
