package eye.set.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
//설정 페이지 실행켜보려고 생성. 추후 삭제하셔도 무방합니다!!
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException{
		Font.loadFont(getClass().getResourceAsStream("A소나무.TTF"),14.0);
		Parent root = FXMLLoader.load(getClass().getResource("setOverview.fxml"));

		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("setPage.css").toString());

		primaryStage.setScene(scene);
		primaryStage.setTitle("설정화면 테스트");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
