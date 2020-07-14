package Front;

import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.XYChart;

import java.nio.file.Watchable;

public class MatchLogChart<X,Y> extends BarChart<X,Y> {

    public MatchLogChart(Axis<X> xAxis, Axis<Y> yAxis) {
        super(xAxis, yAxis);
        setTitle("Match Log");
    }
}
