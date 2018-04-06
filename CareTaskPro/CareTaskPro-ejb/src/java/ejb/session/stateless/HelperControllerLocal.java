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

    public HelperEntity retrieveHelperByPhone(Integer phone) throws HelperNotFoundException;

    public HelperEntity loginHelper(Integer phone, String password) throws HelperNotFoundException, WrongCredentialException;

}
