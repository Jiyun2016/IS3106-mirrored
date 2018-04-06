package ws.restful.datamodel;

import entity.RequesterEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "createRequesterReq", propOrder = {
    "requester"
})
public class CreateRequesterReq {

    private RequesterEntity requester;
    
    public CreateRequesterReq() {
    }

    public CreateRequesterReq(RequesterEntity requester) {
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
