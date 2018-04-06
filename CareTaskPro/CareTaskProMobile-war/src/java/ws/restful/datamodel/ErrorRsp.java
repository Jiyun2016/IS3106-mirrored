package ws.restful.datamodel;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlType(name = "errorRsp", propOrder = {
    "message"
})
public class ErrorRsp {
    private String message;

    public ErrorRsp() {
    }

    public ErrorRsp(String message) {
        this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
}
