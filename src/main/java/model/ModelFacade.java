package model;

import observer.Observable;
import observer.Observer;
import view.IView;

public class ModelFacade implements IModel, Observable {

    @Override
    public void addObserver(Observer obs) {

    }

    @Override
    public void removeObserver() {

    }

    @Override
    public void notifyObserver(String str) {

    }

    @Override
    public void addObserver(IView view) {

    }
}
