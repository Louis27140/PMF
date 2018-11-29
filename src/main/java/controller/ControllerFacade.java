package controller;

import model.IModel;
import view.IView;

public class ControllerFacade implements IController {

    private final IView view;
    private final IModel model;

    public ControllerFacade(final IView view, final IModel model) {
        super();
        this.view = view;
        this.model = model;
    }
}
