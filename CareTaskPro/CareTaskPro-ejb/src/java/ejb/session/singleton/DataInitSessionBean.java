package ejb.session.singleton;

import ejb.session.stateless.AdminControllerLocal;
import ejb.session.stateless.HelperControllerLocal;
import ejb.session.stateless.PaymentControllerLocal;
import ejb.session.stateless.RequesterControllerLocal;
import ejb.session.stateless.TaskControllerLocal;
import entity.AdminEntity;
import entity.HelperEntity;
import entity.RequesterEntity;
import entity.TaskEntity;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.Category;
import util.stringConstant.GenderString;
import util.stringConstant.TaskStatusString;

/**
 *
 * @author panjiyun
 */
@Singleton
@LocalBean
@Startup

public class DataInitSessionBean {

    @EJB(name = "PaymentControllerLocal")
    private PaymentControllerLocal paymentControllerLocal;

    @EJB(name = "TaskControllerLocal")
    private TaskControllerLocal taskControllerLocal;

    @EJB(name = "AdminControllerLocal")
    private AdminControllerLocal adminControllerLocal;
    
    @EJB(name = "RequesterControllerLocal")
    private RequesterControllerLocal requesterControllerLocal;
    
    @EJB(name = "HelperControllerLocal")
    private HelperControllerLocal helperControllerLocal;

    @PersistenceContext(unitName = "CareTaskPro-ejbPU")
    private EntityManager em;

    @PostConstruct
    public void postConstruct() {

        List<AdminEntity> allAdmin = adminControllerLocal.retrieveAllAdmin();
        if (allAdmin == null || allAdmin.isEmpty()) {
            initializeAdminData();
        }
        
        List<RequesterEntity> allRequester = requesterControllerLocal.retrieveAllRequesters();
        if (allRequester == null || allRequester.isEmpty()) {
            initializeRequesterData();
        }
        
        List<HelperEntity> allHelper = helperControllerLocal.retrieveAllHelpers();
        if (allHelper == null || allHelper.isEmpty()) {
            initializeHelperData();
            initializeTaskData();
        }

    }

    private void initializeAdminData() {
        adminControllerLocal.createNewAdmin(new AdminEntity("staff1", "staff1", "staff1", "password1"));
        adminControllerLocal.createNewAdmin(new AdminEntity("staff2", "staff2", "staff2", "password2"));
        adminControllerLocal.createNewAdmin(new AdminEntity("staff3", "staff3", "staff3", "password3"));
    }

    private void initializeRequesterData() {
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName1", "lastName1", "MALE", "email1", "61119111", "password1", "address1", "creditCardName1", "5541", "1", "2021", "001"));
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName2", "lastName2","FEMALE", "email2", "62229222", "password2", "address2", "creditCardName2", "5542", "2", "2022", "002"));
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName3", "lastName3", "MALE", "email3", "63339333", "password3", "address3", "creditCardName3", "5543", "3", "2023", "003"));
    }

    private void initializeHelperData() {
        helperControllerLocal.createNewHelper(new HelperEntity("firstName1", "lastName1", "MALE", "email1", "91116111", "password1", "address1", true, "certName1", "certNum1", "bankAcct1"));
        helperControllerLocal.createNewHelper(new HelperEntity("firstName2", "lastName2", "FEMALE", "email2", "92226222", "password2", "address2", false, "certName2", "certNum2", "bankAcct2"));
        helperControllerLocal.createNewHelper(new HelperEntity("firstName3", "lastName3", "MALE", "email3", "93336333", "password3", "address3", true, "certName3", "certNum3", "bankAcct3"));
    }

    private void initializeTaskData() {
        RequesterEntity r = requesterControllerLocal.createNewRequester(new RequesterEntity("firstName4", "lastName4", GenderString.MALE, "email4", "61119444", "password4", "address4", "creditCardName4", "5542", "2", "2022", "003"));
        taskControllerLocal.createNewTask(new TaskEntity(Category.HOUSEWORK, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 420000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(Category.COMPANIONSHIP, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 420000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(Category.HEALTHCARE, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 420000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(Category.HOUSEWORK, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 420000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(Category.COMPANIONSHIP, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 420000)), TaskStatusString.PENDING, r));
    }

}
