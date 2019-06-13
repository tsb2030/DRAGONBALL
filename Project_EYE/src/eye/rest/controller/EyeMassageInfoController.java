package eye.rest.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.Music;
import eye.main.Main;
import eye.set.controller.setController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EyeMassageInfoController implements Initializable {

	public static Stage currentStage;

	public static boolean isPause = false;

	@FXML
	private AnchorPane eyeMassageInfoPage;

	@FXML
	private ImageView backBtn;

	@FXML
	private Text title;

	@FXML
	private Button eyeMassageNextBtn;  // 시작 버튼

	@FXML  /* 눈 마사지 설명 페이지에서 시작버튼 누르면 실행 페이지로 이동 */
	void goEyeMassageRestPage(ActionEvent event) throws IOException {
		Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
		effectMusic.start();
		currentStage = (Stage) eyeMassageNextBtn.getScene().getWindow();
		AnchorPane ClosedEyeRestPane = null;
		ClosedEyeRestPane = FXMLLoader.load(getClass().getResource("/eye/rest/view/EyeMassageRest.fxml"));

		Scene scene = new Scene(ClosedEyeRestPane);
		scene.getStylesheets().add(getClass().getResource("/eye/rest/view/rest.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());

		currentStage.setScene(scene);
		currentStage.show();
	}

	@FXML   /* 눈 마사지 설명 페이지에서 뒤로가기 누르면 휴식 메인 페이지로 이동 */
	void goRestMainPage1(MouseEvent event) {
		currentStage = (Stage) eyeMassageNextBtn.getScene().getWindow();
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
				currentStage.setScene(scene);
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
