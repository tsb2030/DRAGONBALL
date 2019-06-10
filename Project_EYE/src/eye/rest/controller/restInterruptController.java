package eye.rest.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.Music;
import eye.main.Main;
import eye.main.controller.mainController;
import eye.set.controller.setController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//알람을 통해 휴식프로그램에 접속시 중단할 경우 팝업 controller
public class restInterruptController implements Initializable {

	@FXML
	private AnchorPane restInterruptPopup;

	@FXML
	private Button alarmOutButton;

	@FXML
	private Button alarmInButton;

	// pause풀고 현재 창 닫기 == 지정된 횟수를 모두 마치겠다는 의미
	@FXML
	void alarmInButtonAction(ActionEvent event) {
		Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
		effectMusic.start();
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

		Stage stage = (Stage) alarmOutButton.getScene().getWindow();
		stage.close();

	}

	// 더이상 휴식을 하지 않겠다는 의미
	@FXML
	void alarmOutButtonAction(ActionEvent event) throws IOException {
		Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
		effectMusic.start();
		setController.isRestStart = false;
		// 음악 바꾸기
		Main.setMusic("mainMusic", true, 1);

		Stage stage = (Stage) alarmOutButton.getScene().getWindow();
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
