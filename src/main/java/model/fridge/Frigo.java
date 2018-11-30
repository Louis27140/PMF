package model.fridge;

import model.ModelFacade;

public class Frigo {

    public Frigo(float temp) {
        this.setTempInt(temp);
    }

    private float tempInt;

    private float hygrometry;

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
}
