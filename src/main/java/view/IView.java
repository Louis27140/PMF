package view;

import controller.IController;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;

public interface IView {
    JTextField getTextField();
    void setController(IController controller);
    JFreeChart thermometer(float tempExt, String outdoor_thermometer);

    ChartPanel getThermo();

    JComboBox<Object> getList();
}
