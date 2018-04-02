package ws.restful;

import entity.AdminEntity;
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
import ws.restful.datamodel.GetAdminRsp;

/**
 * REST Web Service
 *
 * @author Bowen
 */
@Path("Admin")
public class AdminResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AdminResource
     */
    public AdminResource() {
    }

    /**
     * Retrieves representation of an instance of ws.restful.AdminResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmin() {
        try {
            AdminEntity adminEntity = new AdminEntity("test", "test", "test", "test");
            GetAdminRsp getAdminRsp = new GetAdminRsp(adminEntity.getFirstName(), adminEntity.getLastName(), adminEntity.getUsername(), adminEntity.getPassword());
            return Response.status(Status.OK).entity(getAdminRsp).build();
        }
        catch(Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    /**
     * PUT method for updating or creating an instance of AdminResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putAdmin(String content) {
    }
}
