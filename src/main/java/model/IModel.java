package model;

import model.fridge.Frigo;
import org.jfree.data.xy.XYSeries;
import view.IView;

public interface IModel {
    Frigo getFrigo();

    /**
     *
     * @param tempPlate
     * @param plate_thermometer
     */
    void displayThermometer(float tempPlate, String plate_thermometer);

    /**
     *
     * @param view
     */
    void setView(IView view);

    /**
     *
     * @param str
     * @param coolingPlateSeries
     * @param indoorSeries
     * @param outdoorSeries
     * @param time
     */
    void takeValue(String str, XYSeries coolingPlateSeries, XYSeries indoorSeries, XYSeries outdoorSeries, Long time);
}
