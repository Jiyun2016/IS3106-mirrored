/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AdminEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.AdminEntityNotFoundException;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author panjiyun
 */
@Local
public interface AdminControllerLocal {

    public List<AdminEntity> retrieveAllAdmin();

    public AdminEntity createNewAdmin(AdminEntity adminEntity);

    public AdminEntity adminLogin(String username, String inPassword) throws InvalidLoginCredentialException;

    public AdminEntity retrieveAdminByUsername(String username) throws AdminEntityNotFoundException;
    
}
