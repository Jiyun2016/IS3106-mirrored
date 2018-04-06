package ws.restful.datamodel;

import entity.HelperEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "retrieveAllHelpersRsp", propOrder = {
    "helpers"
})
public class RetrieveAllHelpersRsp {
    
    private List<HelperEntity> helpers;

    public RetrieveAllHelpersRsp() {
    }

    public RetrieveAllHelpersRsp(List<HelperEntity> helpers) {
        this.helpers = helpers;
    }

    /**
     * @return the helpers
     */
    public List<HelperEntity> getHelpers() {
        return helpers;
    }

    /**
     * @param helpers the helpers to set
     */
    public void setHelpers(List<HelperEntity> helpers) {
        this.helpers = helpers;
    }
    
}
