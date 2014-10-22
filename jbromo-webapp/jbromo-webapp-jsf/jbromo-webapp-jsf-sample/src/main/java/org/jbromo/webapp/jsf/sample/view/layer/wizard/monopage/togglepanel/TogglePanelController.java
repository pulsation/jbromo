package org.jbromo.webapp.jsf.sample.view.layer.wizard.monopage.togglepanel;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;

import org.jbromo.common.BooleanUtil;
import org.jbromo.webapp.jsf.component.wizard.AbstractWizardController;
import org.jbromo.webapp.jsf.component.wizard.AbstractWizardItemPreviousEvent;
import org.jbromo.webapp.jsf.sample.view.View;
import org.jbromo.webapp.jsf.sample.view.layer.wizard.monopage.togglepanel.first.FirstTogglePanelItemNextEvent;
import org.jbromo.webapp.jsf.sample.view.layer.wizard.monopage.togglepanel.first.FirstTogglePanelItemRenderData;
import org.jbromo.webapp.jsf.sample.view.layer.wizard.monopage.togglepanel.first.FirstTogglePanelItemRenderEvent;
import org.jbromo.webapp.jsf.sample.view.layer.wizard.monopage.togglepanel.second.SecondTogglePanelItemNextEvent;
import org.jbromo.webapp.jsf.sample.view.layer.wizard.monopage.togglepanel.second.SecondTogglePanelItemPreviousEvent;
import org.jbromo.webapp.jsf.sample.view.layer.wizard.monopage.togglepanel.second.SecondTogglePanelItemRenderEvent;
import org.jbromo.webapp.jsf.sample.view.layer.wizard.monopage.togglepanel.third.ThirdTogglePanelItemNextEvent;
import org.jbromo.webapp.jsf.sample.view.layer.wizard.monopage.togglepanel.third.ThirdTogglePanelItemRenderData;
import org.jbromo.webapp.jsf.sample.view.layer.wizard.monopage.togglepanel.third.ThirdTogglePanelItemRenderEvent;
import org.jbromo.webapp.jsf.view.IView;
import org.jbromo.webapp.jsf.view.RenderView;

/**
 * Define the dynamic tab panel controller of the view.
 * 
 * @author qjafcunuas
 * 
 */
@Named
@RequestScoped
public class TogglePanelController extends
        AbstractWizardController<TogglePanelModel> {

    /**
     * serial version UID.
     */
    private static final long serialVersionUID = 3549835982536608004L;

    /**
     * The view's model.
     */
    @Inject
    @Getter(AccessLevel.PROTECTED)
    private TogglePanelModel model;

    @Override
    protected IView getWizard() {
        return View.TOGGLE_PANEL_WIZARD;
    }

    @Override
    protected void loadData() {
        // Load first panel item.
        fireEvent(new FirstTogglePanelItemRenderEvent(
                new FirstTogglePanelItemRenderData(false)));
        getModel().setPageLoaded(true);
    }

    /**
     * Call when toggle is rendered.
     * 
     * @param event
     *            the event.
     */
    public void observeRenderToggle(@Observes final TogglePanelRenderEvent event) {
        super.onLoadPage();
    }

    /**
     * Call when user click on next button of first panel item.
     * 
     * @param nextEvent
     *            the event.
     */
    public void observeFirstItemNext(
            @Observes @RenderView(controller = TogglePanelController.class) final FirstTogglePanelItemNextEvent nextEvent) {
        getModel().setFirstData(nextEvent.getData());
        if (BooleanUtil.isTrue(nextEvent.getData().getNextItemThird())) {
            fireEvent(new ThirdTogglePanelItemRenderEvent(nextEvent,
                    new ThirdTogglePanelItemRenderData(true)));
        } else {
            fireEvent(new SecondTogglePanelItemRenderEvent(nextEvent));
        }
    }

    /**
     * Call when user click on next button of first panel item.
     * 
     * @param nextEvent
     *            the event.
     */
    public void observeSecondItemNext(
            @Observes @RenderView(controller = TogglePanelController.class) final SecondTogglePanelItemNextEvent nextEvent) {
        fireEvent(new ThirdTogglePanelItemRenderEvent(nextEvent,
                new ThirdTogglePanelItemRenderData(false)));
    }

    /**
     * Call when user click on next button of first panel item.
     * 
     * @param event
     *            the event.
     */
    public void observeSecondItemPrevious(
            @Observes @RenderView(controller = TogglePanelController.class) final SecondTogglePanelItemPreviousEvent event) {
        fireEvent(new FirstTogglePanelItemRenderEvent(
                new FirstTogglePanelItemRenderData(getModel().getFirstData()
                        .getNextItemThird())));
    }

    @Override
    protected void observeItemPreviousEvent(
            @Observes @RenderView(controller = TogglePanelController.class) final AbstractWizardItemPreviousEvent<? extends Serializable> event) {
        observeItemPreviousEventImpl(event);
    }

    /**
     * Call when user click on next button of first panel item.
     * 
     * @param nextEvent
     *            the event.
     */
    public void observeThirdItemNext(
            @Observes @RenderView(controller = TogglePanelController.class) final ThirdTogglePanelItemNextEvent nextEvent) {
        fireEventRenderView(nextEvent, View.WIZARD);
    }

}
