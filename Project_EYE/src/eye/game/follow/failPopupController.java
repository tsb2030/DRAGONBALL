package eye.game.follow;

import eye.Music;
import eye.main.Main;
import eye.set.controller.setController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class failPopupController {

	@FXML
	private Button closeBtn,restartPopUpBtn;
	
	// 팝업창에서 게임 재시작
		public void restart(ActionEvent event) {
			try {
				Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
				effectMusic.start();
				Parent gameInfo = FXMLLoader.load(getClass().getResource("gameIntroEng.fxml")); // 불러올 페이지 지정
				Scene scene = new Scene(gameInfo);
				scene.getStylesheets().add(getClass().getResource("intro.css").toExternalForm()); // css 지정
				Stage primaryStage = (Stage) restartPopUpBtn.getScene().getWindow(); // 현재 윈도우 가져오기
				engGameController.currentStage.setResizable(false);
				engGameController.currentStage.setScene(scene);
				engGameController.currentStage.setTitle("A to Z Game");
				primaryStage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 팝업창 닫기
		public void closePopUp() {
			try {
				setController.isGameStart = false;
				Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
				effectMusic.start();
				Main.setMusic("mainMusic", true, 1);
				Parent root = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = (Stage) closeBtn.getScene().getWindow();
				scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
				engGameController.currentStage.setResizable(false);
				engGameController.currentStage.setScene(scene);
				engGameController.currentStage.setTitle("gameMainPage");
				primaryStage.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			Stage stage = (Stage) closeBtn.getScene().getWindow(); // 버튼이 있는 창을 닫는다
			stage.close();
		}
}
