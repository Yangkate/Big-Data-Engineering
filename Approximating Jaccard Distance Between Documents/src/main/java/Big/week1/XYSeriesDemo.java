package Big.week1;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class XYSeriesDemo extends ApplicationFrame {

/**
* A demonstration application showing an XY series containing a null value.
*
* @param title  the frame title.
*/
public XYSeriesDemo(final String title,XYSeries series) {
	// TODO Auto-generated constructor stub

   super(title);
   
   final XYSeriesCollection data = new XYSeriesCollection(series);
   final JFreeChart chart = ChartFactory.createXYLineChart(
       "error in function of n",
       "n", 
       "error", 
       data,
       PlotOrientation.VERTICAL,
       true,
       true,
       false
   );

   final ChartPanel chartPanel = new ChartPanel(chart);
   chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
   setContentPane(chartPanel);

}



//****************************************************************************
//* JFREECHART DEVELOPER GUIDE                                               *
//* The JFreeChart Developer Guide, written by David Gilbert, is available   *
//* to purchase from Object Refinery Limited:                                *
//*                                                                          *
//* http://www.object-refinery.com/jfreechart/guide.html                     *
//*                                                                          *
//* Sales are used to provide funding for the JFreeChart project - please    * 
//* support us so that we can continue developing free software.             *
//****************************************************************************


}