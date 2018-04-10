/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.PaymentEntity;
import entity.TaskEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.PaymentEntityNotFoundException;

/**
 *
 * @author panjiyun
 */
@Local
public interface PaymentControllerLocal {

    public PaymentEntity createPaymentEntity(TaskEntity taskEntity);

    public List<PaymentEntity> retrievePaymentByHelperId(Long helperId) throws PaymentEntityNotFoundException;

    public List<PaymentEntity> retrievePaymentByRequesterId(Long requesterId) throws PaymentEntityNotFoundException;
    
}
