package view;

import observer.Observer;

import javax.swing.*;

public class ViewFacade implements IView, Observer {

    public ViewFacade(String title, int width, int height) {
        start(title, width, height);
    }

    public void start(String title, int width, int height) {
        JFrame window = new JFrame(title);
        window.setVisible(true);
        window.setSize(width, height);
    }

    @Override
    public void update(String str) {

    }
}
