package ws.restful.datamodel;

import entity.TaskEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "createTaskReq", propOrder = {
    "task"
})
public class CreateTaskReq {
    
    private TaskEntity task;

    public CreateTaskReq() {
    }

    public CreateTaskReq(TaskEntity task) {
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
