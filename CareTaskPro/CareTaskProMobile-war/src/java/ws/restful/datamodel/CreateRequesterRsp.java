package ws.restful.datamodel;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "createRequesterRsp", propOrder = {
    "requesterId"
})
public class CreateRequesterRsp {
    
    private Long requesterId;

    public CreateRequesterRsp() {
    }

    public CreateRequesterRsp(Long requesterId) {
        this.requesterId = requesterId;
    }

    /**
     * @return the requesterId
     */
    public Long getRequesterId() {
        return requesterId;
    }

    /**
     * @param requesterId the requesterId to set
     */
    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }
    
}
