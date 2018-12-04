package model.fridge;

import java.util.Observable;

public class Frigo extends Observable {

    /**
     *
     * @param tempInt
     * @param hygro
     * @param tempExt
     * @param tempPlate
     * @param target
     */
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

    /**
     *
     * @return
     */
    public float getTempInt() {
        return tempInt;
    }

    /**
     *
     * @param tempInt
     */
    public void setTempInt(float tempInt) {
        this.tempInt = tempInt;
    }

    /**
     *
     * @return
     */
    public float getHygrometry() {
        return hygrometry;
    }

    /**
     *
     * @param hygrometry
     */
    public void setHygrometry(float hygrometry) {
        this.hygrometry = hygrometry;
    }

    /**
     *
     * @return
     */
    public float getTarget() {
        return target;
    }

    /**
     *
     * @param target
     */
    public void setTarget(float target) {
        this.target = target;
    }

    /**
     *
     * @return
     */
    public float getTempExt() {
        return tempExt;
    }

    /**
     *
     * @param tempExt
     */
    public void setTempExt(float tempExt) {
        this.tempExt = tempExt;
    }

    /**
     *
     * @return
     */
    public float getTempPlate() {
        return tempPlate;
    }

    /**
     *
     * @param tempPlate
     */
    public void setTempPlate(float tempPlate) {
        this.tempPlate = tempPlate;
    }

    /**
     *
     * @return
     */
    public boolean dewPoint() {
     double a = 17.27;
     double b = 237.7;

     double temp = (a * getTempInt()) / (b + getTempInt()) + Math.log(getHygrometry() * 0.01);
     temp = (b * temp) / (a - temp);
     return  temp > getTempPlate();
    }

    @Override
    public void notifyObservers(){
        setChanged();
        super.notifyObservers();
    }
}
