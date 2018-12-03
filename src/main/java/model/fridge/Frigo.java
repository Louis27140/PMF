package model.fridge;

import java.util.Observable;

public class Frigo extends Observable {

    public Frigo(float tempInt, float hygro, float tempExt, float tempPlate, float target) {
        this.setTempInt(tempInt);
        this.setHygrometry(hygro);
        this.setTempExt(tempExt);
        this.setTempPlate(tempPlate);
        this.setTarget(target);
    }

    private float tempInt;

    private float tempExt;

    private float tempPlate;

    private float hygrometry;

    private float target;

    public float getTempInt() {
        return tempInt;
    }

    public void setTempInt(float tempInt) {
        this.tempInt = tempInt;
    }

    public float getHygrometry() {
        return hygrometry;
    }

    public void setHygrometry(float hygrometry) {
        this.hygrometry = hygrometry;
    }

    public float getTarget() {
        return target;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public float getTempExt() {
        return tempExt;
    }

    public void setTempExt(float tempExt) {
        this.tempExt = tempExt;
    }

    public float getTempPlate() {
        return tempPlate;
    }

    public void setTempPlate(float tempPlate) {
        this.tempPlate = tempPlate;
    }

    @Override
    public void notifyObservers(){
        setChanged();
        super.notifyObservers();
    }
}
