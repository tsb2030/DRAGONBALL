package eye.game.eyeMovement1;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;


//fxml파일 로딩을 확인하기 위해 기존 Main을 끌어왔습니다 -수빈-

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Font.loadFont(getClass().getResourceAsStream("A소나무.TTF"),14.0);

//		Parent root = FXMLLoader.load(getClass().getResource("zz_priorPage1.fxml"));
//		Parent root = FXMLLoader.load(getClass().getResource("strip_priorPage1.fxml"));
//		Parent root = FXMLLoader.load(getClass().getResource("view/strip_priorPage1.fxml"));
//		Parent root = FXMLLoader.load(getClass().getResource("view/Overview2.fxml"));
		Parent root = FXMLLoader.load(getClass().getResource("Overview.fxml"));
//		Parent root = FXMLLoader.load(getClass().getResource("view/popup_failure.fxml"));
//		Parent root = FXMLLoader.load(getClass().getResource("view/popup_success.fxml"));
//		Parent root = FXMLLoader.load(getClass().getResource("view/popup_question.fxml"));

		Scene scene = new Scene(root);

//		scene.getStylesheets().add(getClass().getResource("priorPage.css").toString());

		primaryStage.setScene(scene);
		primaryStage.setTitle("시선이동트레이닝");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
