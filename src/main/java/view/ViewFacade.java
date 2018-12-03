package view;

import controller.IController;
import model.IModel;
import model.fridge.Frigo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class ViewFacade implements IView , Runnable {

    private final IModel model;

    private Frigo frigo;

    JTextField textField = new JTextField(5);

    final int hygroMin = 0;
    final int hygroMax = 100;
    final int hygroDefault = 50;

    private JFrame window = new JFrame();
    private JPanel panHead = new JPanel();
    private JPanel panBody = new JPanel();
    private JLabel title = new JLabel("Pimp My Fridge !!");
    private JLabel doorState = new JLabel("Porte Ouverte");
    private JButton button = new JButton("Change value");
    private String[] choiceThermo = new String[]{"Indoor Thermometer", "Outdoor Thermometer", "Plate Thermometer"};
    private JComboBox list = new JComboBox(choiceThermo);

    private ChartPanel thermo = new ChartPanel(null);
    ChartPanel hygrometer = new ChartPanel(null);
    ChartPanel chartTemp = new ChartPanel(null);

    private IController controller;

    public ViewFacade(IModel model) {
        this.model = model;
        this.setFrigo(model.getFrigo());
        SwingUtilities.invokeLater(this);
    }

    public void setFrigo(Frigo frigo) {
        this.frigo = frigo;
    }

    public Frigo getFrigo() {
        return frigo;
    }

    @Override
    public void run() {
        buildFrame();
    }

    public void buildFrame() {
        GridBagConstraints c = new GridBagConstraints();
        Font titleFont = new Font("Arial", Font.BOLD, 20);

        // Window Settings
        window.setTitle("Pimp My Fridge UI");
        window.setSize(1200,1200);
        window.setResizable(false);
        window.setLayout(new FlowLayout());
        window.setVisible(true);
        window.getContentPane().setBackground(Color.CYAN);
        window.add(panHead);
        window.add(panBody);

        // Content Panel Header settings
        panHead.setLayout(new GridBagLayout());
        panBody.setLayout(new GridBagLayout());
        panHead.setSize(1000, 500);
        panBody.setSize(1000, 500);
        panHead.setBackground(Color.CYAN);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panHead.add(title, c);
        c.gridx = 1;
        c.gridy = 0;
        panHead.add(list);
        c.gridx = 0;
        c.gridy = 2;
        thermo.setChart(thermometer(frigo.getTempInt(), "Indoor Thermometer"));
        panHead.add(thermo, c);
        c.gridx = 1;
        c.gridy =1;
        panHead.add(doorState, c);
        c.gridx = 3;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        textField.setText(Float.toString(frigo.getTarget()));
        panHead.add(textField,c);
        hygrometer.setChart(hygrometer());
        chartTemp.setChart(createChartPanel());
        thermo.setPreferredSize(new Dimension(200,400));
        hygrometer.setPreferredSize(new Dimension(300, 200));
        chartTemp.setPreferredSize(new Dimension(1000, 500));
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 2;
        panHead.add(thermo, c);
        c.gridx = 3;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        panHead.add(hygrometer, c);
        c.anchor = GridBagConstraints.PAGE_END;
        panBody.add(chartTemp, c);
        // Title Label setting
        title.setFont(titleFont);
        doorState.setFont(titleFont);
        doorState.setBackground(Color.GREEN);
    }

    public JFreeChart thermometer(float temp, String title) {
        final DefaultValueDataset data = new DefaultValueDataset(temp);
        final ThermometerPlot thermometer = new ThermometerPlot(data);
        thermometer.setRange(0, 40);
        final JFreeChart chart = new JFreeChart(title,
                JFreeChart.DEFAULT_TITLE_FONT,
                thermometer,
                false);
        return chart;
    }

    public JFreeChart hygrometer() {
        final DefaultValueDataset data = new DefaultValueDataset(frigo.getHygrometry());
        final MeterPlot meter = new MeterPlot(data);
        final JFreeChart chart = new JFreeChart("Hygrometer indoor",
                JFreeChart.DEFAULT_TITLE_FONT,
                meter,
                false);
        meter.setUnits("% Humidity");
        return chart;
    }

    private JFreeChart createChartPanel() {
        String chartTitle = "Test";
        String xAxisLabel = "X";
        String yAxisLabel = "Y";

        XYDataset dataset = dataSet();

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        return chart;
    }

    public XYDataset dataSet() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Object 1");
        XYSeries series2 = new XYSeries("Object 2");
        XYSeries series3 = new XYSeries("Object 3");

        series1.add(1.0, 2.0);
        series1.add(2.0, 3.0);
        series1.add(3.0, 2.5);
        series1.add(3.5, 2.8);
        series1.add(4.2, 6.0);

        series2.add(2.0, 1.0);
        series2.add(2.5, 2.4);
        series2.add(3.2, 1.2);
        series2.add(3.9, 2.8);
        series2.add(4.6, 3.0);

        series3.add(1.2, 4.0);
        series3.add(2.5, 4.4);
        series3.add(3.8, 4.2);
        series3.add(4.3, 3.8);
        series3.add(4.5, 4.0);

        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        return dataset;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setController(IController controller) {
        this.controller = controller;
    }

    public ChartPanel getThermo() {
        return this.thermo;
    }

    public JComboBox getList() {
        return this.list;
    }
}


