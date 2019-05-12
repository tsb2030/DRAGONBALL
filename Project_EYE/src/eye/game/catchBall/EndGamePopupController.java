package eye.game.catchBall;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.jfoenix.controls.JFXButton;

import eye.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EndGamePopupController implements Initializable {

	@FXML
	private JFXButton backGame;

	@FXML
	private JFXButton goMainButton;

	@FXML
	private Label gameTime;

	@FXML
	private Label gameScore;

	@FXML
	void backGameAction(ActionEvent event) {
		
		try {

			Main.mainMusic.stopMusic();
			Main.mainMusic.resetNameAudioStream("LaLaLa");
			//catchballGame fxml파일을 scene에 저아하는 방법
			Parent CatchballGame = FXMLLoader.load(getClass().getResource("CatchballGame.fxml"));
			Scene scene = new Scene(CatchballGame);
			
			//css저장
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());  // css 지정
			
			//이전 창을 static으로 만들었기 때문에 그 창을 변경 할 수 있다.
			CatchballGameController.currentStage.setScene(scene);
			CatchballGameController.currentStage.setTitle("CatchballGame");
			
			//현재 팝업창을 닫기위해 일단 지정
			Stage primaryStage = (Stage) backGame.getScene().getWindow(); // 현재 윈도우 가져오기
			//현재 팝업 창 닫기
			primaryStage.close();
			
			//점수 초기화
			CatchballGameController.bigScore = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void goMainButtonAction(ActionEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		// 현재 팝업 창을 닫고, 이전 창을 변경하는 작업을 수행해야 함
		Main.mainMusic.stopMusic();
		Main.mainMusic.resetNameAudioStream("LaLaLa");
		// go main
    	try {
    		//catchballGame fxml파일을 scene에 저아하는 방법
			Parent selectSpeedPage = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
			Scene scene = new Scene(selectSpeedPage);

			//css저장
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());  // css 지정
			
			//현재 팝업창을 닫기위해 일단 지정
			Stage primaryStage = (Stage) backGame.getScene().getWindow(); // 현재 윈도우 가져오기

			//점수 초기화
			CatchballGameController.bigScore = 0;
			
			//이전 창을 static으로 만들었기 때문에 그 창을 변경 할 수 있다.
			CatchballGameController.currentStage.setScene(scene);
			CatchballGameController.currentStage.setTitle("game_main_page");
			
			//현재 팝업 창 닫기
			primaryStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gameScore.setText(String.valueOf(CatchballGameController.bigScore));
		gameTime.setText(String.valueOf(CatchballGameController.timeTime));
	}

}
