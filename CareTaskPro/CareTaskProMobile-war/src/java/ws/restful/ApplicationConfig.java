package ws.restful;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Bowen
 */
@javax.ws.rs.ApplicationPath("Resources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.restful.AdminResource.class);
        resources.add(ws.restful.HelperResource.class);
        resources.add(ws.restful.PaymentResource.class);
        resources.add(ws.restful.RequesterResource.class);
        resources.add(ws.restful.ReviewResource.class);
        resources.add(ws.restful.TaskResource.class);
    }
    
}
