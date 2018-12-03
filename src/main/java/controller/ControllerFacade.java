package controller;

import model.IModel;
import view.IView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerFacade implements IController, ActionListener {

    private final IView view;
    private final IModel model;

    public ControllerFacade(final IView view, final IModel model) {
        super();
        this.view = view;
        this.view.setController(this);
        this.model = model;
    }

    @Override
    public void start() {
        this.view.getTextField().addActionListener(this);
        this.view.getList().addActionListener(this);
    }


    public void setTarget(float target) {
        this.model.getFrigo().setTarget(target);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (view.getList().getSelectedIndex()){
            case 0:
                view.getThermo().setChart(view.thermometer(model.getFrigo().getTempInt(), "Indoor Thermometer"));
                break;
            case 1:
                view.getThermo().setChart(view.thermometer(model.getFrigo().getTempExt(), "Outdoor Thermometer"));
                break;
            case 2:
                view.getThermo().setChart(view.thermometer(model.getFrigo().getTempPlate(), "Plate Thermometer"));
                break;
            default:
                break;
        }
    }
}
