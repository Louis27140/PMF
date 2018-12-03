package view;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYZDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public interface IView extends Observer {
    JTextField getTextField();
    JComboBox<Object> getList();
    ChartPanel getThermo();
    JFreeChart thermometer(float temp, String title);

    @Override
    void update(Observable o, Object arg);

    XYSeries getCoolingPlateSeries();
    XYSeries getIndoorSeries();
    XYSeries getOutdoorSeries();
}
