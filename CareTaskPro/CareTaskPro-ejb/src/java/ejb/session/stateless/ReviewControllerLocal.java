/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.ReviewEntity;
import javax.ejb.Local;
import util.exception.ReviewNotFoundException;

/**
 *
 * @author Yap Jun Hao
 */
@Local
public interface ReviewControllerLocal {

    public ReviewEntity createNewReview(ReviewEntity reviewEntity);

    public ReviewEntity retrieveReviewById(long reviewId) throws ReviewNotFoundException;

    public ReviewEntity updateReview(ReviewEntity reviewEntity);

    public void deleteReview(Long reviewId) throws ReviewNotFoundException;
    
}
