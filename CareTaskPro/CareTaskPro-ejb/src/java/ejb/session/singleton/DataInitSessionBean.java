package ejb.session.singleton;

import ejb.session.stateless.AdminControllerLocal;
import ejb.session.stateless.HelperControllerLocal;
import ejb.session.stateless.PaymentControllerLocal;
import ejb.session.stateless.RequesterControllerLocal;
import ejb.session.stateless.ReviewControllerLocal;
import ejb.session.stateless.TaskControllerLocal;
import entity.AdminEntity;
import entity.HelperEntity;
import entity.PaymentEntity;
import entity.RequesterEntity;
import entity.ReviewEntity;
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

    @EJB(name = "ReviewControllerLocal")
    private ReviewControllerLocal reviewControllerLocal;

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

        List<TaskEntity> allTask = taskControllerLocal.retrieveAllTask();
        if (allTask == null || allTask.isEmpty()) {
            initializeTaskData();

            initializeRequesterData();

            initializeHelperData();
        }

//         List<RequesterEntity> allRequester = requesterControllerLocal.retrieveAllRequesters();
//        if (allRequester == null || allRequester.isEmpty()) {
//            initializeRequesterData();
//        }
//
//        List<HelperEntity> allHelper = helperControllerLocal.retrieveAllHelpers();
//        if (allHelper == null || allHelper.isEmpty()) {
//            initializeHelperData();
//            
//        }
    }

    private void initializeAdminData() {
        adminControllerLocal.createNewAdmin(new AdminEntity("staff1", "staff1", "staff1", "password1"));
        adminControllerLocal.createNewAdmin(new AdminEntity("staff2", "staff2", "staff2", "password2"));
        adminControllerLocal.createNewAdmin(new AdminEntity("staff3", "staff3", "staff3", "password3"));
    }
    
    private void initializeTaskData() {

        RequesterEntity r = requesterControllerLocal.createNewRequester(new RequesterEntity("firstName1", "lastName1", "MALE", "remail1@mail.com", "61119111", "password1", "address1", "5541", "creditCardName1", "1", "2021", "001"));
        HelperEntity h = helperControllerLocal.createNewHelper(new HelperEntity("firstName1", "lastName1", "MALE", "hemail1@mail.com", "91116111", "password1", "address1", true, "certName1", "certNum1", "bankAcct1"));

        // Create Pending Tasks
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "some housework", new Date((System.currentTimeMillis() + 60000 * 2000)), new Date((System.currentTimeMillis() + 2700 * 60000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "some companionship", new Date((System.currentTimeMillis() + 60000 * 2800)), new Date((System.currentTimeMillis() + 60000 * 3000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HEALTHCARE, "some healthcare", new Date((System.currentTimeMillis() + 60000 * 5)), new Date((System.currentTimeMillis() + 8 * 60000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "some housework", new Date((System.currentTimeMillis() + 60000 * 9)), new Date((System.currentTimeMillis() + 16 * 60000)), TaskStatusString.PENDING, r));
        taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "some companionship", new Date((System.currentTimeMillis() + 60000 * 12)), new Date((System.currentTimeMillis() + 30 * 60000)), TaskStatusString.PENDING, r));

        // Create Assigned Tasks with Pending Payments
        TaskEntity t1 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "some housework", new Date((System.currentTimeMillis() + 30 * 60000)), new Date((System.currentTimeMillis() + 60 * 60000)), TaskStatusString.ASSIGNED, r, h));
        TaskEntity t2 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "some companionship", new Date((System.currentTimeMillis() + 70 * 60000)), new Date((System.currentTimeMillis() + 90 * 60000)), TaskStatusString.ASSIGNED, r, h));
        TaskEntity t3 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HEALTHCARE, "some healthcare", new Date((System.currentTimeMillis() + 100 * 60000)), new Date((System.currentTimeMillis() + 150 * 60000)), TaskStatusString.ASSIGNED, r, h));
        TaskEntity t4 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "some housework", new Date((System.currentTimeMillis() + 160 * 60000)), new Date((System.currentTimeMillis() + 230 * 60000)), TaskStatusString.ASSIGNED, r, h));
        TaskEntity t5 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "some companionship", new Date((System.currentTimeMillis() + 240 * 60000)), new Date((System.currentTimeMillis() + 300 * 60000)), TaskStatusString.ASSIGNED, r, h));


        paymentControllerLocal.createPaymentEntity(t1);
        paymentControllerLocal.createPaymentEntity(t2);
        paymentControllerLocal.createPaymentEntity(t3);
        paymentControllerLocal.createPaymentEntity(t4);
        paymentControllerLocal.createPaymentEntity(t5);


        // Create Completed Tasks with Completed Payments and Completed Reviews
        TaskEntity t6 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "some housework", new Date((System.currentTimeMillis() + 20 * 60000)), new Date((System.currentTimeMillis() + 50 * 60000)), TaskStatusString.COMPLETED, r, h));
        TaskEntity t7 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "some companionship", new Date((System.currentTimeMillis() + 60 * 60000)), new Date((System.currentTimeMillis() + 90 * 60000)), TaskStatusString.COMPLETED, r, h));
        TaskEntity t8 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HEALTHCARE, "some healthcare", new Date((System.currentTimeMillis() + 100 * 60000)), new Date((System.currentTimeMillis() + 150 * 60000)), TaskStatusString.COMPLETED, r, h));
        TaskEntity t9 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.HOUSEWORK, "some housework", new Date((System.currentTimeMillis() + 160 * 60000)), new Date((System.currentTimeMillis() + 230 * 60000)), TaskStatusString.COMPLETED, r, h));
        TaskEntity t10 = taskControllerLocal.createNewTask(new TaskEntity(CategoryString.COMPANIONSHIP, "some companionship", new Date((System.currentTimeMillis() + 240 * 60000)), new Date((System.currentTimeMillis() + 300 * 60000)), TaskStatusString.COMPLETED, r, h));


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
        
        reviewControllerLocal.createNewReview(new ReviewEntity("Good!", new Integer(4), t6));
        reviewControllerLocal.createNewReview(new ReviewEntity("Very Good!", new Integer(5), t7));
        reviewControllerLocal.createNewReview(new ReviewEntity("Expect improvement.", new Integer(2), t8));
        reviewControllerLocal.createNewReview(new ReviewEntity("Well Done!", new Integer(5), t9));
        reviewControllerLocal.createNewReview(new ReviewEntity("Super Good!", new Integer(5), t10));
        

    }

    private void initializeRequesterData() {
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName2", "lastName2", "FEMALE", "remail2@mail.com", "62229222", "password2", "address2", "5542", "creditCardName2", "2", "2022", "002"));
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName3", "lastName3", "MALE", "remail3@mail.com", "63339333", "password3", "address3", "5543", "creditCardName3", "3", "2023", "003"));
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName4", "lastName4", "MALE", "remail4@mail.com", "64449444", "password4", "address4", "5544", "creditCardName4", "4", "2024", "004"));

    }

    private void initializeHelperData() {
        helperControllerLocal.createNewHelper(new HelperEntity("firstName2", "lastName2", "FEMALE", "hemail2@mail.com", "92226222", "password2", "address2", false, "certName2", "certNum2", "bankAcct2"));
        helperControllerLocal.createNewHelper(new HelperEntity("firstName3", "lastName3", "MALE", "hemail3@mail.com", "93336333", "password3", "address3", true, "certName3", "certNum3", "bankAcct3"));
        helperControllerLocal.createNewHelper(new HelperEntity("firstName4", "lastName4", "MALE", "hemail4@mail.com", "94446444", "password4", "address4", true, "certName4", "certNum5", "bankAcct4"));

    }

    

}
