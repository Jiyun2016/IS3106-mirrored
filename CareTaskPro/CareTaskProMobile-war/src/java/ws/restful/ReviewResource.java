package ws.restful;

import ejb.session.stateless.ReviewControllerLocal;
import entity.ReviewEntity;
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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;
import ws.restful.datamodel.CreateReviewReq;
import ws.restful.datamodel.CreateReviewRsp;
import ws.restful.datamodel.ErrorRsp;
import ws.restful.datamodel.RetrieveAllReviewsRsp;
import ws.restful.datamodel.RetrieveReviewRsp;

/**
 * REST Web Service
 *
 * @author Bowen
 */
@Path("Review")
public class ReviewResource {

    @Context
    private UriInfo context;

    private final ReviewControllerLocal reviewControllerLocal = lookupReviewControllerLocal();    
    
    public ReviewResource() {
    }

    @Path("retrieveReviewsByHelperId/{helperId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveReviewsByHelperId(@PathParam("helperId") Long helperId) {
        try {
            List<ReviewEntity> reviewEntities = reviewControllerLocal.retrieveReviewByHelperId(helperId);
            
            //to prevent cyclic relationship checks when marshalling/unmarshalling
            for(ReviewEntity review: reviewEntities) {
                review.setTaskEntity(null);
            }
            
            return Response.status(Status.OK).entity(new RetrieveAllReviewsRsp(reviewEntities)).build();
        }
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());  
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }    

    @Path("retrieveReviewByTaskId/{taskId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveReviewByTaskId(@PathParam("taskId") Long taskId) {
        try {
            ReviewEntity review = reviewControllerLocal.retrieveReviewByTaskId(taskId);
            
            //to prevent cyclic relationship checks when marshalling/unmarshalling
            review.getTaskEntity().setReviewEntity(null);
            
            return Response.status(Status.OK).entity(new RetrieveReviewRsp(review)).build();
        }
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());  
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }       
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReview(JAXBElement<CreateReviewReq> jaxbCreateReviewReq) {
        
        if((jaxbCreateReviewReq != null) && (jaxbCreateReviewReq.getValue() != null)) {
            try {
                CreateReviewReq createReviewReq = jaxbCreateReviewReq.getValue();               
                ReviewEntity review = reviewControllerLocal.createNewReview(createReviewReq.getReview());
                CreateReviewRsp createReviewRsp = new CreateReviewRsp(review.getReviewId());
                
                return Response.status(Response.Status.OK).entity(createReviewRsp).build();
            } 
            catch(Exception ex) {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
            }
        }
        else {
            ErrorRsp errorRsp = new ErrorRsp("Invalid create new review request");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }
    
    private ReviewControllerLocal lookupReviewControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ReviewControllerLocal) c.lookup("java:global/CareTaskPro/CareTaskPro-ejb/ReviewController!ejb.session.stateless.ReviewControllerLocal");
        } 
        catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }    
}
