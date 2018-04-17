/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.managedbean;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Amber
 */
@Named(value = "helperViewReviewsManagedBean")
@RequestScoped
public class helperViewReviewsManagedBean implements Serializable{

    /**
     * Creates a new instance of helperViewReviewsManagedBean
     */
    public helperViewReviewsManagedBean() {
    }
    
}
