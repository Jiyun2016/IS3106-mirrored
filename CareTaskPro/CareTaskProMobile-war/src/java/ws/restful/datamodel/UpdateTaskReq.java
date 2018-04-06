package ws.restful.datamodel;

import entity.TaskEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "updateTaskReq", propOrder = {
    "task"
})
public class UpdateTaskReq {
    
    private TaskEntity task;

    public UpdateTaskReq() {
    }

    public UpdateTaskReq(TaskEntity task) {
        this.task = task;
    }

    /**
     * @return the task
     */
    public TaskEntity getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(TaskEntity task) {
        this.task = task;
    }
    
}
