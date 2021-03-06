package ws.restful;

import ejb.session.stateless.TaskControllerLocal;
import entity.HelperEntity;
import entity.TaskEntity;
import java.util.List;
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
import util.enumeration.TaskStatus;
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

    @Path("retrieveTasksByCategory/{category}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveTasksByCategory(@PathParam("category") String category) {
        try {
            List<TaskEntity> taskEntities = taskControllerLocal.retrieveTaskByCategory(Category.valueOf(category));
            
            //to prevent cyclic relationship checks when marshalling/unmarshalling
            for(TaskEntity task: taskEntities) {
                for(HelperEntity helper: task.getPreferredHelpers()) {
                    helper.getRecommendedTaskEntities().clear();
                    helper.getTaskEntities().clear();
                }
                task.getRequesterEntity().getTaskEntities().clear();
                
                if(task.getHelperEntity() != null) {
                    task.getHelperEntity().getRecommendedTaskEntities().clear();
                    task.getHelperEntity().getTaskEntities().clear();
                }
                if(task.getReviewEntity() != null) {
                    task.getReviewEntity().setTaskEntity(null);
                }
                if(task.getPaymentEntity() != null) {
                    task.getPaymentEntity().setTaskEntity(null);
                }
            }      
            
            return Response.status(Status.OK).entity(new RetrieveAllTasksRsp(taskEntities)).build();
        }
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());  
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }    
    
    @Path("retrieveTasksByStatus/{status}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveTasksByStatus(@PathParam("status") String status) {
        try {
            List<TaskEntity> taskEntities = taskControllerLocal.retrieveTasksByStatus(status);

            //to prevent cyclic relationship checks when marshalling/unmarshalling
            for(TaskEntity task: taskEntities) {
                for(HelperEntity helper: task.getPreferredHelpers()) {
                    helper.getRecommendedTaskEntities().clear();
                    helper.getTaskEntities().clear();
                }
                task.getRequesterEntity().getTaskEntities().clear();
                
                if(task.getHelperEntity() != null) {
                    task.getHelperEntity().getRecommendedTaskEntities().clear();
                    task.getHelperEntity().getTaskEntities().clear(); 
                }
                if(task.getReviewEntity() != null) {
                    task.getReviewEntity().setTaskEntity(null);
                }
                if(task.getPaymentEntity() != null) {
                    task.getPaymentEntity().setTaskEntity(null);
                }
            }

            return Response.status(Status.OK).entity(new RetrieveAllTasksRsp(taskEntities)).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());  
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }        
    
    @Path("retrieveTasksByPreferredHelperId/{helperId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveTasksByStatusByHelperId(@PathParam("helperId") Long helperId) {
        try {
            List<TaskEntity> taskEntities = taskControllerLocal.retrieveTaskByPreferredHelperId(helperId);
            
            //to prevent cyclic relationship checks when marshalling/unmarshalling
            for(TaskEntity task: taskEntities) {
                for(HelperEntity helper: task.getPreferredHelpers()) {
                    helper.getRecommendedTaskEntities().clear();
                    helper.getTaskEntities().clear();
                }
                task.getRequesterEntity().getTaskEntities().clear();
                
                if(task.getHelperEntity() != null) {
                    task.getHelperEntity().getRecommendedTaskEntities().clear();
                    task.getHelperEntity().getTaskEntities().clear();
                }
                if(task.getReviewEntity() != null) {
                    task.getReviewEntity().setTaskEntity(null);
                }
                if(task.getPaymentEntity() != null) {
                    task.getPaymentEntity().setTaskEntity(null);
                }
            }
            
            return Response.status(Status.OK).entity(new RetrieveAllTasksRsp(taskEntities)).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());  
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }           
    
    @Path("retrieveTasksByStatusByHelperId/{helperId}/{status}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveTasksByStatusByHelperId(@PathParam("helperId") Long helperId, @PathParam("status") String status) {
        try {
            List<TaskEntity> taskEntities = taskControllerLocal.retrieveTaskByStatusByHelperId(helperId, status);
            
            //to prevent cyclic relationship checks when marshalling/unmarshalling
            for(TaskEntity task: taskEntities) {
                for(HelperEntity helper: task.getPreferredHelpers()) {
                    helper.getRecommendedTaskEntities().clear();
                    helper.getTaskEntities().clear();
                }
                task.getRequesterEntity().getTaskEntities().clear();
                
                if(task.getHelperEntity() != null) {
                    task.getHelperEntity().getRecommendedTaskEntities().clear();
                    task.getHelperEntity().getTaskEntities().clear();
                }
                if(task.getReviewEntity() != null) {
                    task.getReviewEntity().setTaskEntity(null);
                }
                if(task.getPaymentEntity() != null) {
                    task.getPaymentEntity().setTaskEntity(null);
                }
            }
            
            return Response.status(Status.OK).entity(new RetrieveAllTasksRsp(taskEntities)).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());  
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }        
    
    @Path("retrieveTasksByStatusByRequesterId/{requesterId}/{status}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveTasksByRequesterId(@PathParam("requesterId") Long requesterId, @PathParam("status") String status) {
        try {
            List<TaskEntity> taskEntities = taskControllerLocal.retrieveTaskByStatusByRequesterId(requesterId, status);
            
            //to prevent cyclic relationship checks when marshalling/unmarshalling
            for(TaskEntity task: taskEntities) {
                for(HelperEntity helper: task.getPreferredHelpers()) {
                    helper.getRecommendedTaskEntities().clear();
                    helper.getTaskEntities().clear();
                }
                task.getRequesterEntity().getTaskEntities().clear();
                
                if(task.getHelperEntity() != null) {
                    task.getHelperEntity().getRecommendedTaskEntities().clear();
                    task.getHelperEntity().getTaskEntities().clear();
                }
                if(task.getReviewEntity() != null) {
                    task.getReviewEntity().setTaskEntity(null);
                }
                if(task.getPaymentEntity() != null) {
                    task.getPaymentEntity().setTaskEntity(null);
                }
            }
            
            return Response.status(Status.OK).entity(new RetrieveAllTasksRsp(taskEntities)).build();
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
            TaskEntity task = taskControllerLocal.retrieveTaskById(taskId);
            for(HelperEntity helper: task.getPreferredHelpers()) {
                helper.getRecommendedTaskEntities().clear();
                helper.getTaskEntities().clear();
            }
            task.getRequesterEntity().getTaskEntities().clear();
                
            if(task.getHelperEntity() != null) {
                task.getHelperEntity().getRecommendedTaskEntities().clear();
                task.getHelperEntity().getTaskEntities().clear();
            }
            if(task.getReviewEntity() != null) {
                task.getReviewEntity().setTaskEntity(null);
            }
            if(task.getPaymentEntity() != null) {
                task.getPaymentEntity().setTaskEntity(null);
            }
            
            return Response.status(Status.OK).entity(new RetrieveTaskRsp(task)).build();
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
    
    @Path("updateTask")
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
    @Path("assignHelperToTask")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignHelperToTask(JAXBElement<UpdateTaskReq> jaxbUpdateTaskReq) {
        if((jaxbUpdateTaskReq != null) && (jaxbUpdateTaskReq.getValue() != null)) {
            try {
                UpdateTaskReq updateTaskReq = jaxbUpdateTaskReq.getValue();
                taskControllerLocal.assignHelperToTask(updateTaskReq.getTask().getHelperEntity().getHelperId(),
                                                        updateTaskReq.getTask().getTaskId());      
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
