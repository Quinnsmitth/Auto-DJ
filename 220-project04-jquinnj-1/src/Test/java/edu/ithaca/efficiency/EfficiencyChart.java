package edu.ithaca.efficiency;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Borrowed largely from: https://www.tutorialspoint.com/jfreechart/jfreechart_line_chart.htm
 */
public class EfficiencyChart extends ApplicationFrame {

   public EfficiencyChart(DefaultCategoryDataset dataset, String title ) {
      super(title);
      JFreeChart lineChart = ChartFactory.createLineChart(
         title,
         "Amount of Data","Time",
         dataset,
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 900 ) );
      setContentPane( chartPanel );
      this.pack( );
      this.setVisible( true );
   }

   private static DefaultCategoryDataset createExampleDataset( ) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      dataset.addValue( 10, "LeftFrontListGrabBag", "10000" );
      dataset.addValue( 16, "LeftFrontListGrabBag", "20000" );
      dataset.addValue( 19, "LeftFrontListGrabBag", "30000" );
      dataset.addValue( 25, "LeftFrontListGrabBag", "40000" );
      dataset.addValue( 28, "LeftFrontListGrabBag", "50000" );
      return dataset;
   }
   
   public static void main( String[ ] args ) {
      new EfficiencyChart(createExampleDataset(), "Data");
   }
}
