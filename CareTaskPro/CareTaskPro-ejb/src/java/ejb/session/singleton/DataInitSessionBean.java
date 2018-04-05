/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.stateless.AdminControllerLocal;
import ejb.session.stateless.RequesterControllerLocal;
import entity.AdminEntity;
import entity.RequesterEntity;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.Gender;

/**
 *
 * @author panjiyun
 */
@Singleton
@LocalBean
@Startup

public class DataInitSessionBean {

    @EJB(name = "AdminControllerLocal")
    private AdminControllerLocal adminControllerLocal;
    @EJB(name = "RequesterControllerLocal")
    private RequesterControllerLocal requesterControllerLocal;    
    
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
    }

    private void initializeAdminData() {
        adminControllerLocal.createNewAdmin(new AdminEntity("staff1", "staff1", "staff1", "password1"));
        adminControllerLocal.createNewAdmin(new AdminEntity("staff2", "staff2", "staff2", "password2"));
        adminControllerLocal.createNewAdmin(new AdminEntity("staff3", "staff3", "staff3", "password3"));
    }
    
    private void initializeRequesterData() {
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName1", "lastName1", Gender.MALE, new Date(), "email1", "phone1", "password1", "address1", "creditCardNum1", "creditCardName1", "creditCardCVC1"));
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName2", "lastName2", Gender.FEMALE, new Date(), "email2", "phone2", "password2", "address2", "creditCardNum2", "creditCardName2", "creditCardCVC2"));
        requesterControllerLocal.createNewRequester(new RequesterEntity("firstName3", "lastName3", Gender.FEMALE, new Date(), "email3", "phone3", "password3", "address3", "creditCardNum3", "creditCardName3", "creditCardCVC3"));
    }

}
