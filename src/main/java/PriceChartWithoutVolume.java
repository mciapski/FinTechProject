import Companies.ING;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class PriceChartWithoutVolume extends JFrame {
  public PriceChartWithoutVolume(String applicationTitle, String title) {
    super(title);
    XYDataset dataset = createDataset();
    JFreeChart chart = createChart(dataset);
    ChartPanel chartPanel = new ChartPanel(chart, false);
    chartPanel.setPreferredSize(new Dimension(500, 270));
    chartPanel.setMouseWheelEnabled(true);
    chartPanel.setMouseZoomable(true);

    setContentPane(chartPanel);
  }

  /**
   * Creating chart
   *
   * @param dataset
   * @return chart
   */
  private static JFreeChart createChart(XYDataset dataset) {
    JFreeChart chart = ChartFactory.createTimeSeriesChart(
        "ING Stock Prices",
        "Date",
        "Closing price",
        dataset,
        true,
        true,
        false
    );
    chart.setBackgroundPaint(Color.WHITE);
    XYPlot plot = (XYPlot) chart.getPlot();
    plot.setBackgroundPaint(Color.white);
    plot.setDomainGridlinePaint(Color.white);
    plot.setRangeGridlinePaint(Color.white);
    plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
    plot.setDomainCrosshairVisible(true);
    plot.setRangeCrosshairVisible(true);

    XYItemRenderer r = plot.getRenderer();
    if (r instanceof XYLineAndShapeRenderer) {
      XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
      renderer.setBaseShapesFilled(true);
      renderer.setBaseShapesVisible(true);
    }

    DateAxis axis = (DateAxis) plot.getDomainAxis();
    axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyy"));
    plot.setRenderer(r);

    return chart;
  }

  /**
   * Create dataset, one series for 1 month for ING
   *
   * @return the dataset.
   */
  private XYDataset createDataset() {
    TimeSeries s1 = new TimeSeries("ING share prices for July");
    GetDataFromH getDataFromH = new GetDataFromH();
    for (int i = 0; i < getDataFromH.storingList.size(); i++) {
      s1.add(new Day((Date) getDataFromH.storingDateList.get(i)),
          (Double) getDataFromH.IngPricesListToChart().get(i));
    }
    TimeSeriesCollection dataset = new TimeSeriesCollection();
    dataset.addSeries(s1);
    return dataset;
  }

  public void showChart() {
    PriceChartWithoutVolume chartOne = new PriceChartWithoutVolume(
        "Prices for month: July", "ING");
    chartOne.pack();
    RefineryUtilities.centerFrameOnScreen(chartOne);
    chartOne.setVisible(true);
  }
}
