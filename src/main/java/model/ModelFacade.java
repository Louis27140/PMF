package model;

import model.communication.SerialCom;
import model.fridge.Frigo;
import view.IView;

public class ModelFacade implements IModel {

    private Frigo frigo;

    private SerialCom serialCom;

    private String split[];

    public ModelFacade() {
        serialCom = new SerialCom(this);
        this.setFrigo(new Frigo(20.0f, 0.5f, 5.0f, 17.0f, 16.0f));
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

    public void setFrigo(Frigo frigo) {
        this.frigo = frigo;
    }

    public Frigo getFrigo() {
        return this.frigo;
    }

    @Override
    public void addObserver(IView view) {

    }

    public SerialCom getSerialCom() {
        return serialCom;
    }

    public void setSerialCom(SerialCom serialCom) {
        this.serialCom = serialCom;
    }
}
