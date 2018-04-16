/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.converter;

import entity.HelperEntity;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author panjiyun
 */
@FacesConverter(value = "helperEntityConverter", forClass = HelperEntity.class)

public class HelperEntityConverter implements Converter{

    public HelperEntityConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().length() == 0 || value.equals("null")) {
            return null;
        }

        try {
            List<HelperEntity> helperEntities = (List<HelperEntity>) context.getExternalContext().getSessionMap().get("HelperEntityConverter.helperEntities");

            for (HelperEntity helperEntity : helperEntities) {
                if (helperEntity.getHelperId().toString().equals(value)) {
                    return helperEntity;
                }
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Please select a valid value");
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof String) {
            return "";
        }

        if (value instanceof HelperEntity) {
            HelperEntity helperEntity = (HelperEntity) value;

            try {
                return helperEntity.getHelperId().toString();
            } catch (Exception ex) {
                throw new IllegalArgumentException("Invalid value:" +ex.getMessage() );
            }
        } else {
            throw new IllegalArgumentException("Invalid value");
        }
    }
}
