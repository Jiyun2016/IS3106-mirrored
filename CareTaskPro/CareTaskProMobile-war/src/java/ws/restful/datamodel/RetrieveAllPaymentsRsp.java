package ws.restful.datamodel;

import entity.PaymentEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "retrieveAllPaymentsRsp", propOrder = {
    "payments"
})
public class RetrieveAllPaymentsRsp {
    
    private List<PaymentEntity> payments;

    public RetrieveAllPaymentsRsp() {
    }

    public RetrieveAllPaymentsRsp(List<PaymentEntity> payments) {
        this.payments = payments;
    }

    /**
     * @return the payments
     */
    public List<PaymentEntity> getPayments() {
        return payments;
    }

    /**
     * @param payments the payments to set
     */
    public void setPayments(List<PaymentEntity> payments) {
        this.payments = payments;
    }
    
}