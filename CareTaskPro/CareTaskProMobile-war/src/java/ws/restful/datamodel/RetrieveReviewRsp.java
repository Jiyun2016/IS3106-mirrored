package ws.restful.datamodel;

import entity.ReviewEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "retrieveReviewRsp", propOrder = {
    "review"
})
public class RetrieveReviewRsp {

    private ReviewEntity review;
    
    public RetrieveReviewRsp() {
        
    }

    public RetrieveReviewRsp(ReviewEntity review) {
        this.review = review;
    }

    /**
     * @return the review
     */
    public ReviewEntity getReview() {
        return review;
    }

    /**
     * @param review the review to set
     */
    public void setReview(ReviewEntity review) {
        this.review = review;
    }
    
}
