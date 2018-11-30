package model;

import model.fridge.Frigo;

public class ModelFacade implements IModel {

    private Frigo frigo;

    public ModelFacade() {
        this.setFrigo(new Frigo(5.0f));
    }

    public void setFrigo(Frigo frigo) {
        this.frigo = frigo;
    }

    public Frigo getFrigo() {
        return this.frigo;
    }
}
