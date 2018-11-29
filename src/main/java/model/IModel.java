package model;

import view.IView;

public interface IModel {
    void addObserver(IView view);
}
