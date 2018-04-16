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
@FacesValidator(value = "endDateValidator")

public class endDateValidator implements Validator {

    private static final Long HOUR_IN_MILLISEC = new Long(3600000);
    private static final Long MIN_HOUR = new Long(2);
    private static final Long MAX_HOUR = new Long(4);
    private static final Long MIN_DURATION = MIN_HOUR * HOUR_IN_MILLISEC;
    private static final Long MAX_DURATION = MAX_HOUR * HOUR_IN_MILLISEC;

    public endDateValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value != null) {

            UIInput startDateComponent = (UIInput) component.getAttributes().get("startDateComponent");

            if (startDateComponent.isValid()) {
                Long startDateTime = ((Date) startDateComponent.getValue()).getTime();

                if (startDateTime != null) {
                    Long endDateTime = ((Date) value).getTime();

                    if (startDateTime.compareTo(endDateTime) >= 0) {
                        startDateComponent.setValid(false);
                        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Start date cannot be after end date.", null));
                    }

//                    if ((endDateTime-startDateTime)<=MIN_DURATION) {
//                        startDateComponent.setValid(false);
//                        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duration needs to be longer than "+MIN_HOUR+" hours.", null));
//                    }
//                    if ((endDateTime-startDateTime)>=MIN_DURATION) {
//                        startDateComponent.setValid(false);
//                        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duration needs to be shorter than "+MAX_HOUR+" hours.", null));
//                    }
                }
            }

        }
    }
}
