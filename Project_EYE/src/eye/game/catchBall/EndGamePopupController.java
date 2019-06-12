package eye.game.catchBall;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.jfoenix.controls.JFXButton;

import eye.Music;
import eye.main.Main;
import eye.set.controller.setController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import eye.db.*;
public class EndGamePopupController implements Initializable {

	dbconn db = new dbconn();
	
	@FXML
	private Button goMainButton, backGame;

	@FXML
	private Label gameTime,first,second,third;

	@FXML
	private Label gameScore,level;

	@FXML
	void backGameAction(ActionEvent event) {

		Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
		effectMusic.start();

		try {
			Parent CatchballGame = FXMLLoader.load(getClass().getResource("CatchballGame.fxml"));
			Scene scene = new Scene(CatchballGame);

			// css
			scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());

			CatchballGameController.currentStage.setScene(scene);
			CatchballGameController.currentStage.setTitle("CatchballGame");

			Stage primaryStage = (Stage) backGame.getScene().getWindow(); // ���� ������ ��������
			primaryStage.close();

			CatchballGameController.bigScore = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void goMainButtonAction(ActionEvent event)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {

		Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
		effectMusic.start();

		Main.setMusic("mainMusic", true, 1);
		// go main
		try {
			setController.isGameStart = false;
			Parent selectSpeedPage = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
			Scene scene = new Scene(selectSpeedPage);

			// css
			scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());

			Stage primaryStage = (Stage) backGame.getScene().getWindow(); // ���� ������ ��������

			CatchballGameController.bigScore = 0;

			CatchballGameController.currentStage.setScene(scene);
			CatchballGameController.currentStage.setTitle("game_main_page");

			primaryStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gameScore.setText(String.valueOf(CatchballGameController.bigScore));
		gameTime.setText(String.valueOf(CatchballGameController.timeTime));
		int speed = (int) IntroducePageController.gameSpeed;
		Double recordData[] = null;
		try {
			if(speed==5) {
			recordData = db.getGameData("catchball1",1);
			level.setText("기록(Easy)");
			}
			if(speed==4) {
				recordData = db.getGameData("catchball2",1);
				level.setText("기록(Normal)");
			}
			if(speed==3) {
				recordData = db.getGameData("catchball3",1);
				level.setText("기록(Hard)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Arrays.toString(recordData));
		if(recordData[0]!=null)
			first.setText("1위 : "+Double.toString(recordData[0])+"점");
		else
			first.setText("1위 : ");
		if(recordData[1]!=null)
			second.setText("2위 : "+Double.toString(recordData[1])+"점");
		else
			second.setText("2위 : ");
		if(recordData[2]!=null)
			third.setText("3위 : "+Double.toString(recordData[2])+"점");
		else
			third.setText("3위 : ");
	}

}
