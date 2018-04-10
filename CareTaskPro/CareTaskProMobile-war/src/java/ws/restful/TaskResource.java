package ws.restful;

import ejb.session.stateless.TaskControllerLocal;
import entity.TaskEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;
import util.enumeration.Category;
import util.exception.TaskEntityNotFoundException;
import ws.restful.datamodel.CreateTaskReq;
import ws.restful.datamodel.CreateTaskRsp;
import ws.restful.datamodel.ErrorRsp;
import ws.restful.datamodel.RetrieveAllTasksRsp;
import ws.restful.datamodel.RetrieveTaskRsp;
import ws.restful.datamodel.UpdateTaskReq;

/**
 * REST Web Service
 *
 * @author Bowen
 */
@Path("Task")
public class TaskResource {

    @Context
    private UriInfo context;

    private final TaskControllerLocal taskControllerLocal = lookupTaskControllerLocal();

    public TaskResource() {
    }

    @Path("retrieveTasksByCategory")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveTasksByCategory(@PathParam("category") String category) {
        try {
            return Response.status(Status.OK).entity(new RetrieveAllTasksRsp(taskControllerLocal.retrieveTaskByCategory(Category.valueOf(category)))).build();
        }
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());  
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }    
    
    @Path("retrieveAllUnassignedTasks")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllUnassignedTasks() {
        try {
            return Response.status(Status.OK).entity(new RetrieveAllTasksRsp(taskControllerLocal.retrieveTaskNotAssigned())).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());  
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }    
    
    @Path("retrieveAllAssignedTasks")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllAssignedTasks() {
        try {
            return Response.status(Status.OK).entity(new RetrieveAllTasksRsp(taskControllerLocal.retrieveTaskAssigned())).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());  
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }   
    
    @Path("retrieveTask/{taskId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveTask(@PathParam("taskId") Long taskId) {
        try {
            return Response.status(Status.OK).entity(new RetrieveTaskRsp(taskControllerLocal.retrieveTaskById(taskId))).build();
        } 
        catch(TaskEntityNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(errorRsp).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTask(JAXBElement<CreateTaskReq> jaxbCreateTaskReq) {
        
        if((jaxbCreateTaskReq != null) && (jaxbCreateTaskReq.getValue() != null)) {
            try {
                CreateTaskReq createTaskReq = jaxbCreateTaskReq.getValue();               
                TaskEntity task = taskControllerLocal.createNewTask(createTaskReq.getTask());
                CreateTaskRsp createTaskRsp = new CreateTaskRsp(task.getTaskId());
                
                return Response.status(Response.Status.OK).entity(createTaskRsp).build();
            } 
            catch(Exception ex) {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
            }
        }
        else {
            ErrorRsp errorRsp = new ErrorRsp("Invalid create new task request");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }
  
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTask(JAXBElement<UpdateTaskReq> jaxbUpdateTaskReq) {
        if((jaxbUpdateTaskReq != null) && (jaxbUpdateTaskReq.getValue() != null)) {
            try {
                UpdateTaskReq updateTaskReq = jaxbUpdateTaskReq.getValue();               
                taskControllerLocal.updateTaskEntity(updateTaskReq.getTask());         
                
                return Response.status(Response.Status.OK).build();
            } 
            catch(Exception ex) {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
            }
        }
        else {
            ErrorRsp errorRsp = new ErrorRsp("Invalid update task request");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }    

    private TaskControllerLocal lookupTaskControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (TaskControllerLocal) c.lookup("java:global/CareTaskPro/CareTaskPro-ejb/TaskController!ejb.session.stateless.TaskControllerLocal");
        } 
        catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
