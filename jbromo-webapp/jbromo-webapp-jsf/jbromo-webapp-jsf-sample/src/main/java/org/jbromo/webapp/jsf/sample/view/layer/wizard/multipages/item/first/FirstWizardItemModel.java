package org.jbromo.webapp.jsf.sample.view.layer.wizard.multipages.item.first;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import org.jbromo.webapp.jsf.component.wizard.AbstractWizardItemModel;

/**
 * Define the dynamic tab panel model of the view.
 * 
 * @author qjafcunuas
 * 
 */
@Named
@ConversationScoped
public class FirstWizardItemModel extends AbstractWizardItemModel {

    /**
     * serial version UID.
     */
    private static final long serialVersionUID = -2930800098788027207L;

    /**
     * if true, next item panel must be the third, else the second.
     */
    @Getter
    @Setter
    private boolean nextItemThird;

}
