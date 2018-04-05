package ws.restful.datamodel;

import entity.RequesterEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "retrieveAllRequestersRsp", propOrder = {
    "requesters"
})
public class RetrieveAllRequestersRsp {
    
    private List<RequesterEntity> requesters;

    public RetrieveAllRequestersRsp() {
    }

    public RetrieveAllRequestersRsp(List<RequesterEntity> requesters) {
        this.requesters = requesters;
    }

    /**
     * @return the requesters
     */
    public List<RequesterEntity> getRequesters() {
        return requesters;
    }

    /**
     * @param requesters the requesters to set
     */
    public void setRequesters(List<RequesterEntity> requesters) {
        this.requesters = requesters;
    }
    
}
