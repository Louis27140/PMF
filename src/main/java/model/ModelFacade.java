package model;

import model.fridge.Frigo;
import org.jfree.data.xy.XYSeries;
import view.IView;

public class ModelFacade implements IModel {

    private Frigo frigo;

    private String split[];

    private IView view;

    public ModelFacade() {
        this.setFrigo(new Frigo(20.0f, 0.5f, 5.0f, 17.0f, 16.0f));
    }

    /**
     *
     * @param str
     * @param coolingPlateSeries
     * @param indoorSeries
     * @param outdoorSeries
     * @param time
     */

    public void takeValue(String str, XYSeries coolingPlateSeries, XYSeries indoorSeries, XYSeries outdoorSeries, Long time) {
        if (str.startsWith("s") && str.endsWith("e")) {
            split = str.substring(1,str.length() - 1).split(";");

            if(split.length == 4){
                this.getFrigo().setTempPlate(Float.parseFloat(split[0]));
                this.getFrigo().setTempInt(Float.parseFloat(split[1]));
                this.getFrigo().setTempExt(Float.parseFloat(split[2]));
                this.getFrigo().setHygrometry(Float.parseFloat(split[3]));

                coolingPlateSeries.add(System.currentTimeMillis() - time, Float.parseFloat(split[0]));
                indoorSeries.add(System.currentTimeMillis() - time, Float.parseFloat(split[1]));
                outdoorSeries.add(System.currentTimeMillis() - time, Float.parseFloat(split[2]));

                this.getFrigo().notifyObservers();
            }
        }
    }

    /**
     *
     * @param temp,  thermometer
     */

    public void displayThermometer(float temp, String thermometer) {
        this.getView().getThermo().setChart(this.getView().thermometer(temp, thermometer));
    }

    /**
     *
     * @param frigo
     */
    public void setFrigo(Frigo frigo) {
        this.frigo = frigo;
    }

    /**
     *
     * @return
     */
    public Frigo getFrigo() {
        return this.frigo;
    }

    /**
     *
     * @return
     */
    public IView getView() {
        return view;
    }

    /**
     *
     * @param view
     */

    public void setView(IView view) {
        this.view = view;
    }
}
