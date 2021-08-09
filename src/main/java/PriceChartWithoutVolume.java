import Companies.Choice;
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
import java.util.Date;

public class PriceChartWithoutVolume extends JFrame {

  private Choice choice;

  public PriceChartWithoutVolume(String applicationTitle, String title, Choice choice) {
    super(title);
    XYDataset dataset;
    switch (choice) {
      case ING:
        dataset = createDatasetING();
        break;
      case CDProject:
        dataset = createDatasetCD();
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + choice);
    }
    this.choice = choice;
    JFreeChart chart = createChart(dataset);
    ChartPanel chartPanel = new ChartPanel(chart, false);
    chartPanel.setPreferredSize(new Dimension(500, 270));
    chartPanel.setMouseWheelEnabled(true);
    chartPanel.setMouseZoomable(true);

    setContentPane(chartPanel);
  }

  public Choice getChoice() {
    return choice;
  }

  public void setChoice(Choice choice) {
    this.choice = choice;
  }

  /**
   * Creating chart
   *
   * @param dataset
   * @return chart
   */
  private static JFreeChart createChart(XYDataset dataset) {
    JFreeChart chart = ChartFactory.createTimeSeriesChart(
        "Stock Prices",
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
  private XYDataset createDatasetING() {
    TimeSeries s1 = new TimeSeries("ING historical share prices");
    GetDataFromH getDataFromH = new GetDataFromH();
    for (int i = 0; i < getDataFromH.storingINGList.size(); i++) {
      s1.add(new Day((Date) getDataFromH.storingINGDateList.get(i)),
          (Double) getDataFromH.IngPricesListToChart().get(i));
    }
    TimeSeriesCollection dataset = new TimeSeriesCollection();
    dataset.addSeries(s1);
    return dataset;
  }

  private XYDataset createDatasetCD() {
    TimeSeries s2 = new TimeSeries("CDProject historical share prices");
    GetDataFromH getDataFromHCD = new GetDataFromH();
    for (int i = 0; i < getDataFromHCD.storingCDList.size(); i++) {
      s2.add(new Day((Date) getDataFromHCD.storingCDDateList.get(i)),
          (Double) getDataFromHCD.CDPricesListToChart().get(i));
    }
    TimeSeriesCollection dataset = new TimeSeriesCollection();
    dataset.addSeries(s2);
    return dataset;
  }

  public void showChart(PriceChartWithoutVolume priceChartWithoutVolume) {

    priceChartWithoutVolume.pack();
    RefineryUtilities.centerFrameOnScreen(priceChartWithoutVolume);
    priceChartWithoutVolume.setVisible(true);
  }
}
