package view;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;

public interface IView {
    JTextField getTextField();
    JComboBox<Object> getList();
    ChartPanel getThermo();
    JFreeChart thermometer(float temp, String title);
}
