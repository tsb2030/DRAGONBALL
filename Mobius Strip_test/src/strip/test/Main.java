package strip.test;

import java.io.IOException;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("view/Overview.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("트레이닝 구현 연습1");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
