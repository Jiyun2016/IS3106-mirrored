/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.managedbean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Amber
 */
@Named(value = "myAccountManagedBean")
@RequestScoped
public class myAccountManagedBean {

    /**
     * Creates a new instance of myAccountManagedBean
     */
    public myAccountManagedBean() {
    }
    
}
