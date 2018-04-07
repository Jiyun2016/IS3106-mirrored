package ejb.session.stateless;

import entity.HelperEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.HelperNotFoundException;
import util.exception.WrongCredentialException;

/**
 *
 * @author Bowen
 */
@Stateless
public class HelperController implements HelperControllerLocal {

    @PersistenceContext(unitName = "CareTaskPro-ejbPU")
    private EntityManager em;

    @Override
    public HelperEntity createNewHelper(HelperEntity helperEntity) {
        em.persist(helperEntity);
        em.flush();
        em.refresh(helperEntity);
        return helperEntity;
    }
    
    @Override
    public HelperEntity retrieveHelperById(Long id) throws HelperNotFoundException {
        HelperEntity helperEntity = em.find(HelperEntity.class, id);
        if (helperEntity != null) {
            return helperEntity;
        }
        else {
            throw new HelperNotFoundException("Helper with id " + id + " does not exist!");
        }
    }
    
    @Override
    public HelperEntity updateHelper(HelperEntity helperEntity) {
        em.merge(helperEntity);
        em.refresh(helperEntity);
        return helperEntity;
    }
    
    @Override
    public void deleteHelper(Long id) throws HelperNotFoundException {
        HelperEntity helperEntity = retrieveHelperById(id);
        em.remove(helperEntity);
    }
    
    @Override
    public List<HelperEntity> retrieveAllHelpers() {
        Query query = em.createQuery("SELECT h FROM HelperEntity h");
        return query.getResultList();
    }
    
    @Override
    public HelperEntity retrieveHelperByPhone(Integer phone) throws HelperNotFoundException {
        Query query = em.createQuery("SELECT h FROM HelperEntity h WHERE h.phone = :helperPhone")
                .setParameter("helperPhone", phone);
        if (query.getResultList().isEmpty()) {
            throw new HelperNotFoundException("Helper with phone " + phone + " does not exist!");
        }
        else {
            return (HelperEntity)query.getResultList().get(0);
        }
    }
    
    @Override
    public HelperEntity loginHelper(Integer phone, String password) throws HelperNotFoundException, WrongCredentialException {
        HelperEntity helperEntity = retrieveHelperByPhone(phone);
        if (!helperEntity.getPassword().equals(password)) {
            throw new WrongCredentialException("Invalid phone or password entered!");
        }
        else {
            em.merge(helperEntity);
            em.flush();
            return helperEntity;
        }
    }
    
//    @Override
//    public HelperEntity logoutHelper(Long id) throws HelperNotFoundException {
//        HelperEntity helperEntity = retrieveHelperById(id);
//        if (helperEntity.getIsLoggedIn()) {
//            helperEntity.setIsLoggedIn(false);
//            em.merge(helperEntity);
//            return helperEntity;
//        }
//        else {
//            return helperEntity;
//        }
//    }
}
