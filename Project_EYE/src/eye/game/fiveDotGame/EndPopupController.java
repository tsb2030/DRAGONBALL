package eye.game.fiveDotGame;

import eye.Music;
import eye.main.Main;
import eye.set.controller.setController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EndPopupController {

	@FXML
	AnchorPane endPopup, fiveDotPage;

	// 다시하기 버튼 누를 때 실행
	public void tryAgain(ActionEvent e) {
		try {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			Parent root = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
			Scene scene = new Scene(root);
			GamePageController.gamePageStage.setScene(scene);
			GamePageController.gamePageStage.setTitle("5점 카드 트레이닝");

			scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());

			Stage currentStage = (Stage) endPopup.getScene().getWindow();
			currentStage.close();

		} catch (Exception e1) {
			// TODO: handle exception
		}

	}

	@FXML
	void backToGameSelectPage(ActionEvent event) {
		try {
			setController.isGameStart = false;
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			Main.setMusic("mainMusic", true, 1);
			Parent root = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
			Scene scene = new Scene(root);
			GamePageController.gamePageStage.setScene(scene);
			GamePageController.gamePageStage.setTitle("게임 고르기 페이지");

			scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());

			Stage currentStage = (Stage) endPopup.getScene().getWindow();
			currentStage.close();

		} catch (Exception e1) {
		}
	}

}
