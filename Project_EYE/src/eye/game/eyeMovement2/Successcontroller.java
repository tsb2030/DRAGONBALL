package eye.game.eyeMovement2;

import java.net.URL;
import java.util.ResourceBundle;

import eye.game.catchBall.CatchballGameController;
import eye.game.eyeMovement2.Playcontroller;
import eye.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Successcontroller implements Initializable{
	@FXML
	private Button closeBtn;

	@FXML
	private Button restartPopUpBtn;

	@FXML
	private AnchorPane SuccessPage,eyePlayPage;

	@FXML
	void numGameRestart(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Overview2.fxml"));
			Scene scene = new Scene(root);

			Playcontroller.currentStage.setScene(scene);
			Playcontroller.currentStage.setTitle("시선이동트레이닝2");

			Stage primaryStage = (Stage) SuccessPage.getScene().getWindow();
			primaryStage.close();

			Playcontroller.cnt = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void closePopUp(ActionEvent event) {
		try {
			Parent MainPage = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
			Scene scene = new Scene(MainPage);

			Playcontroller.currentStage.setScene(scene);
			Playcontroller.currentStage.setTitle("game_main_page");
			
			scene.getStylesheets().add(getClass().getResource("../../main/controller/application.css").toExternalForm());

			Stage primaryStage = (Stage) SuccessPage.getScene().getWindow();
			primaryStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}