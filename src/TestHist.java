//import java.util.Random;

import java.util.Date;
import java.util.Random;

import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.data.EnumeratedData;
import de.erichseifert.gral.data.statistics.Histogram1D;
import de.erichseifert.gral.data.statistics.Statistics;
import de.erichseifert.gral.plots.BarPlot;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.GraphicsUtils;
import de.erichseifert.gral.util.Insets2D;
import de.erichseifert.gral.util.MathUtils;
import de.erichseifert.gral.util.Orientation;


public class TestHist extends MyPanel{
	private static final long serialVersionUID = 4458280577519421950L;

	private static final int SAMPLE_COUNT = 100;
	
	private double[] dataPAA;

	@SuppressWarnings("unchecked")
	public TestHist(double[] inputData) {
		// Create example data
		dataPAA = inputData;
		Random random = new Random();
		DataTable data = new DataTable(Integer.class, Double.class);
		int j = 0;
		/*while (true){
			if (dataPAA[j] != 0) break;
			j++;
		}*/
		System.out.println("while fertig; j=" + j);
		for (int i = j ; i < dataPAA.length; i++) {
			data.add(i, dataPAA[i]);
			//data.add(random.nextGaussian());
			System.out.println(dataPAA[i] + " " + random.nextGaussian());
		}

		// Create histogram from data
		Histogram1D histogram = new Histogram1D(data, Orientation.VERTICAL, 20);
//				new Number[] {0.0, 0.001, 0.0012, 0.0014, 0.0016,  0.0018, 0.002});
		// Create a second dimension (x axis) for plotting
		DataSource histogram2d = new EnumeratedData(histogram);//, 0.0, 0.01);

		// Create new bar plot
//		BarPlot plot = new BarPlot(histogram2d);
		XYPlot plot = new XYPlot(data);
		plot.getAxis(XYPlot.AXIS_X).setAutoscaled(true);
		plot.autoscaleAxis(XYPlot.AXIS_Y);
		// Format plot 
		//inset = Platz zum Rand (damit man die Beschriftung sieht)
		plot.setInsets(new Insets2D.Double(20.0, 65.0, 50.0, 40.0));
		plot.getTitle().setText(
		String.format("%d Datenpunkte", data.getRowCount()));
		//plot.setBarWidth(0.78);

		// Format x axis
		//plot.getAxisRenderer(BarPlot.AXIS_X).setTickAlignment(0.0);
		//plot.getAxisRenderer(BarPlot.AXIS_X).setTickSpacing(0.5);
		plot.getAxisRenderer(BarPlot.AXIS_X).setMinorTicksVisible(false);
		// Format y axis
		//plot.getAxis(BarPlot.AXIS_Y).setRange(0.0,
		//MathUtils.ceil(histogram.getStatistics().get(Statistics.MAX)*1.1, 25.0));
		plot.getAxisRenderer(BarPlot.AXIS_Y).setTickAlignment(0.0);
		plot.getAxisRenderer(BarPlot.AXIS_Y).setMinorTicksVisible(false);
		plot.getAxisRenderer(BarPlot.AXIS_Y).setIntersection(-4.4);
		
		// Format bars
		/*plot.getPointRenderer(histogram2d).setColor(
		GraphicsUtils.deriveWithAlpha(COLOR1, 128));
		plot.getPointRenderer(histogram2d).setValueVisible(true);
*/
		// Add plot to Swing component
		InteractivePanel panel = new InteractivePanel(plot);
		panel.setPannable(false);
		panel.setZoomable(false);
		add(panel);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyPAA myPAA = new MyPAA(0.999, 0.999, 2000);
		System.out.println("Start:     " + new Date());
		double[] waitingtimes = myPAA.waitingTimeForValue(6000, myPAA.getValueCount()-2);
		System.out.println("Calculated " + new Date());
		//myPAA.ausgabe1(waitingtimes);
		//System.out.println(new Date());
		new TestHist(waitingtimes).showInFrame();
		System.out.println("Feddisch   " + new Date());

	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Titel";
	}

	@Override
	public String getDescription() {
		return String.format("Histogram of %d samples", SAMPLE_COUNT);
	}

}
