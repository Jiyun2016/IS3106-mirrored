package ejb.session.stateless;

import entity.RequesterEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.RequesterNotFoundException;
import util.exception.WrongCredentialException;

/**
 *
 * @author Bowen
 */
@Stateless
public class RequesterController implements RequesterControllerLocal {

    @PersistenceContext(unitName = "CareTaskPro-ejbPU")
    private EntityManager em;

    @Override
    public RequesterEntity createNewRequester(RequesterEntity requesterEntity) {
        em.persist(requesterEntity);
        em.flush();
        em.refresh(requesterEntity);
        return requesterEntity;
    }
    
    @Override
    public RequesterEntity retrieveRequesterById(Long id) throws RequesterNotFoundException {
        RequesterEntity requesterEntity = em.find(RequesterEntity.class, id);
        if (requesterEntity != null) {
            return requesterEntity;
        }
        else {
            throw new RequesterNotFoundException("Requester with id " + id + " does not exist!");
        }
    }
     
    @Override
    public RequesterEntity updateRequester(RequesterEntity requesterEntity) {
        em.merge(requesterEntity);
        em.refresh(requesterEntity);
        return requesterEntity;
    }
    
    @Override
    public void deleteRequester(Long id) throws RequesterNotFoundException {
        RequesterEntity requesterEntity = retrieveRequesterById(id);
        em.remove(requesterEntity);
    }
    
    @Override
    public List<RequesterEntity> retrieveAllRequesters() {
        Query query = em.createQuery("SELECT h FROM RequesterEntity h");
        return query.getResultList();
    }

    @Override
    public RequesterEntity retrieveRequesterByPhone(Integer phone) throws RequesterNotFoundException {
        Query query = em.createQuery("SELECT r FROM RequesterEntity r WHERE r.phone = :requesterPhone")
                .setParameter("requesterPhone", phone.toString());
        if (query.getResultList().isEmpty()) {
            throw new RequesterNotFoundException("Requester with phone " + phone + " does not exist!");
        }
        else {
            return (RequesterEntity)query.getResultList().get(0);
        }
    }

    @Override
    public RequesterEntity loginRequester(Integer phone, String password) throws RequesterNotFoundException, WrongCredentialException {
        RequesterEntity requesterEntity = retrieveRequesterByPhone(phone);
        if (!requesterEntity.getPassword().equals(password)) {
            throw new WrongCredentialException("Invalid phone or password entered!");
        }
        else {
            em.merge(requesterEntity);
            em.flush();
            return requesterEntity;
        }
    }
    
//    @Override
//    public RequesterEntity logoutRequester(Long id) throws RequesterNotFoundException {
//        RequesterEntity requesterEntity = retrieveRequesterById(id);
//        if (requesterEntity.getIsLoggedIn()) {
//            requesterEntity.setIsLoggedIn(false);
//            em.merge(requesterEntity);
//            return requesterEntity;
//        }
//        else {
//            return requesterEntity;
//        }
//    }
}
