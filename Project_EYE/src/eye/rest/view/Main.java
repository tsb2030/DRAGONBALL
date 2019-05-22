package eye.rest.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		Font.loadFont(getClass().getResourceAsStream("a소나무B.ttf"), 14.0);
		Font.loadFont(getClass().getResourceAsStream("a소나무L.ttf"), 14.0);
		Font.loadFont(getClass().getResourceAsStream("a소나무M.ttf"), 14.0);
		
		Parent root = FXMLLoader.load(getClass().getResource("recordMain.fxml"));

		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("recordDesign.css").toString());

		primaryStage.setScene(scene);
		primaryStage.setTitle("기록 화면 테스트");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
