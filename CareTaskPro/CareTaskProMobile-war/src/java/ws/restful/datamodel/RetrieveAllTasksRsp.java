package ws.restful.datamodel;

import entity.TaskEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "retrieveAllTasksRsp", propOrder = {
    "tasks"
})
public class RetrieveAllTasksRsp {
    
    private List<TaskEntity> tasks;

    public RetrieveAllTasksRsp() {
    }

    public RetrieveAllTasksRsp(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return the tasks
     */
    public List<TaskEntity> getTasks() {
        return tasks;
    }

    /**
     * @param tasks the tasks to set
     */
    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }
    
}
