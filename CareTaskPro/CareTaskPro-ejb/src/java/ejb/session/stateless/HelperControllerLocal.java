package ejb.session.stateless;

import entity.HelperEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.HelperNotFoundException;
import util.exception.WrongCredentialException;

/**
 *
 * @author Bowen
 */
@Local
public interface HelperControllerLocal {

    public HelperEntity createNewHelper(HelperEntity helperEntity);

    public HelperEntity retrieveHelperById(Long id) throws HelperNotFoundException;

    public HelperEntity updateHelper(HelperEntity helperEntity);

    public void deleteHelper(Long id) throws HelperNotFoundException;

    public List<HelperEntity> retrieveAllHelpers();

    public HelperEntity retrieveHelperByPhone(String phone) throws HelperNotFoundException;

    public HelperEntity loginHelper(String phone, String password) throws HelperNotFoundException, WrongCredentialException;

    public HelperEntity logoutHelper(Long id) throws HelperNotFoundException;

}
