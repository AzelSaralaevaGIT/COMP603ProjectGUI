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
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.XYPlot;

/*
    This class generates the CostPerShare graph to be used in the GUI using the jFreeChart library
*/

public class CostPerShareGraph extends JPanel  {

    JFreeChart lineChart;
    ChartPanel chartPanel; 
    
    public CostPerShareGraph(String chartTitle, ArrayList<Company> companyList, int chosenCompanyIndex, javax.swing.JPanel panel) {
        lineChart = ChartFactory.createXYLineChart(
           chartTitle,
           "Days","Cost per Share ($)",
           createDataset(companyList, chosenCompanyIndex),
           PlotOrientation.VERTICAL,
           true,true,false);
        
        XYPlot plot = lineChart.getXYPlot();
        
        // Create a renderer with lines and shapes (dots)
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
        plot.setRenderer(renderer);

        // Create a SimpleDateFormat with your desired date format
        SimpleDateFormat customDateFormat = new SimpleDateFormat("dd/MM/yy");

        // Set a tooltip generator for the renderer with the custom date format
        StandardXYToolTipGenerator tooltipGenerator = new StandardXYToolTipGenerator(
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
        
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

        // Set the desired range for the y-axis (adjust these values as needed)
        rangeAxis.setRange(companyList.get(chosenCompanyIndex).computeMinPrice()-1, companyList.get(chosenCompanyIndex).computeMaxPrice()+1);

        // Add an annotation to label the last point as "Today"
        XYSeriesCollection ds = createDataset(companyList, chosenCompanyIndex);
        XYSeries series = ds.getSeries(0);
        int lastIndex = series.getItemCount() - 1;
        
        XYTextAnnotation annotation = new XYTextAnnotation("Today", (double)series.getX(lastIndex), (double)series.getY(lastIndex));
        plot.addAnnotation(annotation);
        
        lineChart.removeLegend();
        
        lineChart.setAntiAlias(true); // sets whether the edges of text/graph lines are smoothed (causes blurriness)
        
        chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(panel.getSize());
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        chartPanel.setInitialDelay(0);
        chartPanel.setDismissDelay(1000);
        chartPanel.setReshowDelay(0);
        add(chartPanel);
    }

    public JFreeChart getLineChart() {
        return lineChart;
    }

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
    
}