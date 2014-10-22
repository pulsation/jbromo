package org.jbromo.webapp.jsf.sample.view.country;

import java.util.List;
import java.util.Locale;

import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.jbromo.webapp.jsf.mvc.view.AbstractViewModel;

/**
 * Define the country model of the view.
 * 
 * @author qjafcunuas
 * 
 */
@Named
@ViewAccessScoped
public class CountryModel extends AbstractViewModel {

    /**
     * serial version UID.
     */
    private static final long serialVersionUID = -2930800098788027207L;

    /**
     * Define countries list.
     */
    @Getter
    @Setter
    private List<Locale> countries;

    /**
     * The selected country.
     */
    @Getter
    @Setter
    private Locale selected;

}
