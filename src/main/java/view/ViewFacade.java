package view;

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
import java.util.Observable;
import java.util.Observer;

public class ViewFacade implements IView, Runnable, Observer {

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
    private String[] choiceThermo = new String[]{"Indoor Thermometer", "Outdoor Thermometer", "Plate Thermometer"};
    private JComboBox list = new JComboBox(choiceThermo);
    private JLabel targetLabel = new JLabel();
    private JLabel imageLabel;

    private ChartPanel thermo = new ChartPanel(null);
    ChartPanel hygrometer = new ChartPanel(null);
    ChartPanel chartTemp = new ChartPanel(null);

    XYSeries coolingPlateSeries = new XYSeries("Cooling plate");
    XYSeries indoorSeries = new XYSeries("Indoor");
    XYSeries outdoorSeries = new XYSeries("Outdoor");

    /**
     *
     * @param model
     */
    public ViewFacade(IModel model) {
        this.model = model;
        this.setFrigo(model.getFrigo());
        SwingUtilities.invokeLater(this);
    }

    /**
     *
     * @param frigo
     */
    public void setFrigo(Frigo frigo) {
        this.frigo = frigo;
    }

    @Override
    public void run() {
            buildFrame();
    }

    public void buildFrame() {
        ImageIcon imageIcon = new ImageIcon("src/Image/cesi.png");
        imageLabel = new JLabel(imageIcon);

        GridBagConstraints c = new GridBagConstraints();
        Font titleFont = new Font("Arial", Font.BOLD, 20);

        // Window Settings
        window.setTitle("Pimp My Fridge UI");
        window.setSize(1100, 1100);
        window.setResizable(false);
        window.setLayout(new FlowLayout());
        window.setVisible(true);
        window.getContentPane().setBackground(Color.WHITE);
        window.add(panHead);
        window.add(panBody);

        // Content Panel Header settings
        panHead.setLayout(new GridBagLayout());
        panHead.setSize(1000, 500);
        panHead.setBackground(Color.WHITE);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.setGrid(c, 0 , 0);
        panHead.add(title, c);
        this.setGrid(c , 1, 0);
        panHead.add(list);
        this.setGrid(c, 2, 0);
        imageLabel.setSize(146, 86);
        panHead.add(imageLabel);
        this.setGrid(c, 0 , 2);
        thermo.setChart(thermometer(frigo.getTempInt(), "Indoor Thermometer"));
        panHead.add(thermo, c);
        this.setGrid(c, 1, 1);
        panHead.add(doorState, c);
        this.setGrid(c, 4, 0);
        c.fill = GridBagConstraints.NONE;
        textField.setText(Float.toString(frigo.getTarget()));
        panHead.add(textField, c);

        // Chart Settings

        hygrometer.setChart(hygrometer());
        chartTemp.setChart(createChartPanel());
        thermo.setPreferredSize(new Dimension(200, 400));
        hygrometer.setPreferredSize(new Dimension(300, 200));
        chartTemp.setPreferredSize(new Dimension(1000, 400));
        c.fill = GridBagConstraints.VERTICAL;
        this.setGrid(c , 0, 2);
        panHead.add(thermo, c);
        this.setGrid(c, 3, 2);
        panHead.add(hygrometer, c);

        // Panel Body Settings

        panBody.setSize(1000, 500);
        panBody.setLayout(new GridBagLayout());
        c.anchor = GridBagConstraints.PAGE_END;
        panBody.add(chartTemp, c);

        // Title Label setting
        title.setFont(titleFont);
        doorState.setFont(titleFont);
        doorState.setVisible(false);

        // Target
        this.setGrid(c, 3, 0);
        panHead.add(targetLabel, c);
        targetLabel.setText("Target :");
        targetLabel.setFont(titleFont);
    }

    public void setGrid(GridBagConstraints c, int x, int y) {
        c.gridx = x;
        c.gridy = y;
    }

    /**
     *
     * @param temp
     * @param title
     * @return
     */

    public JFreeChart thermometer(float temp, String title) {
        final DefaultValueDataset data = new DefaultValueDataset(temp);
        final ThermometerPlot thermometer = new ThermometerPlot(data);
        thermometer.setRange(0, 40);
        thermometer.setSubrangePaint(21, Color.blue);
        final JFreeChart chart = new JFreeChart(title,
                JFreeChart.DEFAULT_TITLE_FONT,
                thermometer,
                false);
        chart.setBackgroundPaint(Color.WHITE);
        return chart;
    }


    /**
     *
     * @return
     */
    public JFreeChart hygrometer() {
        final DefaultValueDataset data = new DefaultValueDataset(frigo.getHygrometry());
        final MeterPlot meter = new MeterPlot(data);
        final JFreeChart chart = new JFreeChart("Hygrometer",
                JFreeChart.DEFAULT_TITLE_FONT,
                meter,
                false);
        chart.setBackgroundPaint(Color.WHITE);
        meter.setUnits("% Humidity");
        return chart;
    }

    /**
     *
     * @return
     */

    private JFreeChart createChartPanel() {
        String chartTitle = "Temperature changes";
        String xAxisLabel = "Time (ms)";
        String yAxisLabel = "Temperature (°C)";

        XYDataset dataset = dataSet();

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        return chart;
    }


    /**
     *
     * @return
     */

    public XYDataset dataSet() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(coolingPlateSeries);
        dataset.addSeries(indoorSeries);
        dataset.addSeries(outdoorSeries);

        return dataset;
    }

    /**
     *
     * @return
     */
    public JTextField getTextField() {
        return textField;
    }

    /**
     *
     * @return
     */

    public ChartPanel getThermo() {
        return this.thermo;
    }

    /**
     *
     * @return
     */
    public JComboBox getList() {
        return this.list;
    }

    /**
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        switch (getList().getSelectedIndex()) {
            case 0:
                getThermo().setChart(thermometer(((Frigo) o).getTempInt(), "Indoor Thermometer"));
                break;
            case 1:
                getThermo().setChart(thermometer(((Frigo) o).getTempExt(), "Outdoor Thermometer"));
                break;
            case 2:
                getThermo().setChart(thermometer(((Frigo) o).getTempPlate(), "Plate Thermometer"));
                break;
        }

        hygrometer.setChart(hygrometer());

        if (((Frigo) o).getTempInt() >= ((Frigo) o).getTempExt() - 2) {
            doorState.setVisible(true);
        }

        if(((Frigo) o).dewPoint())
            JOptionPane.showMessageDialog(window,
                    "Risk of condensation");
    }

    /**
     *
     * @return
     */
    public XYSeries getCoolingPlateSeries() {
        return coolingPlateSeries;
    }

    /**
     *
     * @return
     */
    public XYSeries getIndoorSeries() {
        return indoorSeries;
    }

    /**
     *
     * @return
     */
    public XYSeries getOutdoorSeries() {
        return outdoorSeries;
    }
}


