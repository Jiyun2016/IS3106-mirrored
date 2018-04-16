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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
<<<<<<< HEAD
=======
import util.enumeration.Category;
>>>>>>> 4fcb0c200f7b5c8a5c2055ee61313d046395718f
import util.stringConstant.CategoryString;
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
            
        }
        
        List<TaskEntity> allTask = taskControllerLocal.retrieveAllTask();
        if (allTask == null || allTask.isEmpty()) {
            initializeTaskData();
        }

    }

    private void initializeAdminData() {
        adminControllerLocal.createNewAdmin(new AdminEntity("staff1", "staff1", "staff1", "password1"));
        adminControllerLocal.createNewAdmin(new AdminEntity("staff2", "staff2", "staff2", "password2"));
        adminControllerLocal.createNewAdmin(new AdminEntity("staff3", "staff3", "staff3", "password3"));
    }

    private void initializeRequesterData() {
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName1", "lastName1", "MALE", "email1", "61119111", "password1", "address1", "5541", "creditCardName1", "1", "2021", "001"));
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName2", "lastName2", "FEMALE", "email2", "62229222", "password2", "address2", "5542", "creditCardName2", "2", "2022", "002"));
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName3", "lastName3", "MALE", "email3", "63339333", "password3", "address3", "5543", "creditCardName3", "3", "2023", "003"));
    }

    private void initializeHelperData() {
        helperControllerLocal.createNewHelper(new HelperEntity("firstName1", "lastName1", "MALE", "email1", "91116111", "password1", "address1", true, "certName1", "certNum1", "bankAcct1"));
        helperControllerLocal.createNewHelper(new HelperEntity("firstName2", "lastName2", "FEMALE", "email2", "92226222", "password2", "address2", false, "certName2", "certNum2", "bankAcct2"));
        helperControllerLocal.createNewHelper(new HelperEntity("firstName3", "lastName3", "MALE", "email3", "93336333", "password3", "address3", true, "certName3", "certNum3", "bankAcct3"));
    }

    private void initializeTaskData() {
<<<<<<< HEAD
        RequesterEntity r = requesterControllerLocal.createNewRequester(new RequesterEntity("firstName4", "lastName4", GenderString.MALE, "email4", "64449444", "password4", "address4", "creditCardName4", "5544", "4", "2024", "004"));
        HelperEntity h = helperControllerLocal.createNewHelper(new HelperEntity("firstName4", "lastName4", "MALE", "email4", "94446444", "password4", "address4", true, "certName4", "certNum5", "bankAcct4"));

        // Create Pending Tasks
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 7 * 60000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 8 * 60000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HEALTHCARE, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 10 * 60000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 12 * 60000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 16 * 60000)), TaskStatusString.PENDING, r));

       
        
        // Create Assigned Tasks with Pending Payments
        TaskEntity t1 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "housework", new Date((System.currentTimeMillis() + 5 * 60000)), new Date((System.currentTimeMillis() + 30 * 60000)), TaskStatusString.ASSIGNED, r, h));
        TaskEntity t2 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "housework", new Date((System.currentTimeMillis() + 40 * 60000)), new Date((System.currentTimeMillis() + 90 * 60000)), TaskStatusString.ASSIGNED, r, h));
        TaskEntity t3 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HEALTHCARE, "housework", new Date((System.currentTimeMillis() + 100 * 60000)), new Date((System.currentTimeMillis() + 150 * 60000)), TaskStatusString.ASSIGNED, r, h));
        TaskEntity t4 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "housework", new Date((System.currentTimeMillis() + 160 * 60000)), new Date((System.currentTimeMillis() + 230 * 60000)), TaskStatusString.ASSIGNED, r, h));
        TaskEntity t5 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "housework", new Date((System.currentTimeMillis() + 240 * 60000)), new Date((System.currentTimeMillis() + 300 * 60000)), TaskStatusString.ASSIGNED, r, h));

        paymentControllerLocal.createPaymentEntity(t1);
        paymentControllerLocal.createPaymentEntity(t2);
        paymentControllerLocal.createPaymentEntity(t3);
        paymentControllerLocal.createPaymentEntity(t4);
        paymentControllerLocal.createPaymentEntity(t5);



        // Create Completed Tasks with Completed Payments
        TaskEntity t6 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "housework", new Date((System.currentTimeMillis() + 5 * 60000)), new Date((System.currentTimeMillis() + 30 * 60000)), TaskStatusString.COMPLETED, r, h));
        TaskEntity t7 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "housework", new Date((System.currentTimeMillis() + 40 * 60000)), new Date((System.currentTimeMillis() + 90 * 60000)), TaskStatusString.COMPLETED, r, h));
        TaskEntity t8 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HEALTHCARE, "housework", new Date((System.currentTimeMillis() + 100 * 60000)), new Date((System.currentTimeMillis() + 150 * 60000)), TaskStatusString.COMPLETED, r, h));
        TaskEntity t9 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "housework", new Date((System.currentTimeMillis() + 160 * 60000)), new Date((System.currentTimeMillis() + 230 * 60000)), TaskStatusString.COMPLETED, r, h));
        TaskEntity t10 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "housework", new Date((System.currentTimeMillis() + 240 * 60000)), new Date((System.currentTimeMillis() + 300 * 60000)), TaskStatusString.COMPLETED, r, h));

        PaymentEntity p6 = paymentControllerLocal.createPaymentEntity(t6);
        PaymentEntity p7 = paymentControllerLocal.createPaymentEntity(t7);
        PaymentEntity p8 = paymentControllerLocal.createPaymentEntity(t8);
        PaymentEntity p9 = paymentControllerLocal.createPaymentEntity(t9);
        PaymentEntity p10 = paymentControllerLocal.createPaymentEntity(t10);

        paymentControllerLocal.setPaymentAsCompleted(p6.getPaymentId());
        paymentControllerLocal.setPaymentAsCompleted(p7.getPaymentId());
        paymentControllerLocal.setPaymentAsCompleted(p8.getPaymentId());
        paymentControllerLocal.setPaymentAsCompleted(p9.getPaymentId());
        paymentControllerLocal.setPaymentAsCompleted(p10.getPaymentId());
=======
        RequesterEntity r = requesterControllerLocal.createNewRequester(new RequesterEntity("firstName4", "lastName4", GenderString.MALE, "email4", "61119444", "password4", "address4", "creditCardName4", "5542", "2", "2022", "003"));
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 420000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 420000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HEALTHCARE, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 420000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 420000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "housework", new Date((System.currentTimeMillis() + 300000)), new Date((System.currentTimeMillis() + 420000)), TaskStatusString.PENDING, r));
>>>>>>> 4fcb0c200f7b5c8a5c2055ee61313d046395718f
    }

}
