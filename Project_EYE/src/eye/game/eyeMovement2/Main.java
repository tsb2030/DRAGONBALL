package eye.game.eyeMovement2;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException{
		Font.loadFont(getClass().getResourceAsStream("a家唱公B.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("a家唱公L.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("a家唱公M.ttf"), 10);
		
		Parent root = FXMLLoader.load(getClass().getResource("design3.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("eyetraining");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}