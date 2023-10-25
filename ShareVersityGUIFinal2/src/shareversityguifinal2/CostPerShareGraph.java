package shareversityguifinal2;

import java.awt.Color;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleInsets;

/**
 * This class generates a JPanel with an interactive line chart using JFreeChart to display
 * the cost per share over time for a selected company. Methods are provided to customize the chart
 * appearance and enable click interactions to display details on a selected data point.
 */

public class CostPerShareGraph extends JPanel  {

    JFreeChart lineChart;
    ChartPanel chartPanel; 
    XYPlot plot;
    XYLineAndShapeRenderer renderer;
    StandardXYToolTipGenerator tooltipGenerator;
    XYSeriesCollection dataSet;
    ChartDotClickMouseListener customMouseListener;
    
    public CostPerShareGraph(String chartTitle, ArrayList<Company> companyList, int chosenCompanyIndex, javax.swing.JPanel panel) {
        lineChart = ChartFactory.createXYLineChart(
           chartTitle,
           "Days","Cost per Share ($)",
           createDataset(companyList, chosenCompanyIndex),
           PlotOrientation.VERTICAL,
           true,true,false);

        plot = lineChart.getXYPlot();
        
        // Create a renderer with lines and shapes (dots)
        renderer = new XYLineAndShapeRenderer(true, true);
        plot.setRenderer(renderer);

        // Reformatting date (from milliseconds)
        SimpleDateFormat customDateFormat = new SimpleDateFormat("dd-MM-yy");

        // Set a tooltip generator for the renderer with the custom date format
        tooltipGenerator = new StandardXYToolTipGenerator(
            "{1}: {2}",  // Tooltip format
            customDateFormat,  // Custom date format
            java.text.NumberFormat.getCurrencyInstance()  // Number format
        );
        
        renderer.setBaseToolTipGenerator(tooltipGenerator);
        renderer.setSeriesPaint(0, new Color(153, 107, 229));
        
        // Configure the x-axis as a date axis with a custom date format
        DateAxis dateAxis = new DateAxis("Date");
        dateAxis.setDateFormatOverride(customDateFormat);
        plot.setDomainAxis(dateAxis);
        
        // Add an annotation to label the last point as "Today"
        XYSeriesCollection ds = createDataset(companyList, chosenCompanyIndex);
        XYSeries series = ds.getSeries(0);
        int lastIndex = series.getItemCount() - 1;
        
        XYTextAnnotation annotation = new XYTextAnnotation("Today", (double)series.getX(lastIndex), (double)series.getY(lastIndex));
        plot.addAnnotation(annotation);
        
        // remove legend (uneeded, takes up space)
        lineChart.removeLegend();
        
        // add extra spacing to right outside graph
        lineChart.setPadding(new RectangleInsets(0,0,0,10));
        
        // sets whether the edges of text/graph lines are smoothed (causes blurriness)
        lineChart.setAntiAlias(true); 
        
        // Set margin inside graph range axis dynamically (to make "selected" label visible in all cases)
        double lowerBound = companyList.get(chosenCompanyIndex).computeMinPrice();
        double upperBound = companyList.get(chosenCompanyIndex).computeMaxPrice();
        double rangePadding = (upperBound-lowerBound)*0.1; // padding is 10% difference of the highest value and lowest value
        plot.getRangeAxis().setRange(lowerBound-rangePadding, upperBound+rangePadding);
        
        // chart panel creation
        chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(panel.getSize());
        chartPanel.setDomainZoomable(false); // disable zoom as this can mess up the graph axis formatting
        chartPanel.setRangeZoomable(false);
        chartPanel.setInitialDelay(0); // attempt to make tooltips faster
        chartPanel.setDismissDelay(1000);
        chartPanel.setReshowDelay(0);
        add(chartPanel);
    }

    // Get methods
    public JFreeChart getLineChart() {
        return lineChart;
    }

    public XYPlot getPlot() {
        return plot;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public XYLineAndShapeRenderer getRenderer() {
        return renderer;
    }

    public StandardXYToolTipGenerator getTooltipGenerator() {
        return tooltipGenerator;
    }

    public XYSeriesCollection getDataSet() {
        return dataSet;
    }

    public ChartDotClickMouseListener getCustomMouseListener() {
        return customMouseListener;
    }
    
    /*
        This method creates the dataSet (values) to be used in the XY plot line graph
        by pulling the cost per share history from a company
    */
    private XYSeriesCollection createDataset(ArrayList<Company> companyList, int chosenCompanyIndex) {
        Company chosenCompany = companyList.get(chosenCompanyIndex);

        XYSeries series = new XYSeries("Cost per Share");
        
        SimpleDateFormat customDateFormat = new SimpleDateFormat("dd-MM-yy");

        for (int cpsIndex = 0; cpsIndex < chosenCompany.getCostPerShareHistory().size(); cpsIndex++) {
            String dateString = chosenCompany.getCostPerShareHistory().get(cpsIndex).getDate(); // Get the date as a string
            try {
                Date date = customDateFormat.parse(dateString); // Parse the string as a Date
                series.add(date.getTime(), chosenCompany.getCostPerShareHistory().get(cpsIndex).getCost()); // Have to convert Date to milliseconds in order for add method to work...
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        return dataset;
    }
    
    /*
        This method allows the size of the graph to be resized instead of conforming to the JPanel size it is placed in (default)
    */
    public void setChartPanelSize(int width, int height)
    {
        Dimension customDimensions = new Dimension(width, height);
        chartPanel.setPreferredSize(customDimensions);
    }
    
    /*
        This method allows a generated CostPerShareGraph to have selectable dots
        using the custom ChartMouseListener - ChartDotClickMouseListener
    */
    public void enableClickableDots(javax.swing.JTable tableToUpdate)
    {
        getChartPanel().addChartMouseListener(new ChartDotClickMouseListener(this, tableToUpdate));
    }
}

