/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AdminEntity;
import entity.RequesterEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AdminEntityNotFoundException;
import util.exception.AdminPasswordChangeException;
import util.exception.InvalidLoginCredentialException;
import util.exception.RequesterNotFoundException;

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
    public List<AdminEntity> retrieveAllAdmin() {
        Query query = em.createQuery("SELECT s FROM AdminEntity s");
        return query.getResultList();

    }

    @Override
    public AdminEntity retrieveAdminByUsername(String username) throws AdminEntityNotFoundException {
        Query query = em.createQuery("SELECT s FROM AdminEntity s WHERE s.username = :inUsername");
        query.setParameter("inUsername", username);

        try {
            return (AdminEntity) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new AdminEntityNotFoundException("Admin Username " + username + " does not exist!");
        }
    }

    @Override
    public AdminEntity retrieveAdminById(Long id) throws AdminEntityNotFoundException {
        AdminEntity adminEntity = em.find(AdminEntity.class, id);
        if (adminEntity != null) {
            return adminEntity;
        } else {
            throw new AdminEntityNotFoundException("Admin with id " + id + " does not exist!");
        }
    }

    @Override
    public AdminEntity adminLogin(String username, String inPassword) throws InvalidLoginCredentialException {
        try {
            AdminEntity adminEntity = retrieveAdminByUsername(username);
            String password = adminEntity.getPassword();

            if (inPassword.equals(password)) {
                return adminEntity;
            } else {
                throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
            }
        } catch (AdminEntityNotFoundException ex) {
            throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
        }
    }

    @Override
    public void updateEmployee(AdminEntity adminEntity) {
        em.merge(adminEntity);
    }

    @Override
    public void deleteEmployee(String username) throws AdminEntityNotFoundException {
        AdminEntity adminToRemove = retrieveAdminByUsername(username);
        em.remove(adminToRemove);
    }

    @Override
    public void changePassword(AdminEntity adminEntity, String currentPassword, String newPassword) throws AdminEntityNotFoundException, AdminPasswordChangeException {

        if (adminEntity.getPassword().equals(currentPassword)) {
            adminEntity.setPassword(newPassword);
            em.merge(adminEntity);
        } else {
            throw new AdminPasswordChangeException("Current Password is invalid");
        }
    }

}
