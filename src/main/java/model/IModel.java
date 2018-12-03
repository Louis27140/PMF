package model;

import model.fridge.Frigo;
import org.jfree.data.xy.XYSeries;
import view.IView;

public interface IModel {
    Frigo getFrigo();

    void displayThermometer(float tempPlate, String plate_thermometer);

    void setView(IView view);
    void takeValue(String str, XYSeries coolingPlateSeries, XYSeries indoorSeries, XYSeries outdoorSeries, Long time);
}
