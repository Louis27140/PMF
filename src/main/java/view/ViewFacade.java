package view;

import model.fridge.Frigo;
import observer.Observer;

import javax.swing.*;

public class ViewFacade implements IView, Observer , Runnable{

    private Frigo frigo;

    public ViewFacade(Frigo frigo) {
        this.setFrigo(frigo);
        SwingUtilities.invokeLater(this);
    }

    public void setFrigo(Frigo frigo) {
        this.frigo = frigo;
    }

    public Frigo getFrigo() {
        return frigo;
    }

    @Override
    public void update(String str) {

    }

    @Override
    public void run() {
        JFrame window = new JFrame("Pimp My Fridge");
        window.setVisible(true);
        window.setSize(1000, 1000);
        JTextArea text = new JTextArea();
        text.setText(Float.toString(frigo.getTempInt()));
        text.setEditable(false);
        window.add(text);
    }
}
