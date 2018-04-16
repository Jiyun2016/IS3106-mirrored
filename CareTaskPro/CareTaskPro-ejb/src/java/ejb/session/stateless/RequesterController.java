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
        } else {
            throw new RequesterNotFoundException("Requester with id " + id + " does not exist!");
        }
    }

    @Override
    public RequesterEntity updateRequester(RequesterEntity re) {
       
        em.merge(re);
        em.refresh(re);
       
        return re;
    }
    
    @Override
    public RequesterEntity updateRequesterProfile(RequesterEntity re) {
       
        RequesterEntity r = em.find(RequesterEntity.class, re.getRequesterId());

        r.setAddress(re.getAddress());
        r.setCreditCardCVC(re.getCreditCardCVC());
        r.setCreditCardExpiryMonth(re.getCreditCardExpiryMonth());
        r.setCreditCardExpiryYear(re.getCreditCardExpiryYear());
        r.setCreditCardName(re.getCreditCardName());
        r.setCreditCardNum(re.getCreditCardNum());
        r.setEmail(re.getEmail());
        r.setFirstName(re.getFirstName());
        r.setGender(re.getGender());
        r.setLastName(re.getLastName());
        r.setPassword(re.getPassword());
        r.setPhone(re.getPhone());
       
        return r;
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
    public RequesterEntity retrieveRequesterByPhone(String phone) throws RequesterNotFoundException {
        Query query = em.createQuery("SELECT r FROM RequesterEntity r WHERE r.phone = :requesterPhone")
                .setParameter("requesterPhone", phone);
        if (query.getResultList().isEmpty()) {
            throw new RequesterNotFoundException("Requester with phone " + phone + " does not exist!");
        } else {
            return (RequesterEntity) query.getResultList().get(0);
        }
    }

    @Override
    public RequesterEntity loginRequester(String phone, String password) throws RequesterNotFoundException, WrongCredentialException {
        RequesterEntity requesterEntity = retrieveRequesterByPhone(phone);
        if (!requesterEntity.getPassword().equals(password)) {
            throw new WrongCredentialException("Invalid phone or password entered!");
        } else {
            return requesterEntity;
        }
    }

}
