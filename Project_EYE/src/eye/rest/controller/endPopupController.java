package eye.rest.controller;

import java.io.IOException;

import eye.Music;
import eye.main.Main;
import eye.main.controller.mainController;
import eye.set.controller.setController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class endPopupController {

	@FXML
	private AnchorPane restEndingPopup;

	@FXML
	private Button closeBtn;

	@FXML
	void closeBtnAction(ActionEvent event) throws IOException {
		setController.isRestStart = false;
		Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
		effectMusic.start();
		// 음악 바꾸기
		Main.setMusic("mainMusic", true, 1);

		Stage stage = (Stage) restEndingPopup.getScene().getWindow();
		stage.close();

		ClosedEyeInfoController.isPause = false;
		ClosedEyeRestController.isPause = false;
		EyeMassageInfoController.isPause = false;
		EyeMassageRestController.isPause = false;
		EyeRollingInfoController.isPause = false;
		EyeRollingRestcontroller.isPause = false;
		LookAfarInfoController.isPause = false;
		LookAfarRestController.isPause = false;
		WarmEyeInfoController.isPause = false;
		WarmEyeRestController.isPause = false;
		mainController.currentStage.setOpacity(1.0);

		AnchorPane an = FXMLLoader.load(getClass().getResource("/eye/rest/view/rest_main_page.fxml"));
		Scene scene = new Scene(an);
		scene.getStylesheets().add(getClass().getResource("/eye/rest/view/restMain.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
		mainController.currentStage.setScene(scene);
	}
}
