package gmain.test;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameMain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException{
		
		Parent root = FXMLLoader.load(getClass().getResource("game_main_page.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("gameMainDesign.css").toExternalForm());
		
		Font.loadFont(getClass().getResourceAsStream("font.a�ҳ���B.ttf"), 10);
		Font.loadFont(getClass().getResourceAsStream("font.a�ҳ���L.ttf"), 10);
		Font.loadFont(getClass().getResourceAsStream("font.a�ҳ���M.ttf"), 10);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("� ��� ������");
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
