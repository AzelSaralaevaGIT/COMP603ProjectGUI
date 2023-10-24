package shareversityguifinal2;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.TextAnchor;
import org.jfree.chart.JFreeChart;


/**
 * This class implements a listener for handling clicks on data points in a JFreeChart. It updates a table
 * with information related to the clicked data point.
 */

public class ChartDotClickMouseListener implements ChartMouseListener {
    private final XYPlot plot;
    private JFreeChart chart; 
    javax.swing.JTable tableToUpdate;
    private XYTextAnnotation annotation;
    private String clickedDate;
    private double clickedValue;

    public ChartDotClickMouseListener(CostPerShareGraph cpsGraph, javax.swing.JTable tableToUpdate) {
        this.plot = cpsGraph.getPlot();
        this.chart = cpsGraph.getLineChart();
        this.tableToUpdate = tableToUpdate;
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent event) {
        ChartEntity entity = event.getEntity();

        if (entity instanceof XYItemEntity) {
            XYItemEntity itemEntity = (XYItemEntity) entity;
            double x = itemEntity.getDataset().getXValue(itemEntity.getSeriesIndex(), itemEntity.getItem());
            double y = itemEntity.getDataset().getYValue(itemEntity.getSeriesIndex(), itemEntity.getItem());

            if (annotation != null) {
                plot.removeAnnotation(annotation);
            }

            annotation = new XYTextAnnotation("Selected", x, y);
            annotation.setTextAnchor(TextAnchor.BOTTOM_CENTER);

            plot.addAnnotation(annotation);
            
            // Redraw the chart to update the dot and annotation
            chart.fireChartChanged();
            
            // Get tooltip to save the date and value into variables to be retrieved in application
            String tooltip = itemEntity.getToolTipText();
            
            String[] parts = tooltip.split(": \\$");
            System.out.println("Number of parts: " + parts.length);
            if (parts.length == 2)
            {
                clickedDate = parts[0];
                clickedValue = Double.valueOf(parts[1]);
            }
            
            System.out.println("Split: Date: "+clickedDate+", Value: $"+clickedValue);
            
            tableToUpdate.setValueAt(clickedDate, 0, 0);
            tableToUpdate.setValueAt(clickedValue, 0, 1);
        }
    }

    @Override
    public void chartMouseMoved(ChartMouseEvent event) {
        // Handle mouse movement (uneeded)
    }

    // Getters 
    
    public String getClickedDate() {
        return clickedDate;
    }

    public double getClickedValue() {
        return clickedValue;
    }
}
