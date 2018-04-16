package ws.restful;

import ejb.session.stateless.RequesterControllerLocal;
import entity.RequesterEntity;
import entity.TaskEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;
import util.exception.WrongCredentialException;
import util.exception.RequesterNotFoundException;
import ws.restful.datamodel.CreateRequesterReq;
import ws.restful.datamodel.CreateRequesterRsp;
import ws.restful.datamodel.ErrorRsp;
import ws.restful.datamodel.RetrieveAllRequestersRsp;
import ws.restful.datamodel.RetrieveRequesterRsp;
import ws.restful.datamodel.UpdateRequesterReq;

/**
 * REST Web Service
 *
 * @author Bowen
 */
@Path("Requester")
public class RequesterResource {

    @Context
    private UriInfo context;

    private final RequesterControllerLocal requesterControllerLocal = lookupRequesterControllerLocal();

    public RequesterResource() {
    }

    @Path("retrieveAllRequesters")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllRequesters() {
        try {
            List<RequesterEntity> requesterEntities = requesterControllerLocal.retrieveAllRequesters();
            
            //to prevent cyclic relationship checks when marshalling/unmarshalling
            for(RequesterEntity requester: requesterEntities) {
                for(TaskEntity task: requester.getTaskEntities()) {
                    task.setRequesterEntity(null);
                }
            }
            
            return Response.status(Status.OK).entity(new RetrieveAllRequestersRsp(requesterEntities)).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());  
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }    
    
    @Path("retrieveRequester/{requesterId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveRequester(@PathParam("requesterId") Long requesterId) {
        try {
            return Response.status(Status.OK).entity(new RetrieveRequesterRsp(requesterControllerLocal.retrieveRequesterById(requesterId))).build();
        } 
        catch(RequesterNotFoundException ex) {
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
    public Response createRequester(JAXBElement<CreateRequesterReq> jaxbCreateRequesterReq) {
        
        if((jaxbCreateRequesterReq != null) && (jaxbCreateRequesterReq.getValue() != null)) {
            try {
                CreateRequesterReq createRequesterReq = jaxbCreateRequesterReq.getValue();               
                RequesterEntity requester = requesterControllerLocal.createNewRequester(createRequesterReq.getRequester());
                CreateRequesterRsp createRequesterRsp = new CreateRequesterRsp(requester.getRequesterId());
                
                return Response.status(Response.Status.OK).entity(createRequesterRsp).build();
            } 
            catch(Exception ex) {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
            }
        }
        else {
            ErrorRsp errorRsp = new ErrorRsp("Invalid create new requester request");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }
  
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRequester(JAXBElement<UpdateRequesterReq> jaxbUpdateRequesterReq) {
        if((jaxbUpdateRequesterReq != null) && (jaxbUpdateRequesterReq.getValue() != null)) {
            try {
                UpdateRequesterReq updateRequesterReq = jaxbUpdateRequesterReq.getValue();               
                requesterControllerLocal.updateRequester(updateRequesterReq.getRequester());         
                
                return Response.status(Response.Status.OK).build();
            } 
            catch(Exception ex) {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
            }
        }
        else {
            ErrorRsp errorRsp = new ErrorRsp("Invalid update requester request");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }    
    
    @Path("{requesterId}")
    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRequester(@PathParam("requesterId") Long requesterId) {
        try {
            requesterControllerLocal.deleteRequester(requesterId);
            
            return Response.status(Status.OK).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }    

    @Path("loginRequester")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginRequester(@QueryParam("phone") String phone, @QueryParam("password") String password) {
        try {
            RequesterEntity requester = requesterControllerLocal.loginRequester(phone, password);

            //to prevent cyclic relationship checks when marshalling/unmarshalling
            for(TaskEntity task: requester.getTaskEntities()) {
                task.setRequesterEntity(null);
            }
                            
            return Response.status(Status.OK).entity(new RetrieveRequesterRsp(requester)).build();
        } 
        catch(WrongCredentialException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.UNAUTHORIZED).entity(errorRsp).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    private RequesterControllerLocal lookupRequesterControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (RequesterControllerLocal) c.lookup("java:global/CareTaskPro/CareTaskPro-ejb/RequesterController!ejb.session.stateless.RequesterControllerLocal");
        } 
        catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}

