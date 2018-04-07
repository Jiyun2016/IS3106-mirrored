package ejb.session.stateless;

import entity.ReviewEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.ReviewNotFoundException;

/**
 *
 * @author Yap Jun Hao
 */
@Stateless
public class ReviewController implements ReviewControllerLocal {

    @PersistenceContext(unitName = "CareTaskPro-ejbPU")
    private EntityManager em;

    @Override
    public ReviewEntity createNewReview(ReviewEntity reviewEntity) {
        em.persist(reviewEntity);
        em.flush();
        em.refresh(reviewEntity);
        return reviewEntity;
    }

    @Override
    public ReviewEntity retrieveReviewById(long reviewId) throws ReviewNotFoundException {
        ReviewEntity reviewEntity = em.find(ReviewEntity.class, reviewId);
        if (reviewEntity != null) {
            return reviewEntity;
        } else {
            throw new ReviewNotFoundException("Review with id " + reviewId + " does not exist!");
        }
    }
    
    @Override
    public ReviewEntity updateReview(ReviewEntity reviewEntity) {
        em.merge(reviewEntity);
        em.refresh(reviewEntity);
        return reviewEntity;
    }

    @Override
    public void deleteReview(Long reviewId) throws ReviewNotFoundException {
        ReviewEntity reviewEntity = retrieveReviewById(reviewId);
        em.remove(reviewEntity);
    }

    
}
