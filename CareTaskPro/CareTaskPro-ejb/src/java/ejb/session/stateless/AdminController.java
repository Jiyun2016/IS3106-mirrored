/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AdminEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author panjiyun
 */
@Stateless
public class AdminController implements AdminControllerLocal {

    @PersistenceContext(unitName = "CareTaskPro-ejbPU")
    private EntityManager em;
    
    @Override
    public AdminEntity createNewAdmin(AdminEntity adminEntity) {
        em.persist(adminEntity);
        em.flush();
        em.refresh(adminEntity);
        return adminEntity;
    }
    
    
    
    @Override
    public List<AdminEntity> retrieveAllAdmin(){
        Query query = em.createQuery("SELECT s FROM AdminEntity s");
        return query.getResultList();
        
    }
  

    
}
