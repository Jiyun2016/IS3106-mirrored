package ws.restful.datamodel;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "createReviewRsp", propOrder = {
    "reviewId"
})
public class CreateReviewRsp {
    
    private Long reviewId;

    public CreateReviewRsp() {
    }

    public CreateReviewRsp(Long reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * @return the reviewId
     */
    public long getReviewId() {
        return reviewId;
    }

    /**
     * @param reviewId the reviewId to set
     */
    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }
    
}
