package controller;

import model.IModel;
import view.IView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

import javax.swing.*;

public class ControllerFacade implements IController, ActionListener {

    private final IView view;
    private final IModel model;

    private SerialPort arduino;
    private Scanner arduinoReader;

    private final String com = "COM5";

    public ControllerFacade(final IView view, final IModel model) {
        super();
        this.view = view;
        this.model = model;

        this.model.getFrigo().addObserver(this.view);
    }

    @Override
    public void start() {
        this.view.getTextField().addActionListener(this);
        this.view.getList().addActionListener(this);

        System.out.println("Opening serial communication");

        for(SerialPort port : SerialPort.getCommPorts())
            if(port.getSystemPortName().contains(com))
                arduino = port;

        System.out.println("Port : " + arduino.getSystemPortName());

        if(!arduino.openPort())
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }

        arduino.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        arduinoReader = new Scanner(arduino.getInputStream());

        while(this.arduinoReader.hasNextLine()){
            String message = this.arduinoReader.nextLine();
            this.model.takeValue(message);
        }
    }


    public void setTarget(float target) {
        this.model.getFrigo().setTarget(target);
        byte[] data = ("s" + target + "e").getBytes();
        try {
            arduino.getOutputStream().write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (view.getList().getSelectedIndex()){
            case 0:
                model.displayThermometer(model.getFrigo().getTempInt(), "Indoor Thermometer");
                break;
            case 1:
                model.displayThermometer(model.getFrigo().getTempExt(), "Outdoor Thermometer");
                break;
            case 2:
                model.displayThermometer(model.getFrigo().getTempPlate(), "Plate Thermometer");
                break;
            default:
                break;
        }
    }
}
