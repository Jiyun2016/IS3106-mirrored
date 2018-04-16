package ws.restful.datamodel;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "createHelperRsp", propOrder = {
    "helperId"
})
public class CreateHelperRsp {
    
    private Long helperId;

    public CreateHelperRsp() {
    }

    public CreateHelperRsp(Long helperId) {
        this.helperId = helperId;
    }

    /**
     * @return the helperId
     */
    public Long getHelperId() {
        return helperId;
    }

    /**
     * @param helperId the helperId to set
     */
    public void setHelperId(Long helperId) {
        this.helperId = helperId;
    }
      
}
