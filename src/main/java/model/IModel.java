package model;

import model.fridge.Frigo;
import view.IView;

public interface IModel {
    Frigo getFrigo();

    void displayThermometer(float tempPlate, String plate_thermometer);

    void setView(IView view);
}
