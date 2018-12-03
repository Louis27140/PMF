package model;

import model.fridge.Frigo;
import view.IView;

public class ModelFacade implements IModel {

    private Frigo frigo;

    private String split[];

    private IView view;

    public ModelFacade() {
        this.setFrigo(new Frigo(20.0f, 0.5f, 5.0f, 17.0f, 16.0f));
        this.setView(view);
    }

    public void takeValue(String str) {
        if (str.startsWith("s") && str.endsWith("e")) {
            split = str.substring(1,str.length() - 1).split(";");

            this.getFrigo().setTempPlate(Float.parseFloat(split[0]));
            this.getFrigo().setTempInt(Float.parseFloat(split[1]));
            this.getFrigo().setTempExt(Float.parseFloat(split[2]));
            this.getFrigo().setHygrometry(Float.parseFloat(split[3]));
        }
    }

    public void displayThermometer(float temp, String thermometer) {
        this.getView().getThermo().setChart(this.getView().thermometer(temp, thermometer));
    }

    public void setFrigo(Frigo frigo) {
        this.frigo = frigo;
    }

    public Frigo getFrigo() {
        return this.frigo;
    }

    public IView getView() {
        return view;
    }

    public void setView(IView view) {
        this.view = view;
    }
}
