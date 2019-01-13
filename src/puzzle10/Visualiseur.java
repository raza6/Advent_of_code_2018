package puzzle10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Visualiseur extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("chart.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Puzzle10");
		primaryStage.show();
		primaryStage.centerOnScreen();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
