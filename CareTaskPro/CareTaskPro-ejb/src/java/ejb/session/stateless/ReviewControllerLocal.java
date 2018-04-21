/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.ReviewEntity;
import entity.TaskEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.ReviewNotFoundException;

/**
 *
 * @author Yap Jun Hao
 */
@Local
public interface ReviewControllerLocal {

    public ReviewEntity createNewReview(ReviewEntity reviewEntity);

    public ReviewEntity retrieveReviewById(Long reviewId) throws ReviewNotFoundException;

    public ReviewEntity updateReview(ReviewEntity reviewEntity);

    public void deleteReview(Long reviewId) throws ReviewNotFoundException;

    public List<ReviewEntity> retrieveReviewByHelperId(Long helperId) throws ReviewNotFoundException;

    public ReviewEntity retrieveReviewByTaskId(Long taskId) throws ReviewNotFoundException;
    
}
