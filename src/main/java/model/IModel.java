package model;

import model.fridge.Frigo;
import view.IView;

public interface IModel {
    Frigo getFrigo();

    void addObserver(IView view);
}
