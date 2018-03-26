package ejb.session.stateless;

import entity.RequesterEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.RequesterNotFoundException;
import util.exception.WrongCredentialException;

/**
 *
 * @author Bowen
 */
@Local
public interface RequesterControllerLocal {

    public RequesterEntity createNewRequester(RequesterEntity requesterEntity);

    public RequesterEntity retrieveRequesterById(Long id) throws RequesterNotFoundException;

    public RequesterEntity updateRequester(RequesterEntity requesterEntity);

    public void deleteRequester(Long id) throws RequesterNotFoundException;

    public List<RequesterEntity> retrieveAllRequesters();

    public RequesterEntity retrieveRequesterByPhone(String phone) throws RequesterNotFoundException;

    public RequesterEntity loginRequester(String phone, String password) throws RequesterNotFoundException, WrongCredentialException;

    public RequesterEntity logoutRequester(Long id) throws RequesterNotFoundException;
    
}
