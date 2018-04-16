/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.validator;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author panjiyun
 */
@FacesValidator(value = "startDateValidator")

public class StartDateValidator implements Validator {
    
    private static final Long MINI_IN_MILLISEC = new Long(600000);
    private static final Long MIN_BUFFER = new Long(2);

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null) {

            Long startDateTime = ((Date) value).getTime();

            if ((startDateTime-System.currentTimeMillis()) <= MIN_BUFFER) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Start datetime needs to be at least "+MIN_BUFFER+" minites later.", null));
            }

        }

    }

}
