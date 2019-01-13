package puzzle10;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class Controller implements Initializable{	
	@FXML ScatterChart<Number, Number> chart;
	
	DataBuilder db; //Modele
	int nbSecond = 0;
	
	@Override
	public void initialize(URL loc, ResourceBundle resource) {
		try {
			db = new DataBuilder();
		} catch (IOException e) {
			db = null;
			e.printStackTrace();
		}
		initPlot();
	}
	
	public void initPlot() {
		chart.getData().clear();
		XYChart.Series<Number,Number> series = new XYChart.Series<Number,Number>();
		for(Coordinate c : db.speedPoint.values()) {
			series.getData().add(new XYChart.Data<Number,Number>(c.pointx,c.pointy));
		}
		chart.getData().add(series);
	}
	
	public void updatePlot(int nb) {
		chart.getData().clear();
		XYChart.Series<Number,Number> series = new XYChart.Series<Number,Number>();
		db.update(nb);
		nbSecond += nb;
		for(Coordinate c : db.speedPoint.values()) {
			series.getData().add(new XYChart.Data<Number,Number>(c.pointx,c.pointy));
		}
		chart.getData().add(series);
	}
	
	public void updatePlot500() {
		updatePlot(500);
	}
	
	public void updatePlot10() {
		updatePlot(10);
	}
	
	public void updatePlot1() {
		updatePlot(1);
	}
}
