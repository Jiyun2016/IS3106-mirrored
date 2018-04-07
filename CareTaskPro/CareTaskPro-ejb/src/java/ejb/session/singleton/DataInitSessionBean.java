package ejb.session.singleton;

import ejb.session.stateless.AdminControllerLocal;
import ejb.session.stateless.HelperControllerLocal;
import ejb.session.stateless.PaymentControllerLocal;
import ejb.session.stateless.RequesterControllerLocal;
import ejb.session.stateless.TaskControllerLocal;
import entity.AdminEntity;
import entity.HelperEntity;
import entity.PaymentEntity;
import entity.RequesterEntity;
import entity.TaskEntity;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.Category;
import util.enumeration.Gender;
import util.enumeration.TaskStatus;

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


//        List<AdminEntity> allAdmin = adminControllerLocal.retrieveAllAdmin();
//        if (allAdmin == null || allAdmin.isEmpty()) {
//            initializeAdminData();
//        }
//        
//        List<RequesterEntity> allRequester = requesterControllerLocal.retrieveAllRequesters();
//        if (allRequester == null || allRequester.isEmpty()) {
//            initializeRequesterData();
//        }
//        
//        List<HelperEntity> allHelper = helperControllerLocal.retrieveAllHelpers();
//        if (allHelper == null || allHelper.isEmpty()) {
//            initializeHelperData();
//        }
    }

    private void initializeAdminData() {
        adminControllerLocal.createNewAdmin(new AdminEntity("staff1", "staff1", "staff1", "password1"));
        adminControllerLocal.createNewAdmin(new AdminEntity("staff2", "staff2", "staff2", "password2"));
        adminControllerLocal.createNewAdmin(new AdminEntity("staff3", "staff3", "staff3", "password3"));
    }

    private void initializeRequesterData() {
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName1", "lastName1", Gender.MALE, "email1", 61119111, "password1", "address1", "creditCardName1", 5541, 1, 2021, 001));
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName2", "lastName2", Gender.FEMALE, "email2", 62229222, "password2", "address2", "creditCardName2", 5542, 2, 2022, 002));
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName3", "lastName3", Gender.MALE, "email3", 63339333, "password3", "address3", "creditCardName3", 5543, 3, 2023, 003));
    }

    private void initializeHelperData() {
        helperControllerLocal.createNewHelper(new HelperEntity("firstName1", "lastName1", Gender.MALE, "email1", 91116111, "password1", "address1", false, "certName1", "certNum1", "bankAcct1"));
        helperControllerLocal.createNewHelper(new HelperEntity("firstName2", "lastName2", Gender.FEMALE, "email2", 92226222, "password2", "address2", false, "certName2", "certNum2", "bankAcct2"));
        helperControllerLocal.createNewHelper(new HelperEntity("firstName3", "lastName3", Gender.MALE, "email3", 93336333, "password3", "address3", true, "certName3", "certNum3", "bankAcct3"));
    }

    private void initializeTaskData1() {
        HelperEntity h = helperControllerLocal.createNewHelper(new HelperEntity("firstName1", "lastName1", Gender.MALE, "email1", 91116111, "password1", "address1", false, "certName1", "certNum1", "bankAcct1"));
        RequesterEntity r = requesterControllerLocal.createNewRequester(new RequesterEntity("firstName1", "lastName1", Gender.MALE, "email1", 61119111, "password1", "address1", "creditCardName1", 5541, 1, 2021, 001));
        taskControllerLocal.createNewTask(new TaskEntity(Category.HOUSEWORK, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 420000)), TaskStatus.PENDING, r, h));

    }

    private void initializeTaskData2() {
        HelperEntity h = helperControllerLocal.createNewHelper(new HelperEntity("firstName1", "lastName1", Gender.MALE, "email2", 91116141, "password1", "address1", false, "certName1", "certNum1", "bankAcct1"));
        RequesterEntity r = requesterControllerLocal.createNewRequester(new RequesterEntity("firstName1", "lastName1", Gender.MALE, "email2", 61119112, "password1", "address1", "creditCardName1", 5501, 1, 2021, 001));
        TaskEntity t = taskControllerLocal.createNewTask(new TaskEntity(Category.HOUSEWORK, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 420000)), TaskStatus.ASSIGNED, r, h));
        System.out.println("********** Task Created: task with id " + t.getTaskId() + " is " + t.getTaskStatus().toString());

        PaymentEntity p = paymentControllerLocal.createPaymentEntity(t);
        System.out.println("********** Payment Created: payment created for task with id " + t.getTaskId() + " the status of task is " + t.getTaskStatus().toString());

    }

}
