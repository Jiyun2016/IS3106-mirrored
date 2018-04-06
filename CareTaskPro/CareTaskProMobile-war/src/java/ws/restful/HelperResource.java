package ws.restful;

import ejb.session.stateless.HelperControllerLocal;
import entity.HelperEntity;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import util.exception.HelperNotFoundException;
import ws.restful.datamodel.CreateHelperReq;
import ws.restful.datamodel.CreateHelperRsp;
import ws.restful.datamodel.ErrorRsp;
import ws.restful.datamodel.RetrieveAllHelpersRsp;
import ws.restful.datamodel.RetrieveHelperRsp;
import ws.restful.datamodel.UpdateHelperReq;
import javax.xml.bind.JAXBElement;

/**
 * REST Web Service
 *
 * @author Bowen
 */
@Path("Helper")
public class HelperResource {

    @Context
    private UriInfo context;

 private final HelperControllerLocal helperControllerLocal = lookupHelperControllerLocal();

    public HelperResource() {
    }

    @Path("retrieveAllHelpers")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllProducts() {
        try {
            return Response.status(Status.OK).entity(new RetrieveAllHelpersRsp(helperControllerLocal.retrieveAllHelpers())).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());  
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }    
    
    @Path("retrieveHelper/{helperId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveHelper(@PathParam("helperId") Long helperId) {
        try {
            return Response.status(Status.OK).entity(new RetrieveHelperRsp(helperControllerLocal.retrieveHelperById(helperId))).build();
        } 
        catch(HelperNotFoundException ex) {
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
    public Response createHelper(JAXBElement<CreateHelperReq> jaxbCreateHelperReq) {
        
        if((jaxbCreateHelperReq != null) && (jaxbCreateHelperReq.getValue() != null)) {
            try {
                CreateHelperReq createHelperReq = jaxbCreateHelperReq.getValue();               
                HelperEntity helper = helperControllerLocal.createNewHelper(createHelperReq.getHelper());
                CreateHelperRsp createHelperRsp = new CreateHelperRsp(helper.getHelperId());
                
                return Response.status(Response.Status.OK).entity(createHelperRsp).build();
            } 
            catch(Exception ex) {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
            }
        }
        else {
            ErrorRsp errorRsp = new ErrorRsp("Invalid create new helper request");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }
  
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHelper(JAXBElement<UpdateHelperReq> jaxbUpdateHelperReq) {
        if((jaxbUpdateHelperReq != null) && (jaxbUpdateHelperReq.getValue() != null)) {
            try {
                UpdateHelperReq updateHelperReq = jaxbUpdateHelperReq.getValue();               
                helperControllerLocal.updateHelper(updateHelperReq.getHelper());         
                
                return Response.status(Response.Status.OK).build();
            } 
            catch(Exception ex) {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
            }
        }
        else {
            ErrorRsp errorRsp = new ErrorRsp("Invalid update helper request");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }    
    
    @Path("{helperId}")
    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("helperId") Long helperId) {
        try {
            helperControllerLocal.deleteHelper(helperId);
            
            return Response.status(Status.OK).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }    

    private HelperControllerLocal lookupHelperControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (HelperControllerLocal) c.lookup("java:global/CareTaskPro/CareTaskPro-ejb/HelperController!ejb.session.stateless.HelperControllerLocal");
        } 
        catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
