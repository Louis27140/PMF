package view;

import model.ModelFacade;
import model.fridge.Frigo;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultValueDataset;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewFacadeTest {

    private Frigo frigo;
    private ViewFacade view;

    @Before
    public void setUp() throws Exception {
        this.frigo = new Frigo(15.0f, 53.0f, 21.0f, 14.0f, 15.0f);
        this.view = new ViewFacade(frigo);
    }

    @Test
    public void thermometer() {
        JFreeChart chart = view.thermometer(15.0f, "title");
        assertEquals(view.thermometer(frigo.getTempInt(), "title"), chart);
    }
}