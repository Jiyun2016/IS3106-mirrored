package ws.restful.datamodel;

import entity.RequesterEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "updateRequesterReq", propOrder = {
    "requester"
})
public class UpdateRequesterReq {
    
    private RequesterEntity requester;

    public UpdateRequesterReq() {
    }

    public UpdateRequesterReq(RequesterEntity requester) {
        this.requester = requester;
    }

    /**
     * @return the requester
     */
    public RequesterEntity getRequester() {
        return requester;
    }

    /**
     * @param requester the requester to set
     */
    public void setRequester(RequesterEntity requester) {
        this.requester = requester;
    }
    
}
