/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicPage.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Amber
 */
@Named(value = "imagesViewManagedBean")
@ViewScoped
public class imagesViewManagedBean implements Serializable{

    /**
     * Creates a new instance of imagesViewManagedBean
     */
    public imagesViewManagedBean() {
    }
    
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        
        for (int i = 1; i <= 5; i++) {
            images.add("CareTaskPro" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
    
}
