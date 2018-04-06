/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.managedbean;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Amber
 */
@Named(value = "editProfileManagedBean")
@ViewScoped
public class profileEditManagedBean  implements Serializable {

    /**
     * Creates a new instance of editProfileManagedBean
     */
    public profileEditManagedBean() {
    }
    
}
