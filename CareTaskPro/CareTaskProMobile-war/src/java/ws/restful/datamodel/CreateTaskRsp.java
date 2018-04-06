package ws.restful.datamodel;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "createTaskRsp", propOrder = {
    "taskId"
})
public class CreateTaskRsp {
    
    private Long taskId;

    public CreateTaskRsp() {
    }

    public CreateTaskRsp(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the taskId
     */
    public long getTaskId() {
        return taskId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    
}
