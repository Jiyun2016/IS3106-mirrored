/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AdminEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author panjiyun
 */
@Local
public interface AdminControllerLocal {

    public List<AdminEntity> retrieveAllAdmin();

    public AdminEntity createNewAdmin(AdminEntity adminEntity);
    
}
