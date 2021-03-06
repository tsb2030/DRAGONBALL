package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Font.loadFont(getClass().getResourceAsStream("a家唱公B.ttf"), 10);
			Font.loadFont(getClass().getResourceAsStream("a家唱公L.ttf"), 10);
			Font.loadFont(getClass().getResourceAsStream("a家唱公M.ttf"), 10);

			Parent root = FXMLLoader.load(getClass().getResource("GameIntro.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("A to Z 款悼 汲疙");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
