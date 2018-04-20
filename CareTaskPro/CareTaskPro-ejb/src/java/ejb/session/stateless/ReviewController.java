package ejb.session.stateless;

import entity.ReviewEntity;
import java.util.List;
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
    public ReviewEntity retrieveReviewById(Long reviewId) throws ReviewNotFoundException {
        ReviewEntity reviewEntity = em.find(ReviewEntity.class, reviewId);
        if (reviewEntity != null) {
            return reviewEntity;
        } else {
            throw new ReviewNotFoundException("Review with id " + reviewId + " does not exist!");
        }
    }
    
    @Override
    public List<ReviewEntity> retrieveReviewByHelperId(Long helperId) throws ReviewNotFoundException {
        
        List<ReviewEntity> reviews;
        reviews = em.createQuery("SELECT r FROM ReviewEntity r WHERE r.taskEntity.helperEntity.helperId = :inId" )
                .setParameter("inId", helperId)
                .getResultList();
        if (reviews != null && !reviews.isEmpty()) {
            for (ReviewEntity r : reviews) {
                r.getReviewId();
            }
            return reviews;
        } else {
            throw new ReviewNotFoundException("No review found for helper with id "+helperId);
        }
       
    }
    
    @Override
    public ReviewEntity retrieveReviewByTaskId(Long taskId) throws ReviewNotFoundException {
        
        ReviewEntity review;
        review = (ReviewEntity)em.createQuery("SELECT r FROM ReviewEntity r WHERE r.taskEntity.taskId = :inId" )
                .setParameter("inId", taskId)
                .getResultList().get(0);
        if (review != null) {
            review.getReviewId();
            return review;
        }
        else {
            throw new ReviewNotFoundException("No reivew found for task with id "+ taskId);
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
