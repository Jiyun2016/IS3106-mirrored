package ws.restful;

import ejb.session.stateless.PaymentControllerLocal;
import entity.PaymentEntity;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import util.exception.PaymentEntityNotFoundException;
import ws.restful.datamodel.ErrorRsp;
import ws.restful.datamodel.RetrieveAllPaymentsRsp;

/**
 * REST Web Service
 *
 * @author Bowen
 */
@Path("Payment")
public class PaymentResource {

    @Context
    private UriInfo context;

    private final PaymentControllerLocal paymentControllerLocal = lookupPaymentControllerLocal();
    
    /**
     * Creates a new instance of PaymentResource
     */
    public PaymentResource() {
    }

    @Path("retrieveAllPayments")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllPayments() {
        try {
            List<PaymentEntity> paymentEntities = paymentControllerLocal.retrieveAllPayment();
            
            //to prevent cyclic relationship checks when marshalling/unmarshalling
            for(PaymentEntity payment: paymentEntities) {            
                payment.setTaskEntity(null);
            }
            
            return Response.status(Status.OK).entity(new RetrieveAllPaymentsRsp(paymentEntities)).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    } 
    
    @Path("retrieveAllPaymentsByHelperId/{helperId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllPaymentsByHelperId(@PathParam("helperId") Long helperId) {
        try {
            List<PaymentEntity> paymentEntities = paymentControllerLocal.retrievePaymentByHelperId(helperId);
            
            //to prevent cyclic relationship checks when marshalling/unmarshalling
            for(PaymentEntity payment: paymentEntities) {            
                payment.setTaskEntity(null);
            }
            
            return Response.status(Status.OK).entity(new RetrieveAllPaymentsRsp(paymentEntities)).build();
        } 
        catch(PaymentEntityNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(errorRsp).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveAllPaymentsByRequesterId/{requesterId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllPaymentsByRequesterId(@PathParam("requesterId") Long requesterId) {
        try {
            List<PaymentEntity> paymentEntities = paymentControllerLocal.retrievePaymentByHelperId(requesterId);
            
            //to prevent cyclic relationship checks when marshalling/unmarshalling
            for(PaymentEntity payment: paymentEntities) {            
                payment.setTaskEntity(null);
            }           
            
            return Response.status(Status.OK).entity(new RetrieveAllPaymentsRsp(paymentEntities)).build();
        } 
        catch(PaymentEntityNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(errorRsp).build();
        } 
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    private PaymentControllerLocal lookupPaymentControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PaymentControllerLocal) c.lookup("java:global/CareTaskPro/CareTaskPro-ejb/PaymentController!ejb.session.stateless.PaymentControllerLocal");
        } 
        catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
