package eye.game.eyeMovement2;

import java.net.URL;
import java.util.ResourceBundle;

import eye.db.AchievementDB;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Failcontroller implements Initializable {

	AchievementDB aDB = new AchievementDB();

	@FXML
	private AnchorPane FailPage;

	@FXML
	private Button closeBtn;

	@FXML
	private Button restartPopUpBtn;

	public static boolean eyeAchievementVoidValue = false;

	// 종료 후 게임 목록으로 변환 메소드
	@FXML
	void closePopUp(ActionEvent event) {
		eyeAchievementVoidValue = true;
		aDB.ach();
		try {
			setController.isGameStart = false;
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			Main.setMusic("mainMusic", true, 1);
			Parent MainPage = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
			Scene scene = new Scene(MainPage);
			Playcontroller.currentStage.setResizable(false);
			Playcontroller.currentStage.setScene(scene);
			Playcontroller.currentStage.setTitle("game_main_page");

			scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());

			Stage primaryStage = (Stage) FailPage.getScene().getWindow();
			primaryStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 트레이닝 다시 시작 버튼
	@FXML
	void numGameRestart(ActionEvent event) {
		eyeAchievementVoidValue = true;
		aDB.ach();
		try {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			Parent root = FXMLLoader.load(getClass().getResource("Overview2.fxml"));
			Scene scene = new Scene(root);
			Playcontroller.currentStage.setResizable(false);
			Playcontroller.currentStage.setScene(scene);
			Playcontroller.currentStage.setTitle("시선이동트레이닝2");

			Stage primaryStage = (Stage) FailPage.getScene().getWindow();
			primaryStage.close();

			Playcontroller.cnt = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
