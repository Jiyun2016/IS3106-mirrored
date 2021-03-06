package ws.restful;

import ejb.session.stateless.AdminControllerLocal;
import entity.AdminEntity;
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
import util.exception.AdminEntityNotFoundException;
import util.exception.InvalidLoginCredentialException;
import ws.restful.datamodel.CreateAdminReq;
import ws.restful.datamodel.CreateAdminRsp;
import ws.restful.datamodel.ErrorRsp;
import ws.restful.datamodel.RetrieveAdminRsp;
import ws.restful.datamodel.RetrieveAllAdminRsp;
import ws.restful.datamodel.UpdateAdminReq;

/**
 * REST Web Service
 *
 * @author Bowen
 */
@Path("Admin")
public class AdminResource {

    @Context
    private UriInfo context;

    private final AdminControllerLocal adminControllerLocal = lookupAdminControllerLocal();
    
    /**
     * Creates a new instance of AdminResource
     */
    public AdminResource() {
    }

    @Path("retrieveAllAdmin")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllAdmin() {
        try {
            return Response.status(Status.OK).entity(new RetrieveAllAdminRsp(adminControllerLocal.retrieveAllAdmin())).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());  
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }    
    
    @Path("retrieveAdmin/{username}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAdmin(@PathParam("username") String username) {
        try {
            return Response.status(Status.OK).entity(new RetrieveAdminRsp(adminControllerLocal.retrieveAdminByUsername(username))).build();
        } 
        catch(AdminEntityNotFoundException ex) {
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
    public Response createAdmin(JAXBElement<CreateAdminReq> jaxbCreateAdminReq) {
        
        if((jaxbCreateAdminReq != null) && (jaxbCreateAdminReq.getValue() != null)) {
            try {
                CreateAdminReq createAdminReq = jaxbCreateAdminReq.getValue();
                AdminEntity admin = adminControllerLocal.createNewAdmin(createAdminReq.getAdmin());
                CreateAdminRsp createAdminRsp = new CreateAdminRsp(admin.getAdminId());
                
                return Response.status(Response.Status.OK).entity(createAdminRsp).build();
            }
            catch(Exception ex) {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
            }
        }
        else {
            ErrorRsp errorRsp = new ErrorRsp("Invalid create new admin request");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }
  
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAdmin(JAXBElement<UpdateAdminReq> jaxbUpdateAdminReq) {
        if((jaxbUpdateAdminReq != null) && (jaxbUpdateAdminReq.getValue() != null)) {
            try {
                UpdateAdminReq updateAdminReq = jaxbUpdateAdminReq.getValue();               
                adminControllerLocal.updateEmployee(updateAdminReq.getAdmin());         
                
                return Response.status(Response.Status.OK).build();
            }
            catch(Exception ex) {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
            }
        }
        else {
            ErrorRsp errorRsp = new ErrorRsp("Invalid update admin request");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }    
    
    @Path("{username}")
    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAdmin(@PathParam("username") String username) {
        try {
            adminControllerLocal.deleteEmployee(username);
            
            return Response.status(Status.OK).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }    

    @Path("loginAdmin")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginAdmin(@QueryParam("username") String username, @QueryParam("password") String password) {
        try {
            AdminEntity admin = adminControllerLocal.adminLogin(username, password);
            return Response.status(Status.OK).entity(new RetrieveAdminRsp(admin)).build();
        } 
        catch(InvalidLoginCredentialException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.UNAUTHORIZED).entity(errorRsp).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    private AdminControllerLocal lookupAdminControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (AdminControllerLocal) c.lookup("java:global/CareTaskPro/CareTaskPro-ejb/AdminController!ejb.session.stateless.AdminControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
