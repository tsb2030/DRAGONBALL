package eye.rest.controller;

import java.io.IOException;

import eye.Music;
import eye.main.Main;
import eye.set.controller.setController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LookAfarInfoController {

	public static Stage currentStage;

	public static boolean isPause = false;

	@FXML
	private AnchorPane lookAfarInfoPage;

	@FXML
	private ImageView backBtn;

	@FXML
	private Text title;

	@FXML 
	private Button lookAfarNextBtn;  // 시작 버튼

	@FXML  /* 멀리 보기 설명 페이지에서 시작버튼 누르면 실행 페이지로 이동 */
	void goLookAfarRestPage(ActionEvent event) throws IOException {
		Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
		effectMusic.start();
		currentStage = (Stage) backBtn.getScene().getWindow();
		AnchorPane ClosedEyeRestPane = null;
		ClosedEyeRestPane = FXMLLoader.load(getClass().getResource("/eye/rest/view/LookAfarRest.fxml"));

		Scene scene = new Scene(ClosedEyeRestPane);
		scene.getStylesheets().add(getClass().getResource("/eye/rest/view/rest.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());

		currentStage.setResizable(false);
		currentStage.setScene(scene);
		currentStage.show();
	}

	@FXML  /* 멀리 보기 설명 페이지에서 뒤로가기 누르면 휴식 메인 페이지로 이동 */
	void goRestMainPage1(MouseEvent event) {
		currentStage = (Stage) backBtn.getScene().getWindow();
		if (isPause == false) {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			if (setController.isRestStart == true) {
				isPause = true;
				currentStage.setOpacity(0.45);
				try {
					AnchorPane interruptPopup = FXMLLoader
							.load(getClass().getResource("/eye/rest/view/interruptPopup.fxml"));
					Scene scene = new Scene(interruptPopup);
					Stage stage = new Stage();
					stage.setResizable(false);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				AnchorPane restMainAnchorPane = null;
				try {
					setController.isGameStart = false;
					setController.isRestStart = false;
					// 음악 바꾸기
					Main.setMusic("mainMusic", true, 1);
					// 교체할 페이지인 rest_main_page.fxml를 가져와서 closedEyeIntroPage에 넣어준다.
					restMainAnchorPane = FXMLLoader.load(getClass().getResource("/eye/rest/view/rest_main_page.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 페이지 교체
				Scene scene = new Scene(restMainAnchorPane);
				scene.getStylesheets().add(getClass().getResource("/eye/rest/view/restMain.css").toExternalForm());
				scene.getStylesheets()
						.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
				currentStage.setResizable(false);
				currentStage.setScene(scene);
			}
		}
	}

}
