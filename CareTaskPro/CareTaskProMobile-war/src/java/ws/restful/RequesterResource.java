package ws.restful;

import entity.RequesterEntity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ws.restful.datamodel.ErrorRsp;
import ws.restful.datamodel.GetRequesterRsp;

/**
 * REST Web Service
 *
 * @author Bowen
 */
@Path("Requester")
public class RequesterResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RequesterResource
     */
    public RequesterResource() {
    }

    /**
     * Retrieves representation of an instance of ws.restful.RequesterResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequester() {
        try {
            RequesterEntity requesterEntity = new RequesterEntity();
            GetRequesterRsp getRequesterRsp = new GetRequesterRsp();
            return Response.status(Status.OK).entity(getRequesterRsp).build();
        }
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    /**
     * PUT method for updating or creating an instance of RequesterResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putRequester(String content) {
    }
}
