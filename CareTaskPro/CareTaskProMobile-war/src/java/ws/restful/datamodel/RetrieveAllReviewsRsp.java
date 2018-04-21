package ws.restful.datamodel;

import entity.ReviewEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bowen
 */
@XmlRootElement
@XmlType(name = "retrieveAllReviewsRsp", propOrder = {
    "reviews"
})
public class RetrieveAllReviewsRsp {

    private List<ReviewEntity> reviews;
    
    public RetrieveAllReviewsRsp() {
    }

    public RetrieveAllReviewsRsp(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    /**
     * @return the reviews
     */
    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    /**
     * @param reviews the reviews to set
     */
    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }
    
}
