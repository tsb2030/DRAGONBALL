package eye.rest.controller;

import java.io.IOException;

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

public class WarmEyeInfoController {

	public static Stage currentStage;

	public static boolean isPause = false;

    @FXML
    private AnchorPane warmEyeInfoPage;

    @FXML
    private ImageView backBtn;

    @FXML
    private Text title;

    @FXML
    private Button warmEyeNextBtn;

    @FXML
    void goRestMainPage1(MouseEvent event) throws IOException {
    	currentStage = (Stage) backBtn.getScene().getWindow();
    	if (isPause == false) {
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
					// 음악 바꾸기
					Main.setMusic("mainMusic", true, 1);
					// 교체할 페이지인 rest_main_page.fxml를 가져와서 closedEyeIntroPage에 넣어준다.
					restMainAnchorPane = FXMLLoader.load(getClass().getResource("/eye/rest/view/rest_main_page.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 페이지 교체
				Scene scene = new Scene(restMainAnchorPane);
				scene.getStylesheets()
				.add(getClass().getResource("/eye/rest/view/restMain.css").toExternalForm());				
				scene.getStylesheets()
				.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
				currentStage.setScene(scene);
			}
		}
    }

    @FXML
	void goWarmEyeRestPage(ActionEvent event) throws IOException {
    	currentStage = (Stage) backBtn.getScene().getWindow();
    	AnchorPane ClosedEyeRestPane = null;
    	ClosedEyeRestPane = FXMLLoader.load(getClass().getResource("/eye/rest/view/WarmEyeRest.fxml"));
    	
    	Scene scene = new Scene(ClosedEyeRestPane);
		scene.getStylesheets().add(getClass().getResource("/eye/rest/view/rest.css").toExternalForm());				
		scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
		
		currentStage.setScene(scene);
		currentStage.show();
		
    	
    }

}
