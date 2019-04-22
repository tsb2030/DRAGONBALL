package eye.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Font.loadFont(getClass().getResourceAsStream("a�ҳ���B.ttf"), 10);
			Font.loadFont(getClass().getResourceAsStream("a�ҳ���L.ttf"), 10);
			Font.loadFont(getClass().getResourceAsStream("a�ҳ���M.ttf"), 10);

			Parent root = FXMLLoader.load(getClass().getResource("main_page.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Main Page");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
